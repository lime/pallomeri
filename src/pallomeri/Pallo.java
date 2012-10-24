package pallomeri;

import java.awt.Color;

import processing.core.PApplet;

public class Pallo {

	private float alkuX, alkuY, loppuX, loppuY;
	private Color vari;

	public Pallo(int x, int y, float r, float g, float b) {
		new Col
		// TODO Auto-generated constructor stub
	}

	public void liiku() {
		float matkaX;
		float matkaY;

		float eksponentti = 5;

		float liike = 0.01;
		float kuljettuMatka = 0.0;

		 kuljettuMatka += liike;

		 if( kuljettuMatka < 1.0) {

		   this.x = alkuX + (kuljettuMatka * matkaX);
		   this.y = alkuY + (pow(kuljettuMatka, eksponentti) * matkaY);

		 }

	}
	
	public void piirra(){
		 /*fill(255);
		 rect(0, 0, width, height);
		 fill(0);
		 ellipse(x, y, 20, 20);*/
	}



/*	void setup() {
	 
	size(400, 400);
	noStroke();
	smooth();

	matkaX = loppuX - alkuX;
	matkaY = loppuY - alkuY;
	  
	}


	void draw() {
	  
	 

	  
	}

	void mousePressed() {
	 
	kuljettuMatka = 0.0;
	alkuX = x;
	alkuY = y;
	loppuX = mouseX;
	loppuY = mouseY;
	matkaX = loppuX - alkuX;
	matkaY = loppuY - alkuY;

	}*/


}



