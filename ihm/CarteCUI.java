// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet de convertir une carte en objet Texte2D pour l'affichage.
 * Elle contient des propriétés publiques de couleur et de forme.
 * Méthodes : convetisseur, initialisateurs basiques, accesseurs.
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 12/03/23
Dernière maj  : 12/03/23
Version       : 1
==============================================================================*/

package solitaire.ihm;
import solitaire.metier.*;

public class CarteCUI
{
	private static final String BORDURE_H = "+-----+";
	private static final String BORDURE_V = "|";

	public static final String CARREAU = "♦"; //"D"; "\u2666"; "#";
	public static final String COEUR   = "♥"; //"H"; "\u2665"; "%";
	public static final String PIQUE   = "♠"; //"S"; "\u2660"; "$";
	public static final String TREFLE  = "♣"; //"C"; "\u2663"; "&";

	public static final String ROUGE = "\033[0;31m";
	public static final String NOIR  = "\033[1;30m";
	public static final String RESET = "\033[0m";

	/* Type :
	'H'(demie carte horizontale)
	'V' (demie carte verticale)
	'N' (carte nulle)
	'X' (emplacement vide de carte)
	par défaut, 'E' (carte entière)
	*/
	public static Texte2D carteToTexte2D( Carte c, char type)
	{ //faut me croire sur parole, normalement ça affiche une jolie carte
		if( type == 'N' ){ return CarteCUI.carteVide();}
		if( type == 'X' ){ return CarteCUI.emplacementCarteVide();}
		if( c == null   ){ return null; }

		String[] s = new String[6];

		if( c.estVisible() )
		{
			String valeur = CarteCUI.getStringValeur( c.getNom() ); //nom de la carte
			String cc = CarteCUI.getStringCodeCouleur( c.getCouleur() ); //code couleur
			String cr = CarteCUI.getStringCodeCouleur( "Reset" ); //code couleur reset

			//demie carte
			s[0] = cc + valeur + cr;
			s[1] = cc + " " + cr;
			s[2] = cc + CarteCUI.getStringCouleur( c.getCouleur() ) + cr;

			if( type == 'E' ) //dans le cas d'une carte entière
			{
				s[3] = s[2];
				s[4] = s[1];
				s[5] = s[0];
			}

			if( valeur.length() > 1 ) //dans le cas où il y a 2 caractères ("10")
			{
				s[0] = cc + valeur.charAt(0) + cr;
				s[1] = cc + valeur.charAt(1) + cr;
				s[4] = s[0];
				s[5] = s[1];
			}
		}
		else
		{
			//demie carte
			s[0] = "/";
			s[1] = " ";
			s[2] = "\\";

			if( type == 'E' ) //dans le cas d'une carte entière
			{
				s[3] = s[2];
				s[4] = s[1];
				s[5] = s[0];
			}
		}

		if( type == 'E' ) //carte entière
		{
			return new Texte2D( new String[][]{	{"+", "-", "-", "-", "-", "-", "+"},
												{"|",s[0],s[1], " ", " ",s[2], "|"},
												{"|", " ", " ", " ", " ", " ", "|"},
												{"|", " ", " ", " ", " ", " ", "|"},
												{"|",s[3], " ", " ",s[4],s[5], "|"},
												{"+", "-", "-", "-", "-", "-", "+"}} );
		}

		if( type == 'H' ) //demie carte horizontale
		{
			return new Texte2D( new String[][]{	{"+", "-", "-", "-", "-", "-", "+"},
												{"|",s[0],s[1], " ", " ",s[2], "|"}} );
		}

		if( type == 'V' ) //demie carte verticale
		{
			return new Texte2D( new String[][]{	{"+", "-", "-"},
												{"|",s[0],s[1]},
												{"|", " ", " "},
												{"|", " ", " "},
												{"|",s[2], " "},
												{"+", "-", "-"}} );
		}

		return null;
	}

	public static Texte2D carteVide()
	{
		return new Texte2D( new String[][]{	{" ", " ", " ", " ", " ", " ", " "},
											{" ", " ", " ", " ", " ", " ", " "},
											{" ", " ", " ", " ", " ", " ", " "},
											{" ", " ", " ", " ", " ", " ", " "},
											{" ", " ", " ", " ", " ", " ", " "},
											{" ", " ", " ", " ", " ", " ", " "}} );
	}

	public static Texte2D emplacementCarteVide()
	{
		return new Texte2D( new String[][]{	{"x", "-", "-", "-", "-", "-", "x"},
											{"|", " ", " ", " ", " ", " ", "|"},
											{"|", " ", " ", " ", " ", " ", "|"},
											{"|", " ", " ", " ", " ", " ", "|"},
											{"|", " ", " ", " ", " ", " ", "|"},
											{"x", "-", "-", "-", "-", "-", "x"}} );
	}

	public static Texte2D emplacementCarteVide( int indice )
	{
		Texte2D tmp = CarteCUI.emplacementCarteVide();

		if( indice == 0 ) //coeur
		{
			tmp.setCase(2,1, "(");
			tmp.setCase(2,2, "\\");
			tmp.setCase(2,4, "/");
			tmp.setCase(2,5, ")");
			tmp.setCase(3,2, "\\");
			tmp.setCase(3,4, "/");
		}

		if( indice == 1 ) //carreau
		{
			tmp.setCase(2,2, "/");
			tmp.setCase(2,4, "\\");
			tmp.setCase(3,2, "\\");
			tmp.setCase(3,4, "/");
		}

		if( indice == 2 ) //trefle
		{
			tmp.setCase(2,2, "(");
			tmp.setCase(2,4, ")");
			tmp.setCase(3,1, "(");
			tmp.setCase(3,2, ")");
			tmp.setCase(3,4, "(");
			tmp.setCase(3,5, ")");
		}

		if( indice == 3 ) //pique
		{
			tmp.setCase(2,2, "/");
			tmp.setCase(2,4, "\\");
			tmp.setCase(3,1, "(");
			tmp.setCase(3,2, "_");
			tmp.setCase(3,4, "_");
			tmp.setCase(3,5, ")");
		}

		return tmp;
	}

	private static String getStringCodeCouleur( String couleur )
	{
		String s;

		switch( couleur )
		{
			case "Carreau" -> s = CarteCUI.ROUGE;
			case "Coeur"   -> s = CarteCUI.ROUGE;
			case "Pique"   -> s = CarteCUI.NOIR ;
			case "Trefle"  -> s = CarteCUI.NOIR ;
			default        -> s = CarteCUI.RESET;
		}
		return s;
	}

	private static String getStringCouleur( String couleur )
	{
		String s;

		switch( couleur )
		{
			case "Carreau" -> s = CarteCUI.CARREAU;
			case "Coeur"   -> s = CarteCUI.COEUR;
			case "Pique"   -> s = CarteCUI.PIQUE;
			case "Trefle"  -> s = CarteCUI.TREFLE;
			default        -> s = "X";
		}
		return s;
	}

	private static String getStringValeur( String nom )
	{
		String s="";
		s += nom.charAt(0);

		if( nom.length() > 1 && nom.charAt(1) == '0' )
		{
			s += nom.charAt(1);
		}
		return s;
	}

}
