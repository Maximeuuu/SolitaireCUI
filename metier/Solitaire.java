// -*- coding: utf-8 -*-
/**
 * Description : Cette classe représente la partie principale d'un jeu de Solitaire
 * Elle contient un plateau et un nombre de coup
 * Méthodes : constucteur, accesseurs, modifieur, piocher, deplacer, toString
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 12/02/23
Dernière maj  : 12/02/23
Version       : 2
==============================================================================*/

package solitaire.metier;

public class Solitaire
{

	/*ATTRIBUTS*/
	private Plateau plateau;
	private int nbCoup;

	/*CONSTRUCTEUR*/
	public Solitaire()
	{
		this.plateau = new Plateau();
		this.nbCoup = 0;
	}

	/*ACCESSEURS*/
	public static Carte getCarte( Pile tas, int pos )
	{
		Pile tasTemp;
		Carte carte = null;

		tasTemp = tas;

		for( int cpt=0; cpt < pos; cpt++ )
		{
			carte = tasTemp.depiler();
		}

		return carte;
	}
	public int getNbCoup()
	{
		return this.nbCoup;
	}
	public Plateau getPlateau()
	{
		return this.plateau;
	}

	/*MODIFIEURS*/
	public void setTas( Pile tas, int lig, char col )
	{
		if( lig == 1 )
		{
			if( col == 'A' )
			{
				this.plateau.setPioche( tas );
			}
			else if( col == 'B' )
			{
				this.plateau.setDefausse( tas );
			}
			else
			{
				this.plateau.setTasCouleur( tas, (int)(col -'A')-2 ); //ici -2 car il y a deux lignes avant dans la grille
			}
		}
		else
		{
			this.plateau.setTasJeux( tas, (int)(col - 'A') );
		}
	}

	public void incrementerNbCoup()
	{
		this.nbCoup++;
	}

	/*METHODES*/
	/**Détecte si la partie peut être terminée automatiquement.*/
	public boolean estGagne()
	{
		Pile tas;
		
		if( !this.plateau.getPioche().estVide()   ){ return false; }
		if( !this.plateau.getDefausse().estVide() ){ return false; }
		
		for( int cpt=0; cpt<Plateau.getNbTasJeux(); cpt++ )
		{
			tas = plateau.getTasJeux( cpt );
			if( tas.getOrigine() != null )
			{
				if( !tas.getOrigine().estVisible() ){ return false; }
			}
		}
		return true;
	}
	
	/**Détecte si la partie est terminée.*/
	public boolean estTermine()
	{
		return this.plateau.tasCouleurEstPleins();
	}
	
	/**Effectue un déplacement automatique*/
	public boolean actionAuto()
	{
		Pile tasJeux;
		Pile tasCouleur;
		
		for( int cptTasJ=0; cptTasJ < Plateau.getNbTasJeux(); cptTasJ++ )
		{
			tasJeux = this.plateau.getTasJeux( cptTasJ );
			for( int cptTasC=0; cptTasC < Plateau.getNbCouleurs(); cptTasC++ )
			{
				System.out.println("deplacement");
				tasCouleur = this.plateau.getTasCouleur( cptTasC );
				if( this.deplacer( tasJeux.getNbElt(), (char)(cptTasJ+'A'), 0, (char)(cptTasC+'A') ) )
				{
					System.out.println("finito");
					return true;
				}
				
				//setTasJeux();
				//setTasCouleur();
			}
		}
		
		return false;
	}

	/**Permet de piocher 3 carte dans le tas et les poser dans la défausse. Si le tas est vide, au prochain tour la défausse est transférée.*/
	public void piocher()
	{
		int cpt = 0;
		Pile pioche = this.plateau.getPioche();
		Pile defausse =  this.plateau.getDefausse();

		if( pioche.estVide() )
		{
			pioche = new Pile( defausse );
			pioche.retourner();
			defausse = new Pile( pioche.getTailleMax() );
			this.plateau.setPioche(pioche);
			this.plateau.setDefausse(defausse);
		}

		while( !pioche.estVide() && cpt < 3)
		{
			defausse.empiler( pioche.depiler() );
			defausse.getSommet().retourner();
			cpt++;
		}
	}

	public boolean deplacer( int ligOrig, char colOrig, int ligDest, char colDest )
	{
		Pile tasOrig;
		Pile tasDest;
		int nbCarteDeplacer;

		//test si le tas d'origine est un tas à carte unique (1ere ligne)
		if( ligOrig == 1 )
		{
			//copie du tas d'origine
			if( colOrig == 'B')
			{
				tasOrig = new Pile( this.plateau.getDefausse() );
			}
			else
			{
				tasOrig = new Pile( this.plateau.getTasCouleur( (int)(colOrig -'A')-2 ) );
			}
		}
		//copie du tas d'origine
		else{ tasOrig = new Pile( this.plateau.getTasJeux( (int)(colOrig-'A') ) ); }

		//determination du nombre de carte à déplacer
		if( ligOrig == 1 ){ nbCarteDeplacer = 1; }
		else{ nbCarteDeplacer =  tasOrig.getNbElt() - (ligOrig-2); } //-2 car l'origine est à 2

		//ne rien faire s'il n'y a pas de carte à déplacer
		if( nbCarteDeplacer <= 0 ){ return false; }

		//test si le tas de destination est un tas à carte unique (1ere ligne)
		if( ligDest == 1 )
		{
			tasDest = this.plateau.getTasCouleur( (int)(colDest -'A')-2 );

			if( nbCarteDeplacer > 1 )
			{
				return false;
			}
			if( tasDest.getNbElt() == 0 )
			{
				if( !tasOrig.getSommet().getNom().equals("As") )
				{
					return false;
				}
			}
			else
			{
				if( tasDest.getSommet().compareTo( tasOrig.getSommet() ) != -1 )
				{
					return false;
				}
				if( tasDest.getSommet().getCouleur() != tasOrig.getSommet().getCouleur() )
				{
					return false;
				}
			}

		}
		else
		{
			tasDest = this.plateau.getTasJeux( (int)(colDest -'A') );

			if( tasDest.getNbElt() != 0 )
			{
				if( !tasDest.getSommet().aCouleurDifferente( tasOrig.getTasCarte()[ tasOrig.getNbElt()-nbCarteDeplacer ] ) )
				{
					System.out.println(tasDest.getSommet().toString()+" == "+ tasOrig.getTasCarte()[ tasOrig.getNbElt()-nbCarteDeplacer ].toString());
					return false;
				}
				if( tasDest.getSommet().compareTo( tasOrig.getTasCarte()[ tasOrig.getNbElt()-nbCarteDeplacer ] ) != 1 )
				{
					System.out.println(tasDest.getSommet().toString()+" == "+ tasOrig.getTasCarte()[ tasOrig.getNbElt()-nbCarteDeplacer ].toString());
					return false;
				}
			}
		}

		//ajout des cartes dans le tas de destination
		if( !tasDest.ajouter( tasOrig, nbCarteDeplacer ) ){ return false; }

		//on rend visible la dernière carte du tas d'origine si elle est à l'envers
		if( !tasOrig.estVide() && !tasOrig.getSommet().estVisible() ){ tasOrig.getSommet().retourner(); }

		//mise à jour des tas
		//this.setTas( tasDest, ligDest, colDest );
		this.setTas( tasOrig, ligOrig, colOrig );


		return true;
	}

	public String toString()
	{
		return this.plateau.toString();
	}

}
