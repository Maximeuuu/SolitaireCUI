/*
Classe Pile de cartes

Auteur  : Maxime L
Date    : 12/02/23
Version : 1
*/

public class Pile
{
	/*ATTRIBUTS*/
	private Carte[]	tasCarte;
	private int		sommet;

	/*CONSTRUCTEURS*/
	public Pile( int nbCarteTot )
	{
		this.tasCarte = new Carte[ nbCarteTot ];
		this.sommet   = -1;

	}

	public Pile( Pile p )
	{
		/*
		this.tasCarte = new Carte[ p.getTailleMax() ];
		this.sommet = -1;

		for( int cpt=0; cpt < p.getNbElt(); cpt++ )
		{
			//this.empiler( new Carte( p.getTasCarte()[cpt] ) );
			this.tasCarte[cpt] = new Carte( p.getTasCarte()[cpt] );
			this.sommet++;
		}*/

		//v2
		this.sommet = -1;
		this.tasCarte = new Carte[ p.getTailleMax() ];

		while( this.sommet+1 < p.getNbElt() )
		{
			this.tasCarte[ ++this.sommet ] = new Carte( p.getTasCarte()[ this.sommet ] );
		}
	}

	/*ACCESSEURS*/
	public Carte getSommet()
	{
		return this.tasCarte[ this.sommet ];
	}

	public int getNbElt()
	{
		return this.sommet+1;
	}

	public int getTailleMax()
	{
		return this.tasCarte.length;
	}

	public Carte[] getTasCarte()
	{
		return this.tasCarte;
	}

	/*METHODES*/
	public boolean estVide()
	{
		return this.sommet == -1;
	}

	public boolean estPleine()
	{
		return this.sommet == this.tasCarte.length;
	}

	public boolean empiler( Carte c )
	{
		if( this.estPleine() )
		{
			return false;
		}

		this.tasCarte[ ++this.sommet ] = c;
		return true;
	}

	public Carte depiler()
	{
		if( this.estVide() )
		{
			return null;
		}

		return this.tasCarte[ this.sommet-- ];
	}

	public void retourner()
	{
		Carte[] ensCarteTemp = new Carte[ this.tasCarte.length ];

		int sommetTemp = this.sommet;
		int cpt = 0;
		while( !this.estVide() )
		{
			ensCarteTemp[ cpt ] = this.depiler();
			ensCarteTemp[ cpt ].retourner();
			cpt++;
		}

		this.sommet = sommetTemp;
		this.tasCarte = ensCarteTemp;
	}

	public void melanger()
	{
		Carte carteTmp;
		int indexAlea;

		for( int cpt=0; cpt<this.sommet; cpt++ )
		{
			carteTmp = this.tasCarte[cpt];

			indexAlea = (int)( Math.random() * this.sommet );
			this.tasCarte[ cpt ] = this.tasCarte[ indexAlea ];

			this.tasCarte[ indexAlea ] = carteTmp;
		}
	}

	public String toString()
	{
		String s = "";

		for( int cpt=0; cpt < this.sommet+1; cpt++)
		{
			s += this.tasCarte[ cpt ].toString()+"/";
		}

		return s;
	}

}
