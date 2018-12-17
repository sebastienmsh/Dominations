import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tuile {

	int nbCouronne, num1, num2;
	String type;

	public static Tuile[][] tuiles = new Tuile[48][2];
	public static ArrayList<Tuile> listeTuiles = new ArrayList<Tuile>();

	public Tuile(int num1, int num2, int nbCouronne, String type) {
		this.num1 = num1;
		this.num2 = num2;
		this.nbCouronne = nbCouronne;
		this.type = type;

	}

	public static Tuile[][] ajoutertuile(Domino domi) {

		Tuile tuile1 = new Tuile(domi.getnum(), 1, domi.getnbCouronne1(), domi.gettype1());
		Tuile tuile2 = new Tuile(domi.getnum(), 2, domi.getnbCouronne2(), domi.gettype2());

		tuiles[domi.getnum()][0] = tuile1;
		tuiles[domi.getnum()][1] = tuile2;

		// old
		/*
		 * listeTuiles.set(domi.getnum(),tuile1); listeTuiles.set(domi.getnum(),tuile2);
		 * listeTuiles.add(tuile1); listeTuiles.add(tuile2);
		 */

		// This prints out the working directory
		return tuiles;

	}
	
	//remplir la liste des tuiles avec tous les dominos
	public static Tuile[][] remplirlistetuile(Domino[] dominos){
		
		for (int i=0; i<=Domino.nombreinitialdominos-1; i++) {
			ajoutertuile(dominos[i]);
		}
		
		return tuiles;
		
	}
	
	//supprimer une tuile de la liste
	public static Tuile[][] supprimertuiledetuile(int num0, int num1){
		
		tuiles[num0][num1] = null;
		
		return tuiles;
	}
	
	public static Tuile[][] supprimerdominodetuile(int num0){
		
		tuiles[num0][0] = null;
		tuiles[num0][1] = null;
		
		return tuiles;
	}
	
	
	//affichage console des attributs d'une tuile
	
	public String toString() {
		return "num�ro Domino: " + num1 + "\nnum�ro Tuile: " + num2 + "\nnbcouronne: " + nbCouronne +"\ntype : " + type;
	}
	
	
	//GETTERS & SETTERS
	
	public int getnumdomi() {
		return num1;
	}
	public int getnumtuile() {
		return num2;
	}
	public int getnbcouronne() {
		return nbCouronne;
	}
	public String gettype() {
		return type;
	}
	public void setnumdomi(int num1) {
		this.num1 = num1;
	}
	public void setnumtuile(int num2) {
		this.num2 = num2;
	}
	public void setnbcouronne(int nbCouronne) {
		this.nbCouronne = nbCouronne;
	}
	public void settype(String type) {
		this.type = type;
	}
	

}
