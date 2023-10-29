// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet de convertir une Pile en objet Texte2D pour l'affichage.
 * Elle contient une méthode de convertion.
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 12/03/23
Dernière maj  : 12/03/23
Version       : 1
==============================================================================*/

package solitaire.ihm;
import solitaire.metier.*;

public class PileCUI
{

	public static Texte2D pileToTexte2D( Pile p, char type ) //D[effause], [P]ioche, [C]ouleur, [J]eux
	{
		p = new Pile( p );

		if( type == 'P' )
		{
			if( p.estVide() ){ return CarteCUI.carteToTexte2D( p.getSommet(), 'N' ); }
			else{              return CarteCUI.carteToTexte2D( p.getSommet(), 'E' ); }
		}

		if( type == 'C' )
		{
			if( p.estVide() ){ return CarteCUI.carteToTexte2D( p.getSommet(), 'X' ); }
			else{              return CarteCUI.carteToTexte2D( p.getSommet(), 'E' ); }
		}

		if( type == 'D' )
		{
			if( p.estVide() ){ return new Texte2D(6,13); }

			int taille=0;
			if( p.getNbElt() > 3 ){ taille = 3; }
			else                  { taille = p.getNbElt(); }
			Texte2D[] tabCarte = new Texte2D[ taille ];

			if( taille > 0 )
			{
				tabCarte[taille-1] = CarteCUI.carteToTexte2D( p.depiler(), 'E' );
			}
			if( taille > 1 )
			{
				tabCarte[taille-1-1] = CarteCUI.carteToTexte2D( p.depiler(), 'V' );
			}
			if( taille > 2 )
			{
				tabCarte[taille-2-1] = CarteCUI.carteToTexte2D( p.depiler(), 'V' );
			}

			return Texte2D.assemblerHorizontalement(0, tabCarte); //.completer(6,13);
		}

		if( type == 'J' )
		{
			if( p.estVide() ){ return new Texte2D(2,7); }

			int taille = p.getNbElt();
			Texte2D[] tabCarte = new Texte2D[ taille ];

			tabCarte[ taille-1 ] = CarteCUI.carteToTexte2D( p.depiler(), 'E' );

			for( int cpt=taille-1-1; cpt>=0; cpt-- )
			{
				tabCarte[ cpt ] = CarteCUI.carteToTexte2D( p.depiler(), 'H' );
			}

			return Texte2D.assemblerVerticalement(0, tabCarte); //.completer(6,13);
		}

		System.out.println( "problème" );
		return null;
	}
}
