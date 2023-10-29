// -*- coding: utf-8 -*-
/*
Classe Carte

Auteur  : Maxime L
Date    : 12/02/23
Version : 1
*/

public class Carte
{
	/*ATTRIBUTS DE CLASSE*/
	private static final String[] tabNom     = new String[]{"As","2","3","4","5","6","7","8","9","10","Valet","Dame","Roi","Joker"};
	private static final String[] tabCouleur = new String[] {"Carreau","Pique","Trefle","Coeur"};

	/*ATTRIBUTS D'INSTANCE*/
	private String    nom;
	private String    couleur;
	private int       valeur;
	private boolean   estVisible;

	/*CONSTRUCTEURS*/
	public Carte( Carte c )
	{
		this.nom        = c.getNom();
		this.couleur    = c.getCouleur();
		this.valeur     = c.getValeur();
		this.estVisible = c.estVisible();
	}

	public Carte( int valeurNom, int indexCouleur )
	{
		this.nom        = Carte.tabNom[ valeurNom ];
		this.couleur    = Carte.tabCouleur[ indexCouleur ];
		this.valeur     = valeurNom;
		this.estVisible = false;
	}

	public Carte( String nom, String couleur )
	{
		this.nom        = nom;
		this.couleur    = couleur;
		this.valeur     = this.initValeur();
		this.estVisible = false;
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

	/*MODIFIEURS*/
	public void retourner()
	{
		this.estVisible = !this.estVisible;
	}

	/*METHODES PUBLIQUES*/
	public boolean aCouleurDifferente( Carte carte )
	{
		if( carte == null )
		{
			return true;
		}

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
				case "Carreau" -> s+="D"; //s+="\u2666"; s+="♦"; s+="#";
				case "Coeur"   -> s+="H"; //s+="\u2665"; s+="♥"; s+="%";
				case "Pique"   -> s+="S"; //s+="\u2660"; s+="♠"; s+="$";
				case "Trefle"  -> s+="C"; //s+="\u2663"; s+="♣"; s+="&";
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
		return 0;
	}

}
