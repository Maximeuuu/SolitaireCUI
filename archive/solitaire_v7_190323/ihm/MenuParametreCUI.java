// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet d'afficher le menu des parametre
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

public class MenuParametreCUI
{
	public static String menu()
	{
		IHMCUI.headerMenu( "Paramètre" );

		System.out.println( "[S]tyle" );
		System.out.println( "[P]roblèmes affichage" );
		System.out.println( "[H]elp (comment jouer)" );
		System.out.println( "[C]ommandes" );
		System.out.println( "[D]ifficulté" );

		IHMCUI.footerMenu();

		return IHMCUI.saisieAction();
	}

}
