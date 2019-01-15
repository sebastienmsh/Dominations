import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

public class Affichage implements ActionListener {

	Icon imgIcon = new ImageIcon(this.getClass().getResource("/giphy.gif"));
	Icon ImgIcon = new ImageIcon(this.getClass().getResource("/Giph.gif"));
	
	// vers Jeu
	int numdomitour = 0;
	int numtuiletour = 0;
	Tuile choixtuiletour;
	Joueur joueurencours;

	// provenant de Jeu
	int nbjoueurs = 0;
	int nbrois = 0;
	public Joueur[][] ordrejoueurs = new Joueur[4][2];
	boolean bonchoix;

	int compteurpioche = 0;
	public int compteurjoueur = 0;
	public int compteurclictuile =0;

	String NombreDeJoueursString = String.valueOf(nbjoueurs);
	JLabel lblNombreDeJoueurs = new JLabel("Nombre de joueurs :" + nbjoueurs + ", veuillez choisir ...");

	JFrame FenetreJeu = new JFrame("Projet Kingdomino");
	Cote Panel = new Cote();

	AffichageJeu PageJeu = new AffichageJeu();
	AffichageAccueil PageAccueil = new AffichageAccueil();

	JPanel pane = new JPanel();
	JPanel pan = new JPanel();

	JPanel pa = new JPanel();
	CardLayout PlusieursPages = new CardLayout(0, 0);
	GridBagLayout recadrage = new GridBagLayout();
	String[] listeIndice = { "Accueil", "Principal" };

	JComboBox<String> SelectionDefilante = new JComboBox<String>();
	int ValeurSelection = 0;

	public Tuile[][] dominostour = new Tuile[4][2];

	public ArrayList<JButton> ListTour1 = new ArrayList<>();
	public ArrayList<JButton> ListTour2 = new ArrayList<>();
	public ArrayList<JButton> List1BoutonRoi = new ArrayList<>();
	public ArrayList<JButton> List2BoutonRoi = new ArrayList<>();

	private final JSeparator separator = new JSeparator();
	private final JSeparator separator1 = new JSeparator();
	private final JSeparator separator2 = new JSeparator();
	private final JSeparator separator3 = new JSeparator();
	private final JSeparator separator4 = new JSeparator();
	private final JSeparator separator5 = new JSeparator();

	public Affichage() {

		FenetreJeu.setIconImage(new ImageIcon(this.getClass().getResource("/chateau.png")).getImage());
		FenetreJeu.setBounds(0, 0, 1440, 900);
		FenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FenetreJeu.getContentPane().setLayout(PlusieursPages);

		FenetreJeu.getContentPane().add(PageAccueil, listeIndice[0]);
		FenetreJeu.getContentPane().add(Panel, listeIndice[1]);

		SelectionDefilante.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Choissisez votre Terrain...", "Terrain 1", "Terrain 2", "Terrain 3", "Terrain 4" }));
		PageJeu.SudOuest.add(SelectionDefilante);

		Panel.setLayout(recadrage);
		Panel.add(PageJeu);
		Panel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resizePreview(PageJeu, Panel);
			}
		});

		PageAccueil.QuatreJoueurs.addActionListener((ActionListener) this);
		PageAccueil.DeuxJoueurs.addActionListener((ActionListener) this);
		PageAccueil.TroisJoueurs.addActionListener((ActionListener) this);
		PageJeu.btnRetournerAuMenu.addActionListener((ActionListener) this);
		PageAccueil.btnJouer.addActionListener((ActionListener) this);
		PageJeu.btnPioche.addActionListener((ActionListener) this);
		
		//PageAccueil.add(PageAccueil.ChampJ1);
		//PageAccueil.add(PageAccueil.ChampJ2);
		//PageAccueil.ChampJ1.addActionListener((ActionListener) this);
		//PageAccueil.ChampJ2.addActionListener((ActionListener) this);
		//ageAccueil.ChampJ3.addActionListener((ActionListener) this);
		//PageAccueil.ChampJ4.addActionListener((ActionListener) this);	
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

	public void setnbrois(int n) {
		this.nbrois = n;
	}

	public ActionListener AjoutDominoTerrain = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

		}
	};

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == PageAccueil.QuatreJoueurs || source == PageAccueil.TroisJoueurs
				|| source == PageAccueil.DeuxJoueurs) {
			PageAccueil.lblNombreDeJoueurs.setText("Vous êtes " + ((JRadioButton) source).getText());
			PageAccueil.DeuxJoueurs.setSelected(false);
			PageAccueil.TroisJoueurs.setSelected(false);
			PageAccueil.QuatreJoueurs.setSelected(false);
			((JRadioButton) source).setSelected(true);

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
			PageJeu.lblNbreJoueurs.setText("Partie à " + ((JRadioButton) source).getText());
			/*
			if (nbjoueurs==3) {
				PageAccueil.remove(PageAccueil.ChampJ4);
				PageAccueil.add(PageAccueil.ChampJ3);
			}
			else if (nbjoueurs==4) {
				PageAccueil.add(PageAccueil.ChampJ3);
				PageAccueil.add(PageAccueil.ChampJ4);
			}
			else {
				PageAccueil.remove(PageAccueil.ChampJ4);
				PageAccueil.remove(PageAccueil.ChampJ3);
			}
			*/
			System.out.println(nbjoueurs);
			lblNombreDeJoueurs.setText("Nombre de joueurs :" + nbjoueurs + ", veuillez choisir ...");
		}

		if (source == PageJeu.btnRetournerAuMenu) {
			PlusieursPages.show(FenetreJeu.getContentPane(), listeIndice[0]);
		}

		if (source == PageAccueil.btnJouer) {

			Principal.initialisation();
			
			ordrejoueurs[0][0].setNomJoueur(PageAccueil.J);
			ordrejoueurs[1][0].setNomJoueur(PageAccueil.B);
			ordrejoueurs[2][0].setNomJoueur(PageAccueil.R);
			ordrejoueurs[3][0].setNomJoueur(PageAccueil.V);
			PageJeu.EcranOuest.Nom.setText(PageAccueil.J);
			PageJeu.EcranNord.Nom.setText(PageAccueil.B);
			PageJeu.EcranEst.Nom.setText(PageAccueil.R);
			PageJeu.EcranSud.Nom.setText(PageAccueil.V);
			
			/*
			for (int i = 0; i < nbrois; i++) {

				// demande nom des joueurs
				System.out.println("Entrez le nom du joueur: ");
				Scanner scanner = new Scanner(System.in);
				String username = scanner.nextLine();
				ordrejoueurs[i][0].setNomJoueur(username);

			}*/

			PlusieursPages.show(FenetreJeu.getContentPane(), listeIndice[1]);
			
			ImageIcon ChateauIcone = new ImageIcon(this.getClass().getResource("/chateau.png"));
			Image ChateauIconeResize = ChateauIcone.getImage().getScaledInstance(PageJeu.EcranSud.BtnChateau.getWidth(), PageJeu.EcranSud.BtnChateau.getHeight(), java.awt.Image.SCALE_SMOOTH);
	        PageJeu.EcranOuest.BtnChateau.setIcon(new ImageIcon(ChateauIconeResize));
	        PageJeu.EcranNord.BtnChateau.setIcon(new ImageIcon(ChateauIconeResize));
	        PageJeu.EcranEst.BtnChateau.setIcon(new ImageIcon(ChateauIconeResize));
	        PageJeu.EcranSud.BtnChateau.setIcon(new ImageIcon(ChateauIconeResize));

			for (int i = 0; i < 6; i++) {
				PageJeu.Centre.add(new JLabel());
			}

			for (int i = 0; i < nbrois; i++) {
				PanelRoi R = new PanelRoi();
				JButton NouvelleTuile1 = new JButton();
				JButton NouvelleTuile2 = new JButton();

				PageJeu.Centre.add(R);
				PageJeu.Centre.add(NouvelleTuile1);
				PageJeu.Centre.add(NouvelleTuile2);

				ListTour1.add(NouvelleTuile1);
				ListTour1.add(NouvelleTuile2);
				List1BoutonRoi.add(R.Roibtn);
			}

			separator.setBackground(new Color(102, 0, 51));
			separator1.setBackground(new Color(102, 0, 51));
			separator2.setBackground(new Color(102, 0, 51));
			separator3.setBackground(new Color(102, 0, 51));
			separator4.setBackground(new Color(102, 0, 51));
			separator5.setBackground(new Color(102, 0, 51));

			if (nbrois==3) {
				for (int i=0;i<3;i++) {
					PageJeu.Centre.add(new JLabel());
				}
				PageJeu.Centre.add(separator);
				PageJeu.Centre.add(separator1);
				PageJeu.Centre.add(separator2);
				PageJeu.Centre.add(separator3);
				PageJeu.Centre.add(separator4);
				for (int j=0; j<21-(6*nbrois); j++) {
					PageJeu.Centre.add(new JLabel());
				}
			}
			else {
				PageJeu.Centre.add(separator);
				PageJeu.Centre.add(separator1);
				PageJeu.Centre.add(separator2);
				PageJeu.Centre.add(separator3);
				PageJeu.Centre.add(separator4);
				PageJeu.Centre.add(separator5);
				for (int i=0; i<24-(6*nbrois); i++) {
					PageJeu.Centre.add(new JLabel());
				}
			}

			
			for (int i = 0; i < nbrois; i++) {
				PanelRoi R = new PanelRoi();
				JButton NouvelleTuile1 = new JButton();
				JButton NouvelleTuile2 = new JButton();

				PageJeu.Centre.add(R);
				PageJeu.Centre.add(NouvelleTuile1);
				PageJeu.Centre.add(NouvelleTuile2);

				ListTour2.add(NouvelleTuile1);
				ListTour2.add(NouvelleTuile2);
				List2BoutonRoi.add(R.Roibtn);
			}
			
			switch (String.valueOf(nbjoueurs)) {
			case "2" :
				PageJeu.EcranSud.setBackground(new Color(0,0,0,64));
				PageJeu.EcranSud.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128), 1, true), new LineBorder(new Color(0,0,0,64), 4, true)));
				PageJeu.EcranSud.Nom.setBackground(new Color(0,0,0,64));
				PageJeu.EcranSud.PlanCentral.setBackground(new Color(0,0,0,64));
				PageJeu.EcranSud.removeAll();
				JLabel label1 = new JLabel(ImgIcon);
				PageJeu.EcranSud.add(label1);
				PageJeu.EcranEst.setBackground(new Color(0,0,0,64));
				PageJeu.EcranEst.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128), 1, true), new LineBorder(new Color(0,0,0,64), 4, true)));
				PageJeu.EcranEst.Nom.setBackground(new Color(0,0,0,64));
				PageJeu.EcranEst.PlanCentral.setBackground(new Color(0,0,0,64));
				PageJeu.EcranEst.removeAll();
				JLabel label = new JLabel(imgIcon);
				PageJeu.EcranEst.add(label);
				break;
			case "3" :
				PageJeu.EcranSud.setBackground(new Color(0,0,0,64));
				PageJeu.EcranSud.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128), 1, true), new LineBorder(new Color(0,0,0,64), 4, true)));
				PageJeu.EcranSud.Nom.setBackground(new Color(0,0,0,64));
				PageJeu.EcranSud.PlanCentral.setBackground(new Color(0,0,0,64));
				PageJeu.EcranSud.removeAll();
				JLabel label2 = new JLabel(imgIcon);
				PageJeu.EcranNord.add(label2);
				break;
			}

		}

		if (source == PageJeu.btnPioche) {
			if (compteurpioche == 0) {

				

				for (int k = 0; k < ListTour1.size() / 2; k++) {

					JButton jButton1 = ListTour1.get(2 * k);
					JButton jButton2 = ListTour1.get(2 * k + 1);

					int numdomituile1 = dominostour[k][0].getnumdomi();
					int numdomituile2 = dominostour[k][1].getnumdomi();
					
					Tuile tuile1 = dominostour[k][0];
					Tuile tuile2 = dominostour[k][1];


					display(dominostour[k][0], jButton1);
					display(dominostour[k][1], jButton2);

					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Object source = e.getSource();
							if (compteurclictuile == 0) {
							if (compteurjoueur < nbrois) {
								setJoueurEnCours(ordrejoueurs[compteurjoueur][0]);
								
								/*
								setNumDomiTour(numdomituile1);
								setNumTuileTour(0);
								*/
								
								setChoixTuileTour(tuile1);
								
								Principal.preselection();
								if (bonchoix==true) {
									compteurjoueur += 1;

								}
								else if (bonchoix==false){
									System.out.println("\nCe domino a deja ete selectionne !");
								}
								

								if (compteurjoueur >= nbrois) {
									System.out.println("Il faut piocher � nouveau");
									appelDefinitionNouvelOrdre();
									System.out.println("\nC'est a " + ordrejoueurs[0][0].getNomJoueur() + " de jouer.");
									compteurclictuile=1;
									compteurjoueur=0;
								} else {
									
									

									System.out.println("\nC'est a " + ordrejoueurs[compteurjoueur][0].getNomJoueur() + " de jouer");

									

								}

							} else {
								System.out.println("Il faut piocher � nouveau 2");
								appelDefinitionNouvelOrdre();
								System.out.println("\nC'est a " + ordrejoueurs[0][0].getNomJoueur() + " de jouer.");
								compteurclictuile=1;
								compteurjoueur=0;
							}
							}
							else if (compteurclictuile == 1){
								
								if (compteurjoueur < nbrois) {
									setJoueurEnCours(ordrejoueurs[compteurjoueur][0]);
									
					
									
									setChoixTuileTour(tuile1);
									
									Principal.tourJeu();
									
									if (bonchoix==true) {
										compteurjoueur += 1;

									}
									else if (bonchoix==false){
										System.out.println("\nCe domino a deja ete selectionne !");
									}
									

									if (compteurjoueur >= nbrois) {
										System.out.println("Il faut piocher � nouveau");
										appelDefinitionNouvelOrdre();
									} else {
										
										

										System.out.println("\nC'est a " + ordrejoueurs[compteurjoueur][0].getNomJoueur() + " de jouer");

										

									}

								}
							
							}
							
					

						}
					});
					// pour bouton tuile2 excatement la meme qu'au dessus
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Object source = e.getSource();
							
							if (compteurjoueur < nbrois) {
								setJoueurEnCours(ordrejoueurs[compteurjoueur][0]);
								
								/*
								setNumDomiTour(numdomituile1);
								setNumTuileTour(0);
								*/
								
								setChoixTuileTour(tuile2);
								
								Principal.preselection();
								if (bonchoix==true) {
									compteurjoueur += 1;

								}
								else if (bonchoix==false){
									System.out.println("\nCe domino a deja ete selectionne !");
								}
								

								if (compteurjoueur >= nbrois) {
									System.out.println("Il faut piocher � nouveau");
									appelDefinitionNouvelOrdre();
									
									
									
									
								} else {

									System.out.println("\nC'est a " + ordrejoueurs[compteurjoueur][0].getNomJoueur() + " de jouer");

									

								}

							} else {
								System.out.println("Il faut piocher � nouveau 2");
								appelDefinitionNouvelOrdre();
								
							}
							
							
							
							
							
							
						}
					});

				}
				
				


			}

			else if (compteurpioche == 1) {

				// Retour couleur selectionn�e

			}

			else if (compteurpioche == 2) {

			}

		}
		
		/*
		if (source==PageAccueil.ChampJ2) {
			String input = ((JTextComponent) e.getSource()).getText();
			PageJeu.B=input; //Nord 
		}
		if (source==PageAccueil.ChampJ3) {
			String input = ((JTextComponent) e.getSource()).getText();
			PageJeu.Y=input; //Est
		}
		if (source==PageAccueil.ChampJ4) {
			String input = ((JTextComponent) e.getSource()).getText();
			//PageJeu.EcranEst.Nom.setText(input); 
			PageJeu.V=input;  //Sud

		}/*/

	}
	
	public void setRoiTour1 (String clr, Tuile T) {
		Color Couleur=Color.WHITE;
		switch (clr) {
		case "Vert" :
			Couleur = Color.GREEN;
			break;
		case "Bleu" :
			Couleur = Color.BLUE;
			break;
		case "Rouge" :
			Couleur = Color.RED;
			break;
		case "Jaune" :
			Couleur = Color.YELLOW;
			break;
		}
		for (int j=0; j<List1BoutonRoi.size();j++) {
			if (T.getnumdomi()==dominostour[j][0].getnumdomi()) { 
			 List1BoutonRoi.get(j).setBackground(Couleur);
			 List1BoutonRoi.get(j).setForeground(Color.WHITE);
			 List1BoutonRoi.get(j).setText("R");
			}
		}	
	}
	
	
	public void appelDefinitionNouvelOrdre() {
		//definition nouvel ordre
		Principal.definirordre();
		
		System.out.println("Ceci est un nouvel ordre de joueurs :\n");
		for (int j = 0; j <= ordrejoueurs.length - 1; j++) {
		System.out.println(ordrejoueurs[j][0].nomjoueur);
		}
	}

	public void setNumDomiTour(int num) {
		this.numdomitour = num;
		System.out.println("Num domi clique: " + this.numdomitour);

	}

	public void setNumTuileTour(int num) {
		this.numtuiletour = num;
		System.out.println("Num tuile clique: " + this.numtuiletour);

	}
	
	public void setChoixTuileTour(Tuile tuile) {
		this.choixtuiletour = tuile;

	}

	public void setOrdreJoueurs(Joueur[][] ordrejoueurs) {
		this.ordrejoueurs = ordrejoueurs;
	}

	public void setJoueurEnCours(Joueur joueur) {
		this.joueurencours = joueur;
	}

	public Joueur getJoueurEnCours() {
		return this.joueurencours;
	}
	
	public Tuile getChoixTuileTour() {
		return this.choixtuiletour;
	}
	
	public void setBonChoix(boolean bonchoix) {
		this.bonchoix=bonchoix;
	}
	
	public void display(Tuile Tuile, JButton T) {

		ImageIcon img = new ImageIcon(this.getClass().getResource("/"+Tuile.gettype()+Tuile.getnbcouronne()+".png"));
		Image newimg = img.getImage().getScaledInstance(T.getWidth(), T.getHeight(), java.awt.Image.SCALE_SMOOTH);
		T.setIcon(new ImageIcon(newimg));
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

class PanelRoi extends JPanel {
	private final JLabel lblNewLabel1 = new JLabel();
	private final JLabel lblNewLabel2 = new JLabel();
	private final JLabel lblNewLabel3 = new JLabel();
	private final JLabel lblNewLabel4 = new JLabel();
	private final JLabel lblNewLabel5 = new JLabel();
	private final JLabel lblNewLabel6 = new JLabel();
	private final JLabel lblNewLabel7 = new JLabel();
	private final JLabel lblNewLabel8 = new JLabel();
	JButton Roibtn = new JButton();

	public PanelRoi() {
		setLayout(new GridLayout(3, 3, 0, 0));
		this.add(lblNewLabel1);
		this.add(lblNewLabel2);
		this.add(lblNewLabel3);
		this.add(lblNewLabel4);
		this.add(Roibtn);
		this.add(lblNewLabel5);
		this.add(lblNewLabel6);
		this.add(lblNewLabel7);
		this.add(lblNewLabel8);
	}
}
