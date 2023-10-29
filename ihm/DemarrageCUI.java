// -*- coding: utf-8 -*-
/**
 * Description : Cette classe affiche un petit menu de chargement
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

public class DemarrageCUI
{
	public static void affichage()
	{
		//tous les "\" ont été doublés pour ne pas avoir de problèmes de caractères d'échapement
		System.out.println( "    ___  ________  ___      ___ ___  _________  ________  ___  ________  _______        " );
		IHMCUI.attendre(70);
		System.out.println( "   |\\  \\|\\   __  \\|\\  \\    /  /|\\  \\|\\___   ___\\\\   __  \\|\\  \\|\\   __  \\|\\  ___ \\      " );
		IHMCUI.attendre(60);
		System.out.println( "   \\ \\  \\ \\  \\|\\  \\ \\  \\  /  / | \\  \\|___ \\  \\_\\ \\  \\|\\  \\ \\  \\ \\  \\|\\  \\ \\   __/|     " );
		IHMCUI.attendre(50);
		System.out.println( " __ \\ \\  \\ \\   __  \\ \\  \\/  / / \\ \\  \\   \\ \\  \\ \\ \\   __  \\ \\  \\ \\   _  _\\ \\  \\_|/__   " );
		IHMCUI.attendre(40);
		System.out.println( "|\\  \\\\_\\  \\ \\  \\ \\  \\ \\    / /   \\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\ \\  \\ \\  \\\\  \\\\ \\  \\_|\\ \\  " );
		IHMCUI.attendre(30);
		System.out.println( "\\ \\________\\ \\__\\ \\__\\ \\__/ /     \\ \\__\\   \\ \\__\\ \\ \\__\\ \\__\\ \\__\\ \\__\\\\ _\\\\ \\_______\\ " );
		IHMCUI.attendre(20);
		System.out.println( " \\|________|\\|__|\\|__|\\|__|/       \\|__|    \\|__|  \\|__|\\|__|\\|__|\\|__|\\|__|\\|_______| " );
		IHMCUI.attendre(10);

/*
		System.out.println( "" );
		System.out.println( "" );
		System.out.println( "" );
		System.out.println( "" );
		
		System.out.println( "\t"+" ▄▄▄▄ ▓██   ██▓    ███▄ ▄███▓ ▄▄▄      ▒██   ██▒ ██▓ ███▄ ▄███▓▓█████  " );
		IHMCUI.attendre(50);
		System.out.println( "\t"+"▓█████▄▒██  ██▒   ▓██▒▀█▀ ██▒▒████▄    ▒▒ █ █ ▒░▓██▒▓██▒▀█▀ ██▒▓█   ▀  " );
		IHMCUI.attendre(40);
		System.out.println( "\t"+"▒██▒ ▄██▒██ ██░   ▓██    ▓██░▒██  ▀█▄  ░░  █   ░▒██▒▓██    ▓██░▒███    " );
		IHMCUI.attendre(40);
		System.out.println( "\t"+"▒██░█▀  ░ ▐██▓░   ▒██    ▒██ ░██▄▄▄▄██  ░ █ █ ▒ ░██░▒██    ▒██ ▒▓█  ▄  " );
		IHMCUI.attendre(30);
		System.out.println( "\t"+"░▓█  ▀█▓░ ██▒▓░   ▒██▒   ░██▒ ▓█   ▓██▒▒██▒ ▒██▒░██░▒██▒   ░██▒░▒████▒ " );
		IHMCUI.attendre(30);
		System.out.println( "\t"+"░▒▓███▀▒ ██▒▒▒    ░ ▒░   ░  ░ ▒▒   ▓▒█░▒▒ ░ ░▓ ░░▓  ░ ▒░   ░  ░░░ ▒░ ░ " );
		IHMCUI.attendre(20);
		System.out.println( "\t"+"▒░▒   ░▓██ ░▒░    ░  ░      ░  ▒   ▒▒ ░░░   ░▒ ░ ▒ ░░  ░      ░ ░ ░  ░ " );
		IHMCUI.attendre(20);
		System.out.println( "\t"+" ░    ░▒ ▒ ░░     ░      ░     ░   ▒    ░    ░   ▒ ░░      ░      ░    " );
		IHMCUI.attendre(10);
		System.out.println( "\t"+" ░     ░ ░               ░         ░  ░ ░    ░   ░         ░      ░  ░ " );
		IHMCUI.attendre(10);
		System.out.println( "\t"+"      ░░ ░" );
*/
		IHMCUI.attendre(500);

	}

}
