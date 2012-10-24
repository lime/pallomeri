package pallomeri;

public class Pallo {
	
	private float alkuX, alkuY, loppuX, loppuY;
	
	public Pallo() {
		// TODO Auto-generated constructor stub
	}
	
	public void liiku() {
		float random = random(0, 400);

		float matkaX;
		float matkaY;

		float eksponentti = 5;

		float x = 0.0;
		float y = 0.0;

		float liike = 0.01;
		float kuljettuMatka = 0.0;
		
		 kuljettuMatka += liike;
		 
		 if( kuljettuMatka < 1.0) {
		  
		   x = alkuX + (kuljettuMatka * matkaX);
		   y = alkuY + (pow(kuljettuMatka, eksponentti) * matkaY);
		   
		 }
		 fill(255);
		 rect(0, 0, width, height);
		 fill(0);
		 ellipse(x, y, 20, 20);
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



