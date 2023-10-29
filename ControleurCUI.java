// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet de controler le jeu de Solitaire.
 * Elle contient un attribut de metier et d'ihm.
 * Méthodes : constructeur, main, accesseur, controle.
 *
 * @author Maxime Lemoine.
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 12/02/23
Dernière maj  : 22/03/23
Version       : 2
==============================================================================*/

//compiler      : javac ControleurCUI.java ./ihm/*.java ./metier/*.java ./test/*.java
//exécuter      : cd ..; java solitaire.ControleurCUI
//documentation : javadoc *.java */*.java -d documentation -private -author

package solitaire;

import solitaire.ihm.*;
import solitaire.metier.*;

public class ControleurCUI
{
	private Solitaire metier;
	private IHMCUI    ihm;
	//private boolean   finDePartie;

	/*CONSTRUCTEUR*/
	public ControleurCUI()
	{
		Boolean afficherIndice;
	
		this.ihm         = new IHMCUI( this );
		this.metier      = new Solitaire();
		//this.finDePartie = false;

		IHMCUI.clearScreen();
		DemarrageCUI.affichage();

		IHMCUI.clearScreen();
		this.ihm.afficherPlateau();

		afficherIndice = true;
		while( !this.metier.estTermine() )
		{
			if( this.metier.estGagne() && afficherIndice == true )
			{
				if( MenuTerminer.menu().equals("O") )
				{
					this.finirPartieAuto();
				}
				afficherIndice = false;
				
				//System.out.println("Voulez-vous finir la partie automatiquement ? "); // partie temporaire à remplacer par les commentaire ci-dessous
				//afficherIndice = false;
				//PROPOSER DE FINIR LA PARTIE AUTOMATIQUEMENT
				//IF NON -> afficherIndice = false;
				//SINON -> couper le controle et finir le jeu avec un affichage
			}
			this.partie();
		}

		this.ihm.victoire();
	}

	/*MAIN*/
	/**Permet de lancer une partie de solitaire.*/
	public static void main(String[] argv)
	{
		new ControleurCUI();
	}

	/*ACCESSEURS*/
	/**Permet de récupérer un accès à la partie métier.*/
	public Solitaire getMetier()
	{
		return this.metier;
	}

	/**Correspond à une action de partie.*/
	private void partie()
	{
		String selection;
		String destination;

		selection = IHMCUI.lireSelection();

		switch( selection )
		{
			case "P" -> this.piocher();
			case "S" -> this.sauvegarder();
			case "C" -> this.charger();
			case "O" -> this.option();
			case "H" -> this.help();
			case "Q" ->	this.quitter();
			//case "F" ->	this.finirPartieAuto();

			default  ->
			{
				destination = IHMCUI.lireDestination();
				this.deplacer( selection, destination );
				break;
			}
		}

		IHMCUI.clearScreen();
		this.ihm.afficherPlateau();
	}

/*
METHODES POUR LES ACTIONS
*/
	/**Appel la méthode piocher().*/
	private void piocher()
	{
		this.metier.piocher();
	}
		//707

	/**Permet de finir automatiquement une partie*/
	public void finirPartieAuto()
	{
		while( !this.metier.estTermine() )
		{
			this.metier.actionAuto();
			IHMCUI.clearScreen();
			IHMCUI.attendre(100);
			this.ihm.afficherPlateau(); //il manque un toString ?? (à verifier)
		}
	}

	/**Permer de sauvegarder une partie.*/
	private boolean sauvegarder()
	{
		String saisie;
		Sauvegarder save;

		IHMCUI.clearScreen();
		saisie = MenuSauvegarderCUI.menu( Sauvegarder.genererFichier() );
		if( saisie.equals("R") )
		{
			MenuSauvegarderCUI.etat( false );
			return false;
		}

		if( saisie.equals("") ) //si la saisie est vide
		{
			save = new Sauvegarder( this.metier );
		}
		else
		{
			save = new Sauvegarder( this.metier, saisie);
		}

		if( !MenuSauvegarderCUI.confirmation( save.getFichier() ).equals("O") )
		{
			MenuSauvegarderCUI.etat( false );
			return false;
		}

		save.sauvegarde();
		MenuSauvegarderCUI.etat( true );

		return true;
	}

	/**Permet de charger une partie.*/
	private boolean charger()
	{
		String saisie;
		Charger save;

		IHMCUI.clearScreen();
		saisie = MenuChargerCUI.menu();
		
		//test si une sortie est demandée
		if( saisie.equals("R") )
		{
			MenuChargerCUI.etat( false );
			return false;
		}
		
		// ou si le fichier n'existe pas
		if( !Charger.existe(saisie) )
		{
			MenuChargerCUI.etat( false );
			return this.charger(); //on relance le chargement d'une partie
		}

		save = new Charger( this.metier, saisie );

		if( !MenuChargerCUI.confirmation( save.getFichier() ).equals("O") )
		{
			MenuChargerCUI.etat( false );
			return false;
		}

		MenuChargerCUI.etat( save.chargement() );
		return true;
	}

	/**Affiche le menu des options.*/
	private void option()
	{
		IHMCUI.clearScreen();
		System.out.println( MenuParametreCUI.menu() );
	}

	/**Affiche le menu d'aide.*/
	private void help()
	{
		IHMCUI.clearScreen();
		System.out.println( MenuAideCUI.menu() );
	}

	/**Quitte le jeu.*/
	private void quitter()
	{
		//IHMCUI.clearScreen();
		if( IHMCUI.saisieConfirmation().equals("O") ){ System.exit(0); }
	}
	
	

	/**Permet de déplacer une carte sélectionnée.*/
	private boolean deplacer( String selection, String destination )
	{
		int  ligOrig;
		int  ligDest;
		char colOrig;
		char colDest;

		if( selection.length() == 3 )
		{
			ligOrig = Integer.parseInt( selection.charAt(0)+""+selection.charAt(1) );
			colOrig = selection.charAt(2);
		}
		else
		{
			ligOrig = Integer.parseInt( selection.charAt(0)+"" );
			colOrig = selection.charAt(1);
		}

		if( destination.length() == 3 )
		{
			ligDest = Integer.parseInt( destination.charAt(0)+""+destination.charAt(1) );
			colDest = destination.charAt(2);
		}
		else
		{
			ligDest = Integer.parseInt( destination.charAt(0)+"" );
			colDest = destination.charAt(1);
		}

		ligOrig++;
		ligDest++;
		if( ligOrig < 1 ){ ligOrig = 1; }
		if( ligDest < 1 ){ ligDest = 1; }

		return this.metier.deplacer( ligOrig, colOrig, ligDest, colDest );
	}

	/*public String toString()
	{
		return this.metier.toString();
	}*/
}
