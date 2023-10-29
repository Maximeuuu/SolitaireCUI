/*
Classe Test Pile et Carte

Auteur  : Maxime L
Date    : 12/02/23
Version : 1
*/

public class TestPileCarte
{

	public static void main(String[] argv)
	{
		Pile pile1 = new Pile(10);
		System.out.println( "pile vide : " + pile1.toString() );

		pile1.empiler( new Carte("As", "Carreau"));
		pile1.empiler( new Carte("3", "Trefle"));
		pile1.empiler( new Carte("10", "Carreau"));

		System.out.println( "pile de 3 cartes cachées : " + pile1.toString() );
		pile1.retourner();
		System.out.println( "pile de 3 cartes visibles : " + pile1.toString() );

		Carte carte = new Carte( 5, 2 );
		System.out.println( "une carte cachée : " + carte.toString() );
		carte.retourner();
		System.out.println( "une carte visible : " + carte.toString() );
		System.out.println( carte.getNom() + " " + carte.getCouleur() + " " + carte.estVisible() );

		carte.retourner();
		System.out.println( carte.estVisible() );
		carte.retourner();

		Carte carte2 = new Carte( 7, 1 );
		carte2.retourner();
		Carte carte3 = new Carte( 7, 0 );
		carte3.retourner();
		if( carte2.compareTo( carte ) > 0 )
		{
			System.out.println( carte2.toString() + ">" + carte.toString()  );
		}
		if( carte2.compareTo( carte3 ) == 0 )
		{
			System.out.println( carte2.toString() + "=" + carte3.toString()  );
		}


		System.out.println( "pile originale : " + pile1.toString());
		Pile pile2 = new Pile( pile1 );
		System.out.println( "dupliquer une pile : " + pile2.toString() );
		pile2.empiler( carte2 );
		System.out.println( "ajouter une carte : " + pile2.toString() );
		System.out.println( "pile originale : " + pile1.toString());

		Carte carteDuplique = new Carte( carte );
		System.out.println( "carte dupliquée : " + carte.toString() + " // " + carteDuplique.toString() );

	}
}
