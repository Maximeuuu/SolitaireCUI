/*
Classe Solitaire

Auteur  : Maxime L
Date    : 12/02/23
Version : 1
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
	}

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
		Pile tas;
		Pile tasTemp;
		Pile tasDest;
		int nbCarteDeplacer;
		sop(ligOrig+"/"+colOrig+" -> "+ligDest+"/"+colDest);

		//récupération de copie des tas
		tas = new Pile( this.getTas( ligOrig, colOrig ) );
		tasTemp = new Pile( tas.getNbElt() );
		nbCarteDeplacer = tas.getNbElt() - (ligOrig-1); //+1;

		sop(tas.toString());
		sop(tasTemp.toString());
		sop(nbCarteDeplacer);

		//ne rien faire s'il n'y a pas de carte à déplacer
		if( nbCarteDeplacer <= 0 )
		{
			sop("0 cartes");
			return false;
		}

		//récupération, dans un tas à part, des cartes à déplacer
		int cpt = 0;
		while( cpt < nbCarteDeplacer )
		{
			tasTemp.empiler( tas.depiler() );
			cpt++;
		}
		sop(tasTemp.toString());

		//si les cartes sont visibles alors on déplacera, sinon on remet à l'endroit initial
		if( Solitaire.getCarte( tasTemp, tasTemp.getNbElt() ).estVisible() )
		{
			tasDest = this.getTas( ligDest, colDest );
			sop("visible");
		}
		else
		{
			tasDest = tas;
			sop("non visible");
		}

		//on empile toutes les cartes du tas mis de coté à l'emplacement de destination
		while( !tasTemp.estVide() )
		{
			tasDest.empiler( tasTemp.depiler() );
		}

		//on rend visible la dernière carte du tas d'origine si elle est à l'envers
		if( !tas.estVide() && !tas.getSommet().estVisible() )
		{
			tas.getSommet().retourner();
		}

		//mise à jour des tas
		sop(tasDest.toString());
		sop(tas.toString());
		this.setTas( tasDest, ligDest, colDest );
		this.setTas( tas, ligOrig, colOrig );

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
