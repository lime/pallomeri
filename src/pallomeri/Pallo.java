package pallomeri;

import java.awt.Color;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Pallo {

	// Ominaisuudet
	private float x, y;
	private float loppuX, loppuY;
	private int vari;
	private Pallomeri pallomeri;
	
	public Pallo(int x, int y, int color, Pallomeri p) {
		this.pallomeri = p;
		this.x = pallomeri.randomSijainti().x;
		this.y = pallomeri.randomSijainti().y;
		this.loppuX = x * Asetukset.SKAALA;
		this.loppuY = y * Asetukset.SKAALA;
		this.vari = color;
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
		
		/*if(pallomeri.mousePressed) { // pallo voisi liikkua lähemmäs hiirtä
			this.x += laskeSiirto(x, pallomeri.mouseX);
			this.y += laskeSiirto(y, pallomeri.mouseY);
		}*/

	}

	private double laskeSiirto(float koordinaatti, float paamaara) {
		// lasketaan matkan pituus toivottuun päämäärään
		double matka = paamaara - koordinaatti;
		double siirto;

		siirto = matka * Asetukset.VAUHTI; // hidastuu loppuun päin
		return siirto;
	}

	public void piirra() {
		pallomeri.fill(this.vari);
		pallomeri.ellipse(this.x, this.y, Asetukset.PALLOJEN_KOKO, Asetukset.PALLOJEN_KOKO);
	}

}
