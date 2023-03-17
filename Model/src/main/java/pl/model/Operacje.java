package pl.model;

import java.math.BigInteger;

import static java.lang.Math.abs;
import static java.lang.Math.log;

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
    
    public static byte[] getBits(byte[] wejscie,byte[] ktore){
        int ileBajtow = (ktore.length - 1) / 8 + 1;
        byte[] wyjscie = new byte[ileBajtow];
        for (int i = 0;i < ktore.length; i++) {
            int wartosc = getBitAt(wejscie,ktore[i]-1);
            wyjscie = setBitAt(wyjscie,i,wartosc);

        }

        return wyjscie;
    }

    public static int getBitAt(byte[] wej,int pos){
        BigInteger wyj = new BigInteger(wej);

        return wyj.testBit((wej.length*8 - 1) - pos) ? 1 : 0;
    }

    public static byte[] setBitAt(byte[] wej,int pos,int wartosc){
        BigInteger wyj = createBig(wej);
        if (wartosc == 0){
            wyj = wyj.clearBit((wej.length*8 - 1) - pos);
        } else {
            wyj = wyj.setBit((wej.length*8 - 1) - pos);
        }
        return bigToArray(wyj,wej.length);
    }


    public static byte[] trim(byte[] trimmed){

        if (trimmed[0] != 0){
            return trimmed;
        }
            byte[] tab = new byte[trimmed.length-1];
            System.arraycopy(trimmed,1,tab,0,trimmed.length-1);

        return tab;
    }




    public static byte[] kopiuj(byte[] wejscie,int pozycja,int ile) {
        int rozmiar  = (ile - 1)/8 + 1;
        byte[] wyjscie = new byte[rozmiar];

        for (int i = 0; i < ile; i++) {
            int wartosc = getBitAt(wejscie,pozycja+i);
            wyjscie = setBitAt(wyjscie,i,wartosc);
        }

        return wyjscie;
    }



    public static byte[] rotateBitsToLeft(byte[] wejscie,int dlugosc,int skok){

        byte[] wyjscie = new byte[wejscie.length];

        for (int i = 0 ;i < dlugosc; i++) {
            int wartosc = getBitAt(wejscie,(i+skok)%dlugosc);
            wyjscie = setBitAt(wyjscie,i,wartosc);
        }

        return wyjscie;
    }

    public static byte[] zwroc6bitow(byte[] bytes, int numer){
        byte[] wyj = {0};

        for(int i = 0; i< bytes.length; i++){
            wyj = Operacje.setBitAt(wyj,i + 2,Operacje.getBitAt(bytes,i + (numer * 6)));
        }

        return wyj;
    }
}
