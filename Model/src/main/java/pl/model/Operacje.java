package pl.model;

import java.math.BigInteger;
import java.util.Arrays;

import static java.lang.Math.abs;

public class Operacje {


    public static BigInteger createBig(byte[] wejscie) {
        int ileBitow = wejscie.length * 8;

        BigInteger big = new BigInteger(wejscie);
        if (big.compareTo(BigInteger.ZERO) < 0) {
            big = big.add(BigInteger.ONE.shiftLeft(ileBitow));
        }

        return big;
    }



    public static byte[] bigToArray(BigInteger big,int bytes){

        if(big.toByteArray().length > bytes){
            return trim(big.toByteArray());
        } else if (big.toByteArray().length < bytes) {
            byte[] tab = new byte[bytes];
            byte[] tab1 = big.toByteArray();
            for (int i = bytes - tab1.length; i < bytes;i++)
                tab[i] = tab1[i-(bytes-tab1.length)];
            return tab;
        } else {
            return big.toByteArray();
        }

    }
    
    public static byte[] permutacja(byte[] wejscie, byte[] permutacja, int ileBajt){

        byte[] wyjscie = new byte[ileBajt];

        for (int i = 0; i < permutacja.length; i++) {

            setBit(wyjscie,i,getBit(wejscie, permutacja[i]-1));

        }

        return wyjscie;
    }

    public static int getBit(byte[] wej, int pos){
//        BigInteger wyj = new BigInteger(wej);
//
//        return wyj.testBit((wej.length*8 - 1) - pos) ? 1 : 0;
        int ktoryBajt = pos / 8;
        int ktoryBit = pos % 8;
        byte bajt = wej[ktoryBajt];
        return ((bajt << (ktoryBit)) & 128) > 0 ? 1 : 0;
    }

    public static byte[] setBit(byte[] wej, int pos, int wartosc){
//        BigInteger wyj = createBig(wej);
//        if (wartosc == 0){
//            wyj = wyj.clearBit((wej.length*8 - 1) - pos);
//        } else {
//            wyj = wyj.setBit((wej.length*8 - 1) - pos);
//        }
//        return bigToArray(wyj,wej.length);
        int ktoryBajt = pos / 8;
        int ktoryBit = pos % 8;
        byte bajt = wej[ktoryBajt];
        if(wartosc == 0){
            bajt = (byte)(~(128 >> ktoryBit) & bajt);
            wej[ktoryBajt] = bajt;
            return wej;
        }else {
            bajt = (byte)((128 >> ktoryBit) | bajt);
            wej[ktoryBajt] = bajt;
            return wej;
        }


    }


    public static byte[] trim(byte[] trimmed){

        if (trimmed[0] != 0){
            return trimmed;
        }
            byte[] tab = new byte[trimmed.length-1];
            System.arraycopy(trimmed,1,tab,0,trimmed.length-1);

        return tab;
    }



    public static byte[] skopiujBity(byte[] wej, int od, int ile, int ilaBajt) {
        byte[] wyjscie = new byte[ilaBajt];

        for (int i = 0; i < ile; i++) {

            setBit(wyjscie,i, getBit(wej,od+i));

        }

        return wyjscie;
    }



    public static byte[] przesunieciePolowekKlucza(byte[] wejscie, int przesuniecie){

        byte[] wyjscie = Arrays.copyOf(wejscie,4);
//
//        for (int i = 0 ;i < 28; i++) {
//
//            wyjscie = setBit(wyjscie,i,getBit(wejscie,(i+ przesuniecie)%28));
//
//        }
        for(int i = 0; i < przesuniecie;i++){

            int bit = Operacje.getBit(wyjscie,0);

            for(int j = 0;j<27;j++){
                setBit(wyjscie,j,getBit(wyjscie,(j + 1)));

            }
            setBit(wyjscie,27,bit);
        }

        return wyjscie;
    }

    public static byte[] zwroc6bitow(byte[] bytes, int numer){
        byte[] wyj = {0};

        for(int i = 0; i< bytes.length; i++){
            Operacje.setBit(wyj,i + 2,Operacje.getBit(bytes,i + (numer * 6)));
        }

        return wyj;
    }

    public static byte[] polaczDwieTablice(byte[] l, byte[] p, int ile, int rozmiar){
        byte[] wyj = new byte[rozmiar];
        for(int i = 0;i<ile;i++){
            Operacje.setBit(wyj,i,Operacje.getBit(l,i));
        }
        for(int i = 0;i<ile;i++){
            Operacje.setBit(wyj,ile + i,Operacje.getBit(p,i));
        }
        return wyj;
    }

    public static byte[] XOR(byte[] bytes1, byte[] bytes2, int ileBajt){
//        BigInteger b1 = new BigInteger(bytes1);
//        BigInteger b2 = new BigInteger(bytes2);
//
//        return bigToArray(b1.xor(b2),ileBajt);
        byte[] wyj = new byte[ileBajt];

        for(int i =0;i<ileBajt;i++){
            wyj[i] = (byte)(bytes1[i] ^ bytes2[i]);
        }

        return wyj;

    }

    public static byte[] zwroc64bity(int numer, byte[] bytes){

        int maxindex = bytes.length / 8;
        if(numer > maxindex){
            return null;
        }

        byte[] out = new byte[8];

        if((bytes.length % 8 == 0) || (bytes.length / 8 != 0 && numer < maxindex)) {
            for(int i = 0; i < 8; i++){
                out[i] = bytes[i + (numer * 8)];
            }

        }else{
            int delta = bytes.length - (numer * 8);
            for(int i = 0; i < delta; i++){
                out[i] = bytes[i + (numer * 8)];
            }

            for(int i = delta; i < 8; i++){
                out[i] = 0;
            }
        }
        return out;
    }


}
