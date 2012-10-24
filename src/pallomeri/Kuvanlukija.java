package pallomeri;

import processing.core.PApplet;
import processing.core.PImage;



public class Kuvanlukija {
/**
 * 	
 * @param kuvannimi tiedoston nimi, joka pit�isi lukea
 * @param applet se processing-applet jonka yhteydess� ollaan
 */
	
PImage img;
PApplet applet;

public Kuvanlukija(String kuvannimi, PApplet applet){
	 this.applet = applet;
	 if(kuvannimi == null) {
		 kuvannimi = "/data/default.jpg";
	 }
	 this.img = applet.loadImage(kuvannimi);
	  
	 /*Pit�nee ottaa koko huomioon jotenkin...img.resize(applet.width-20, 0); */
	  applet.loadPixels();
	  //ladataan saadun kuvan pikselit pixels[]-jonoon
	  img.loadPixels();
	  
}
public int annaLeveys(){
	return img.width;
}

public int annaKorkeus(){
	return img.height;
}

public Pallo luePikseli(int x, int y){
	 
	  
	  //Tutkitaan saaduista koordinaateista v�riarvot
	      
	      int loc = x + y*img.width;
	      
	     // otetaan v�riarvot talteen pixels[]-jonosta
	      float r = applet.red(img.pixels[loc]);
	      float g = applet.green(img.pixels[loc]);
	      float b = applet.blue(img.pixels[loc]);
	      
	      //jos rgb-arvot kunnossa palautetaan sijainti ja v�riarvot, muuten null
	      if (r>=0 && r<=255 && g>=0 && g<=255 && b>=0 && b<=255){
	      return new Pallo (x, y, r, g, b);
	      }
		return null;
	      
	    
	  }  

	
	
}


