// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet d'afficher le menu de sauvegarde
 *
 * Méthodes :
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 19/03/23
Dernière maj  : 19/03/23
Version       : 1
==============================================================================*/

package solitaire.ihm;
import solitaire.metier.Sauvegarde;

public class MenuSauvegarderCUI
{
	public static String menu( String fichier )
	{
		IHMCUI.headerMenu( "Sauvegarder" );

		System.out.println( "Repertoire de sauvegarde : " + Sauvegarde.getRepertoire() );
		System.out.println( "\t(Attention à ne pas écraser une sauvegarde existante.)");

		System.out.println( "fichier par défaut : " + fichier );
		System.out.println( "[<fichier.data>]" );

		IHMCUI.footerMenu();

		return IHMCUI.saisieAction();
	}

	public static String confirmation( String fichier )
	{
		System.out.println("Sauvegarder la partie : " + fichier);
		return IHMCUI.saisieConfirmation();
	}

	public static void etat( boolean estSave )
	{
		if( estSave ){ System.out.println("Partie sauvegardée."); }
		else         { System.out.println("Erreur lors de la sauvegarde."); }
	}
}
