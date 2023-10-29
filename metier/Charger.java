// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet de charger une partie de solitaire
 *
 * Méthodes :
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 15/03/23
Dernière maj  : 20/03/23
Version       : 3
==============================================================================*/

package solitaire.metier;

//lecture de fichier
import java.util.Scanner;
import java.io.FileInputStream;

//lecture de repertoire
import java.io.File;

public class Charger
{
	/*ATTRIBUTS DE CLASSE*/
	private static final String REPERTOIRE_CHARGEMENT = "solitaire/sauvegardes/";

	/*ATTRIBUTS D'INSTANCE*/
	private Solitaire partie;
	private String fichier;

	/*CONSTRUCTEURS*/
	/**Constucteur avec fichier personnalisé.*/
	public Charger( Solitaire partie, String fichier )
	{
		this.partie  = partie;
		this.fichier = fichier;
	}

	/*METHODES*/
	/**Lire la sauvegarde à partir d'un fichier.*/
	public boolean chargement()
	{
		Plateau plateau = this.partie.getPlateau();

		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( REPERTOIRE_CHARGEMENT+this.fichier ) );

			for( int cpt=0; cpt<5; cpt++ ) //skip l'entete
			{
				sc.nextLine();
			}

			plateau.setPioche( Charger.stringToPile( sc.nextLine() ) );
			plateau.setDefausse( Charger.stringToPile( sc.nextLine() ) );

			for( int cpt=0; cpt<4; cpt++ )
			{
				plateau.setTasCouleur( Charger.stringToPile( sc.nextLine() ), cpt );
			}

			for( int cpt=0; cpt<7; cpt++ )
			{
				plateau.setTasJeux( Charger.stringToPile( sc.nextLine() ), cpt );
			}

			sc.close();
		}
		catch (Exception e){ e.printStackTrace() ; return false; } //e.printStackTrace();

		return true;
	}

	/*CONVERSIONS*/
	/**Conversion de données de la sauvegarde pour le jeu.*/
	private static Pile stringToPile( String s )
	{
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
		return Charger.REPERTOIRE_CHARGEMENT;
	}

	/*FICHIERS ET SAUVEGARDES*/
	/*public boolean estSauvegarde()
	{ //vérifie si les données sont valides

	}*/

	/**Vérifie si le fichier existe*/
	public static boolean existe( String fichier )
	{
		String[] listeFichiers = Charger.listeSauvegarde();
		
		for( int cpt=0; cpt<listeFichiers.length; cpt++ )
		{
			if( fichier.equals( listeFichiers[cpt] ) ){ return true; }
		}
		return false;
	}

	/**Lister toutes les sauvegardes disponibles dans le dossier*/
	public static String[] listeSauvegarde()
	{
		File repertoire = new File( Charger.REPERTOIRE_CHARGEMENT );
 		return repertoire.list();
	}

}
