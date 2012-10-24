package pallomeri;

import java.util.HashSet;
import java.util.Set;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Pallomeri extends PApplet {

	private Set<Pallo> pallot;
	private Valikko valikko;
	private static final int STAGE_HEIGHT = 500;
	private static final int VALIKKO_HEIGHT = 100;

	public void setup() {
		this.size(800, STAGE_HEIGHT + VALIKKO_HEIGHT);
		
		String kuvanNimi = null; // TODO hoida tämä
		
		this.pallot = new HashSet<Pallo>();
		
		// Luodaan uusi Kuvanlukija
		Kuvanlukija lukija = new Kuvanlukija(kuvanNimi, this);

		for (int x = 0; x < lukija.annaLeveys(); x++) {
			for (int y = 0; y < lukija.annaKorkeus(); y++) {
				this.pallot.add(lukija.luePikseli(x, y));
			}
		}
		
		// Varsinaista kikkailua jolla saamme valikkoa käyttämään PGraphics:ia
		this.valikko = new Valikko(this.width, VALIKKO_HEIGHT, this);
	}

	public void draw() {
		
		// Piirrä tausta
		this.background(this.color(32, 38, 39));
		
		// Päivitä valikko ja piirrä se näytölle
		this.valikko.draw();
		this.image(valikko.getGraphics(), 0.0f, this.height - valikko.height);
	}
}
