package pallomeri;

import java.util.Random;

public enum Albumi {

	MONALISA, URKKI, NELSON, TOMATSOPPA, YODA, AKU, APPLE, NEMO, HOMER, HUUTO, JUDELAW, KORALLI, PABLO;
	
	public String annakuvannimi(){
		
		String s;
		switch(this){
		case MONALISA: s= "data/Mona_Lisa.jpg"; break; 
		case URKKI: s="data/urho.jpg"; break;
		case NELSON: s="data/nelson.jpg"; break;
		case TOMATSOPPA: s="data/tomatsoppa.png"; break;
		case YODA: s="data/yoda.jpg"; break;
		case  AKU: s = "data/aku.jpg"; break;
		case APPLE: s= "data/apple.jpg"; break;
		case NEMO: s = "data/nemo.jpg"; break;
		case HOMER: s = "data/homer.jpg"; break;
		case HUUTO: s = "data/huuto.jpg"; break;
		case JUDELAW: s = "data/judelaw.jpg"; break;
		case KORALLI: s = "data/koralli.jpg"; break;
		case PABLO: s = "data/pablo.jpg"; break;
		default: s="data/urho.jpg"; break;
		}
		return s;
	
	}
	
	public static Albumi arvokuva() {
		return Albumi.values()[new Random().nextInt(Albumi.values().length)];
	}
}
