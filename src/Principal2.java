import java.awt.EventQueue; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
 
public class Principal2 { 
	 
	static Affichage window = new Affichage(); 
	 
	static Jeu jeu1 = new Jeu(); 
	 
 
	public static void initialisation() { 
		jeu1.setNbJoueurs(window.getnbjoueurs()); 
		//jeu1.setChoixDomiTour(1,0,2,3); 
		jeu1.initialisationpartie(); 
		jeu1.preparationtour(); 
		 
		window.setDominosTour(jeu1.dominostour); 
		window.setnbrois(jeu1.getnbrois());
		window.setJoueurs(jeu1.joueurs);
		window.setOrdreJoueurs(jeu1.getOrdreJoueurs());
		
		
		
		 
		//jeu1.setChoixDomiTour(window.numdomitour,window.numtuiletour,window.PageJeu.) 
		
		//jeu1.tourjoueur(); 
		 
	} 
	
	public static void preselection() {
		
		jeu1.setChoixTuilePreselect(window.getChoixTuileTour());
		jeu1.setJoueurEnCours(window.getJoueurEnCours());
		jeu1.preselection();
		window.setBonChoix(jeu1.getBonChoix());
		
		
		
	}
	
	public static void definirordre() {
		jeu1.definirnouvelordre();
		window.setOrdreJoueurs(jeu1.getOrdreJoueurs());
		
		
		
		
		
		
		
		
		
	}
	
	
	public static void tourJeu() {
		jeu1.setChoixTuilePreselect(window.getChoixTuileTour());
		jeu1.setJoueurEnCours(window.getJoueurEnCours());
		jeu1.setPositionChoixTuileTour(window.getPosX(),window.getPosY());
		jeu1.tourjoueur();
		
	
		

		
	
		window.setBonChoix(jeu1.getBonChoix());
	}

	
	public static void main(String[] args) {

		Domino.extraireCsv("dominos.csv");
		System.out.println(
				"Voici la longueur de la liste des dominos extraits du csv : " + Domino.listedetouslesDominos.size());

		Domino.creationListeDominos();
		System.out.println("La liste dominos a correctement ete creee.");

		Tuile.remplirlistetuile(Domino.dominos);
		System.out.println("La liste tuiles a ete remplie e partir de la liste dominos.");

		System.out.println("\n_____________Nouvelle Partie_____________\n");
		
		
		window.initialisation();
		while(!jeu1.partieterminee) {
			
			window.deroulement();
			jeu1.preparationtour();
			
		}
		
		
		
		

	}
	 
 
	 
	 
	 
 
} 