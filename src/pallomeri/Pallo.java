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

	// Liike
	private final double liike = 0.1;
	private double kuljettuMatka;
	private double matkaY, matkaX;
	
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

	public void liiku() {
		
		matkaX = loppuX - x;
		matkaY = loppuY - y;
		
		this.x += liike * matkaX;
		this.y += liike * matkaY;

		/*float eksponentti = 4;

		kuljettuMatka = 0.0;

		kuljettuMatka += liike;

		if (kuljettuMatka < 1.0) {

			this.x += (kuljettuMatka * matkaX);
			this.y += (Math.pow(kuljettuMatka, eksponentti) * matkaY);

		} else {
			kuljettuMatka = 0.0;
			matkaX = loppuX - x;
			matkaY = loppuY - y;
		}*/

	}

	public void piirra(PApplet applet) {
		applet.fill(this.vari);
		applet.ellipse(this.x, this.y, this.koko, this.koko);
	}

	/*
	 * void setup() {
	 * 
	 * size(400, 400); noStroke(); smooth();
	 * 
	 * matkaX = loppuX - alkuX; matkaY = loppuY - alkuY;
	 * 
	 * }
	 * 
	 * 
	 * void draw() {
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * void mousePressed() {
	 * 
	 * kuljettuMatka = 0.0; alkuX = x; alkuY = y; loppuX = mouseX; loppuY =
	 * mouseY; matkaX = loppuX - alkuX; matkaY = loppuY - alkuY;
	 * 
	 * }
	 */

}
