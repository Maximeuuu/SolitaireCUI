// -*- coding: utf-8 -*-
/**
 * Description : Cette permet d'afficher le jeu de Solitaire et interragir avec l'utilisateur.
 * Elle contient un attribut ControleurCUI.
 * Méthodes : constucteur, lecture, affichage, victoire
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 12/02/23
Dernière maj  : 12/03/23
Version       : 6
==============================================================================*/

package solitaire.ihm;
import solitaire.metier.*;
import solitaire.ControleurCUI;

import iut.algo.Clavier;

public class IHMCUI
{
	/*ATTRIBUT*/
	private ControleurCUI ctrl;

	/*CONSTRUCTEUR*/
	public IHMCUI( ControleurCUI ctrl )
	{
		this.ctrl = ctrl;
	}

	/*METHODES*/
	public String lireSelection()
	{
		String saisie="";

		System.out.println("[P]ioche, carte en [<LIG><COL>]			[S]auvegarder, [C]harger, [Q]uitter");
		System.out.print("Selectionner : ");
		saisie = Clavier.lireString();

		while( (saisie.equals("") || saisie.length() > 3) && (!saisie.equals("P") || !saisie.equals("S")) )
		{
			System.out.println("Saisie non valide");
			System.out.print("Selectionner : ");
			saisie = Clavier.lireString();
		}

		return saisie;
	}

	public String lireDestination()
	{
		String saisie="";

		System.out.println("Position en [<LIG><COL>]");
		System.out.print("Destination : ");
		saisie = Clavier.lireString();

		return saisie;
	}

	public void victoire()
	{
		System.out.println("Partie terminée");
	}

	public void sauvegarde()
	{
		System.out.println("Partie sauvegardée");
	}

	public void chargement( boolean charge )
	{
		if( charge == true ){ System.out.println("Partie chargée avec succès"); }
		else                { System.out.println("Echec du chargement de la partie"); }
	}

	public void afficherPlateau() // penser à ajouter le timer + pts + etc..
	{
		IHMCUI.clearScreen();
		System.out.println( PlateauCUI.plateauToTexte2D( this.ctrl.getMetier().getPlateau() ));
	}

	public static void clearScreen()
	{
    	System.out.print("\033[H\033[2J");
    	System.out.flush();
	}

/*
	public String solitaireToString()
	{
		return "";
	}
*/
}
