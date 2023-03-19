package pl.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

         final byte[] PC1 = {57, 49, 41, 33, 25, 17,  9,
                1, 58, 50, 42, 34, 26, 18,
                10,  2, 59, 51, 43, 35, 27,
                19, 11,  3, 60, 52, 44, 36,
                63, 55, 47, 39, 31, 23, 15,
                7, 62, 54, 46, 38, 30, 22,
                14,  6, 61, 53, 45, 37, 29,
                21, 13,  5, 28, 20, 12,  4};
         byte[] IP={58,	50,	42,	34,	26,	18,	10,	2,
                 60	,52	,44	,36	,28,	20,	12,	4,
                 62,	54,	46,	38,	30,	22,	14,	6,
                 64	,56,	48,	40,	32,	24,	16,	8,
                 57,	49,	41,	33,	25,	17,	9,	1,
                 59,	51,	43,	35,	27,	19,	11,	3,
                 61,	53,	45	,37,	29,	21	,13,	5,
                 63,	55,	47,	39,	31,	23,	15	,7};

        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.


        byte[] klucz = INPUT.wczytajZpliku("klucz.txt");
        byte[] tekst = INPUT.wczytajZpliku("szyfrdoc.txt");

        //INPUT.wypiszBity(klucz);
       // INPUT.wypiszBity(tekst);


//        INPUT.wypiszBity(IP);
//        byte[] tab = Arrays.copyOf(IP,IP.length+1);
//        INPUT.wypiszBity(tab);




        DES pojedynczydes = new DES();
        pojedynczydes.klucz = klucz;
    pojedynczydes.generujPodklucze();
//
//        System.out.println();
        String  a = "01234567";
        String b = "11223344";
        String c = "klucz123";
        String  x= "12345678";
//        des.klucz = klucz;
//        des.generujPodklucze();
//
        byte[] szyfr = pojedynczydes.szyfrujWiadomosc(x.getBytes());
//
//        byte[] deszyfr = pojedynczydes.deszyfrujWiadomosc(szyfr);
//
//        szyfr = pojedynczydes.szyfrujWiadomosc(deszyfr);
//
//        deszyfr = pojedynczydes.deszyfrujWiadomosc(szyfr);
//
//        System.out.println(new String(deszyfr).equals(x));
//
//        byte[] pdf = INPUT.wczytajZpliku("pdf.pdf");
//
        try {
            PotrojnyDES des = new PotrojnyDES();
            des.ustawKlucze(a.getBytes(),b.getBytes(),c.getBytes());
            szyfr = des.szyfrujWiadomosc(x.getBytes("UTF-8"));

            byte[] deszyfruj = des.deszyfrujWiadomosc(szyfr);
            System.out.println(new String(deszyfruj,"UTF-8"));
        } catch (IOException e) {

        }
        //INPUT.wypiszBity(szyfr);


//        INPUT.zapiszDoPliku("doc2.doc",szyfr);
//        byte[] deszyfr = des.deszyfrujWiadomosc(szyfr);
//        INPUT.wypiszBity(deszyfr);




    }




    }
