// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet d'afficher le menu d'aide
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

public class MenuAideCUI
{
	public static String menu()
	{
		IHMCUI.headerMenu( "Aide" );

		System.out.println( "Jeu de solitaire en JAVA codé par Maxime Lemoine." );
		System.out.println( "" );
		System.out.println( "Le solitaire (ou Klondike) est un jeu de carte à un seul joueur, c'est à dire une patience." );
		System.out.println( "Il y a 52 cartes disposées en sept colonnes de tailles croissantes. Le reste est disponible dans la pioche." );
		System.out.println( "" );
		System.out.println( "But du jeu :" );
		System.out.println( "Reconstituer quatre piles de cartes dans chacune des couleurs, du roi à l'as — on pose en premier le roi, puis la reine, le valet et les cartes : 10, 9, 8, 7, 6, 5, 4, 3, 2, et enfin l'as. Dès qu'une \"famille\" a été reconstituée, elle est mise de côté. La partie se termine lorsque tout un « groupe » a été reconstitué." );
		System.out.println( "Chaque colonne doit alterner rouge et noir, et peut commencer par n'importe quelle carte si la colonne était vide." );
		System.out.println( "" );
		System.out.println( "Source : https://fr.wikipedia.org/wiki/Solitaire_(patience)" );

		IHMCUI.footerMenu();

		return IHMCUI.saisieAction();
	}

}
