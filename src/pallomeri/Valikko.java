package pallomeri;

import guicomponents.GHorzSlider;
import guicomponents.GLabel;
import guicomponents.GSlider;
import guicomponents.GWSlider;
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
	private PImage kysymysmerkki;
	private PImage lataamerkki;
	private int y;
	private int x;

	public Valikko(int x, int y, int width, int height, Pallomeri p) {
		this.x = x;
		this.y = y;
		this.pallomeri = p;
		g = this.pallomeri.createGraphics(width, height, PConstants.JAVA2D);
		this.nakymattomyys = 10;

		this.lataamerkki = pallomeri.loadImage("data/reload.png");
		this.kysymysmerkki = this.pallomeri.loadImage("data/kysarimerkki2.png");

		int sliderX = loc2GlobX(10);
		int sliderIncrementY = loc2GlobY(this.annaKorkeus() / 44);
		int sliderWidth = this.annaLeveys() - 20;

		GLabel maaraLabel = new GLabel(pallomeri, "MÄÄRÄ", sliderX,
				sliderIncrementY * 27, sliderWidth);
		GWSlider maaraSlider = new GWSlider(pallomeri, sliderX,
				sliderIncrementY * 28, sliderWidth);
		maaraSlider.addEventHandler(this, "maaraMuutos");

		GLabel kokoLabel = new GLabel(pallomeri, "KOKO", sliderX,
				sliderIncrementY * 38, sliderWidth);
		GWSlider kokoSlider = new GWSlider(pallomeri, sliderX,
				sliderIncrementY * 39, sliderWidth);
		kokoSlider.addEventHandler(this, "kokoMuutos");
		
		GLabel vauhtiLabel = new GLabel(pallomeri, "VAUHTI", sliderX,
				sliderIncrementY * 42, sliderWidth);
		GWSlider vauhtiSlider = new GWSlider(pallomeri, sliderX,
				sliderIncrementY * 43, sliderWidth);
		vauhtiSlider.addEventHandler(this, "vauhtiMuutos");

		kokoSlider.setLimits(Asetukset.PALLOJEN_KOKO,
				Asetukset.PALLOJEN_KOKO_MIN, Asetukset.PALLOJEN_KOKO_MAX);
		maaraSlider.setLimits(Asetukset.PALLOJEN_VALI,
				Asetukset.PALLOJEN_VALI_MIN, Asetukset.PALLOJEN_VALI_MAX);
		vauhtiSlider.setLimits(Asetukset.VAUHTI, Asetukset.VAUHTI_MIN,
				Asetukset.VAUHTI_MAX);

		for (GWSlider slider : new GWSlider[] { kokoSlider, maaraSlider,
				vauhtiSlider }) {
			slider.setValueType(GWSlider.DECIMAL);
			slider.setRenderMaxMinLabel(false); // hides labels
			slider.setRenderValueLabel(false); // hides value label
			slider.setStickToTicks(false); // false by default
			slider.setTickLength(0);
		}

		vauhtiSlider.setValueType(GWSlider.EXPONENT);

		g.smooth();
	}

	/**
	 * Tässä metodissa voi tehdä muutoksia valikolle
	 */
	public void render() {
		g.beginDraw(); // aloita

		g.background(pallomeri.color(58, 63, 64));

		if (this.hiiriKuvanpaalla()) {
			g.image(esikatselukuva, 10, 10);
		}

		else {
			// ei kannata varmaan ladata joka kerta, kun piirretään... eli
			// siirsin konstruktoriin...ja kokeilin toisella kuvalla
			// this.kysymysmerkki =
			// this.pallomeri.loadImage("data/kysymysmerkki.jpg");

			// kysymysmerkki.resize(this.annaLeveys()-60, 0);

			g.image(kysymysmerkki, 15, 10);

		}

		/*
		 * if(this.hiiriKuvanpaalla() && this.nakymattomyys > 0) {
		 * esikatselukuva.filter(PConstants.BLUR, 1); this.nakymattomyys -= 0.1;
		 * } else { esikatselukuva.filter(PConstants.BLUR, -1);
		 * this.nakymattomyys += 0.1; }
		 * 
		 * 
		 * g.image(esikatselukuva, 10, 10);
		 */

		g.image(lataamerkki, 10, 200);

		g.endDraw(); // lopeta
	}

	void paivitaKuva() {
		esikatselukuva = this.pallomeri.annaLukija().annaKuva();
		esikatselukuva.resize(this.annaLeveys() - 20, 0);

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
		return this.pallomeri.dist(60 + this.pallomeri.stageLeveys(), 60,
				this.pallomeri.mouseX, this.pallomeri.mouseY) < 50;

	}

	/**
	 * Local to global X
	 */
	public int loc2GlobX(int locX) {
		return locX + this.x;
	}

	/**
	 * Local to global Y
	 */
	public int loc2GlobY(int locY) {
		return locY + this.y;
	}

	public boolean over(int x, int y, int leveys, int korkeus) {
		if (pallomeri.mouseX >= x && pallomeri.mouseX <= x + leveys
				&& pallomeri.mouseY >= y && pallomeri.mouseY <= y + korkeus) {
			return true;
		} else {
			return false;
		}
	}

	public void kokoMuutos(GWSlider slider) {
		Asetukset.PALLOJEN_KOKO = slider.getValuef();
	}

	public void maaraMuutos(GWSlider slider) {
		Asetukset.PALLOJEN_VALI = slider.getValuef();
	}

	public void vauhtiMuutos(GWSlider slider) {
		Asetukset.VAUHTI = slider.getValuef();
		System.out.println("Valikko.vauhtiMuutos() "
				+ Math.pow(Asetukset.VAUHTI, 1));
	}

}
