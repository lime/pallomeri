package pallomeri;

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
	
	public Valikko(int width, int height, Pallomeri p) {
		this.pallomeri = p;
		g = this.pallomeri.createGraphics(width, height, PConstants.JAVA2D);
		this.nakymattomyys = 10;
		
		this.lataamerkki = pallomeri.loadImage("data/reload.png");
		this.kysymysmerkki = this.pallomeri.loadImage("data/kysarimerkki2.png");
		

	}

	/**
	 * Tässä metodissa voi tehdä muutoksia valikolle
	 */
	public void render() {
		g.beginDraw(); // aloita

		g.background(pallomeri.color(58, 63, 64));
		
		if(this.hiiriKuvanpaalla()) {
			g.image(esikatselukuva, 10, 10);
		}
		
		else {
			//ei kannata varmaan ladata joka kerta, kun piirretään... eli siirsin konstruktoriin...ja kokeilin toisella kuvalla
			//this.kysymysmerkki = this.pallomeri.loadImage("data/kysymysmerkki.jpg");
			
			//kysymysmerkki.resize(this.annaLeveys()-60, 0);
			
			g.image(kysymysmerkki, 15, 10);
			
		}
		
		/*if(this.hiiriKuvanpaalla() && this.nakymattomyys > 0) {
			esikatselukuva.filter(PConstants.BLUR, 1);
			this.nakymattomyys -= 0.1;
		}
		else {
			esikatselukuva.filter(PConstants.BLUR, -1);
			this.nakymattomyys += 0.1;
		}

		
		g.image(esikatselukuva, 10, 10);*/
		
		
		g.image(lataamerkki, 10, 200);

		g.endDraw(); // lopeta
	}

	void paivitaKuva() {
		esikatselukuva = this.pallomeri.annaLukija().annaKuva();
		esikatselukuva.resize(this.annaLeveys()-20, 0);
		
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
		return this.pallomeri.dist(30+this.pallomeri.stageLeveys(), 60, this.pallomeri.mouseX, this.pallomeri.mouseY) < 50;
			
	}
	
	//Siirretty Pallomereen..
/*	public void hiirtaPainettu(){
		System.out.println("pallomeri.mouseX" + pallomeri.mouseX + "Pallomeri.mouseY " + pallomeri.mouseY);
		if (over (pallomeri.stageLeveys()+10, 200, 130, 130)){
			pallomeri.vaihdaKuva(null);
		}
		else{}
		
		System.out.println("pallomeri.mouseX" + pallomeri.mouseX + "Pallomeri.mouseY " + pallomeri.mouseY);
	}*/

	public boolean over (int x, int y, int leveys, int korkeus){
    if  (pallomeri.mouseX >= x && pallomeri.mouseX <= x + leveys && pallomeri.mouseY >= y && pallomeri.mouseY <= y + korkeus){
      return true;
    }
    else {
      return false;
    }
}

}
