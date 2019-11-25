import meggy.Meggy;




class newClass {
    public static void main(String[] args){
        Meggy.setPixel((byte)0,(byte)0, Meggy.Color.RED);
        Meggy.toneStart(Meggy.Tone.C3, 1);
        new newerClass().tones((byte)1,(byte)2);
        Meggy.setPixel((byte) 1,(byte) 0, Meggy.Color.RED);
        Meggy.toneStart(Meggy.Tone.C3, 1);
        new newerClass().tones((byte)1,(byte)2);
        new newerClass().tones((byte)2,(byte)2);
        new newerClass().tones((byte)1,(byte)2);
        new newerClass().tones((byte)2,(byte)2);
        Meggy.toneStart(Meggy.Tone.C3, 1000000);

    }
}
class newerClass{
    public void tones(byte x, byte y){
            if(x < y){
                Meggy.setPixel((byte)7, (byte)7, Meggy.Color.WHITE);
            }


    }
}
