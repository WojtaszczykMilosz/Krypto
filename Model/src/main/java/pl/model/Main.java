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
//        byte[] a = {0,1,1,1};
//        DES des  = new DES(a);
//        byte[][] test = des.generujPodklucze();
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
//
//            byte[] b = {127};
//            BigInteger bi = new BigInteger("128");
//            System.out.println(bi.toByteArray().length);
//            INPUT in = new INPUT();
//        DES des = new DES();
//        for(int i = 0; i<8;i++){
//            for(int j = 0; j<64;j++){
//                System.out.print(des.s_box[i][j] + " ");
//                if (j % 15 == 0 && j != 0){
//                    System.out.println();
//                }
//            }
//            System.out.println();
//        }
//        BigInteger wyj = new BigInteger("2");
//        for(int i = 7 ;i>=0 ;i--){
//            System.out.print(wyj.testBit(i) ? 1 : 0);
//        }

        INPUT in = new INPUT();
        in.wczytajTekst();

        byte[] b = {5};
        DES des = new DES();
        des.zwroc32bity(in.tekst);




        }


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
