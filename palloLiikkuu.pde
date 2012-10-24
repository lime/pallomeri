
float random = random(0, 400);

float alkuX = random;
float alkuY = 50;
float loppuX = 150;
float loppuY = 250;


float matkaX;
float matkaY;

float eksponentti = 5;

float x = 0.0;
float y = 0.0;

float liike = 0.01;
float kuljettuMatka = 0.0;

void setup() {
 
size(400, 400);
noStroke();
smooth();

matkaX = loppuX - alkuX;
matkaY = loppuY - alkuY;
  
}


void draw() {
  
 
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

void mousePressed() {
 
kuljettuMatka = 0.0;
alkuX = x;
alkuY = y;
loppuX = mouseX;
loppuY = mouseY;
matkaX = loppuX - alkuX;
matkaY = loppuY - alkuY;

}

/*class Pallo {
  
float x = 0.0;
float y = 0.0;
  
  public boolean luoPallo( color vari, int koko, float alkuX, float alkuY,
  float loppuX, float loppuY) {
   
    this.alkuX = random(x);
    this.alkuY = random(y);
    this.vari = color(0);
    this.koko = 20;
    
    if( alkuX > width || alkuX < 0) {
     return false; 
    }
    
    ellipse(alkuX, alkuY, koko, koko);
    return true;
  }
}*/



