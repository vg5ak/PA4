/**
 * PA3ifdots.java
 *
 * An example for the students to code up in AVR assembly for PA1.
 * The language features will be from the PA3 grammar.
 *
 * MMS, 1/23/13
 */

import meggy.Meggy;

class PA3Test1 {

    public static void main(String[] whatever){
        //while((true && true) == true )
            //if (Meggy.checkButton(Meggy.Button.A)) {
            if(1<2){
                Meggy.setPixel( (byte)(1), (byte)0, Meggy.Color.RED );
            }

                Meggy.setPixel( (byte)7, (byte)0, Meggy.Color.GREEN );

            //}
            //else if (Meggy.checkButton(Meggy.Button.Up)) {
                    Meggy.setPixel( (byte)0, (byte)0, Meggy.getPixel((byte)2, (byte)2) );
                    Meggy.setPixel( (byte)6, (byte)6, Meggy.Color.ORANGE );
                    Meggy.setPixel( (byte)2, (byte)2, Meggy.Color.BLUE );


            //}
        //    else if(Meggy.checkButton(Meggy.Button.B)) {
                    Meggy.setPixel( (byte)1, (byte)1, Meggy.Color.BLUE );
                    Meggy.setPixel( (byte)6, (byte)1, Meggy.Color.VIOLET );
                    Meggy.setPixel( (byte)0, (byte)0, Meggy.getPixel((byte)2, (byte)2) );

            //}
            //else{
                    Meggy.setPixel( (byte)0, (byte)0, Meggy.Color.RED );
                    Meggy.setPixel( (byte)7, (byte)0, Meggy.getPixel((byte)2, (byte)2) );
                    Meggy.setPixel( (byte)0, (byte)7, Meggy.Color.GREEN);
                    Meggy.setPixel( (byte)7, (byte)7,  Meggy.getPixel((byte)2, (byte)2) );
                    Meggy.setPixel( (byte)1, (byte)1, Meggy.Color.BLUE );
            //    }
                Meggy.delay(500);
            }
            //tenth second delay, 100 milliseconds




}
