public class CarteCUI
{
	private static final String BORDURE_H_C = "+-----+";
	private static final String BORDURE_V_C = "|";
	private static final String COEUR       = "♥";
	private static final String CARREAU     = "♦";
	private static final String TREFLE      = "♣";
	private static final String PIQUE       = "♠";

	public static String carteToString( Carte c )
	{
		String s = BORDURE_H_C + "\n";
		
		if( c.estVisible() )
		{
			//c.getValeur est un indice pour récupérer la valeur dans tabNom de carte -> il faut ajouter une getNom( indice )
			s += BORDURE_V_C + c.getValeur() + String.format("%1$-"+LARGEUR_C+"s"
		}
	}
}
