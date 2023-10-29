// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet de sauvegarder une partie de solitaire
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

//ecriture de fichier
import java.io.PrintWriter;
import java.io.FileOutputStream;

//format et date
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Sauvegarder
{
	/*ATTRIBUTS DE CLASSE*/
	private static final String REPERTOIRE_SAUVEGARDE = "solitaire/sauvegardes/";

	/*ATTRIBUTS D'INSTANCE*/
	private Solitaire partie;
	private String fichier;

	/*CONSTRUCTEURS*/
	/**Constucteur avec fichier personnalisé.*/
	public Sauvegarder( Solitaire partie, String fichier )
	{
		this.partie  = partie;
		this.fichier = fichier;
	}

	/**Constucteur avec fichier automatique.*/
	public Sauvegarder( Solitaire partie )
	{
		this.partie  = partie;
		this.fichier = Sauvegarder.genererFichier();
	}

	/*METHODES*/
	/**Ecrire la sauvegarde dans un fichier.*/
	public void sauvegarde()
	{
		Plateau plateau = this.partie.getPlateau();

		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream( REPERTOIRE_SAUVEGARDE + this.fichier ) );

			pw.println ( "Sauvegarde du [date] de solitaire" );
			pw.println ( "Temps :\t" /*+ this.partie.getTemps()*/ );
			pw.println ( "Déplacements :\t" /*+ this.partie.getNbCoup()*/ );
			pw.println ( "" );
			pw.println ( "DATA" );
			pw.println ( Sauvegarder.pileToString( plateau.getPioche() ) );
			pw.println ( Sauvegarder.pileToString( plateau.getDefausse() ) );
			pw.println ( Sauvegarder.pileToString( plateau.getTasCouleur(0) ) );
			pw.println ( Sauvegarder.pileToString( plateau.getTasCouleur(1) ) );
			pw.println ( Sauvegarder.pileToString( plateau.getTasCouleur(2) ) );
			pw.println ( Sauvegarder.pileToString( plateau.getTasCouleur(3) ) );
			pw.println ( Sauvegarder.pileToString( plateau.getTasJeux(0) ) );
			pw.println ( Sauvegarder.pileToString( plateau.getTasJeux(1) ) );
			pw.println ( Sauvegarder.pileToString( plateau.getTasJeux(2) ) );
			pw.println ( Sauvegarder.pileToString( plateau.getTasJeux(3) ) );
			pw.println ( Sauvegarder.pileToString( plateau.getTasJeux(4) ) );
			pw.println ( Sauvegarder.pileToString( plateau.getTasJeux(5) ) );
			pw.println ( Sauvegarder.pileToString( plateau.getTasJeux(6) ) );
			pw.println ( "" );

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

	/*CONVERSIONS*/
	/**Conversion de données du jeu pour la sauvegarde.*/
	private static String pileToString( Pile tas )
	{
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
		return Sauvegarder.REPERTOIRE_SAUVEGARDE;
	}

	/*MODIFIEURS*/
	public static String genererFichier()
	{
		String s  = "save-";
		GregorianCalendar date = new GregorianCalendar();
		
		s += date.get( Calendar.YEAR );
		s += date.get( Calendar.MONTH );
		s += date.get( Calendar.DAY_OF_MONTH );
		s += date.get( Calendar.HOUR_OF_DAY );
		s += date.get( Calendar.MINUTE );
	
		return s+".data";
	}

}
