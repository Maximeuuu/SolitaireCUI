// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet de convertir un Plateau en objet Texte2D pour l'affichage.
 * Méthode : convertisseur
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 12/03/23
Dernière maj  : 14/03/23
Version       : 1
==============================================================================*/

package solitaire.ihm;
import solitaire.metier.*;

public class PlateauCUI
{

	public static Texte2D plateauToTexte2D( Plateau p ) //cette partie pourrait etre remplacée et complétée par "solitaireCUI.java" avec en plus les points et le temps
	{
		Texte2D[] tabPlateau = new Texte2D[6];

		//indices 1erre ligne
		tabPlateau[0] = PlateauCUI.initLigne( "=======A=============B========================C=========D=========E=========F======" );

		//pioche, tas et couleurs
		tabPlateau[1] = PlateauCUI.assemblerHaut( p );

		//indices 2e ligne
		tabPlateau[2] = PlateauCUI.initLigne( "=======A===========B===========C===========D===========E===========F===========G====" );

		//blanc après la 2e ligne
		tabPlateau[3] = PlateauCUI.initLigne( "  |                                                                                 " );

		//tas de jeux
		tabPlateau[4] = PlateauCUI.assemblerBas( p );

		//fermeture du plateau
		tabPlateau[5] = PlateauCUI.initLigne( "=====================================================================================" );

		return Texte2D.assemblerVerticalement( 0, tabPlateau );
	}

	private static Texte2D assemblerBas( Plateau p )
	{ //permet d'assembler toute la partie du bas (indice, tas de jeux)
		Texte2D[] tabTasBas  = new Texte2D[2];
		Texte2D[] tabTasJeux = new Texte2D[7];

		//creation et assemblage des tas de jeu (tabTasJeux)
		for( int cpt=0; cpt<tabTasJeux.length; cpt++ )
		{
			tabTasJeux[cpt] = PileCUI.pileToTexte2D( p.getTasJeux(cpt), 'J' );
		}

		tabTasBas[1] = Texte2D.assemblerHorizontalement(5, tabTasJeux);
		tabTasBas[0] = PlateauCUI.initIndicesVerticaux( tabTasBas[1].getHauteur() );

		return Texte2D.assemblerHorizontalement( 1, tabTasBas );
	}

	private static Texte2D assemblerHaut( Plateau p )
	{ //permet d'assembler toute la partie du haut (indice, pioche, couleur)
		Texte2D[] tabTasHaut    = new Texte2D[2];
		Texte2D[] tabTasPioche  = new Texte2D[2];
		Texte2D[] tabTasCouleur = new Texte2D[4];

		//pioche et defausse
		tabTasPioche[0] = PileCUI.pileToTexte2D( p.getPioche(), 'P' );
		tabTasPioche[1] = PileCUI.pileToTexte2D( p.getDefausse(), 'D' );
		tabTasHaut[0] = Texte2D.assemblerHorizontalement(5, tabTasPioche);

		//couleurs
		for( int cpt=0; cpt<tabTasCouleur.length; cpt++ )
		{
			tabTasCouleur[cpt] = PileCUI.pileToTexte2D( p.getTasCouleur(cpt), 'C' );
		}
		tabTasHaut[1] = Texte2D.assemblerHorizontalement(3, tabTasCouleur);

		tabTasHaut[1] = Texte2D.assemblerHorizontalement(14, tabTasHaut);
		tabTasHaut[0] = PlateauCUI.initIndiceVertical();

		return Texte2D.assemblerHorizontalement( 1, tabTasHaut );
	}

	private static Texte2D initLigne( String s )
	{ //décompose une chaine pour créer rapidement une ligne
		String[][] tabTmp = new String[1][];
		tabTmp[0] = s.split("");
		return new Texte2D( tabTmp );
	}

	private static Texte2D initIndiceVertical()
	{ //permet de créer l'indice 0
		Texte2D tmp = new Texte2D( 8, 3 );

		tmp.setCase( 0, 0, "0");

		for( int cpt=0; cpt<tmp.getHauteur(); cpt++ )
		{
			tmp.setCase( cpt, 2, "|" );
		}

		return tmp;
	}

	private static Texte2D initIndicesVerticaux( int hauteur )
	{ //permet de créer une partie pour les indices auto-incrémentés
		if( hauteur < 18){ hauteur = 18; }
		hauteur += 3; //pour mettre 3 d'espace en bas

		Texte2D tmp = new Texte2D( hauteur, 3 );
		String sIndice="";

		for( int cpt=0; cpt<tmp.getHauteur(); cpt++ )
		{
			tmp.setCase( cpt, 2, "|" );

			if( cpt%2 == 0 && cpt < tmp.getHauteur()-3-1 ) //mettre un indice une ligne sur 2 et pas pour les 3 derniers indices
			{
				sIndice = String.valueOf( cpt/2 +1);

				//dans le cas où l'indice est sur 2 chiffres
				if( cpt/2 >= 10 ){ tmp.setCase( cpt, 1, sIndice.charAt(1)+"" ); }
				tmp.setCase( cpt, 0, sIndice.charAt(0)+"" );
			}
		}
		return tmp;
	}

}
