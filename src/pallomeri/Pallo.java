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
	private Pallomeri pallomeri;
	
	// Vakiot
	private static final float SKAALA = 3.0f;

	public Pallo(int x, int y, int color, Pallomeri p) {
		this.pallomeri = p;
		this.x = pallomeri.randomSijainti().x;
		this.y = pallomeri.randomSijainti().y;
		this.loppuX = x * SKAALA;
		this.loppuY = y * SKAALA;
		this.vari = color;
		this.koko = pallomeri.PIXEL_ASKEL*2; // TODO jonkun arvon perusteella?
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void liiku() {
		
		this.x += laskeSiirto(x, loppuX);
		this.y += laskeSiirto(y, loppuY);
		
		if(pallomeri.mousePressed) {
			this.x += laskeSiirto(x, pallomeri.mouseX);
			this.y += laskeSiirto(y, pallomeri.mouseY);
		}

	}

	private double laskeSiirto(float koordinaatti, float paamaara) {
		// lasketaan matkan pituus toivottuun päämäärään
		double matka = paamaara - koordinaatti;
		double siirto;

		siirto = matka / 300; // hidastuu loppuun päin
		//siirto = matka / Math.pow(matka/5, 4);
		return siirto;
	}

	public void piirra() {
		pallomeri.fill(this.vari);
		pallomeri.ellipse(this.x, this.y, this.koko, this.koko);
	}

}
