package pl.model;


import java.util.Arrays;

public class Operacje {

    
    public static byte[] permutacja(byte[] wej, byte[] permutacja, int ileBajt){

        byte[] wyj = new byte[ileBajt];

        for (int i = 0; i < permutacja.length; i++) {

            setBit(wyj,i,getBit(wej, permutacja[i]-1));

        }

        return wyj;
    }

    public static int getBit(byte[] wej, int pos){

        int ktoryBajt = pos / 8;
        int ktoryBit = pos % 8;
        byte bajt = wej[ktoryBajt];
        return ((bajt << (ktoryBit)) & 128) > 0 ? 1 : 0;
    }

    public static void setBit(byte[] wej, int pos, int wartosc){

        int ktoryBajt = pos / 8;
        int ktoryBit = pos % 8;
        byte bajt = wej[ktoryBajt];
        if(wartosc == 0){
            bajt = (byte)(~(128 >> ktoryBit) & bajt);
        }else {
            bajt = (byte)((128 >> ktoryBit) | bajt);
        }
        wej[ktoryBajt] = bajt;


    }


    public static byte[] skopiujBity(byte[] wej, int od, int ile, int ilaBajt) {

        byte[] wyj = new byte[ilaBajt];

        for (int i = 0; i < ile; i++) {

            setBit(wyj,i, getBit(wej,od+i));

        }

        return wyj;
    }



    public static byte[] przesunieciePolowekKlucza(byte[] wej, int przesuniecie){

        byte[] wyj = Arrays.copyOf(wej,4);

        for(int i = 0; i < przesuniecie; i++){

            int bit = Operacje.getBit(wyj,0);

            for(int j = 0; j < 27; j++){
                setBit(wyj,j,getBit(wyj,(j + 1)));

            }
            setBit(wyj,27,bit);
        }

        return wyj;
    }

    public static byte[] zwroc6bitow(byte[] wej, int numer){
        byte[] wyj = {0};

        for(int i = 0; i < wej.length; i++){
            Operacje.setBit(wyj,i + 2,Operacje.getBit(wej,i + (numer * 6)));
        }

        return wyj;
    }

    public static byte[] polaczDwieTablice(byte[] l, byte[] p, int ile, int rozmiar){

        byte[] wyj = new byte[rozmiar];

        for(int i = 0; i < ile; i++){
            Operacje.setBit(wyj,i,Operacje.getBit(l,i));
        }

        for(int i = 0;i < ile; i++){
            Operacje.setBit(wyj,ile + i,Operacje.getBit(p,i));
        }

        return wyj;
    }

    public static byte[] XOR(byte[] bytes1, byte[] bytes2, int ileBajt){

        byte[] wyj = new byte[ileBajt];

        for(int i = 0; i < ileBajt; i++){
            wyj[i] = (byte)(bytes1[i] ^ bytes2[i]);
        }

        return wyj;

    }

    public static byte[] zwroc64bity(int numer, byte[] wej){

        int maxindex = wej.length / 8;

        if(numer > maxindex){
            return null;
        }

        byte[] wyj = new byte[8];

        if((wej.length % 8 == 0) || (numer < maxindex)) {
            for(int i = 0; i < 8; i++){
                wyj[i] = wej[i + (numer * 8)];
            }

        }else{
            int delta = wej.length - (numer * 8);
            for(int i = 0; i < delta; i++){
                wyj[i] = wej[i + (numer * 8)];
            }

            for(int i = delta; i < 8; i++){
                wyj[i] = 0;
            }
        }
        return wyj;
    }


}
