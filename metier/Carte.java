// -*- coding: utf-8 -*-
/**
 * Description : Cette classe représente une carte dans un jeu de cartes.
 * Elle contient des propriétés de nom, couleur, valeur et de visibilité.
 * Méthodes : constucteurs, accesseurs, modifieurs, comparateurs, affichage
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 12/02/23
Dernière maj  : 17/03/23
Version       : 2
==============================================================================*/

package solitaire.metier;

public class Carte
{
	/*ATTRIBUTS DE CLASSE*/
	private static final String[] tabNom     = new String[]{"As","2","3","4","5","6","7","8","9","10","Valet","Dame","Roi"};
	private static final String[] tabCouleur = new String[]{"Carreau","Pique","Trefle","Coeur"};

	/*ATTRIBUTS D'INSTANCE*/
	private String    nom; //nom en lettre de la carte
	private String    couleur; //une des quatre couleurs
	private int       valeur; //valeur comprise entre 1 et 13
	private boolean   estVisible; //vrai = visible

	/*CONSTRUCTEURS*/
	public Carte( Carte c )
	{
		this.nom        = c.getNom();
		this.couleur    = c.getCouleur();
		this.valeur     = c.getValeur();
		this.estVisible = c.estVisible();
	}

	public Carte( int indiceNom, int indiceCouleur )
	{
		this.nom        = Carte.tabNom[ indiceNom ];
		this.couleur    = Carte.tabCouleur[ indiceCouleur ];
		this.valeur     = indiceNom+1;
		this.estVisible = false;
	}

	public Carte( String nom, String couleur )
	{
		this.nom        = nom;
		this.couleur    = couleur;
		this.valeur     = this.getIdentifiant();
		this.estVisible = false;
	}

	public Carte( int identifiant )
	{
		this.estVisible = (identifiant > 0);
		identifiant = Math.abs( identifiant );

		this.nom        = Carte.tabNom[ (identifiant-1) % tabNom.length ];
		this.couleur    = Carte.tabCouleur[ (identifiant-1) / tabNom.length ];
		this.valeur     = (identifiant-1) % tabNom.length + 1;
	}

	/*ACCESSEURS*/
	public String getNom()
	{
		return this.nom;
	}

	public String getCouleur()
	{
		return this.couleur;
	}

	public int getValeur()
	{
		return this.valeur;
	}

	public boolean estVisible()
	{
		return this.estVisible;
	}

	public static String[] getTabNom()
	{
		return Carte.tabNom;
	}

	public static String[] getTabCouleur()
	{
		return Carte.tabCouleur;
	}

	public int getIdentifiant()
	{
		int identifiant=0;
		int indexCouleur=0;
		for( int cpt=0; cpt<Carte.tabCouleur.length; cpt++ )
		{
			if( Carte.tabCouleur[cpt] == this.couleur ){ indexCouleur = cpt; }
		}
		identifiant = this.valeur + Carte.tabNom.length * indexCouleur;

		if( !this.estVisible ){ identifiant = -identifiant; }

		return identifiant;
	}

	/*MODIFIEURS*/
	public void retourner()
	{
		this.estVisible = !this.estVisible;
	}

	/*METHODES PUBLIQUES*/
	public boolean aCouleurDifferente( Carte carte )
	{
		if( carte == null ){ return true; }

		//test si la carte actuelle a une couleur opposée (ex : Dame rouge est opposée au Roi noir )
		if( (this.couleur == "Carreau" || this.couleur == "Coeur") &&
			(carte.getCouleur() == "Carreau" || carte.getCouleur() == "Coeur") )
		{
			return false;
		}
		if( (this.couleur == "Pique" || this.couleur == "Trefle") &&
			(carte.getCouleur() == "Pique" || carte.getCouleur() == "Trefle") )
		{
			return false;
		}
		return true;
	}

	public int compareTo( Carte c )
	{
		return this.valeur - c.getValeur();
	}

	public String toString()
	{

		String s="";

		if( this.estVisible )
		{
			switch( this.couleur )
			{
				case "Carreau" -> s+="\033[0;31m"; //s+="\u2666"; s+="♦"; s+="#";
				case "Coeur"   -> s+="\033[0;31m"; //s+="\u2665"; s+="♥"; s+="%";
				case "Pique"   -> s+="\033[1;30m"; //s+="\u2660"; s+="♠"; s+="$";
				case "Trefle"  -> s+="\033[1;30m"; //s+="\u2663"; s+="♣"; s+="&";
			}

			s += this.nom.charAt(0);

			if( this.nom.length() > 1 && this.nom.charAt(1) == '0' )
			{
				s += this.nom.charAt(1);
			}

			switch( this.couleur )
			{
				case "Carreau" -> s+="♦"; //s+="D"; s+="\u2666"; s+="♦"; s+="#";
				case "Coeur"   -> s+="♥"; //s+="H"; s+="\u2665"; s+="♥"; s+="%";
				case "Pique"   -> s+="♠"; //s+="S"; s+="\u2660"; s+="♠"; s+="$";
				case "Trefle"  -> s+="♣"; //s+="C"; s+="\u2663"; s+="♣"; s+="&";
			}
			//s += this.couleur.charAt(0);
		}
		else
		{
			s += "--";
		}

		s += "\033[0m";

		return s;
	}

	/*METHODES PRIVEES*/
	private int initValeur()
	{
		for( int cpt=0; cpt<Carte.tabNom.length; cpt++)
		{
			if( this.nom == Carte.tabNom[ cpt ] )
			{
				return cpt+1;
			}
		}
		return 0; //attention cette valeur génère des erreurs
	}

}
