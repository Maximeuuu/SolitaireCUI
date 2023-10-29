// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet d'afficher le menu de fin de partie automatique
 *
 * Méthodes :
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 20/03/23
Dernière maj  : 20/03/23
Version       : 1
==============================================================================*/

package solitaire.ihm;

public class MenuTerminer
{
	public static String menu()
	{
		IHMCUI.headerMenu( "Terminer partie" );

		System.out.println( "La partie est gagnée d'avance." );
		System.out.println( "" );
		System.out.println( "Voulez-vous finir la partie automatiquement ? [O]ui, [N]on." );

		IHMCUI.footerMenu();

		return IHMCUI.saisieAction();
	}

}
