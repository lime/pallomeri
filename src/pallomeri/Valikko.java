package pallomeri;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PGraphicsJava2D;
import processing.core.PImage;

public class Valikko {
	
	private PGraphics g;
	private Pallomeri applet;
	final float width, height;
	
	public Valikko(int width, int height, Pallomeri applet) {
		this.width = width;
		this.height = height;
		this.applet = applet;
		g = applet.createGraphics(width, height, PConstants.JAVA2D);
	}

	/**
	 * Tässä metodissa voi tehdä muutoksia valikolle
	 */
	public void render() {
		g.beginDraw(); // aloita

		g.background(applet.color(58, 63, 64));
		
		g.image(this.applet.annaLukija().annaKuva(), 10, 10);

		g.endDraw(); // lopeta
	}

	public PImage getGraphics() {
		return g;
	}
}
