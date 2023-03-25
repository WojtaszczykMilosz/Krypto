package pl.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        byte [] b;
        b = OperacjePlikowe.wczytajZpliku("JD.pdf");
        System.out.println(b.length);
        ElGamal el = new ElGamal();
        byte[] szyfr = el.szyfrujWiadomosc(b);
        System.out.println(szyfr.length);
//        System.out.println(szyfr.length);
//        System.out.println(szyfr[102]);
        System.out.println("SIEMA");
//        INPUT.zapiszDoPliku("cos.txt",szyfr);
        byte[] deszyfr = el.deszyfrujWiadomosc(szyfr);
        System.out.println(deszyfr.length);
        OperacjePlikowe.zapiszDoPliku("JD2.pdf",deszyfr);
        byte[] b1 = OperacjePlikowe.wczytajZpliku("JD.pdf");
        byte[] b2 = OperacjePlikowe.wczytajZpliku("JD2.pdf");
        System.out.println(Arrays.equals(b1,b2));
        System.out.println(Arrays.equals(b2,szyfr));


    }
}
