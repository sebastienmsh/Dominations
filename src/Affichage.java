import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class Affichage implements ActionListener {

	// vers Jeu
	int numdomitour = 0;
	int numtuiletour = 0;

	int posx=0;
	int posy=0;

	// provenant de Jeu
	int nbjoueurs = 0;
	int nbrois = 0;
	public Joueur[][] ordrejoueurs = new Joueur[4][2];
	public Joueur[][] joueurs = new Joueur[4][2];
	boolean bonchoix;
	
	//Instanciation de Compteurs
	int compteurpioche = 0;
	public int compteurjoueur = 0;
	public int compteurclictuile = 0;
	

	//Instanciation de la fenêtre et de ses pages
	JFrame FenetreJeu = new JFrame("Projet Kingdomino");
	CardLayout PlusieursPages = new CardLayout(0, 0);
	String[] listeIndice = { "Accueil", "Principal" };
		AffichageAccueil PageAccueil = new AffichageAccueil();
		Cote Panel = new Cote();
			GridBagLayout recadrage = new GridBagLayout();
			AffichageJeu PageJeu = new AffichageJeu() ;
			


	
	public Tuile[][] dominostour = new Tuile[4][2];
	
	public AffichagePlateau Ecran;
	public Affichage() {

		DefinitionFenetre();
		AjouterLesActionsListner();
		DefinitionPageCarreQuiContientPageJeu();
		InitialiselesChampdeTexte();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == PageAccueil.QuatreJoueurs || source == PageAccueil.TroisJoueurs || source == PageAccueil.DeuxJoueurs) {
			ChoixDuNombreDeJoueurs(source);
			DefinitNbreJoueursEnFonctionDuChoix(source);
			PageAccueil.lblNombreDeJoueurs.setText("Vous êtes " + ((JRadioButton) source).getText()); //Affiche en texte le nombre de joueurs sur l'accueil
			PageJeu.lblNbreJoueurs.setText("Partie à " + ((JRadioButton) source).getText()); //Affiche en texte le nombre de joueurs sur le jeu principal
			System.out.println("Il y a " + nbjoueurs +" joueurs !"); //Affiche en texte le nombre de joueurs sur la console
			AfficherChampdeTexteAccueilSelonNbreJoueurs();
		}
		

		if (source == PageJeu.btnRetournerAuMenu) {
			PlusieursPages.show(FenetreJeu.getContentPane(), listeIndice[0]);
		}

		if (source == PageAccueil.btnJouer) {
			Principal.initialisation();
			ReduireA3JoueursSiBesoin();
			setnbjoueursAffichageJeu(PageJeu.compteurjoueur);
			PlusieursPages.show(FenetreJeu.getContentPane(), listeIndice[1]);
			AffecteAOrdrejoueursETauxEcransleNomdeChaqueJoueur();
			DefinirChateauPourTousEcran();
			DefinirNombreEcrans();
			System.out.println("");
			System.out.println("Appuyez sur la pioche pour commencer");
		}

		if (source == PageJeu.btnPioche) {
			
			System.out.println("La première pioche est enclenchée");
			setEcran(compteurjoueur);

			System.out.println("L'écran actif est :" + Ecran.getName());
			
			if (compteurpioche == 0) {

				SortirLa_1ere_Pioche();
				System.out.println("Compteur Joueur = " + compteurjoueur);
				SetActionListenerSurDomino_i_Tuile_j (0); //En argument on a la tuile de gauche ou celle de droite du domino selectionné
				System.out.println("voila" + Ecran.getBoutton(0, 0).getBackground());
				
				

				/*
				//for (int i = 0; i<nbrois; i=+2 ) {
					switch(compteurjoueur) {
						case 0 :
							SetActionListenerSurLa_Tuile0_duDominoEnArgument(0); //Domino 0 de la liste dominostours
							break;
						case 1 :
							SetActionListenerSurLa_Tuile0_duDominoEnArgument(1); //Domino 1 de la liste dominostours
							break;
						case 2 :
							SetActionListenerSurLa_Tuile0_duDominoEnArgument(2); //Domino 2 de la liste dominostours
							break;
						case 3 :
							//SetActionListenerSurLa_Tuile0_duDominoEnArgument(3); //Domino 3 de la liste dominostours
							break;
					}
				//}
			}*/
		}

			else if (compteurpioche == 1) {

				// Retour couleur selectionn�e

			}

			else if (compteurpioche == 2) {

			}

		}
	}
	
	public void SetActionListenerSurDomino_i_Tuile_j (int p) { //Tuile j ème du i ème domino de notre liste dominostour sortante de la pioche
		for (int k=0; k<dominostour.length;k++){
			int m = k;
			PageJeu.ListTour1.get(2*k+p).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setEcran (compteurjoueur);
					PageJeu.PosDomiSelection=m;
					PageJeu.PosTuiSelection=p;
			}});
		}
	//		setChoixTuileTour(T);

		for (int k=0; k<81 ;k++) {
		
			int m = k;
			Ecran.ListeBoutons.get(k).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("[" + m/9 + "][" + m%9 + "]" + " enclenché sur le terrain " + ((JButton) e.getSource()).getParent());
				    JButton source = (JButton) e.getSource();
				    Ecran.setPosX(m/9);
				    Ecran.setPosY(m%9);
			        System.out.println("posx "+ m/9);
			        System.out.println("posy "+ m%9);
					Principal.preselection(Ecran, Principal.jeu1.joueurs[compteurjoueur][0], Principal.jeu1.ordrejoueurs[compteurjoueur][0], dominostour[PageJeu.PosDomiSelection][PageJeu.PosTuiSelection]);	            
						
			}});
			}
			System.out.println("Display done");
	}
	public void SortirLa_1ere_Pioche () {
		for (int k = 0; k < PageJeu.ListTour1.size() / 2; k++) {
	
			JButton jButton1 = PageJeu.ListTour1.get(2 * k);
			JButton jButton2 = PageJeu.ListTour1.get(2 * k + 1);
			
			display(dominostour[k][0], jButton1);
			display(dominostour[k][1], jButton2);
		}
	}
	public void SetActionListenerSurDomino_i_Tuile_j_Bis (int i, int j) { //Tuile j ème du i ème domino de notre liste dominostour sortante de la pioche
		System.out.println("C'est au joueur " + (compteurjoueur+1) +" de jouer");
		PageJeu.ListTour1.get(2*i+j).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				//if (compteurclictuile == 0) {
				//	if (compteurjoueur < nbrois) {
						Principal.preselection(Ecran, Principal.jeu1.joueurs[compteurjoueur][0], Principal.jeu1.ordrejoueurs[compteurjoueur][0], dominostour[0][0]);	
						System.out.println("Display done");
		}});		
	}
	public void setEcran (int compteurjoueur) {
		switch (compteurjoueur+1) {
		case 1 :
			Ecran = PageJeu.EcranOuest;
			break;
		case 2 :
			Ecran = PageJeu.EcranNord;
			break;
		case 3 :
			Ecran = PageJeu.EcranEst;
			break;
		case 4 :
			Ecran = PageJeu.EcranSud;
			break;
		}
	}

	public boolean VerifierSiLaTuileEstPose(int i, int j) {
		if (Ecran.getBoutton(i, j).getText()=="-") {
			System.out.println("cest bon");
			return true;
		}
		else { return false;}
	}
	
	private static void resizePreview(JPanel innerPanel, JPanel container) {
		int w = container.getWidth();
		int h = container.getHeight();
		int size = Math.min(w, h);
		innerPanel.setPreferredSize(new Dimension(size, size));
		container.revalidate();
	}

	public int getnbjoueurs() {
		return nbjoueurs;
	}

	public void setDominosTour(Tuile[][] dominostour) {
		this.dominostour = dominostour;
	}

	

	public void setRoiTour(String clr, Tuile T,Tuile[][] dominostour) {
		Color Couleur = Color.WHITE;
		switch (clr) {
		case "GREEN":
			Couleur = Color.GREEN;
			break;
		case "BLUE":
			Couleur = Color.BLUE;
			break;
		case "RED":
			Couleur = Color.RED;
			break;
		case "YELLOW":
			Couleur = Color.YELLOW;
			break;
		}
		for (int j = 0; j < PageJeu.List1BoutonRoi.size(); j++) {
			if (T.getnumdomi() == dominostour[j][0].getnumdomi()) {
				PageJeu.List1BoutonRoi.get(j).setBackground(Couleur);
				PageJeu.List1BoutonRoi.get(j).setForeground(Color.WHITE);
				PageJeu.List1BoutonRoi.get(j).setText("R");
			}
		}
	}

	public void appelDefinitionNouvelOrdre() {
		// definition nouvel ordre
		
		Principal.definirordre();

		System.out.println("Ceci est un nouvel ordre de joueurs :\n");
		for (int j = 0; j <= ordrejoueurs.length - 1; j++) {
			System.out.println(ordrejoueurs[j][0].numjoueur);
		}
	}

	public void setNumDomiTour(int num) {
		this.numdomitour = num;
		System.out.println("Num domi clique: " + this.numdomitour);

	}
	
	public void display(Tuile Tuile, JButton T) {

		ImageIcon img = new ImageIcon(
				this.getClass().getResource("/" + Tuile.gettype() + Tuile.getnbcouronne() + ".png"));
		Image newimg = img.getImage().getScaledInstance(T.getWidth(), T.getHeight(), java.awt.Image.SCALE_SMOOTH);
		T.setIcon(new ImageIcon(newimg));
	}
	
	public void setNumTuileTour(int num) {
		this.numtuiletour = num;
		System.out.println("Num tuile clique: " + this.numtuiletour);

	}


	public void setOrdreJoueurs(Joueur[][] ordrejoueurs) {
		this.ordrejoueurs = ordrejoueurs;
	}
	
	public void setJoueurs(Joueur[][] joueurs) {
		this.joueurs = joueurs;
	}
	
	

	public void setBonChoix(boolean bonchoix) {
		this.bonchoix = bonchoix;
	}
	
	public int getPosX() {
		return this.posx;
	}
	
	public int getPosY() {
		return this.posy;
	}
	
	public void setPosX(int posx) {
		this.posx=posx;
	}
	
	public void setPosY(int posy) {
		this.posy=posy;
	}


	
	public void InitialiselesChampdeTexte() {
	PageAccueil.ChampJ1.setEnabled(false);
	PageAccueil.ChampJ1.setVisible(false);
	PageAccueil.ChampJ2.setEnabled(false);
	PageAccueil.ChampJ2.setVisible(false);
	PageAccueil.ChampJ3.setEnabled(false);
	PageAccueil.ChampJ3.setVisible(false);
	PageAccueil.ChampJ4.setEnabled(false);
	PageAccueil.ChampJ4.setVisible(false);
	}
	
	public void AfficherChampdeTexteAccueilSelonNbreJoueurs() {
		
		if (nbjoueurs==2) {
			PageAccueil.ChampJ1.setEnabled(true);
			PageAccueil.ChampJ1.setVisible(true);
			PageAccueil.ChampJ2.setEnabled(true);
			PageAccueil.ChampJ2.setVisible(true);
			PageAccueil.ChampJ3.setEnabled(false);
			PageAccueil.ChampJ3.setVisible(false);
			PageAccueil.ChampJ4.setEnabled(false);
			PageAccueil.ChampJ4.setVisible(false);
		}
		else if (nbjoueurs==3) { 
			PageAccueil.ChampJ1.setEnabled(true);
			PageAccueil.ChampJ1.setVisible(true);
			PageAccueil.ChampJ2.setEnabled(true);
			PageAccueil.ChampJ2.setVisible(true);
			PageAccueil.ChampJ3.setEnabled(true);
			PageAccueil.ChampJ3.setVisible(true);
			PageAccueil.ChampJ4.setEnabled(false);
			PageAccueil.ChampJ4.setVisible(false);
		}
		else if (nbjoueurs==4) {
			PageAccueil.ChampJ1.setEnabled(true);
			PageAccueil.ChampJ1.setVisible(true);
			PageAccueil.ChampJ2.setEnabled(true);
			PageAccueil.ChampJ2.setVisible(true);
			PageAccueil.ChampJ3.setEnabled(true);
			PageAccueil.ChampJ3.setVisible(true);
			PageAccueil.ChampJ4.setEnabled(true);
			PageAccueil.ChampJ4.setVisible(true);
		}
	}

	public void AjouterLesActionsListner() {
		PageAccueil.QuatreJoueurs.addActionListener((ActionListener) this);
		PageAccueil.DeuxJoueurs.addActionListener((ActionListener) this);
		PageAccueil.TroisJoueurs.addActionListener((ActionListener) this);
		PageJeu.btnRetournerAuMenu.addActionListener((ActionListener) this);
		PageAccueil.btnJouer.addActionListener((ActionListener) this);
		PageJeu.btnPioche.addActionListener((ActionListener) this);
	}

	public void setnbrois (int i) {
		this.nbrois = i;
	}

	public void DefinirNombreEcrans () {
		Icon drole = new ImageIcon(this.getClass().getResource("/giphy.gif"));
		Icon ImgIcon = new ImageIcon(this.getClass().getResource("/Giph.gif"));
		
		switch (String.valueOf(nbjoueurs)) {
		case "2":
			PageJeu.EcranSud.setBackground(new Color(0, 0, 0, 64));
			PageJeu.EcranSud.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128), 1, true), new LineBorder(new Color(0, 0, 0, 64), 4, true)));
			PageJeu.EcranSud.Nom.setBackground(new Color(0, 0, 0, 64));
			PageJeu.EcranSud.PlanCentral.setBackground(new Color(0, 0, 0, 64));
			PageJeu.EcranSud.removeAll();
			JLabel label1 = new JLabel(ImgIcon);
			PageJeu.EcranSud.add(label1);
			
			PageJeu.EcranEst.setBackground(new Color(0,0,0,64));
			PageJeu.EcranEst.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128), 1, true), new LineBorder(new Color(0,0,0,64), 4, true)));
			PageJeu.EcranEst.Nom.setBackground(new Color(0,0,0,64));
			PageJeu.EcranEst.PlanCentral.setBackground(new Color(0,0,0,64));
			PageJeu.EcranEst.removeAll();
			JLabel label = new JLabel(drole);
			PageJeu.EcranEst.add(label);
			break;
		case "3" :
			PageJeu.EcranSud.setBackground(new Color(0,0,0,64));
			PageJeu.EcranSud.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128), 1, true), new LineBorder(new Color(0,0,0,64), 4, true)));
			PageJeu.EcranSud.Nom.setBackground(new Color(0,0,0,64));
			PageJeu.EcranSud.PlanCentral.setBackground(new Color(0,0,0,64));
			PageJeu.EcranSud.removeAll();
			JLabel label2 = new JLabel(drole);
			PageJeu.EcranSud.add(label2);
			break;
		}
	}
	
	public void DefinirChateauPourTousEcran() {
		ImageIcon ChateauIcone = new ImageIcon(this.getClass().getResource("/chateau.png"));
		Image ChateauIconeResize = ChateauIcone.getImage().getScaledInstance(PageJeu.EcranEst.BtnChateau.getWidth(), PageJeu.EcranEst.BtnChateau.getHeight(), java.awt.Image.SCALE_SMOOTH);
		PageJeu.EcranOuest.BtnChateau.setIcon(new ImageIcon(ChateauIconeResize));
		PageJeu.EcranNord.BtnChateau.setIcon(new ImageIcon(ChateauIconeResize));
		PageJeu.EcranEst.BtnChateau.setIcon(new ImageIcon(ChateauIconeResize));
		PageJeu.EcranSud.BtnChateau.setIcon(new ImageIcon(ChateauIconeResize));
	}

	public void DefinitionFenetre () {
		FenetreJeu.setIconImage(new ImageIcon(this.getClass().getResource("/chateau.png")).getImage());
		FenetreJeu.setBounds(0, 0, 1440, 600);
		FenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FenetreJeu.getContentPane().setLayout(PlusieursPages);
		FenetreJeu.getContentPane().add(PageAccueil, listeIndice[0]);
		FenetreJeu.getContentPane().add(Panel, listeIndice[1]);
	}

	public void DefinitionPageCarreQuiContientPageJeu () {
		Panel.setLayout(recadrage);
		Panel.add(PageJeu);
		Panel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resizePreview(PageJeu, Panel);
			}
		});
	}

	public void AffecteAOrdrejoueursETauxEcransleNomdeChaqueJoueur() {
		ordrejoueurs[0][0].setNomJoueur(PageAccueil.R);
		ordrejoueurs[1][0].setNomJoueur(PageAccueil.J);
		if (nbjoueurs == 3) {
			ordrejoueurs[2][0].setNomJoueur(PageAccueil.V);
		}
		if (nbjoueurs == 4) {
			ordrejoueurs[2][0].setNomJoueur(PageAccueil.V);
			ordrejoueurs[3][0].setNomJoueur(PageAccueil.B);
		}
		
		PageJeu.EcranOuest.Nom.setText(PageAccueil.R);
		PageJeu.EcranNord.Nom.setText(PageAccueil.J);
		PageJeu.EcranEst.Nom.setText(PageAccueil.V);
		PageJeu.EcranSud.Nom.setText(PageAccueil.B);
	}

	public void ChoixDuNombreDeJoueurs(Object source) {
		PageAccueil.DeuxJoueurs.setSelected(false);
		PageAccueil.TroisJoueurs.setSelected(false);
		PageAccueil.QuatreJoueurs.setSelected(false);
		((JRadioButton) source).setSelected(true);
	}
	
	public void ReduireA3JoueursSiBesoin() {
		if (nbrois==3) {
			PageJeu.ListTour1.get(7).setContentAreaFilled(false);
			PageJeu.ListTour1.get(7).setBorderPainted(false); 
			PageJeu.ListTour1.get(7).setFocusPainted(false);
			PageJeu.ListTour1.get(6).setContentAreaFilled(false);
			PageJeu.ListTour1.get(6).setBorderPainted(false); 
			PageJeu.ListTour1.get(6).setFocusPainted(false);
			PageJeu.List1BoutonRoi.get(3).setContentAreaFilled(false);
			PageJeu.List1BoutonRoi.get(3).setBorderPainted(false); 
			PageJeu.List1BoutonRoi.get(3).setFocusPainted(false);
			
			PageJeu.ListTour1.remove(7);
			PageJeu.ListTour1.remove(6);
			PageJeu.List1BoutonRoi.remove(3);
			
			PageJeu.ListTour2.get(1).setContentAreaFilled(false);
			PageJeu.ListTour2.get(1).setBorderPainted(false); 
			PageJeu.ListTour2.get(1).setFocusPainted(false);
			PageJeu.ListTour2.get(0).setContentAreaFilled(false);
			PageJeu.ListTour2.get(0).setBorderPainted(false); 
			PageJeu.ListTour2.get(0).setFocusPainted(false);
			PageJeu.List2BoutonRoi.get(0).setContentAreaFilled(false);
			PageJeu.List2BoutonRoi.get(0).setBorderPainted(false); 
			PageJeu.List2BoutonRoi.get(0).setFocusPainted(false);
			
			PageJeu.ListTour2.remove(1);
			PageJeu.ListTour2.remove(0);
			PageJeu.List2BoutonRoi.remove(0);


		}
	}
	public void DefinitNbreJoueursEnFonctionDuChoix(Object source) {
		switch (((JRadioButton) source).getText()) {
		case "2 joueurs":
			nbjoueurs = 2;
			break;
		case "3 joueurs":
			nbjoueurs = 3;
			break;
		case "4 joueurs":
			nbjoueurs = 4;
			break;
		}
	}
	
	public void setnbjoueursAffichageJeu(int i) {
		i = this.nbjoueurs;
	}
}
	
class Cote extends JPanel {
	public Cote() {
	}

	public void paintComponent(Graphics g) {
		// x1, y1, width, height, arcWidth, arcHeight
		try {
			Image img = ImageIO.read(this.getClass().getResource("/Rid.jpg"));
			// g.drawImage(img, 0, 0, this);
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


