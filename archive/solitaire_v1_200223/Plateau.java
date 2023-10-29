/*
Classe Plateau

Auteur  : Maxime L
Date    : 12/02/23
Version : 1
*/

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

	/*CONSTRUCTEUR*/
	public Plateau()
	{
		this.initPioche(); //cree un jeu de 52 cartes
		this.melanger(); //melange la pioche

		this.initDefausse(); //cree un tas de defausse vide
		this.initEnsTasCouleur(); //cree les 4 tas vide de couleurs
		this.initEnsTasJeux(); //cree les 7 tas de cartes et y place les cartes
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

	public void setTasJeux( Pile defausse, int index )
	{
		this.ensTasJeux[index] = defausse;
	}

	/*ACCESSEURS*/
	public Pile getPioche()
	{
		return this.pioche;
	}

	public Pile getDefausse()
	{
		return this.defausse;
	}

	public Pile getTasCouleur( int index )
	{
		return this.ensTasCouleur[ index ];
	}

	public Pile getTasJeux( int index )
	{
		return this.ensTasJeux[ index ];
	}

	public static int getNbCartes()
	{
		return Plateau.NB_CARTES;
	}

	private void melanger() //melange la pioche à l'initialisation
	{
		this.pioche.melanger();
	}

	/*INITIALISATEURS*/
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

	private void initDefausse() //vide
	{
		this.defausse = new Pile( Plateau.NB_CARTES );
	}

	private void initEnsTasCouleur() //vide
	{
		this.ensTasCouleur = new Pile[Plateau.NB_COULEURS];
		for( int cpt=0; cpt < Plateau.NB_COULEURS; cpt++ )
		{
			this.ensTasCouleur[cpt] = new Pile(Plateau.NB_CARTES_COULEURS);
		}
	}

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
	public String toString()
	{
		String s="";

		s += "===========\n";
		s += "pioche : "+this.pioche.toString()+"\n";
		s += "tas    : "+this.defausse.toString()+"\n";
		s += "===========\n";
		s += "c1 : "+this.ensTasCouleur[0].toString()+"\n";
		s += "c2 : "+this.ensTasCouleur[1].toString()+"\n";
		s += "c3 : "+this.ensTasCouleur[2].toString()+"\n";
		s += "c4 : "+this.ensTasCouleur[3].toString()+"\n";
		s += "===========\n";
		s += "t1 : "+this.ensTasJeux[0].toString()+"\n";
		s += "t2 : "+this.ensTasJeux[1].toString()+"\n";
		s += "t3 : "+this.ensTasJeux[2].toString()+"\n";
		s += "t4 : "+this.ensTasJeux[3].toString()+"\n";
		s += "t5 : "+this.ensTasJeux[4].toString()+"\n";
		s += "t6 : "+this.ensTasJeux[5].toString()+"\n";
		s += "t7 : "+this.ensTasJeux[6].toString()+"\n";
		s += "===========\n";

		return s;
	}

}
