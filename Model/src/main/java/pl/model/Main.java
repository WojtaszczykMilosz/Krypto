package pl.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.BitSet;

public class Main {
    public static void main(String[] args) {
        byte[] klucz  = {1,2,3,4,5,6,7,8};
//        System.out.println(klucz.length);
//        des.generujPodklucze();
//        System.out.println(des.generujPodklucze()[12].length);
        byte[] a = {-127,-127,-127,-127,-127,-127,-127};
        DES des  = new DES(a);
//        des.generujPodklucze();
//
        byte[] test = {127,127,127,127};
//        des.expansion(test);

        byte[] x = new byte[4];

        System.arraycopy(a,0,x,0,4);
        System.out.println(Operacje.fillZeros(a,4).length);









    }
}