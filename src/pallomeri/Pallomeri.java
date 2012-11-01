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
		
		// Lasketaan paras skaalaaus
		Asetukset.SKAALA = lukija.laskeSkaala();

		float pikseliVali = (lukija.annaLeveys() / Asetukset.PALLOJEN_MAARA) / Asetukset.SKAALA;
		System.out.println("Pallomeri.vaihdaKuva()"+pikseliVali+" "+Asetukset.PALLOJEN_MAARA+" "+Asetukset.SKAALA+" "+lukija.annaLeveys());
		for (int x = 0; x < lukija.annaLeveys(); x += pikseliVali ) {
			for (int y = 0; y < lukija.annaKorkeus(); y += pikseliVali ) {
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
		this.image(valikko.getGraphics(), this.stageLeveys(), 0f); // piirrä oikealle sivulle
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
		return this.width - this.valikko.annaLeveys();
	}
	
	public int stageKorkeus() {
		return this.height;
	}

	public Point randomSijainti() {
		Random RAND = new Random();
		return new Point(RAND.nextInt(this.stageLeveys()), RAND.nextInt(this.stageKorkeus()));
	}
	
	public static void main(String args[])
    {
      PApplet.main(new String[] { pallomeri.Pallomeri.class.getName() });
    }
}
