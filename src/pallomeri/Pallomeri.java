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
	public static final int WIDTH = 800;
	private static final int STAGE_HEIGHT = 500;
	private static final int VALIKKO_HEIGHT = 100;
	public static final int HEIGHT = STAGE_HEIGHT + VALIKKO_HEIGHT;
	static final int PIXEL_ASKEL = 5;

	public void setup() {
		// Koko
		this.size(WIDTH, HEIGHT);
		
		String kuvanNimi = null; // TODO hoida tämä
		
		this.pallot = new HashSet<Pallo>();
		
		// Luodaan uusi Kuvanlukija
		Kuvanlukija lukija = new Kuvanlukija(kuvanNimi, this);

		
		for (int x = 0; x < lukija.annaLeveys(); x+=PIXEL_ASKEL) {
			for (int y = 0; y < lukija.annaKorkeus(); y+=PIXEL_ASKEL) {
				Pallo pallo = lukija.luePikseli(x, y);
				this.pallot.add(pallo);
				//System.out.println("Pallomeri.setup() "+pallo.vari+" R:"+red(pallo.vari)+" G:"+green(pallo.vari)+" B:"+blue(pallo.vari));
			}
		}
		
		// Varsinaista kikkailua jolla saamme valikkoa käyttämään PGraphics:ia
		this.valikko = new Valikko(this.width, VALIKKO_HEIGHT, this);
	}

	public void draw() {
		// Piirtotyyli
		noStroke();
		smooth();
		
		// Piirrä tausta
		this.background(this.color(32, 38, 39));
		
		// Päivitä valikko ja piirrä se näytölle
		this.valikko.draw();
		this.image(valikko.getGraphics(), 0.0f, this.height - valikko.height);
		
		// Päivitä ja piirrä pallot
		for(Pallo pallo : this.pallot) {
			pallo.liiku();
			pallo.piirra(this);
		}
	}

	public static Point randomSijainti() {
		Random RAND = new Random();
		return new Point(RAND.nextInt(WIDTH), RAND.nextInt(HEIGHT));
	}
}
