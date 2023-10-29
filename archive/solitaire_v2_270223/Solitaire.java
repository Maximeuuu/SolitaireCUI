// -*- coding: utf-8 -*-

/*
Classe Solitaire

Auteur  : Maxime L
Date    : 12/02/23
Version : 2
*/

public class Solitaire
{

	/*ATTRIBUTS*/
	private Plateau plateau;

	/*CONSTRUCTEUR*/
	public Solitaire()
	{
		this.plateau = new Plateau();
	}

	/*ACCESSEURS*/
	/*
	private Pile getTas( int lig, char col )
	{
		if( lig == 1 )
		{
			if( col == 'A' )
			{
				return this.plateau.getPioche();
			}
			if( col == 'B' )
			{
				return this.plateau.getDefausse();
			}

			return this.plateau.getTasCouleur( (int)(col -'A') );
		}
		else
		{
			return this.plateau.getTasJeux( (int)(col - 'A') );
		}
	}*/

	private static Carte getCarte( Pile tas, int pos )
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

	/*MODIFIEURS*/
	private void setTas( Pile tas, int lig, char col )
	{
		if( lig == 1 )
		{
			if( col == 'A' )
			{
				this.plateau.setPioche( tas );
			}
			if( col == 'B' )
			{
				this.plateau.setDefausse( tas );
			}

			this.plateau.setTasCouleur( tas, (int)(col -'A') );
		}
		else
		{
			this.plateau.setTasJeux( tas, (int)(col - 'A') );
		}
	}

	/*METHODES*/
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
		sop(ligOrig+"/"+colOrig+" -> "+ligDest+"/"+colDest);


		//test si le tas d'origine est un tas à carte unique (1ere ligne)
		if( ligOrig == 1 )
		{
			tasOrig = new Pile( 1 );

			//copie de la carte
			Carte carteTmp;
			if( colOrig == 'B')
			{
				carteTmp = this.plateau.getDefausse().getSommet();
			}
			else
			{
				carteTmp = this.plateau.getTasCouleur( (int)(colOrig -'A')-2 ).getSommet();
			}

			//test si la carte existe
			if( carteTmp != null )
			{
				tasOrig.empiler( carteTmp );
			}

		}
		else
		{
			//copie du tas d'origine
			tasOrig = new Pile( this.plateau.getTasJeux( (int)(colOrig-'A') ) );

		}

		//determination du nombre de carte à déplacer
		if( ligOrig == 1 )
		{
			nbCarteDeplacer = 1;
		}
		else
		{
			nbCarteDeplacer =  tasOrig.getNbElt() - (ligOrig-2); //-2 car l'origine est à 2
		}

		//ne rien faire s'il n'y a pas de carte à déplacer
		if( nbCarteDeplacer <= 0 )
		{
			sop("0 cartes");
			return false;
		}

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
				if( tasDest.getSommet().aCouleurDifferente( tasOrig.getSommet() ) )
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
					sop("e : A");
					System.out.println(tasDest.getSommet().toString()+" == "+ tasOrig.getTasCarte()[ tasOrig.getNbElt()-nbCarteDeplacer ].toString());
					return false;
				}
				if( tasDest.getSommet().compareTo( tasOrig.getTasCarte()[ tasOrig.getNbElt()-nbCarteDeplacer ] ) != 1 )
				{
					sop("e : B");
					System.out.println(tasDest.getSommet().toString()+" == "+ tasOrig.getTasCarte()[ tasOrig.getNbElt()-nbCarteDeplacer ].toString());
					return false;
				}
			}
		}

		sop(nbCarteDeplacer);
		sop(tasOrig.toString());
		sop(tasDest.toString());

		//ajout des cartes dans le tas de destination
		if( !tasDest.ajouter( tasOrig, nbCarteDeplacer ) )
		{
			return false;
		}

		sop(nbCarteDeplacer);
		sop(tasOrig.toString());
		sop(tasDest.toString());

		//suppression des cartes d'origine
		/*for( int cptCarte=0; cptCarte<nbCarteDeplacer; cptCarte++ )
		{
			tasOrig.supprimer();
		}*/

		//on rend visible la dernière carte du tas d'origine si elle est à l'envers
		if( !tasOrig.estVide() && !tasOrig.getSommet().estVisible() )
		{
			tasOrig.getSommet().retourner();
		}

		//mise à jour des tas
		//this.setTas( tasDest, ligDest, colDest );
		this.setTas( tasOrig, ligOrig, colOrig );

		return true;
	}

	public static void sop( Object txt )
	{
		System.out.println( txt );
	}

	public String toString()
	{
		return this.plateau.toString();
	}

	/*
	private Carte getCarte( char type, int tas, int pos )
	{
		Pile tasTemp;
		Carte carte = null;

		if( type == 'P' ) //pioche et defausse
		{
			if( tas == 0 ){ tasTemp = new Pile( this.plateau.getPioche() ); } //pioche
			if( tas == 1 ){ tasTemp = new Pile( this.plateau.getPioche() ); } //pioche
		}
		if( type == 'C' ) //couleur
		{
			tasTemp = new Pile( this.plateau.getTasCouleur( tas ) );
		}
		if( type == 'J' ) //jeux
		{
			tasTemp = new Pile( this.plateau.getTasJeux( tas ) );
		}

		for( int cpt; cpt < pos; cpt++ )
		{
			carte = tasTemp.depiler()
		}

		return carte;
	}*/



}
