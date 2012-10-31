package pallomeri;

public enum Albumi {

	MONALISA, URKKI, NELSON, TOMATSOPPA, YODA;
	
	public String annakuvannimi(){
		
		String s;
		switch(this){
		case MONALISA: s= "data/Mona_Lisa.jpg"; break; 
		case URKKI: s="data/default.jpg"; break;
		case NELSON: s="data/nelson.jpg"; break;
		case TOMATSOPPA: s="data/tomatsoppa.png"; break;
		case YODA: s="data/yoda.jpg"; break;
		default: s=null; break;
		}
		return s;
	
	}
}
