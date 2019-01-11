import java.util.ArrayList;

public class Regles {
	
	
	public static boolean placementTuile(Tuile tuile, Terrain terrain) {
		boolean valide = true;
		int i = 0;
		int j =0;
		while (i <= 8) {
			while (j <= 8) {
				if (tuile.gettype() == terrain.terrain[i][j].gettype())
					j++;
			}
			i++;
		}
		return valide;
	}

	public static boolean isTuileVide(int posx, int posy, Terrain terrain) {
		return terrain.terrain[posx][posy] == null;
	}
	
	public static boolean isTuileDroiteVide(int posx, int posy, Terrain terrain) {
		return terrain.terrain[posx+1][posy] == null;
	}
	
	public static boolean isTuileGaucheVide(int posx, int posy, Terrain terrain) {
		return terrain.terrain[posx-1][posy] == null;
	}
	
	public static boolean isTuileHautVide(int posx, int posy, Terrain terrain) {
		return terrain.terrain[posx][posy+1] == null;
	}
	
	public static boolean isTuileBasVide(int posx, int posy, Terrain terrain) {
		return terrain.terrain[posx-1][posy-1] == null;
	}

}
