// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet de sauvegarder une partie de solitaire
 *
 * Méthodes :
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 15/03/23
Dernière maj  : 18/03/23
Version       : 2
==============================================================================*/

package solitaire.metier;

//ecriture de fichier
import java.io.PrintWriter;
import java.io.FileOutputStream;

//lecture de fichier
import java.util.Scanner;
import java.io.FileInputStream;

public class Sauvegarde
{
	/*ATTRIBUTS DE CLASSE*/
	private static final String REPERTOIRE_SAUVEGARDE = "solitaire/sauvegardes/";

	/*ATTRIBUTS D'INSTANCE*/
	private Solitaire partie;
	private String fichier;

	/*CONSTRUCTEURS*/
	public Sauvegarde( Solitaire partie, String fichier )
	{ //constucteur avec fichier personnalisé
		this.partie  = partie;
		this.fichier = REPERTOIRE_SAUVEGARDE+fichier;
	}

	/*public Sauvegarde( Solitaire partie )
	{ //constucteur avec fichier automatique
		this.partie  = partie;
		this.fichier = ; //genererFichier()
	}*/

	/*METHODES*/
	public void sauvegarder()
	{ //écrire la sauvegarde dans un fichier
		Plateau plateau = this.partie.getPlateau();

		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream( this.fichier ) );

			pw.println ( "Sauvegarde du [date] de solitaire" );
			pw.println ( "Temps :\t" /*+ this.partie.getTemps()*/ );
			pw.println ( "Déplacements :\t" /*+ this.partie.getNbCoup()*/ );
			pw.println ( "" );
			pw.println ( "DATA" );
			pw.println ( Sauvegarde.pileToString( plateau.getPioche() ) );
			pw.println ( Sauvegarde.pileToString( plateau.getDefausse() ) );
			pw.println ( Sauvegarde.pileToString( plateau.getTasCouleur(0) ) );
			pw.println ( Sauvegarde.pileToString( plateau.getTasCouleur(1) ) );
			pw.println ( Sauvegarde.pileToString( plateau.getTasCouleur(2) ) );
			pw.println ( Sauvegarde.pileToString( plateau.getTasCouleur(3) ) );
			pw.println ( Sauvegarde.pileToString( plateau.getTasJeux(0) ) );
			pw.println ( Sauvegarde.pileToString( plateau.getTasJeux(1) ) );
			pw.println ( Sauvegarde.pileToString( plateau.getTasJeux(2) ) );
			pw.println ( Sauvegarde.pileToString( plateau.getTasJeux(3) ) );
			pw.println ( Sauvegarde.pileToString( plateau.getTasJeux(4) ) );
			pw.println ( Sauvegarde.pileToString( plateau.getTasJeux(5) ) );
			pw.println ( Sauvegarde.pileToString( plateau.getTasJeux(6) ) );
			pw.println ( "" );

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

	public boolean charger()
	{ //lire la sauvegarde à partir d'un fichier
		Plateau plateau = this.partie.getPlateau();

		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( this.fichier ) );

			for( int cpt=0; cpt<5; cpt++ ) //skip l'entete
			{
				sc.nextLine();
			}

			plateau.setPioche( Sauvegarde.stringToPile( sc.nextLine() ) );
			plateau.setDefausse( Sauvegarde.stringToPile( sc.nextLine() ) );

			for( int cpt=0; cpt<4; cpt++ )
			{
				plateau.setTasCouleur( Sauvegarde.stringToPile( sc.nextLine() ), cpt );
			}

			for( int cpt=0; cpt<7; cpt++ )
			{
				plateau.setTasJeux( Sauvegarde.stringToPile( sc.nextLine() ), cpt );
			}

			sc.close();
		}
		catch (Exception e){ e.printStackTrace() ; return false; } //e.printStackTrace();

		return true;
	}

	/*CONVERSIONS*/
	private static String pileToString( Pile tas )
	{ //conversion de données du jeu pour la sauvegarde
		String s="";
		Carte[] tmp=tas.getTasCarte();

		for( int cpt=0; cpt<tas.getTailleMax(); cpt++ )
		{
			if( tmp[cpt] == null || cpt > tas.getNbElt()-1 )
			{
				s += "__" + "\t";
			}
			else
			{
				s += tmp[cpt].getIdentifiant() + "\t";
			}
		}
		return s;
	}

	private static Pile stringToPile( String s )
	{ //conversion de données de la sauvegarde pour le jeu
		String[] elements = s.split("\t");
		Pile tmp = new Pile( elements.length );

		for( String elt : elements )
		{
			if( !elt.equals("__") )
			{
				tmp.empiler( new Carte( Integer.parseInt(elt) ) );
			}
		}
		return tmp;
	}

	/*ACCESSEURS*/
	public Solitaire getPartie()
	{
		return this.partie;
	}

	public String getFichier()
	{
		return this.fichier;
	}

	public static String getRepertoire()
	{
		return Sauvegarde.REPERTOIRE_SAUVEGARDE;
	}

	/*MODIFIEURS*/
	public static String genererFichier()
	{
		return "save.data"; //REPERTOIRE_SAUVEGARDE+fichier+[DATE]
	}

	/*FICHIERS ET SAUVEGARDES*/
	/*public boolean estSauvegarde()
	{ //vérifie si les données sont valides

	}*/

	/*public static boolean existe( String fichier )
	{ //vérifie si le chemin existe

	}*/

	public String afficher()
	{ //lister toutes les sauvegardes disponibles dans le dossier
		return null;
	}

}


/*FORMAT : save.data
Sauvegarde du [date] de solitaire
Temps : [minutes]
Déplacements : [nb]


Data
[x/x/.../x] (pioche)
[x/x/.../x] (défausse)
[x/x/.../x] (couleur)
[x/x/.../x] (couleur)
[x/x/.../x] (couleur)
[x/x/.../x] (couleur)
[x/x/.../x] (jeu)
[x/x/.../x] (jeu)
[x/x/.../x] (jeu)
[x/x/.../x] (jeu)
[x/x/.../x] (jeu)
[x/x/.../x] (jeu)
[x/x/.../x] (jeu)

(où x correspond à une carte référencée par rapport à TAB_CARTES, compris dans [-52; 1] U [1;52] où le signe est déterminé par sa visibilité)

Exemple approximatif :
--/--/--/RC/10P/AC
=>  -6/-12/-38/52/36/49
*/
