package pallomeri;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PGraphicsJava2D;
import processing.core.PImage;

public class Valikko {
	
	private PGraphics g;
	private Pallomeri pallomeri;
	private PImage esikatselukuva;
	private float nakymattomyys;
	
	public Valikko(int width, int height, Pallomeri p) {
		this.pallomeri = p;
		g = this.pallomeri.createGraphics(width, height, PConstants.JAVA2D);
		this.nakymattomyys = 10;
	}

	/**
	 * Tässä metodissa voi tehdä muutoksia valikolle
	 */
	public void render() {
		g.beginDraw(); // aloita

		g.background(pallomeri.color(58, 63, 64));
		
		if(this.hiiriKuvanpaalla() && this.nakymattomyys > 0) {
			esikatselukuva.filter(PConstants.BLUR, 1);
		}
		else if (this.nakymattomyys < 10) {
			esikatselukuva.filter(PConstants.BLUR, -1);
		}
		

		
		g.image(esikatselukuva, 10, 10);
		

		g.endDraw(); // lopeta
	}

	void paivitaKuva() {
		esikatselukuva = this.pallomeri.annaLukija().annaKuva();
		esikatselukuva.resize(this.annaLeveys()-20, 0);
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
	
	public boolean hiiriKuvanpaalla() {
		return this.pallomeri.dist(10+this.pallomeri.stageLeveys(), 10, this.pallomeri.mouseX, this.pallomeri.mouseY) < 20;
			
	}
}
