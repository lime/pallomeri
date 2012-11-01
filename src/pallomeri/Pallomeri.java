package pallomeri;

import java.awt.Point;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Pallomeri extends PApplet {

	private Set<Pallo> pallot;
	private Valikko valikko;
	private Kuvanlukija lukija;
	final int PIXEL_ASKEL = 5;

	public void setup() {
		// Koko
		size(800, 600); // Processing vaatii tarkat arvot
		
		this.pallot = new HashSet<Pallo>();

		// Varsinaista kikkailua jolla saamme valikkoa käyttämään PGraphics:ia
		this.valikko = new Valikko(150, this.height, this);
		
		// Valitse ensimänen kuva (null = oletus)
		this.vaihdaKuva(null);
		
		// Piirtotyyli
		noStroke();
		smooth();
	}

	/**
	 * Valitse uusi kuva jota ladataan muistiin ja ruvetaan näyttää
	 * @param kuvanNimi
	 */
	public void vaihdaKuva(String kuvanNimi) {
		// Luodaan uusi Kuvanlukija - jos null, arvotaan kuva Kuvanlukijan taulukosta
		this.lukija = new Kuvanlukija(kuvanNimi, this);
		
		/* Luodaan uusi Kuvanlukija - jos null arvotaan Albumista
		this.lukija = new Kuvanlukija(arvokuva().annakuvannimi(), this);*/

		for (int x = 0; x < lukija.annaLeveys(); x += PIXEL_ASKEL) {
			for (int y = 0; y < lukija.annaKorkeus(); y += PIXEL_ASKEL) {
				Pallo pallo = lukija.luePikseli(x, y);
				this.pallot.add(pallo);
				// System.out.println("Pallomeri.setup() "+pallo.vari+" R:"+red(pallo.vari)+" G:"+green(pallo.vari)+" B:"+blue(pallo.vari));
			}
		}
	}
	private Albumi arvokuva() {
		return Albumi.values()[new Random().nextInt(Albumi.values().length)];
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
		this.image(valikko.getGraphics(), this.width - valikko.annaLeveys(), 0f); // piirrä oikealle sivulle
	}

	/**
	 * 
	 * @return
	 */
	public Kuvanlukija annaLukija() {
		return this.lukija;
	}

	public Point randomSijainti() {
		Random RAND = new Random();
		return new Point(RAND.nextInt(this.width), RAND.nextInt(this.height));
	}
	
	public static void main(String args[])
    {
      PApplet.main(new String[] { pallomeri.Pallomeri.class.getName() });
    }
}
