// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet de tester la classe Texte2D
 * Méthodes : main
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 4/03/23
Dernière maj  : 12/03/23
Version       : X
==============================================================================*/
package solitaire.test;
import solitaire.ihm.*;
import solitaire.metier.*;

public class TestTexte2D
{
	/*TEST*/
	public static void main(String[] argv)
	{

/*
		Texte2D t0 = new Texte2D( new String[]{"  ","  ","  ","  ","  ","  ","  ",} );
		Texte2D t1 = new Texte2D( new String[]{"+----+", "|R   |"} );
		Texte2D t2 = new Texte2D( new String[]{"+----+", "|10  |", "|    |", "|    |", "|  10|", "+----+"});

		Texte2D t3 = Texte2D.assemblerVerticalement( 0, new Texte2D[]{t1,t1} );
		Texte2D t4 = Texte2D.assemblerVerticalement( 0, new Texte2D[]{t3,t2} );

		t2 = t2.completerHauteur( t4.getHauteur() );

		Texte2D t5 = Texte2D.assemblerHorizontalement( 5, new Texte2D[]{t4,t2});
		Texte2D t6 = Texte2D.assemblerHorizontalement( 0, new Texte2D[]{t0,t2});
		Texte2D t7 = Texte2D.assemblerVerticalement( 2, new Texte2D[]{t5, t6});

		System.out.print( t7.toString() );

		t2 = t2.completerTout( t4.getHauteur(), t4.getLargeur() );
		//t2 = t2.completerHauteur( t4.getHauteur() );
		//t2 = t2.completerLargeur( t4.getLargeur() );
		Texte2D t1b = Texte2D.assemblerHorizontalement( 4, new Texte2D[]{t4,t2,t4});
		System.out.print( t1b.toString() );

		System.out.print( Texte2D.stringToTexte2D( "j'ai,un,tableau", ",").toString() );
*/
	Texte2D testVierge = new Texte2D( 0, 0);
	testVierge = Texte2D.assemblerVerticalement( 5, new Texte2D[]{testVierge,testVierge} );
	System.out.println( testVierge );

/*
	Texte2D vide = new Texte2D( new String[][]{{" ", " "},{" ", " "},{" ", " "},{" ", " "},{" ", " "},{" ", " "},{" ", " "},} );
	Texte2D carteR = new Texte2D( new String[][]{{"+","-","-","-","-","+" },{"|","R"," "," "," ","|"}} );
	Texte2D carte10 = new Texte2D( new String[][]{{"+","-","-","-","-","+" },{"|","1","0"," "," ","|"},{"|"," "," "," "," ","|"},{"|"," "," "," "," ","|"},{"|"," "," ","1","0","|"},{"+","-","-","-","-","+" }} );

	Texte2D t3 = Texte2D.assemblerVerticalement( 0, new Texte2D[]{carteR,carteR} );
	Texte2D t4 = Texte2D.assemblerVerticalement( 0, new Texte2D[]{t3,carte10} );

	//Texte2D t5 = carte10.completer( t4.getHauteur(), t4.getLargeur() );
	Texte2D t6 = Texte2D.assemblerHorizontalement( 5, new Texte2D[]{carte10, t4} );

	//t2 = t2.completerHauteur( t4.getHauteur() );

	//Texte2D t5 = Texte2D.assemblerHorizontalement( 5, new Texte2D[]{t4,t2});
	//Texte2D t6 = Texte2D.assemblerHorizontalement( 0, new Texte2D[]{t0,t2});
	Texte2D t7 = Texte2D.assemblerVerticalement( 2, new Texte2D[]{carte10, t6});
	Texte2D t8 = Texte2D.assemblerVerticalement( 2, new Texte2D[]{t7, carte10});
	Texte2D t9 = Texte2D.assemblerHorizontalement( 5, new Texte2D[]{t8, carte10, t8} );
*/
	//System.out.print( t7.toString() );

	//t2 = t2.completerTout( t4.getHauteur(), t4.getLargeur() );
	//t2 = t2.completerHauteur( t4.getHauteur() );
	//t2 = t2.completerLargeur( t4.getLargeur() );
	//Texte2D t1b = Texte2D.assemblerHorizontalement( 4, new Texte2D[]{t4,t2,t4});*/

//	System.out.print( t9.toString() );

	//System.out.print( Texte2D.stringToTexte2D( "j'ai,un,tableau", ",").toString() );

	}
}
