// -*- coding: utf-8 -*-
/**
 * Description : Cette permet d'afficher le jeu de Solitaire et interragir avec l'utilisateur.
 * Elle contient un attribut ControleurCUI.
 * Méthodes : constucteur, lecture, affichage, victoire
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 12/02/23
Dernière maj  : 22/03/23
Version       : 7
==============================================================================*/

package solitaire.ihm;
import solitaire.metier.*;
import solitaire.ControleurCUI;

import java.lang.Thread;
import iut.algo.Clavier;

public class IHMCUI
{
	/*ATTRIBUT*/
	private ControleurCUI ctrl;
	public static final String LIGNE = "=====================================================================================";

	/*CONSTRUCTEUR*/
	public IHMCUI( ControleurCUI ctrl )
	{
		this.ctrl = ctrl;
	}

	/*METHODES*/

/*
AFFICHAGE MENU
*/
	public static void footerMenu()
	{
		System.out.println( "" );
		System.out.println( IHMCUI.LIGNE );
		System.out.println( "[R]etour" );
		System.out.println( IHMCUI.LIGNE );
		System.out.println( "" );
	}

	public static void headerMenu( String titre )
	{
		System.out.println( "\t\t" + titre );
		System.out.println( IHMCUI.LIGNE );
		System.out.println( "" );
	}

/*
LIRE ACTIONS
*/
	public static String saisieAction()
	{
		System.out.println( "" );
		System.out.println("Action : ");
		return Clavier.lireString();
	}

	public static String saisieConfirmation()
	{
		System.out.println( "" );
		System.out.println("Confirmer ? [O]ui, [N]on");
		return Clavier.lireString();
	}

	public static String lireSelection() //A REFAIRE -> lireSelection + selectionInvalide + test dans controleur
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

	public static String lireDestination()
	{
		System.out.println("Position en [<LIG><COL>]");
		System.out.print("Destination : ");
		return Clavier.lireString();
	}

/*
AFFICHAGE
*/
	public void afficherPlateau()
	{
		System.out.println( PlateauCUI.plateauToTexte2D( this.ctrl.getMetier().getPlateau() ).toString() );
	}

	public void victoire()
	{
		System.out.println("Partie terminée");
	}

	public static void clearScreen()
	{
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void attendre( int temps )
	{
		try
		{
            Thread.sleep(temps);
        }
        catch (InterruptedException e){ e.printStackTrace(); }
	}

	/*public void afficherSolitaire() // penser à ajouter le timer + pts + etc.. + afficherPlateau()
	{
	}*/

}
