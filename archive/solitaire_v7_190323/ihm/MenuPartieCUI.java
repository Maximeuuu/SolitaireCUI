// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet d'afficher le menu de la partie
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

public class MenuPartieCUI
{
	public static String menu()
	{
		IHMCUI.headerMenu( "Partie" );

		System.out.println( "[I]ndice" );
		System.out.println( "[S]auvegarder" );
		System.out.println( "[C]harger" );
		System.out.println( "[N]ouvelle partie" );

		IHMCUI.footerMenu();

		return IHMCUI.saisieAction();
	}

}
