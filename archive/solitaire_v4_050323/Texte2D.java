// -*- coding: utf-8 -*-

/*
Nom      : Classe Texte2D.java
Auteur   : Maxime L
Création : 3/03/23
MAJ      : 4/03/23
Version  : 2
*/
/*
Info : Un Texte2D est un objet représentant plusieurs lignes de texte.
C'est un tableau de String où chaque retour à la ligne est marqué par un nouvel indice du tableau.
Par exemple, la chaine suivante :
"+--+\n
|  |\n
+--+"
Correspond à : {"+--+", "|  |", "+--+"}
*/

public class Texte2D
{
	/*ATTRIBUTS*/
	private int largeur;
	private int hauteur;
	private String[] tabLigne;

	/*CONSTRUCTEURS*/
	public Texte2D( String[] tabLigne )
	{
		this.tabLigne  = tabLigne;
		this.hauteur  = tabLigne.length;
		this.largeur  = Texte2D.maxLargeur( tabLigne );
	}

	public Texte2D( int hauteur, int largeur )
	{
		this.tabLigne  = new String[ hauteur ];
		this.hauteur  = hauteur;
		this.largeur  = largeur;
	}

	/*ACCESSEURS*/
	public int getLargeur()
	{
		return this.largeur;
	}

	public int getHauteur()
	{
		return this.hauteur;
	}

	public String[] getTabLigne()
	{
		return this.tabLigne;
	}

	public String getLigne( int indice )
	{
		if( indice > this.hauteur-1 || this.tabLigne[indice] == null )
		{
			return "";
		}
		return this.tabLigne[ indice ];
	}

	/*MODIFIEURS*/
	public boolean setLigne( int indice, String ligne )
	{ //modifie le contenur à l'indice "indice" en ajoutant le texte "ligne"
		if( indice > this.hauteur ){ return false; }

		this.tabLigne[ indice ] = ligne;
		if( ligne.length() > this.largeur ){ this.largeur = ligne.length(); }

		return true;
	}

	public Texte2D addLigne( String ligne )
	{ //ajtoute une ligne avec la chaine "ligne"
		Texte2D tmp = new Texte2D( this.hauteur+1, this.largeur );

		for( int cptLig=0; cptLig<this.hauteur; cptLig++ )
		{
			tmp.setLigne( cptLig, this.getLigne( cptLig ) );
		}
		tmp.setLigne( this.hauteur, ligne );

		return tmp;
	}

	public Texte2D delLigne()
	{ //supprime la derniere ligne
		Texte2D tmp = new Texte2D( this.hauteur-1, this.largeur );

		for( int cptLig=0; cptLig<this.hauteur-1; cptLig++ )
		{
			tmp.setLigne( cptLig, this.getLigne( cptLig ) );
		}

		return tmp;
	}

	/*METHODES*/
	public static Texte2D assemblerVerticalement( int espacement, Texte2D[] tabElement2D )
	{
		if( tabElement2D.length == 0 ){ return null;}

		int hauteur = Texte2D.sommeHauteur( tabElement2D ) + espacement * (tabElement2D.length-1);

		String[] tabTmp = new String[ hauteur ];

		int cptLigTmp=0;
		for( int cptLig=0; cptLig<tabElement2D[0].getHauteur(); cptLig++ )
		{
			tabTmp[ cptLigTmp++ ] = tabElement2D[0].getLigne( cptLig );
		}

		for( int cptElt=1; cptElt<tabElement2D.length; cptElt++ )
		{
			for( int cptLig=0; cptLig<espacement; cptLig++ )
			{
				tabTmp[cptLigTmp++] = "";
			}
			for( int cptLig=0; cptLig<tabElement2D[cptElt].getHauteur(); cptLig++ )
			{
				tabTmp[ cptLigTmp++ ] = tabElement2D[cptElt].getLigne( cptLig );
			}
		}

		return new Texte2D( tabTmp );
	}

//OPTION PAS MISE EN PLACE : s'il est plus grand, il prend sur l'espacement
	public static Texte2D assemblerHorizontalement( int espacement, Texte2D[] tabElement2D )
	{ //assemble plusieurs Texte2D avec un écart de taille "espacement"
		if( tabElement2D.length == 0 ){ return null;}

		int hauteur = Texte2D.maxHauteur( tabElement2D );

		String[] tabTmp = new String[ hauteur ];

		for( int cptLig=0; cptLig<hauteur; cptLig++ )
		{
			tabTmp[ cptLig ] = tabElement2D[0].getLigne( cptLig );
		}

		for( int cptElt=1; cptElt<tabElement2D.length; cptElt++ )
		{
			for( int cptLig=0; cptLig<hauteur; cptLig++ )
			{
				for( int cptCol=0; cptCol<espacement; cptCol++ )
				{
					tabTmp[ cptLig ] += " ";
				}
				tabTmp[ cptLig ] += tabElement2D[cptElt].getLigne( cptLig );
			}
		}

		return new Texte2D( tabTmp );
	}

	public Texte2D completerHauteur( int hauteur )
	{ //complète avec des espaces une chaine pour atteindre la hauteur "hauteur"
		Texte2D tmp = this;
		if( hauteur <=  this.hauteur ){ return tmp; }

		for( int cpt=0; cpt<(hauteur-this.hauteur); cpt++ )
		{
			tmp = tmp.addLigne( " " );
		}

		tmp.hauteur = hauteur;
		return tmp;
	}

	public Texte2D completerLargeur( int largeur )
	{ //complète avec des espaces une chaine pour atteindre la largeur "largeur"
		Texte2D tmp = new Texte2D( this.hauteur, largeur );

		for( int cptLig=0; cptLig<this.hauteur; cptLig++ )
		{
			tmp.setLigne( cptLig, String.format("%1$-"+largeur+"s", this.getLigne(cptLig)) );
		}

		return tmp;
	}

	public Texte2D completerTout( int hauteur, int largeur )
	{ //complète toute la zone avec des espaces
		return this.completerHauteur( hauteur ).completerLargeur( largeur );
	}

	public Texte2D raccourcirHauteur( int hauteur )
	{ //raccourcit pour atteindre la hauteur "hauteur"
		return this.delLigne();
	}

	public Texte2D raccourcirLargeur( int largeur )
	{ //raccourcit pour atteindre la largeur "largeur"
		Texte2D tmp = new Texte2D( this.hauteur, largeur );

		for( int cptLig=0; cptLig<this.hauteur; cptLig++ )
		{
			tmp.setLigne( cptLig, this.getLigne(cptLig).substring(0, hauteur) );
		}

		return tmp;
	}

	/*COMPARAISONS*/

	public static int maxHauteur( Texte2D[] tabElement2D )
	{//determiner la hauteur maximale parmis une liste d'éléments
		int hauteurMax=0;

		for( int cptElt=0; cptElt<tabElement2D.length; cptElt++ )
		{
			if( tabElement2D[cptElt].getHauteur() > hauteurMax ){ hauteurMax = tabElement2D[cptElt].getHauteur(); }
		}
		return hauteurMax;
	}

	private static int maxLargeur( Texte2D[] tabElement2D )
	{ //determiner la largeur maximale parmis une liste d'elements
		int largeurMax=0;

		for( int cptElt=0; cptElt<tabElement2D.length; cptElt++ )
		{
			if( tabElement2D[cptElt].getLargeur() > largeurMax ){ largeurMax = tabElement2D[cptElt].getLargeur(); }
		}

		return largeurMax;
	}

	private static int maxLargeur( String[] tabLigne )
	{  //determiner la largeur maximale d'une chaine dans le tableau de String
		int largeurMax=0;

		for( int cptLig=0; cptLig<tabLigne.length; cptLig++ )
		{
			if( tabLigne[cptLig] != null && tabLigne[cptLig].length() > largeurMax )
			{
				largeurMax = tabLigne[cptLig].length();
			}
		}

		return largeurMax;
	}

	/*CONVERSION*/

	public static Texte2D stringToTexte2D( String chaine, String separateur ) //le séparateur est "\n"
	{
		return new Texte2D( chaine.split(separateur) );
	}

	/*OPERATIONS*/
	public static int sommeHauteur( Texte2D[] tabElement2D )
	{//additionne les hauteurs maximales de chaques elements
		int hauteurTotale=0;

		for( int cptElt=0; cptElt<tabElement2D.length; cptElt++ )
		{
			hauteurTotale += tabElement2D[cptElt].getHauteur();
		}

		return hauteurTotale;
	}

	public static int sommeLargeur( Texte2D[] tabElement2D )
	{//additionne les largeurs maximales de chaques elements
		int largeurTotale=0;

		for( int cptElt=0; cptElt<tabElement2D.length; cptElt++ )
		{
			largeurTotale += tabElement2D[cptElt].getLargeur();
		}

		return largeurTotale;
	}

	/*AFFICHAGE*/
	public String toString()
	{
		String s="";
		for( int cpt=0; cpt<this.getHauteur(); cpt++ )
		{
			s+=this.getLigne( cpt )+"\n";
		}
		return s;
	}

}
