// -*- coding: utf-8 -*-

/*
Classe IHMCUI

Auteur  : Maxime L
Date    : 12/02/23
Version : 1
*/
import iut.algo.Clavier;


public class IHMCUI
{

	/*ATTRIBUT*/
	private ControleurCUI ctrl;

	/*CONSTRUCTEUR*/
	public IHMCUI( ControleurCUI ctrl )
	{
		this.ctrl = ctrl;
	}

	/*METHODES*/
	public String lireSelection()
	{
		String saisie="";

		System.out.println("[P]ioche, ou carte en [<LIG><COL>]");
		System.out.print("Selectionner : ");
		saisie = Clavier.lireString();

		while( (saisie.equals("") || saisie.length() > 3) && !saisie.equals("P") )
		{
			System.out.println("Saisie non valide");
			System.out.print("Selectionner : ");
			saisie = Clavier.lireString();
		}

		return saisie;
	}

	public String lireDestination()
	{
		String saisie="";

		System.out.println("Position en [<LIG><COL>]");
		System.out.print("Destination : ");
		saisie = Clavier.lireString();

		return saisie;
	}

	public void afficherPlateau()
	{
		System.out.println( this.ctrl.toString() );
	}

}
