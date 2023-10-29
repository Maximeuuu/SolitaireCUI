// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet de tester les Piles de Carte.
 * Méthodes : main
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 12/02/23
Dernière maj  : 12/03/23
Version       : X
==============================================================================*/
package solitaire.test;
import solitaire.ihm.*;
import solitaire.metier.*;

public class TestPileCarte
{

	public static void main(String[] argv)
	{
		Pile pile1 = new Pile(10);
		System.out.println( "pile vide : " + pile1.toString() );

		pile1.empiler( new Carte("As", "Carreau"));
		pile1.empiler( new Carte("3", "Trefle"));
		pile1.empiler( new Carte("10", "Carreau"));

		System.out.println( "pile de 3 cartes cachées : " + pile1.toString() );
		pile1.retourner();
		System.out.println( "pile de 3 cartes visibles : " + pile1.toString() );

		Carte carte = new Carte( 5, 2 );
		System.out.println( "une carte cachée : " + carte.toString() );
		carte.retourner();
		System.out.println( "une carte visible : " + carte.toString() );
		System.out.println( carte.getNom() + " " + carte.getCouleur() + " " + carte.estVisible() );

		carte.retourner();
		System.out.println( carte.estVisible() );
		carte.retourner();

		Carte carte2 = new Carte( 7, 1 );
		carte2.retourner();
		Carte carte3 = new Carte( 7, 0 );
		carte3.retourner();
		if( carte2.compareTo( carte ) > 0 )
		{
			System.out.println( carte2.toString() + ">" + carte.toString()  );
		}
		if( carte2.compareTo( carte3 ) == 0 )
		{
			System.out.println( carte2.toString() + "=" + carte3.toString()  );
		}


		System.out.println( "pile originale : " + pile1.toString());
		Pile pile2 = new Pile( pile1 );
		System.out.println( "dupliquer une pile : " + pile2.toString() );
		pile2.empiler( carte2 );
		System.out.println( "ajouter une carte : " + pile2.toString() );
		System.out.println( "pile originale : " + pile1.toString());

		Carte carteDuplique = new Carte( carte );
		System.out.println( "carte dupliquée : " + carte.toString() + " // " + carteDuplique.toString() );

		System.out.println("\u2665 \u2666 \u2663 \u2660");
		//PrintStream out = new PrintStream(System.out, true, "UTF-8");
    	//out.println("\u2665 \u2666 \u2663 \u2660");

		System.out.println("\n\nabc\\" + " : " + "\n\nabc\\".length());

		String chaine = "\033[0;31m"+"♦"+"\033[0m";
		System.out.println( chaine + " : " + chaine.length() );
		System.out.println( (char)9830 );

		//String[][] tab = new String[][]{{"","",""},{"","",""},{"","",""}};
		String[][] tab = new String[5][5];
		System.out.println( tab[0][0] );
		System.out.println( tab.length );
		System.out.println( tab[0] == null );

	}
}


/*
//nécessaire pour l'affichage des caractères spéciaux
import java.io.PrintStream;

public class TestPileCarte2
{

	public static void main(String[] argv)
	{
		System.setProperty("console.encoding", "UTF-8");

		Pile pile1 = new Pile(10);
		System.out.println( "pile vide : " + pile1.toString() );

		pile1.empiler( new Carte("As", "Carreau"));
		pile1.empiler( new Carte("3", "Trefle"));
		pile1.empiler( new Carte("10", "Carreau"));

		System.out.println( "pile de 3 cartes cachées : " + pile1.toString() );
		pile1.retourner();
		System.out.println( "pile de 3 cartes visibles : " + pile1.toString() );

		Carte carte = new Carte( 5, 2 );
		System.out.println( "une carte cachée : " + carte.toString() );
		carte.retourner();
		System.out.println( "une carte visible : " + carte.toString() );
		System.out.println( carte.getNom() + " " + carte.getCouleur() + " " + carte.estVisible() );

		carte.retourner();
		System.out.println( carte.estVisible() );
		carte.retourner();

		Carte carte2 = new Carte( 7, 1 );
		carte2.retourner();
		Carte carte3 = new Carte( 7, 0 );
		carte3.retourner();
		if( carte2.compareTo( carte ) > 0 )
		{
			System.out.println( carte2.toString() + ">" + carte.toString()  );
		}
		if( carte2.compareTo( carte3 ) == 0 )
		{
			System.out.println( carte2.toString() + "=" + carte3.toString()  );
		}


		System.out.println( "pile originale : " + pile1.toString());
		Pile pile2 = new Pile( pile1 );
		System.out.println( "dupliquer une pile : " + pile2.toString() );
		pile2.empiler( carte2 );
		System.out.println( "ajouter une carte : " + pile2.toString() );
		System.out.println( "pile originale : " + pile1.toString());

		Carte carteDuplique = new Carte( carte );
		System.out.println( "carte dupliquée : " + carte.toString() + " // " + carteDuplique.toString() );

		System.out.println("\u2665 \u2666 \u2663 \u2660");
		//PrintStream out = new PrintStream(System.out, true, "UTF-8");
    	//out.println("\u2665 \u2666 \u2663 \u2660");
		try
        {
            PrintStream outStream = new PrintStream(System.out, true, "UTF-8");
            outStream.println("\u2764");
			outStream.println("\u2665 \u2666 \u2663 \u2660");

			System.out.println("\n\nabc\\" + " : " + "\n\nabc\\".length());

			String chaine = "\033[0;31m"+"♦"+"\033[0m";
			System.out.println( chaine + " : " + chaine.length() );
			System.out.println( (char)9830 );
			outStream.println( chaine );
			outStream.println((char)9830);

			//String[][] tab = new String[][]{{"","",""},{"","",""},{"","",""}};
			String[][] tab = new String[5][5];
			System.out.println( tab[0][0] );
			System.out.println( tab.length );
			System.out.println( tab[0] == null );
        }
        catch (Exception e){ }



	}
}
*/
