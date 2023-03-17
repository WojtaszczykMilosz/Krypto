package pl.model;

import java.math.BigInteger;
import java.util.BitSet;

public class DES {

    byte[] klucz;
    byte[][] podklucze;

    public final static byte[] PC1 = {57, 49, 41, 33, 25, 17,  9,
                   1, 58, 50, 42, 34, 26, 18,
                  10,  2, 59, 51, 43, 35, 27,
                  19, 11,  3, 60, 52, 44, 36,
                  63, 55, 47, 39, 31, 23, 15,
                   7, 62, 54, 46, 38, 30, 22,
                  14,  6, 61, 53, 45, 37, 29,
                  21, 13,  5, 28, 20, 12,  4};

    public final static byte[] PC2 = {14, 17, 11, 24,  1,  5,  3, 28,
                  15,  6, 21, 10, 23, 19, 12,  4,
                  26,  8, 16,  7, 27, 20, 13,  2,
                  41, 52, 31, 37, 47, 55, 30, 40,
                  51, 45, 33, 48, 44, 49, 39, 56,
                  34, 53, 46, 42, 50, 36, 29, 32};

    public final static byte[] przesuniecia = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};

    public final static byte [] PR = { 32,   1,  2,  3,  4,  5,  4,  5,
                    6,   7,  8,  9,  8,  9, 10, 11,
                   12,  13, 12, 13, 14, 15, 16, 17,
                   16,  17, 18, 19, 20, 21, 20, 21,
                   22,  23, 24, 25, 24, 25, 26, 27,
                   28,  29, 28, 29, 30, 31, 32,  1};

    public final static byte[] Pblock = {16, 7, 20, 21, 29, 12, 28, 17,
             1, 15, 23, 26,  5, 18, 31, 10,
             2,  8, 24, 14, 32, 27,  3,  9,
            19, 13, 30,  6, 22, 11,  4, 25};

    public final byte[][] s_box = {
            { 14,  4, 13, 1,  2, 15, 11,  8,  3, 10,  6, 12,  5,  9, 0,  7,
               0, 15,  7, 4, 14,  2, 13,  1, 10,  6, 12, 11,  9,  5, 3,  8,
               4,  1, 14, 8, 13,  6,  2, 11, 15, 12,  9,  7,  3, 10, 5,  0,
              15, 12,  8, 2,  4,  9,  1,  7,  5, 11,  3, 14, 10,  0, 6, 13},

            { 15,  1,  8, 14,  6, 11,  3,  4,  9, 7,  2, 13, 12, 0, 5,  10,
               3, 13,  4,  7, 15,  2,  8, 14, 12, 0,  1, 10,  6, 9, 11,  5,
               0, 14,  7, 11, 10,  4, 13,  1,  5, 8, 12,  6,  9, 3,  2, 15,
              13,  8, 10,  1,  3, 15,  4,  2, 11, 6,  7, 12,  0, 5, 14,  9},

            { 10,  0,  9, 14, 6,  3, 15,  5,  1, 13, 12,  7, 11,  4,  2,  8,
              13,  7,  0,  9, 3,  4,  6, 10,  2,  8,  5, 14, 12, 11, 15,  1,
              13,  6,  4,  9, 8, 15,  3,  0, 11,  1,  2, 12,  5, 10, 14,  7,
               1, 10, 13,  0, 6,  9,  8,  7,  4, 15, 14,  3, 11,  5,  2, 12},

            {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15,
            13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9,
            10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4,
            3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14},

            {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9,
            14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6,
            4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14,
            11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3},

            {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11,
            10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8,
            9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6,
            4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13},

            {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1,
            13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6,
            1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2,
            6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12},

            {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7,
            1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2,
            7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8,
            2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
    };


    public DES(byte[] klucz) {
        this.klucz = klucz;
    }
    public DES(){};


    private byte[] Ppermutation(byte[] bytes){

        return Operacje.getBits(bytes,Pblock);
    }


    private byte[] polaczBloki(byte[] a, byte[] b,int rozmiarA,int rozmiarB){

        int ileBajtow = (rozmiarA+rozmiarB - 1) / 8 + 1;
        byte[] wyjscie = new byte[ileBajtow];


        for (int i = 0; i < rozmiarA; i++){
            int wartosc = Operacje.getBitAt(a,i);
            wyjscie = Operacje.setBitAt(wyjscie,i,wartosc);

        }

        for (int i = 0; i < rozmiarB; i++){
            int wartosc = Operacje.getBitAt(b,i);
            wyjscie = Operacje.setBitAt(wyjscie,rozmiarA+i,wartosc);
        }


        return wyjscie;
    }

    public byte[] expansion(byte[] msg){
        byte[] afterExpansion = Operacje.getBits(msg,PR);
        return afterExpansion;

    }


    public byte[][] generujPodklucze(){
        int rozmiarPodKlucza = 28;
        byte[] p = Operacje.getBits(klucz,PC1);
        byte[] a = Operacje.kopiuj(p,0,rozmiarPodKlucza);
        byte[] b = Operacje.kopiuj(p,rozmiarPodKlucza,rozmiarPodKlucza);
        byte[][] wygenerowane = new byte[16][48];
        byte[] ab;

        for (int i = 0; i < 16; i++) {
            a = Operacje.rotateBitsToLeft(a,rozmiarPodKlucza,przesuniecia[i]);
            b = Operacje.rotateBitsToLeft(b,rozmiarPodKlucza,przesuniecia[i]);
            ab = polaczBloki(a,b,rozmiarPodKlucza,rozmiarPodKlucza);
            wygenerowane[i] = Operacje.getBits(ab,PC2);

        }

        return wygenerowane;
    }

    public byte[] zwroc32bity(byte[] bytes){

        byte[] wyj = new byte[4];
        byte[] kol = {0};
        byte[] wie = {0};
        for(int i = 0; i < 8; i++){
            byte[] szostka = Operacje.zwroc6bitow(bytes,i);
            byte[] czworka = new byte[1];

            wie = Operacje.setBitAt(wie,6,Operacje.getBitAt(szostka,2));
            wie = Operacje.setBitAt(wie,7,Operacje.getBitAt(szostka,7));
            kol = Operacje.setBitAt(kol,4,Operacje.getBitAt(szostka,3));
            kol = Operacje.setBitAt(kol,5,Operacje.getBitAt(szostka,4));
            kol = Operacje.setBitAt(kol,6,Operacje.getBitAt(szostka,5));
            kol = Operacje.setBitAt(kol,7,Operacje.getBitAt(szostka,6));


            czworka[0] = s_box[i][16 * wie[0] + kol[0]];

            wyj = Operacje.setBitAt(wyj, (i * 4),Operacje.getBitAt(czworka,4));
            wyj = Operacje.setBitAt(wyj,1 + (i * 4),Operacje.getBitAt(czworka,5));
            wyj = Operacje.setBitAt(wyj,2 + (i * 4),Operacje.getBitAt(czworka,6));
            wyj = Operacje.setBitAt(wyj,3 + (i * 4),Operacje.getBitAt(czworka,7));



        }

        return wyj;
    }

}
