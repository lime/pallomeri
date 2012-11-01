package pallomeri;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class Kuvanlukija {
	/**
	 * 
	 * @param kuvannimi
	 *            tiedoston nimi, joka pit�isi lukea
	 * @param pallomeri
	 *            se processing-applet jonka yhteydessä ollaan
	 */

	PImage img;
	Pallomeri pallomeri;
	public static final String albumikuva[] =
	{"data/Mona_Lisa.jpg", "data/nelson.jpg"};

	public Kuvanlukija(String kuvannimi, Pallomeri p) {
		this.pallomeri = p;
		if (kuvannimi == null) {
			kuvannimi = arvokuvataulukosta();
		}
		this.img = pallomeri.loadImage(kuvannimi);

		pallomeri.loadPixels();
		// ladataan saadun kuvan pikselit pixels[]-jonoon
		img.loadPixels();

	}
	private String arvokuvataulukosta() {
		return albumikuva [new Random().nextInt(albumikuva.length)];
	}

	public int annaLeveys() {
		return img.width;
	}

	public int annaKorkeus() {
		return img.height;
	}
	
	public PImage annaKuva() {
		return this.img;
	}

	public Pallo luePikseli(int x, int y) {

		// Tutkitaan saaduista koordinaateista v�riarvot

		int loc = x + y * img.width;

		// otetaan väriarvot talteen pixels[]-jonosta
		/*
		 * float r = applet.red(img.pixels[loc]); float g =
		 * applet.green(img.pixels[loc]); float b =
		 * applet.blue(img.pixels[loc]);
		 */

		// jos rgb-arvot kunnossa palautetaan sijainti ja väriarvot, muuten null
		if (loc >= 0 && loc < img.pixels.length) {
			return new Pallo(x, y, img.pixels[loc], this.pallomeri);
		} else {
			System.err.println("Koordinaateissa "+x+","+y+" ei ole pikseleitä.");
			return null;
		}

	}

}
