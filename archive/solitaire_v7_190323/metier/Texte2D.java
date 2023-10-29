// -*- coding: utf-8 -*-
/**
 * Description : Cette classe représente un Texte2D permetant de créer plusieurs lignes de textes.
 * C'est un tableau à 2 dimensions de Chaines permettant de placer où l'on veut différents Texte2D.
 * Elle contient des propriétés de largeur, hauteur et un tableau à 2 dimensions de chaines.
 * Méthodes : constructeur de création, constructeur de copie, accesseurs, modifieurs, toString,
 *            assemblerVerticalement, assemblerHorizontalement, completer,
 *            obtenir les valeurs max et les sommes des propriétés des tableaux.
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 3/03/23
Dernière maj  : 12/03/23
Version       : 3
==============================================================================*/

package solitaire.metier;

public class Texte2D
{
	/*ATTRIBUTS*/
	private int largeur;
	private int hauteur;
	private String[][] tabTexte;

	/*CONSTRUCTEURS*/
	public Texte2D( String[][] tabTexte )
	{
		this.tabTexte = tabTexte;
		this.hauteur  = tabTexte.length;
		this.largeur  = Texte2D.maxLargeur( tabTexte );
	}

	public Texte2D( int hauteur, int largeur )
	{
		if( this.hauteur < 0 ){ hauteur=0; }
		if( this.largeur < 0 ){ largeur=0; }

		this.tabTexte = new String[ hauteur ][ largeur ];
		this.hauteur  = hauteur;
		this.largeur  = largeur;

		//initialiser le tableau avec des String vides (au lieu de "null")
		for( int lig=0; lig<hauteur; lig++ )
		{
			for( int col=0; col<largeur; col++)
			{
				tabTexte[lig][col]=" ";
			}
		}
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

	public String[][] getTabTexte()
	{
		return this.tabTexte;
	}

	public String[] getLigne( int indice )
	{
		String[] tabTmp = new String[ this.largeur ];

		if( indice < 0              ){ return tabTmp; }
		if( indice > this.hauteur-1 ){ return tabTmp; }

		return this.tabTexte[ indice ];
	}

	public String[] getColonne( int indice )
	{
		String[] tabTmp = new String[ this.hauteur ];

		if( indice < 0              ){ return tabTmp; }
		if( indice > this.largeur-1 ){ return tabTmp; }

		for( int cpt=0; cpt<this.hauteur; cpt++ )
		{
			tabTmp[cpt] = this.tabTexte[ cpt ][ indice ];
		}

		return tabTmp;
	}

	public String getCase( int lig, int col )
	{
		if( col < 0              ){ return ""; }
		if( col > this.largeur-1 ){ return ""; }

		return this.getLigne( lig )[ col ];
	}

	/*MODIFIEURS*/
	public boolean setLigne( int indice, String[] ligne )
	{ //modifie le contenue à l'indice "indice" en ajoutant le texte "ligne"
		if( indice < 0                     ){ return false; }
		if( indice > this.hauteur-1        ){ return false; }
		if( ligne.length != this.largeur   ){ return false; }

		this.tabTexte[ indice ] = ligne;

		return true;
	}

	public boolean setColonne( int indice, String[] colonne )
	{
		if( indice < 0                       ){ return false; }
		if( indice > this.largeur-1          ){ return false; }
		if( colonne.length != this.hauteur   ){ return false; }

		for( int cpt=0; cpt<this.hauteur; cpt++ )
		{
			this.tabTexte[ cpt ][ indice ] = colonne[ cpt ];
		}

		return true;
	}

	public boolean setCase( int lig, int col, String texte)
	{
		if( col < 0              ){ return false; }
		if( col > this.largeur-1 ){ return false; }
		if( lig < 0              ){ return false; }
		if( lig > this.hauteur-1 ){ return false; }

		this.tabTexte[ lig ][ col ] = texte;

		return true;
	}

/*public Texte2D addLigne( String[] ligne ) //A REVOIR POUR AJOUTER
{ //ajtoute une ligne avec la chaine "ligne"
	if( ligne.length != this.largeur-1 ){ return this; }

	Texte2D tmp = new Texte2D( this.hauteur+1, this.largeur );

	for( int cptLig=0; cptLig<this.hauteur; cptLig++ )
	{
		tmp.setLigne( cptLig, this.getLigne( cptLig ) );
	}
	tmp.setLigne( this.hauteur, ligne )

	return tmp;
}

//ADD COLONNE

public Texte2D delLigne() //A REVOIR POUR AJOUTER
{ //supprime la derniere ligne
	if( this.hauteur <= 0 ){ return this; }

	Texte2D tmp = new Texte2D( this.hauteur-1, this.largeur );

	for( int cptLig=0; cptLig<this.hauteur-1; cptLig++ )
	{
		tmp.setLigne( cptLig, this.getLigne( cptLig ) );
	}

	return tmp;
}

//DEL COLoNNE
*/

	/*METHODES*/
	public static Texte2D assemblerVerticalement( int espacement, Texte2D[] tabElement2D )
	{ //assemble plusieurs Texte2D avec un écart de taille "espacement"
		if( tabElement2D.length == 0 ){ return new Texte2D(0,0);} //s'il n'y a pas d'éléments à ajouter

		//determination de la nouvelle taille du Texte2D assemblant tous les autres
		int hauteur = Texte2D.sommeHauteur( tabElement2D ) + espacement * (tabElement2D.length-1);
		int largeur = Texte2D.maxLargeur( tabElement2D );
		Texte2D tmp = new Texte2D( hauteur, largeur );

		//completer avec des espaces les elements2D pour pouvoir les assembler sans problèmes
		for( int cptElt=0; cptElt<tabElement2D.length; cptElt++ )
		{
			tabElement2D[cptElt] = tabElement2D[cptElt].completer( tabElement2D[cptElt].getHauteur(), largeur );
		}

		//ajout du premier élément
		int cptLigTmp=0;
		for( int cptLig=0; cptLig<tabElement2D[0].getHauteur(); cptLig++ )
		{
			tmp.setLigne( cptLigTmp++, tabElement2D[0].getLigne(cptLig) );
		}

		//ajout des espaces et des autres éléments
		for( int cptElt=1; cptElt<tabElement2D.length; cptElt++ )
		{
			for( int cptLig=0; cptLig<espacement; cptLig++ )
			{
				for( int cptCol=0; cptCol<tmp.largeur; cptCol++ ) //ici cette boucle for, pourrait être supprimée mais elle est là pour la cohérance du code, elle ajoute des espaces, sans cette boucle, ce sont des cases "nulles"
				{
					tmp.setCase(cptLigTmp,cptCol," "); // ajout des espaces lignes par lignes, colonnes par colonnes
				}
				cptLigTmp++;
			}

			for( int cptLig=0; cptLig<tabElement2D[cptElt].getHauteur(); cptLig++ )
			{
				tmp.setLigne( cptLigTmp++, tabElement2D[cptElt].getLigne( cptLig ) );
			}
		}

		return tmp;
	}

//IDEE : option pas mise en place : s'il est plus grand, il prend sur l'espacement
	public static Texte2D assemblerHorizontalement( int espacement, Texte2D[] tabElement2D )
	{ //assemble plusieurs Texte2D avec un écart de taille "espacement"
		if( tabElement2D.length == 0 ){ return new Texte2D(0,0);} //s'il n'y a pas d'éléments à ajouter

		//determination de la nouvelle taille du Texte2D assemblant tous les autres
		int hauteur = Texte2D.maxHauteur( tabElement2D );
		int largeur = Texte2D.sommeLargeur( tabElement2D ) + espacement * (tabElement2D.length-1);
		Texte2D tmp = new Texte2D( hauteur, largeur );

		//completer avec des espaces les elements2D pour pouvoir les assembler sans problèmes
		for( int cptElt=0; cptElt<tabElement2D.length; cptElt++ )
		{
			if( tabElement2D[cptElt] == null )
			{

				tabElement2D[cptElt] = new Texte2D(0,0);
			}
			tabElement2D[cptElt] = tabElement2D[cptElt].completer( hauteur, tabElement2D[cptElt].getLargeur() );
		}

		//ajout du premier élément
		int cptColTmp=0;
		for( int cptCol=0; cptCol<tabElement2D[0].getLargeur(); cptCol++ )
		{
			tmp.setColonne( cptColTmp++,  tabElement2D[0].getColonne( cptCol ) );
		}

		//ajout des espaces et des autres éléments
		for( int cptElt=1; cptElt<tabElement2D.length; cptElt++ )
		{
			for( int cptCol=0; cptCol<espacement; cptCol++ )
			{
				for( int cptLig=0; cptLig<tmp.hauteur; cptLig++ )
				{
					tmp.setCase(cptLig,cptColTmp," "); // ajout des espaces lignes par lignes, colonnes par colonnes
				}
				cptColTmp++;
			}
			for( int cptCol=0; cptCol<tabElement2D[cptElt].getLargeur(); cptCol++ )
			{
				tmp.setColonne( cptColTmp++, tabElement2D[cptElt].getColonne( cptCol ) );
			}
		}

		return tmp;
	}

	public Texte2D completer( int newHauteur, int newLargeur )
	{ //complète toute la zone avec des espaces
		Texte2D tmp = new Texte2D( newHauteur, newLargeur );

		for( int cptLig=0; cptLig<newHauteur; cptLig++ )
		{
			for( int cptCol=0; cptCol<newLargeur; cptCol++ )
			{
				String valeur = this.getCase(cptLig, cptCol);
				if( valeur == null || valeur.equals("") ){ valeur = " "; } //si la case est nulle ou vide on remplace par un espace
				tmp.setCase( cptLig, cptCol, valeur );
			}
		}

		return tmp;
	}

/*POSSIBLEMENT INUTILE
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
*/

	/*COMPARAISONS*/
	public static int maxHauteur( Texte2D[] tabElement2D )
	{//determiner la hauteur maximale parmis une liste d'éléments
		int hauteurMax=0;

		for( int cptElt=0; cptElt<tabElement2D.length; cptElt++ )
		{
			if( tabElement2D[cptElt] != null && tabElement2D[cptElt].getHauteur() > hauteurMax )
			{
				hauteurMax = tabElement2D[cptElt].getHauteur();
			}
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

	//private static int maxHauteur( String[][] tabTexte )

	private static int maxLargeur( String[][] tabTexte )
	{  //determiner la largeur maximale d'une chaine dans le tableau de String
		int largeurMax=0;

		for( int cptLig=0; cptLig<tabTexte.length; cptLig++ )
		{
			if( tabTexte[cptLig] != null && tabTexte[cptLig].length > largeurMax )
			{
				largeurMax = tabTexte[cptLig].length;
			}
		}

		return largeurMax;
	}

/*CONVERSION*//*POUR L'INSTANT TROP COMPLIQUE
public static String[][] stringToTabString( String chaine, String separateurLig, String separateurCol ) //les séparateurs par défaut sont lig : "\n", col : ""
{
	String[] tmpLig = chaine.split(separateurLig);
	String[] tmpCol = new String[][];
	for( int cpt=0; cpt<tmp.length; cpt++ )
	{
		tmp[cpt].split(separateurCol);
	}

	return ;
}*/

	/*OPERATIONS*/
	public static int sommeHauteur( Texte2D[] tabElement2D )
	{//additionne les hauteurs maximales de chaques elements
		int hauteurTotale=0;

		for( int cptElt=0; cptElt<tabElement2D.length; cptElt++ )
		{
			if( tabElement2D[cptElt] != null )
			{
				hauteurTotale += tabElement2D[cptElt].getHauteur();
			}
		}

		return hauteurTotale;
	}

	public static int sommeLargeur( Texte2D[] tabElement2D )
	{//additionne les largeurs maximales de chaques elements
		int largeurTotale=0;

		for( int cptElt=0; cptElt<tabElement2D.length; cptElt++ )
		{
			if( tabElement2D[cptElt] != null )
			{
				largeurTotale += tabElement2D[cptElt].getLargeur();
			}
		}

		return largeurTotale;
	}

	/*AFFICHAGE*/
	public String toString()
	{
		String s="";
		for( int cptLig=0; cptLig<this.getHauteur(); cptLig++ )
		{
			for( int cptCol=0; cptCol<this.getLargeur(); cptCol++ )
			{
				if( this.tabTexte[cptLig][cptCol] == null ) //cette partie là devra être supprimée !!! mais faut voir pk les espaces deviennent du vide
				{
					s+=" ";
				}
				else
				{
					s+=this.tabTexte[cptLig][cptCol];
				}
			}
			s+="\n";
		}
		return s;
	}

}
