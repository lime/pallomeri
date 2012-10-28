package pallomeri;

import java.awt.Color;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Pallo {

	// Ominaisuudet
	private float x, y;
	private float loppuX, loppuY;
	private int vari;
	private float koko;
	
	// Vakiot
	private static final float SKAALA = 3.0f;

	public Pallo(int x, int y, int color) {
		this.x = Pallomeri.randomSijainti().x;
		this.y = Pallomeri.randomSijainti().y;
		this.loppuX = x * SKAALA;
		this.loppuY = y * SKAALA;
		this.vari = color;
		this.koko = Pallomeri.PIXEL_ASKEL*2; // TODO jonkun arvon perusteella?
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void liiku(PApplet applet) {
		
		this.x += laskeSiirto(x, loppuX);
		this.y += laskeSiirto(y, loppuY);
		
		if(applet.mousePressed) {
			this.x += laskeSiirto(x, applet.mouseX);
			this.y += laskeSiirto(y, applet.mouseY);
		}

	}

	private double laskeSiirto(float koordinaatti, float paamaara) {
		// lasketaan matkan pituus toivottuun päämäärään
		double matka = paamaara - koordinaatti;
		// 0.01 * matka // tietty osa jäljellä olevasta matkasta
		return matka / 300.0;
	}

	public void piirra(PApplet applet) {
		applet.fill(this.vari);
		applet.ellipse(this.x, this.y, this.koko, this.koko);
	}

}
