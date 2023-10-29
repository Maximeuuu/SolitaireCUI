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

	public void afficherPlateau() // A REFAIRE !!!
	{
		//System.out.println( this.ctrl.toString() );
		Carte carte1 = new Carte(5, 3);
		Carte carte2 = new Carte(6, 3);
		Carte carte3 = new Carte(7, 3);
		Carte carte4 = new Carte(8, 3);

		carte1.retourner();
		carte2.retourner();
		carte3.retourner();
		carte4.retourner();

		Pile p = new Pile(4);
		p.empiler( carte1 );
		p.empiler( carte2 );
		p.empiler( carte3 );
		p.empiler( carte4 );

		System.out.println( IHMCUI.pileToTexte2D( p, 'D'));
		/*
		System.out.println( IHMCUI.carteToTexte2D( carte, 'H' ) );
		System.out.println( IHMCUI.carteToTexte2D( carte, 'E' ) );
		System.out.println( IHMCUI.carteToTexte2D( carte, 'V' ) );
		*/

	}

	/* Type :
	'H'(demie carte horizontale)
	'V' (demie carte verticale)
	'N' (carte nulle)
	'X' (emplacement vide de carte)
	par défaut, 'E' (carte entière)
	*/
	public static Texte2D carteToTexte2D( Carte c, char type)
	{ //faut me croire sur parole, normalement ça affiche une jolie carte
		if( c == null   ){ return null; }
		if( type == 'N' ){ return IHMCUI.carteVide();}
		if( type == 'X' ){ return IHMCUI.emplacementCarteVide();}

		String[] s = new String[6];

		if( c.estVisible() )
		{
			String valeur = IHMCUI.getStringValeur( c.getNom() ); //nom de la carte
			String cc = IHMCUI.getStringCodeCouleur( c.getCouleur() ); //code couleur
			String cr = "\033[0m"; //code reset

			s[0] = cc + valeur + cr;
			s[1] = cc + " " + cr;
			s[2] = cc + IHMCUI.getStringCouleur( c.getCouleur() ) + cr;

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

		if( type == 'E' )
		{
			return new Texte2D( new String[][]{	{"+", "-", "-", "-", "-", "-", "+"},
												{"|",s[0],s[1], " ", " ",s[2], "|"},
												{"|", " ", " ", " ", " ", " ", "|"},
												{"|", " ", " ", " ", " ", " ", "|"},
												{"|",s[3], " ", " ",s[4],s[5], "|"},
												{"+", "-", "-", "-", "-", "-", "+"}} );
		}
		if( type == 'H' )
		{
			return new Texte2D( new String[][]{	{"+", "-", "-", "-", "-", "-", "+"},
												{"|",s[0],s[1], " ", " ",s[2], "|"}} );
		}
		if( type == 'V' )
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

	public static Texte2D carteVide() //return "       \n"+"       \n"+"       \n"+"       \n"+"       \n"+"       ";
	{
		return new Texte2D( new String[][]{	{" ", " ", " ", " ", " ", " ", " "},
											{" ", " ", " ", " ", " ", " ", " "},
											{" ", " ", " ", " ", " ", " ", " "},
											{" ", " ", " ", " ", " ", " ", " "},
											{" ", " ", " ", " ", " ", " ", " "},
											{" ", " ", " ", " ", " ", " ", " "}} );
	}

	public static Texte2D emplacementCarteVide() //return "+-----+\n"+"|     |\n"+"|     |\n"+"|     |\n"+"|     |\n"+"+-----+";
	{
		return new Texte2D( new String[][]{	{"x", "-", "-", "-", "-", "-", "x"},
											{"|", " ", " ", " ", " ", " ", "|"},
											{"|", " ", " ", " ", " ", " ", "|"},
											{"|", " ", " ", " ", " ", " ", "|"},
											{"|", " ", " ", " ", " ", " ", "|"},
											{"x", "-", "-", "-", "-", "-", "x"}} );
	}

	private static String getStringCodeCouleur( String couleur )
	{
		String s;

		switch( couleur )
		{
			case "Carreau" -> s = "\033[0;31m";
			case "Coeur"   -> s = "\033[0;31m";
			case "Pique"   -> s = "\033[1;30m";
			case "Trefle"  -> s = "\033[1;30m";
			default        -> s = "\033[0m";
		}
		return s;
	}

	private static String getStringCouleur( String couleur )
	{
		String s;

		switch( couleur )
		{
			case "Carreau" -> s = "D"; //s+="\u2666"; s+="♦"; s+="#";
			case "Coeur"   -> s = "H"; //s+="\u2665"; s+="♥"; s+="%";
			case "Pique"   -> s = "S"; //s+="\u2660"; s+="♠"; s+="$";
			case "Trefle"  -> s = "C"; //s+="\u2663"; s+="♣"; s+="&";
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

	public static Texte2D pileToTexte2D( Pile p, char type ) //D[effause], [P]ioche, [C]ouleur, [J]eux
	{
		p = new Pile( p );

		if( type == 'T' )
		{
			return IHMCUI.carteToTexte2D( p.getSommet(), 'E' );
		}

		if( type == 'C' )
		{
			if( p.estVide() ){ return IHMCUI.carteToTexte2D( p.getSommet(), 'E' ); }
			else{              return IHMCUI.carteToTexte2D( p.getSommet(), 'X' ); }
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
				tabCarte[taille-1] = IHMCUI.carteToTexte2D( p.depiler(), 'E' );
			}
			if( taille > 1 )
			{
				tabCarte[taille-1-1] = IHMCUI.carteToTexte2D( p.depiler(), 'V' );
			}
			if( taille > 2 )
			{
				tabCarte[taille-2-1] = IHMCUI.carteToTexte2D( p.depiler(), 'V' );
			}

			return Texte2D.assemblerHorizontalement(0, tabCarte).completer(6,13);
		}

		return null;
	}
/*
	public static String plateauToString( Plateau p )
	{
		return "";
	}

	public String solitaireToString()
	{
		return "";
	}
*/

}
