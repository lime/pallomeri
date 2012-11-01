package pallomeri;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PGraphicsJava2D;
import processing.core.PImage;

public class Valikko {
	
	private PGraphics g;
	private Pallomeri pallomeri;
	
	public Valikko(int width, int height, Pallomeri p) {
		this.pallomeri = p;
		g = this.pallomeri.createGraphics(width, height, PConstants.JAVA2D);
	}

	/**
	 * Tässä metodissa voi tehdä muutoksia valikolle
	 */
	public void render() {
		g.beginDraw(); // aloita

		g.background(pallomeri.color(58, 63, 64));
		
		PImage esikatselukuva = this.pallomeri.annaLukija().annaKuva();
		esikatselukuva.resize(this.annaLeveys()-20, 0);
		g.image(esikatselukuva, 10, 10);

		g.endDraw(); // lopeta
	}

	public PImage getGraphics() {
		return g;
	}
	
	public int annaLeveys() {
		return g.width;
	}

	public int annaKorkeus() {
		return g.height;
	}
}
