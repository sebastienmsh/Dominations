import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO; 

public class AffichagePanelDeJeu4 extends JPanel {
	

	public AffichagePanelDeJeu4() {
	
	}
	public void paintComponent(Graphics g){
		    //x1, y1, width, height, arcWidth, arcHeight
		    try {
		    	File chemin = new File ("//Users/lebens/Desktop/Dominations/images/--SE.jpg");
		        Image img = ImageIO.read(chemin);
		        //g.drawImage(img, 0, 0, this);
		        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		      } catch (IOException e) {
		        e.printStackTrace();
		      }
		  }          
}