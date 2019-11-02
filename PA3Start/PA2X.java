


import meggy.Meggy;

class PA2X {
	public static void main(String[] whatever){
	        // Lower left petal, clockwise

            Meggy.setPixel( (byte)0, (byte)0, Meggy.Color.VIOLET );
	        Meggy.setPixel( (byte)1, (byte)1, Meggy.Color.ORANGE );
	        Meggy.setPixel( (byte)2, (byte)2, Meggy.Color.VIOLET );
	        Meggy.setPixel( (byte)3, (byte)3, Meggy.Color.ORANGE );
	        Meggy.setPixel( (byte)4, (byte)4, Meggy.Color.VIOLET );
	        Meggy.setPixel( (byte)5, (byte)5, Meggy.Color.ORANGE );
			Meggy.setPixel( (byte)6, (byte)6, Meggy.Color.VIOLET );
			Meggy.setPixel( (byte)7, (byte)7, Meggy.Color.ORANGE );

            Meggy.setPixel( (byte)7, (byte)0, Meggy.Color.VIOLET );
	        Meggy.setPixel( (byte)6, (byte)1, Meggy.Color.ORANGE );
	        Meggy.setPixel( (byte)5, (byte)2, Meggy.Color.VIOLET );
	        Meggy.setPixel( (byte)4, (byte)3, Meggy.Color.ORANGE );
	        Meggy.setPixel( (byte)3, (byte)4, Meggy.Color.VIOLET );
	        Meggy.setPixel( (byte)2, (byte)5, Meggy.Color.ORANGE );
			Meggy.setPixel( (byte)1, (byte)6, Meggy.Color.VIOLET );
			Meggy.setPixel( (byte)0, (byte)7, Meggy.Color.ORANGE );

    }
}
