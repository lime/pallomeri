package pallomeri;

import java.util.Set;

import processing.core.PApplet;

public class Pallomeri extends PApplet {

	private Set<Pallo> pallot;

	public void setup() {
		String kuvanNimi = null; // TODO hoida tämä
		// Luodaan uusi Kuvanlukija
		Kuvanlukija lukija = new Kuvanlukija(kuvanNimi, this);

		for (int x = 0; x < lukija.annaLeveys(); x++) {
			for (int y = 0; y < lukija.annaKorkeus(); y++) {
				pallot.add(lukija.luePikseli(x, y));
			}
		}
	}

	public void draw() {
	}
}
