


import meggy.Meggy;

class PA2Diagonal {
	public static void main(String[] whatever){
	        // Diagonal
            
            Meggy.setPixel( (byte)2, (byte)4, Meggy.Color.RED );
	        Meggy.setPixel( (byte)2, (byte)3, Meggy.Color.VIOLET );
	        Meggy.setPixel( (byte)1, (byte)2, Meggy.Color.RED );
	        Meggy.setPixel( (byte)0, (byte)2, Meggy.Color.VIOLET );
	        Meggy.setPixel( (byte)0, (byte)3, Meggy.Color.VIOLET );
	        Meggy.setPixel( (byte)1, (byte)4, Meggy.Color.VIOLET );

    }
}
