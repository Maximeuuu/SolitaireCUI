// -*- coding: utf-8 -*-

/*
Classe IHMCUI

Auteur  : Maxime L
Date    : 12/02/23
Maj     : 05/03/23
Version : 2
*/
import iut.algo.Clavier;


public class IHMCUI
{

	/*ATTRIBUT*/
	private ControleurCUI ctrl;

	/*CONSTRUCTEUR*/
	public IHMCUI( ControleurCUI ctrl )
	{
		this.ctrl = ctrl;
	}

	/*METHODES*/
	public String lireSelection()
	{
		String saisie="";

		System.out.println("[P]ioche, ou carte en [<COL><LIG>]");
		System.out.print("Selectionner : ");
		saisie = Clavier.lireString();

		while( (saisie.equals("") || saisie.length() > 3) && !saisie.equals("P") )
		{
			System.out.println("Saisie non valide");
			System.out.print("Selectionner : ");
			saisie = Clavier.lireString();
		}

		return saisie;
	}

	public String lireDestination()
	{
		String saisie="";

		System.out.println("Position en [<COL><LIG>]");
		System.out.print("Destination : ");
		saisie = Clavier.lireString();

		return saisie;
	}

	public void victoire()
	{
		System.out.println("Partie terminée");
	}

	public void afficherPlateau()
	{
		System.out.println( this.ctrl.toString() );
	}

	/* Type :
	'H'(demie carte horizontale)
	'V' (demie carte verticale)
	'N' (carte nulle)
	'X' (emplacement vide de carte)
	par défaut, 'E' (carte entière)
	*/
	public static String carteToString( Carte c, char type )
	{
		String s;

		if( c == null )
		{
			return
		}
		return "";
	}

	private static String demieCarteHorizontale( Carte c )
	{
		String s="";
	}

	private static String demieCarteVerticale( Carte c )
	{
		
	}

	private static String carteVide()
	{
		return "       \n"+"       \n"+"       \n"+"       \n"+"       \n"+"       ";
	}

	private static String emplacmentCarteVide()
	{
		return "+-----+\n"+"|     |\n"+"|     |\n"+"|     |\n"+"|     |\n"+"+-----+";
	}

	public static String pileToString( Pile p, char type )
	{
		if
		return "";
	}

	public static String plateauToString( Plateau p )
	{
		return "";
	}

	public String solitaireToString()
	{
		return "";
	}

	private static Texte2D carteStringToTexte2D( String carte )
	{
		return new Texte2D( Texte2D.stringToTexte2D(carte, "\n") );
	}

	private static Texte2D pileStringToTexte2D( String pile )
	{
		return new Texte2D( Texte2D.stringToTexte2D(pile, "\n") );
	}

}
