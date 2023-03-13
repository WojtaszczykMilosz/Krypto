package pl.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.BitSet;

public class Main {
    public static void main(String[] args) {
//        byte[] klucz  = {1,2,3,4,5,6,7,8};
//        System.out.println(klucz.length);
//        des.generujPodklucze();
//        System.out.println(des.generujPodklucze()[12].length);
        byte[] a = {5,2,114,-20};
        DES des  = new DES(a);
        byte[][] test = des.generujPodklucze();
//
//        System.out.println();
//        byte[] test = {127,127,127,127};
//        des.expansion(test);
//        for (int i = 0; i < 16; i++) {
//            System.out.println(i);
//            for (byte c : test[i]) {
//                System.out.print(Integer.toBinaryString(c & 255 | 256).substring(1));
//            }
//            System.out.println();

//        }


//
//        for (byte c :a) {
//            System.out.print(Integer.toBinaryString(c & 255 | 256).substring(1));
//        }
//        System.out.println();
//        byte[] x  = Operacje.rotateBitsToLeft(a,5, 1);
//        System.arraycopy(a,0,x,0,4);
//        System.out.println();
//        for (byte c :x) {
//            System.out.print(Integer.toBinaryString(c & 255 | 256).substring(1));
//        }
//        System.out.println();









    }
}