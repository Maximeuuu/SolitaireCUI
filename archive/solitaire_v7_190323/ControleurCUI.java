// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet de controler le jeu de Solitaire.
 * Elle contient un attribut de metier et d'ihm.
 * Méthodes : constructeur, main, accesseur, controle
 *
 * @author Maxime Lemoine
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 12/02/23
Dernière maj  : 13/03/23
Version       : 2
==============================================================================*/

//compiler : javac ControleurCUI.java ./ihm/*.java ./metier/*.java ./test/*.java
//exécuter : cd ..; java solitaire.ControleurCUI

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
		this.ihm         = new IHMCUI( this );
		this.metier      = new Solitaire();
		//this.finDePartie = false;

		IHMCUI.clearScreen();
		DemarrageCUI.affichage();

		IHMCUI.clearScreen();
		this.ihm.afficherPlateau();

		while( !this.metier.estTermine() )
		{
			this.partie();
		}

		ihm.victoire();
	}

	/*MAIN*/
	public static void main(String[] argv)
	{
		new ControleurCUI();
	}

	/*ACCESSEURS*/
	public Solitaire getMetier()
	{
		return this.metier;
	}

	private void partie()
	{ //correspond à une action de partie
		String selection;
		String destination;

		selection = this.ihm.lireSelection();

		switch( selection )
		{
			case "P" -> this.piocher();
			case "S" -> this.sauvegarder();
			case "C" -> this.charger();
			case "O" -> this.option();
			case "H" -> this.help();
			case "Q" ->	this.quitter();

			default  ->
			{
				destination = this.ihm.lireDestination();
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
	private void piocher()
	{
		this.metier.piocher();
	}

	private boolean sauvegarder()
	{
		String saisie;
		Sauvegarde save;

		IHMCUI.clearScreen();
		saisie = MenuSauvegarderCUI.menu( Sauvegarde.genererFichier() );
		if( saisie.equals("R") )
		{
			MenuSauvegarderCUI.etat( false );
			return false;
		}

		if( saisie.equals("") ){ saisie = Sauvegarde.genererFichier(); }

		if( !MenuSauvegarderCUI.confirmation( saisie ).equals("O") )
		{
			MenuSauvegarderCUI.etat( false );
			return false;
		}

		save = new Sauvegarde( this.metier, saisie);
		save.sauvegarder();
		MenuSauvegarderCUI.etat( true );

		return true;
	}

	private boolean charger()
	{
		String saisie;
		Sauvegarde save;

		IHMCUI.clearScreen();
		saisie = MenuChargerCUI.menu();
		if( saisie.equals("R") )
		{
			MenuChargerCUI.etat( false );
			return false;
		}

		if( !MenuChargerCUI.confirmation( saisie ).equals("O") )
		{
			MenuChargerCUI.etat( false );
			return false;
		}

		save = new Sauvegarde( this.metier, saisie );
		MenuChargerCUI.etat( save.charger() ); //PENSER A TESTER SI LE FICHIER EXISTE BIEN
		return true;
	}

	private void option()
	{
		IHMCUI.clearScreen();
		System.out.println( MenuParametreCUI.menu() );
	}

	private void help()
	{
		IHMCUI.clearScreen();
		System.out.println( MenuAideCUI.menu() );
	}

	private void quitter()
	{
		IHMCUI.clearScreen();
		System.exit(0);
	}

	private void deplacer( String selection, String destination )
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

		this.metier.deplacer( ligOrig, colOrig, ligDest, colDest );
	}

	/*public String toString()
	{
		return this.metier.toString();
	}*/
}
