// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet d'afficher le menu de chargement
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

public class MenuChargerCUI
{
	public static String menu()
	{
		IHMCUI.headerMenu( "Charger" );

		System.out.println( "Sauvegardes existantes : " );
		for( int cpt=0; cpt<0; cpt++ )
		{
			System.out.println( "["+ (cpt+1) + "] : ");
		}

		IHMCUI.footerMenu();

		return IHMCUI.saisieAction();
	}

	public static String confirmation( String fichier )
	{
		System.out.println("");
		System.out.println("Voulez vous charger la partie : " + fichier + " ?");
		System.out.println("\t(La partie en cours sera écrasée.)");
		return IHMCUI.saisieConfirmation();
	}

	public static void etat( boolean estChargee )
	{
		if( estChargee ){ System.out.println("Partie chargée avec succès."); }
		else            { System.out.println("Echec du chargement de la partie."); }
	}
}
