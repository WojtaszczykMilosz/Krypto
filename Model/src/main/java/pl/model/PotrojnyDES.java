package pl.model;

import java.io.IOException;
import java.util.Arrays;

public class PotrojnyDES {

    private DES DES1;
    private DES DES2;
    private DES DES3;


    byte bufor;

    public PotrojnyDES(){
//        byte[] klucz1 = new byte[8];
//        byte[] klucz2 = new byte[8];
//        byte[] klucz3 = new byte[8];
//
//        for(int i =0;i<64;i++){
//            Operacje.setBit(klucz1,i,Operacje.getBit(klucz1,i));
//            Operacje.setBit(klucz2,i,Operacje.getBit(klucz1,i + 64));
//            Operacje.setBit(klucz3,i,Operacje.getBit(klucz1,i + 128));
//
//        }

//        if (klucz1.length != 8 || klucz2.length != 8 || klucz3.length != 8) {
//            throw new RuntimeException("ee");
//        }
//        DES1 = new DES(klucz1);
//        DES2 = new DES(klucz2);
//        DES3 = new DES(klucz3);
//        DES1.generujPodklucze();
//        DES2.generujPodklucze();
//        DES3.generujPodklucze();

    }

    public void ustawKlucze(byte[] klucz1, byte[] klucz2, byte[] klucz3) throws IOException{
        if (klucz1.length != 8 || klucz2.length != 8 || klucz3.length != 8) {
            throw new IOException("Złe wartości klucza - powinny mieć 8 bajtow (UTF-8 encoding)");
        }
        DES1 = new DES(klucz1);
        DES2 = new DES(klucz2);
        DES3 = new DES(klucz3);
        DES1.generujPodklucze();
        DES2.generujPodklucze();
        DES3.generujPodklucze();
    }

    public byte[] szyfrujWiadomosc(byte[] wej){
        bufor = -1;
        byte[] wyj;
        wyj = DES1.szyfrujWiadomosc(wej);
        if (wyj.length % 8 != 0) {
            bufor = wyj[wyj.length - 1];
            wyj = Arrays.copyOf(wyj,wyj.length-1);
        }

        wyj = DES2.deszyfrujWiadomosc(wyj);
        wyj = DES3.szyfrujWiadomosc(wyj);

        return wyj;
    }

    public byte[] deszyfrujWiadomosc(byte[] wej){
        byte[] wyj;
        wyj = DES3.deszyfrujWiadomosc(wej);
        wyj = DES2.szyfrujWiadomosc(wyj);
        if (bufor != -1){
            wyj = Arrays.copyOf(wyj,wyj.length+1);
            wyj[wyj.length-1] = bufor;
        }
        wyj = DES1.deszyfrujWiadomosc(wyj);
        return wyj;
    }


}
