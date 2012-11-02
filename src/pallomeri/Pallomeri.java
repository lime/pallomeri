package pallomeri;

import java.awt.Point;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import sojamo.drop.DropEvent;
import sojamo.drop.SDrop;
import sojamo.drop.SDrop.*;

public class Pallomeri extends PApplet {

	private Set<Pallo> pallot;
	private Valikko valikko;
	private Kuvanlukija lukija;
	private SDrop sDrop;

	private static final int STAGE_LEVEYS = 650;
	private static final int STAGE_KORKEUS = 600;
	public void setup() {
		// Koko
		size(800, 600); // Processing vaatii tarkat arvot

		

		// Varsinaista kikkailua jolla saamme valikkoa käyttämään PGraphics:ia
		this.valikko = new Valikko(STAGE_LEVEYS, 0, 150, this.height, this);

		// Valitse ensimänen kuva (null = oletus)
		this.vaihdaKuva(null, null);

		sDrop = new SDrop(this);
		
		// Piirtotyyli
		noStroke();
		smooth();
	}

	/**
	 * Valitse uusi kuva jota ladataan muistiin ja ruvetaan näyttää
	 * 
	 * @param kuvanNimi
	 */
	public void vaihdaKuva(String kuvanNimi, PImage img) {
		// Luodaan uusi Kuvanlukija - jos null, arvotaan kuva Kuvanlukijan
		// taulukosta
		this.pallot = new HashSet<Pallo>();
		
		if (img != null){
			this.lukija = new Kuvanlukija(img, this);
		}
		else {
			this.lukija = new Kuvanlukija(kuvanNimi, this);
		}

		float pikseliVali = lukija.laskePikseliVali();

		for (int x = 0; x < lukija.annaKuvanLeveys(); x += pikseliVali) {
			for (int y = 0; y < lukija.annaKuvanKorkeus(); y += pikseliVali) {
				Pallo pallo = lukija.luePikseli(x, y);
				this.pallot.add(pallo);
				// System.out.println("Pallomeri.setup() "+pallo.vari+" R:"+red(pallo.vari)+" G:"+green(pallo.vari)+" B:"+blue(pallo.vari));
			}
		}
		
		//päivitä valikon kuva
		this.valikko.paivitaKuva();
		
		System.err.println("Pallomeri.vaihdaKuva() Palloja yhteensä: " + this.pallot.size());
	}

	public void draw() {

		// Piirrä tausta
		this.background(this.color(32, 38, 39));

		// Päivitä ja piirrä pallot
		for (Pallo pallo : this.pallot) {
			pallo.liiku();
			pallo.piirra();
		}

		// Päivitä valikko ja piirrä se näytölle
		this.valikko.render();
		// piirrä oikealle puolelle
		this.image(valikko.getGraphics(), this.stageLeveys(), 0f);
	}

	/**
	 * 
	 * @return
	 */
	public Kuvanlukija annaLukija() {
		return this.lukija;
	}

	/**
	 * Leveys ilman valikkoa
	 */
	public int stageLeveys() {
		return STAGE_LEVEYS;
	}

	public int stageKorkeus() {
		return STAGE_KORKEUS;
	}
	
	public int stageLyhinReuna() {
		return Math.min(stageKorkeus(), stageLeveys());
	}

	public Point randomSijainti() {
		Random RAND = new Random();
		return new Point(RAND.nextInt(this.stageLeveys()), RAND.nextInt(this
				.stageKorkeus()));
	}
	
	
	public void mousePressed(){
		System.out.println("mouseX" + mouseX + "Pallomeri.mouseY " + mouseY);
		
		if (valikko.over (stageLeveys()+30, 220, 80, 80)){
			vaihdaKuva(null, null);
		}
		else{}
		
		//System.out.println("pallomeri.mouseX" + mouseX + "Pallomeri.mouseY " + mouseY);
	}

	/*
	 * sDrop drop event
	 */
	public void dropEvent(DropEvent theDropEvent) {
		println("");
		println("isFile()\t" + theDropEvent.isFile());
		println("isImage()\t" + theDropEvent.isImage());
		println("isURL()\t" + theDropEvent.isURL());

		// if the dropped object is an image, then
		// load the image into our PImage.
		if (theDropEvent.isImage()) {
			println("### loading image ...");
			PImage m = theDropEvent.loadImage();
			odota(1800);	
			System.out.println("Pallomeri.dropEvent() " + m);			
			vaihdaKuva(null, m);
		}
	}
	
	public static void odota (int n){

		long t0, t1;

		t0 =  System.currentTimeMillis();

		do{
			t1 = System.currentTimeMillis();
		}
		while (t1 - t0 < n);
	}


	public static void main(String args[]) {
		PApplet.main(new String[] { pallomeri.Pallomeri.class.getName() });
	}
}
