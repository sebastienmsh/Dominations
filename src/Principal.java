
public class Principal {

	public static void main(String[] args) {
		
		//Initialisations
		Domino.extraireCsv("dominos.csv");
		
		System.out.println("\nVoici le nombre de dominos extraits du csv : " + Domino.nombreinitialdominos);
		Domino.creationListeDominos();
		
		System.out.println("\nRemplir la liste des tuiles avec toutes les tuiles.");
		Tuile.remplirlistetuile(Domino.dominos);
		
		
		
		// Test des fonctions
			
		
			
		System.out.println("\nAfficher domino depuis liste :");
			Domino.afficherDomino(Domino.dominos[2]);
			
		System.out.println("\nAfficher domino depuis son attribut num�ro :");
			Domino.afficherDominoParNum(2);
			
		System.out.println("\nR�cup�rer le type 1 du domino 2 :");
			System.out.println(Domino.dominos[2].gettype1());
			
		System.out.println("\nAjouter une tuile � la liste des tuiles depuis la liste des dominos.");
			Tuile.ajoutertuile(Domino.dominos[2]);
			
		System.out.println("\nAfficher une tuile depuis la liste des tuiles :");
			System.out.println(Tuile.tuiles[2][0]);
			
		System.out.println("\nAfficher une tuile depuis la liste des tuiles :");
			System.out.println(Tuile.tuiles[45][0]);
			

	}

}
