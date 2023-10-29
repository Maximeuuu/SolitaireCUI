// -*- coding: utf-8 -*-

/*
Nom      : Classe TestTexte2D.java
Auteur   : Maxime L
Création : 4/03/23
MAJ      : 4/03/23
Version  : 1
*/
/*
Info : test de la classe Texte2D.java
*/

public class TestTexte2D
{
	/*TEST*/
	public static void main(String[] argv)
	{

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
	}
}
