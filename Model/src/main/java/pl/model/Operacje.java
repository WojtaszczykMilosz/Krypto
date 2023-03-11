package pl.model;

import java.math.BigInteger;

public class Operacje {

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
        System.out.println();
        wyjscie = helper.toByteArray();
        wyjscie = trim(wyjscie);
//        System.out.println("wyj");
//        for (byte c : wyjscie) {
//            System.out.print(Integer.toBinaryString(c & 255 | 256).substring(1));
//        }
        return wyjscie;
    }




    public static byte[] fillZeros(byte[] tab,int ile){
        BigInteger help = new BigInteger(tab);
        System.out.println("tab");
        for (byte c : tab) {
            System.out.print(Integer.toBinaryString(c & 255 | 256).substring(1));
        }
        System.out.println();
        for (byte c : help.toByteArray()) {
            System.out.print(Integer.toBinaryString(c & 255 | 256).substring(1));
        }

        for (int i = 0; i < ile;i++){
            help = help.clearBit(i);
        }
//        System.out.println("wynik");
//        for (byte c : help.toByteArray()) {
//            System.out.print(Integer.toBinaryString(c & 255 | 256).substring(1));
//        }
        return help.toByteArray();
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

    //to nie wiem czy dziala
    public static byte[] rotateByte(byte[] wejscie,int ile){

        byte[] wyjscie = new byte[wejscie.length];
        BigInteger wej = new BigInteger(wejscie);
        BigInteger help = new BigInteger(wyjscie);
        for (int i = 0;i < wejscie.length; i++) {
            if (wej.testBit((i+ile)%wejscie.length)) {
                help.setBit(i);
            }
        }
        return wyjscie;
    }
}
