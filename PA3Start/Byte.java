/**
 * PA3Expressions.java
 * MMS 2/16/11, updated WB 7/15/11
 */

import meggy.Meggy;

class Byte {
	public static void main(String[] whatever){
	        // Byte multiplication
	        //Meggy.setPixel((byte)((byte)1*(byte)2),(byte)((byte)3+4 ),Meggy.Color.WHITE);
			Meggy.setPixel((byte)1 ,(byte)2, Meggy.Color.WHITE);
			//Meggy.toneStart( (byte)0 , Meggy.Tone.C3);
			Meggy.toneStart(Meggy.Tone.D3,2);
		/*
	        // Byte casts Byte
	        Meggy.setPixel( (byte)((byte)3),
	                        (byte)((byte)(5-(byte)2*(byte)2)),
	                        Meggy.Color.ORANGE);
		*/
    }
}
