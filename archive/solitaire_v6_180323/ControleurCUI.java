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
		String selection;
		String destination;
		Sauvegarde save;

		this.ihm         = new IHMCUI( this );
		this.metier      = new Solitaire();
		//this.finDePartie = false;

		this.ihm.afficherPlateau();

		while( !this.metier.estTermine() )
		{
			selection = this.ihm.lireSelection();

			switch( selection )
			{
				case "P" :
					this.metier.piocher();
					break;
				case "S" :
					save = new Sauvegarde( this.metier, "sauvegarde.data");
					save.sauvegarder();
					this.ihm.sauvegarde();
					break;
				case "C" :
					save = new Sauvegarde( this.metier, "sauvegarde.data");
					this.ihm.chargement( save.charger() );
					break;
				case "Q" :
					System.exit(0);
					break;
				default :
					destination = this.ihm.lireDestination();
					this.deplacer( selection, destination );
					break;
			}

			this.ihm.afficherPlateau();
		}

		ihm.victoire();
	}


	public static void main(String[] argv)
	{
		new ControleurCUI();
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

	public String toString()
	{
		return this.metier.toString();
	}

	public Solitaire getMetier()
	{
		return this.metier;
	}

}
