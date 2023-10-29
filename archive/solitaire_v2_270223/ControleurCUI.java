// -*- coding: utf-8 -*-

/*
Classe ControleurCUI + main

Auteur  : Maxime L
Date    : 12/02/23
Version : 1
*/

public class ControleurCUI
{
	private Solitaire metier;
	private IHMCUI    ihm;
	private boolean   finDePartie;

	/*CONSTRUCTEUR*/
	public ControleurCUI()
	{
		String selection;
		String destination;

		this.ihm         = new IHMCUI( this );
		this.metier      = new Solitaire();
		this.finDePartie = false;

		this.ihm.afficherPlateau();

		while( !this.finDePartie )
		{
			selection = this.ihm.lireSelection();
			if( selection.equals("P") )
			{
				this.metier.piocher();
			}
			else
			{
				destination = this.ihm.lireDestination();
				this.deplacer( selection, destination );
			}

			this.ihm.afficherPlateau();
		}
	}


	public static void main(String[] argv)
	{
		new ControleurCUI();
	}

	private void deplacer( String selection, String destination )
	{
		int  ligOrig;
		int  ligDest;
		char colOrig;
		char colDest;

		if( selection.length() == 3 )
		{
			ligOrig = Integer.parseInt( selection.charAt(0)+""+selection.charAt(1) );
			colOrig = selection.charAt(2);
		}
		else
		{
			ligOrig = Integer.parseInt( selection.charAt(0)+"" );
			colOrig = selection.charAt(1);
		}

		if( destination.length() == 3 )
		{
			ligDest = Integer.parseInt( destination.charAt(0)+""+destination.charAt(1) );
			colDest = destination.charAt(2);
		}
		else
		{
			ligDest = Integer.parseInt( destination.charAt(0)+"" );
			colDest = destination.charAt(1);
		}

		this.metier.deplacer( ligOrig, colOrig, ligDest, colDest );
	}

	public String toString()
	{
		return this.metier.toString();
	}

/*	public static void main(String[] argv)
	{
		char col;
		int  lig;

		ctrl = new ControleurCUI();

		while( !this.finDePartie )
		{
			ctrl.ihm.afficherPlateau();

			saisie="";
			while( ctrl.action( saisie ) )
			{
				saisie = ctrl.ihm.lireAction();
			}


		}
	}

	private boolean action( String action )
	{
		if( action.equals("") )
		{
			return false;
		}

		if( action.equals("P") )
		{
			this.metier.piocher();
			return true;
		}

		if( action.length == 3 )
		{
			lig = (int)(saisie.charAt(0)+saisie.charAt(1));
			col = saisie.charAt(2);

			this.metier.deplacer( int ligOrig, char colOrig, int ligDest, char colDest );
		}
	}*/

}
