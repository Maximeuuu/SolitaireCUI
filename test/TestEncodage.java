// -*- coding: utf-8 -*-
/**
 * Description : Cette classe permet de tester l'encodage de caractères spéciaux.
 * Méthodes : main
 */

/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 11/03/23
Dernière maj  : 12/03/23
Version       : 1
==============================================================================*/
package solitaire.test;
import solitaire.ihm.*;
import solitaire.metier.*;

import java.io.PrintStream; //nécessaire pour l'affichage des caractères spéciaux

public class TestEncodage
{

	public static void main(String[] argv)
	{
		System.setProperty("console.encoding", "UTF-8");

		try
        {
            PrintStream outStream = new PrintStream(System.out, true, "UTF-8");
            outStream.println("\u2764");
        }
        catch (Exception e){ }

	}
}
