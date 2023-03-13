package pl.model;

import java.math.BigInteger;

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
            byte[] tab;
            big = big.setBit(bytes*8-7);
            tab = big.toByteArray();
            tab[0] = 0;
            return tab;
        } else {
            return big.toByteArray();
        }

    }
    // to chyba dziala
    public static byte[] getBits(byte[] wejscie,byte[] ktore){
        byte[] wyjscie;
        BigInteger helper = new BigInteger("0");
        BigInteger helper2 = new BigInteger(wejscie);
        for (int i = 0;i < ktore.length; i++) {
            if (helper2.testBit(ktore[i]-1)) {
                helper = helper.setBit(i);
            }
        }
//        System.out.println("help");
//        for (byte c : helper.toByteArray()) {
//            System.out.print(Integer.toBinaryString(c & 255 | 256).substring(1));
//        }
//        System.out.println();
        wyjscie = helper.toByteArray();
        wyjscie = trim(wyjscie);
//        System.out.println("wyj");
//        for (byte c : wyjscie) {
//            System.out.print(Integer.toBinaryString(c & 255 | 256).substring(1));
//        }
        return wyjscie;
    }




    public static byte[] fillZeros(byte[] tab,int ile,boolean koniec){
        BigInteger help = createBig(tab);

        for (int i = 0; i < ile;i++){

            int x = koniec ? i : (tab.length*8-1)-i;
            help = help.clearBit(x);
        }

        return trim(help.toByteArray());
    }

    // to odziwo dziala XD
    public static byte[] trim(byte[] trimmed){

        if (trimmed[0] != 0){
            return trimmed;
        }
            byte[] tab = new byte[trimmed.length-1];
            System.arraycopy(trimmed,1,tab,0,trimmed.length-1);

        return tab;
    }


//    public static byte[] copyBits(byte[] wejscie,int od,int ile) {
//        byte[] wyjscie = new byte[(ile-1)/8 + 1];
//        BigInteger int1 = new BigInteger(wyjscie);
//        BigInteger int2 = new BigInteger(wejscie);
//        for (int i = od; i < wejscie.length; i++){
//            if(int1.testBit(i)) {
//
//            }
//        }
//    }


    public static byte[] kopiuj(byte[] wejscie,int pozycja,int ile) {
        int rozmiar  = (ile - 1)/8 + 1;
        byte[] wyjscie = new byte[rozmiar];

        BigInteger wej = createBig(wejscie);
        BigInteger wyj = createBig(wyjscie);

        int x = wejscie.length*8 - pozycja - 1;

        for (int i = 0; i < ile; i++) {
//            System.out.println(x-i);
            if (wej.testBit(x - i)) {
                wyj = wyj.setBit(ile - i - 1);
            }
        }
        return bigToArray(wyj,rozmiar);
    }



    public static byte[] rotateBitsToLeft(byte[] wejscie,int dlugosc,int skok){

        byte[] wyjscie = new byte[wejscie.length];

        BigInteger wej = createBig(wejscie);
        BigInteger wyj = createBig(wyjscie);

        for (int i = 0 ;i < dlugosc; i++) {
            if (wej.testBit(i)) {
                wyj = wyj.setBit((i+skok)%dlugosc);
            }
        }

        return bigToArray(wyj,wejscie.length);
    }
}
