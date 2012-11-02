package pallomeri;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class Kuvanlukija {
	/**
	 * 
	 * @param kuvannimi
	 *            tiedoston nimi, joka pitäisi lukea
	 * @param pallomeri
	 *            se processing-applet jonka yhteydessä ollaan
	 */

	private PImage img;
	private Pallomeri pallomeri;
	private int xSiirto, ySiirto;

	public Kuvanlukija(Pallomeri p) {
		this(Albumi.arvokuva().annakuvannimi(), p);
	}

	public Kuvanlukija(String kuvannimi, Pallomeri p) {
		// kutsu toinen konstruktori kuvalla, lataa kuvan ensin apumetodilla
		this(lataaKuvaKoneelta(kuvannimi, p), p);
	}

	private static PImage lataaKuvaKoneelta(String kuvannimi, Pallomeri p) {
		// jos null niin arvotaan valmiista
		if (kuvannimi == null) {
			kuvannimi = Albumi.arvokuva().annakuvannimi();
		}
		return p.loadImage(kuvannimi);
	}

	// Kuvanlukija, joka luodaan raahatun kuvan yhteydessä
	public Kuvanlukija(PImage img, Pallomeri p) {
		this.pallomeri = p;
		this.img = img;

		// ladataan saadun kuvan pikselit pixels[]-jonoon
		this.pallomeri.loadPixels();
		this.img.loadPixels();

		// laske skaala uusiksi
		this.laskeSkaala();
	}

	public int annaKuvanLeveys() {
		return img.width;
	}

	public int annaKuvanKorkeus() {
		return img.height;
	}

	public int annaKuvanPidempiReuna() {
		return Math.max(img.height, img.width);
	}

	public PImage annaKuva() {
		return this.img;
	}

	public Pallo luePikseli(int x, int y) {

		// Tutkitaan saaduista koordinaateista väriarvot

		int loc = x + y * img.width;

		// otetaan väriarvot talteen pixels[]-jonosta
		/*
		 * float r = applet.red(img.pixels[loc]); float g =
		 * applet.green(img.pixels[loc]); float b =
		 * applet.blue(img.pixels[loc]);
		 */

		// jos rgb-arvot kunnossa palautetaan sijainti ja väriarvot, muuten null
		if (loc >= 0 && loc < img.pixels.length) {
			return new Pallo(x - xSiirto, y - ySiirto, img.pixels[loc],
					this.pallomeri);
		} else {
			System.err.println("Koordinaateissa " + x + "," + y
					+ " ei ole pikseleitä.");
			return null;
		}

	}

	public void laskeSkaala() {

		// asetetaan sopivin skaala niin että mahtuu
		Asetukset.SKAALA = this.pallomeri.stageLyhinReuna()
				/ (float) this.annaKuvanPidempiReuna();

		// lasketaan vielä keskittämiseen vaaditut siirrot
		this.xSiirto = (int) ((this.annaKuvanLeveys() * Asetukset.SKAALA - this.pallomeri
				.stageLeveys()) / 4);
		this.ySiirto = (int) ((this.annaKuvanKorkeus() * Asetukset.SKAALA - this.pallomeri
				.stageKorkeus()) / 4);

		System.out.println("Kuvanlukija.laskeSkaala() " + Asetukset.SKAALA);
	}

	float laskePikseliVali() {
		// kuvan leveys skaalattuna sopivaksi
		float pikseliVali = Asetukset.PALLOJEN_VALI / Asetukset.SKAALA;
		// pallojen määrä yhdessä suunnassa määrää miten pitkälle pitää hypätä
		//pikseliVali /= Asetukset.PALLOJEN_MAARA;

		System.out.println("Kuvanlukija.laskePikseliVali() pikselivali:"
				+ pikseliVali + " skaala:" + Asetukset.SKAALA
				+ " kuvanpidempi:" + this.annaKuvanPidempiReuna()
				+ " skaalattureuna:" + annaKuvanPidempiReuna()
				/ Asetukset.SKAALA);
		// palautetaan uusi väli (maksimissaan yksi)
		return pikseliVali;
	}

}
