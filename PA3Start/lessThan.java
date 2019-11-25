

import meggy.Meggy;


class lessThan {
    public static void main(String[] args){


        new do().bluedot((byte)3, (byte)7);
        if(1 < 2){
            Meggy.toneStart(Meggy.Tone.C3, 2);
        }
        if(2 < 1){
            Meggy.setPixel((byte)1, (byte)2, Meggy.Color.RED);
            Meggy.toneStart(Meggy.Tone.Cs3, 2);
            Meggy.toneStart(Meggy.Tone.D3, 2);
            Meggy.toneStart(Meggy.Tone.E3, 2);
        }


            Meggy.toneStart(Meggy.Tone.B3, 2);
            Meggy.toneStart(Meggy.Tone.C3, 2);
            Meggy.toneStart(Meggy.Tone.B3, 2);
            Meggy.toneStart(Meggy.Tone.C3, 2);
            Meggy.setPixel((byte)1, (byte)2, Meggy.Color.WHITE);
            Meggy.toneStart(Meggy.Tone.B3, 2);
            Meggy.toneStart(Meggy.Tone.C3, 2);

    }
}
class do{
    public void bluedot(byte x, byte y){
        Meggy.setPixel(x, y, Meggy.Color.RED);
    }
}
