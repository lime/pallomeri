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
	public static final int WIDTH = 800;
	private static final int STAGE_HEIGHT = 500;
	private static final int VALIKKO_HEIGHT = 100;
	public static final int HEIGHT = STAGE_HEIGHT + VALIKKO_HEIGHT;
	static final int PIXEL_ASKEL = 5;

	public void setup() {
		// Koko
		this.size(WIDTH, HEIGHT);
		
		this.pallot = new HashSet<Pallo>();

		// Varsinaista kikkailua jolla saamme valikkoa käyttämään PGraphics:ia
		this.valikko = new Valikko(this.width, VALIKKO_HEIGHT, this);
		
		// Valitse ensimänen kuva (oletus)
		this.vaihdaKuva(null);
	}

	/**
	 * Valitse uusi kuva jota ladataan muistiin ja ruvetaan näyttää
	 * @param kuvanNimi
	 */
	public void vaihdaKuva(String kuvanNimi) {
		// Luodaan uusi Kuvanlukija
		this.lukija = new Kuvanlukija(kuvanNimi, this);

		for (int x = 0; x < lukija.annaLeveys(); x += PIXEL_ASKEL) {
			for (int y = 0; y < lukija.annaKorkeus(); y += PIXEL_ASKEL) {
				Pallo pallo = lukija.luePikseli(x, y);
				this.pallot.add(pallo);
				// System.out.println("Pallomeri.setup() "+pallo.vari+" R:"+red(pallo.vari)+" G:"+green(pallo.vari)+" B:"+blue(pallo.vari));
			}
		}
	}

	public void draw() {
		// Piirtotyyli
		noStroke();
		smooth();

		// Piirrä tausta
		this.background(this.color(32, 38, 39));

		// Päivitä valikko ja piirrä se näytölle
		this.valikko.render();
		this.image(valikko.getGraphics(), 0.0f, this.height - valikko.height);

		// Päivitä ja piirrä pallot
		for (Pallo pallo : this.pallot) {
			pallo.liiku(this);
			pallo.piirra(this);
		}
	}

	/**
	 * 
	 * @return
	 */
	public Kuvanlukija annaLukija() {
		return this.lukija;
	}

	public static Point randomSijainti() {
		Random RAND = new Random();
		return new Point(RAND.nextInt(WIDTH), RAND.nextInt(HEIGHT));
	}
}
