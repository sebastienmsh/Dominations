
public class Joueur {
	
	Terrain terrain1;
	String couleur;
	int numjoueur;
	String nomjoueur;
	boolean premiereselection=true;
	Tuile activetuile;
	
	
	public Joueur(int numjoueur, String couleur) {
		this.terrain1 = new Terrain();
		this.couleur=couleur;
		this.numjoueur=numjoueur;
		
		
	}
	
	
	public String getCouleur() {
		return couleur;
		
	}
	
	public void getJoueurSelonCouleur(String couleur) {
		
	}
	
	public boolean getPremiereSelection() {
		return premiereselection;
		
	}
	public void setPremiereSelection(boolean premiereselection) {
		this.premiereselection=premiereselection;
		
	}
	
	public void setPreSelection(Tuile tuile) {
		this.activetuile=tuile;
	}
	
	public void setNomJoueur(String nom) {
		this.nomjoueur=nom;
	}
	

	
	public String getNomJoueur() {
		return this.nomjoueur;
		
	}

}
