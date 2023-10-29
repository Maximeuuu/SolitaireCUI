// -*- coding: utf-8 -*-
/**
 * Description : Cette classe représente un Plateau de Solitaire.
 * Elle contient 1 pioche, 1 defausse, 4 tas de couleurs, 7 tas de jeux
 * Méthodes : constucteurs, accesseurs, modifieurs, initialisateurs, melanger, toString
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 12/02/23
Dernière maj  : 12/03/23
Version       : 2
==============================================================================*/

package solitaire.metier;

public class Plateau
{
	/*ATTRIBUTS DE CLASSE*/
	private static final int NB_CARTES = 52; //nb total
	private static final int NB_COULEURS = 4; //carreau, coeur, pique, trefle
	private static final int NB_TAS_JEUX = 7; //nb tas de jeux
	private static final int NB_CARTES_COULEURS = 13; //as, roi, dame, valet, 10..2

	/*ATTRIBUTS D'INSTANCE*/
	private Pile   pioche; //1
	private Pile   defausse; //1
	private Pile[] ensTasCouleur; //4
	private Pile[] ensTasJeux; //7

	/*CONSTRUCTEURS*/
	public Plateau()
	{
		this.initPioche(); //cree un jeu de 52 cartes
		this.melanger(); //melange la pioche

		this.initDefausse(); //cree un tas de defausse vide
		this.initEnsTasCouleur(); //cree les 4 tas vide de couleurs
		this.initEnsTasJeux(); //cree les 7 tas de cartes et y place les cartes
	}

	public Plateau( Pile pioche, Pile defausse, Pile[] ensTasCouleur, Pile[] ensTasJeux )
	{ //ce constucteur sera utile pour initialiser à partir d'une sauvegarde
		this.pioche        = pioche;
		this.defausse      = defausse;
		this.ensTasCouleur = ensTasCouleur;
		this.ensTasJeux    = ensTasJeux;
	}

	/*MODIFIEURS*/
	public void setPioche( Pile pioche )
	{
		this.pioche = pioche;
	}

	public void setDefausse( Pile defausse )
	{
		this.defausse = defausse;
	}

	public void setTasCouleur( Pile tasCouleur, int index )
	{
		this.ensTasCouleur[index] = tasCouleur;
	}

	public void setTasJeux( Pile tasJeux, int index )
	{
		this.ensTasJeux[index] = tasJeux;
	}

	/*ACCESSEURS*/

	/**Retourne la Pile pioche.*/
	public Pile getPioche()
	{
		return this.pioche;
	}

	/**Retourne la Pile défausse.*/
	public Pile getDefausse()
	{
		return this.defausse;
	}

	/**Retourne une Pile des couleurs à l'indice donné.*/
	public Pile getTasCouleur( int index )
	{
		return this.ensTasCouleur[ index ];
	}

	/**Retourne une Pile des jeux à l'indice donné.*/
	public Pile getTasJeux( int index )
	{
		return this.ensTasJeux[ index ];
	}

	/**Retourne le nombre de carte total le jeu.*/
	public static int getNbCartes()
	{
		return Plateau.NB_CARTES;
	}
	
	/**Retourne le nombre max de tas de jeux sur le plateau.*/
	public static int getNbTasJeux()
	{
		return Plateau.NB_TAS_JEUX;
	}
	
	/**Retourn le nombre max de tas de couleurs sur le plateau.*/
	public static int getNbCouleurs()
	{
		return Plateau.NB_COULEURS;
	}
	
	/**Retourne le nombre de cartes existantes pour une même couleur.*/
	public static int getNbCartesCouleur()
	{
		return Plateau.NB_CARTES_COULEURS;
	}


	/*INITIALISATEURS*/
	/**Initialise la pioche avec les 52 cartes disponibles de façon triée.*/
	private void initPioche() //contient les 52 cartes
	{
		Carte carteTmp;
		this.pioche = new Pile( Plateau.NB_CARTES );

		for( int cptCouleur=0; cptCouleur<Plateau.NB_COULEURS; cptCouleur++)
		{
			for( int cptValeur=0; cptValeur<Plateau.NB_CARTES_COULEURS; cptValeur++)
			{
				carteTmp = new Carte( cptValeur, cptCouleur );
				this.pioche.empiler( carteTmp );
			}
		}
	}

	/**Initialise la Pile de defausse à vide.*/
	private void initDefausse() //vide
	{
		this.defausse = new Pile( Plateau.NB_CARTES );
	}

	/**Initialise toutes les Pile de couleur à vide.*/
	private void initEnsTasCouleur() //vide
	{
		this.ensTasCouleur = new Pile[Plateau.NB_COULEURS];
		for( int cpt=0; cpt < Plateau.NB_COULEURS; cpt++ )
		{
			this.ensTasCouleur[cpt] = new Pile(Plateau.NB_CARTES_COULEURS);
		}
	}

	/**Initialise toutes les Pile de jeu avec le nombre de carte en escalier.*/
	private void initEnsTasJeux() //contient 28 cartes du tas
	{
		int maxCarteTasJeux = Plateau.NB_CARTES_COULEURS -1 + Plateau.NB_TAS_JEUX;

		this.ensTasJeux = new Pile[Plateau.NB_TAS_JEUX];
		for( int cptTas=0; cptTas < Plateau.NB_TAS_JEUX; cptTas++ )
		{
			this.ensTasJeux[cptTas] = new Pile( maxCarteTasJeux );

			for( int cptCarte=0; cptCarte < cptTas+1; cptCarte++ )
			{
				this.ensTasJeux[cptTas].empiler( this.pioche.depiler() );
			}

			this.ensTasJeux[cptTas].getSommet().retourner(); //retourne que la carte en tête de tas
		}
	}

	/*METHODES*/
	public boolean tasCouleurEstPleins() //test tous les tas et s'ils sont pleins
	{
		int cpt=0;
		while( cpt < Plateau.NB_COULEURS )
		{
			if( !this.ensTasCouleur[ cpt++ ].estPleine() )
			{
				return false;
			}
		}

		return true;
	}

	private void melanger() //melange la pioche à l'initialisation
	{
		this.pioche.melanger();
	}

	public String toString()
	{
		String s="\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

		s += "~~~~~~~~~~~~~1~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
		s += "A - pioche : "+this.pioche.toString()+"\n";
		s += "B - tas    : "+this.defausse.toString()+"\n";
		s += "~~~~~~~~~1~~~1~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
		s += "C - c1 : "+this.ensTasCouleur[0].toString()+"\n";
		s += "D - c2 : "+this.ensTasCouleur[1].toString()+"\n";
		s += "E - c3 : "+this.ensTasCouleur[2].toString()+"\n";
		s += "F - c4 : "+this.ensTasCouleur[3].toString()+"\n";
		s += "~~~~~~~~~1~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
		s += "\n";
		s += "=========2==3==4==5==6==7==8==9==10=11=12=========================\n";
		s += "A - t1 : "+this.ensTasJeux[0].toString()+"\n";
		s += "B - t2 : "+this.ensTasJeux[1].toString()+"\n";
		s += "C - t3 : "+this.ensTasJeux[2].toString()+"\n";
		s += "D - t4 : "+this.ensTasJeux[3].toString()+"\n";
		s += "E - t5 : "+this.ensTasJeux[4].toString()+"\n";
		s += "F - t6 : "+this.ensTasJeux[5].toString()+"\n";
		s += "G - t7 : "+this.ensTasJeux[6].toString()+"\n";
		s += "=========2==3==4==5==6==7==8==9==10=11=12=========================\n";

		return s;
	}

}
