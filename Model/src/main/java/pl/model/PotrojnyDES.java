package pl.model;

import java.io.IOException;


public class PotrojnyDES {

    private DES DES1;
    private DES DES2;
    private DES DES3;


    public PotrojnyDES(){

    }

    /**
     * Ustawia podklucze dla każdego z trzech algorytmów DESa
     * @param klucz1 tablica 8 bajtów
     * @param klucz2 tablica 8 bajtów
     * @param klucz3 tablica 8 bajtów
     */
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

    /**
     * Szyfruje podaną wiadomość przy użyciu algorytmu potrójnego DESa. Jesłi jest to konieczne uzupełnia wiadomość szyfrowaną o zera, a w zwracanej tablicy dodaje
     * bajt określający ilosć zer
     * @param wej wejściowa tablica bajtów
     * @return zaszyfrowana wiadomość, tablica bajtów
     */
    public byte[] szyfrujWiadomosc(byte[] wej) {
        int maxindex = wej.length / 8;
        boolean rownosc = wej.length % 8 == 0;
        byte[] wyj;

        if(rownosc){
            wyj = new byte[wej.length];
        } else {
            wyj = new byte[maxindex * 8 + 9];
        }


        byte[] szyfr;

        for(int i = 0; i <= maxindex; i++){

            if(i == maxindex && rownosc){
                break;
            }

            szyfr = DES1.szyfrujBlok(Operacje.zwroc64bity(i,wej));
            szyfr = DES2.deszyfrujBlok(szyfr);
            szyfr = DES3.szyfrujBlok(szyfr);

            for(int j = 0; j < 64; j++){
                Operacje.setBit(wyj,j + (i * 64),Operacje.getBit(szyfr,j));
            }

        }

        if(!rownosc){
            wyj[maxindex * 8 + 8] = (byte)( 8 - wej.length + maxindex * 8);
        }



        return wyj;
    }

    /**
     * Odszyfrowywanie podanej wiadomość przy użyciu algorytmu potrójnego DESa. Jesłi jest to konieczne usuwa niepotrzebne zera
     * @param wej wejściowa tablica bajtów
     * @return odszyfrowana wiadomość, tablica bajtów
     */
    public byte[] deszyfrujWiadomosc(byte[] wej){
        int maxindex = wej.length / 8;
        boolean rownosc = wej.length % 8 == 0;
        byte[] wyj;
        int delta = 0;

        if(rownosc){
            wyj = new byte[wej.length];
        } else {
            delta = wej[maxindex * 8];
            wyj = new byte[maxindex * 8 - delta];
        }

        byte[] deszyfr;

        for(int i = 0; i < maxindex; i++){

            deszyfr = DES3.deszyfrujBlok(Operacje.zwroc64bity(i,wej));
            deszyfr = DES2.szyfrujBlok(deszyfr);
            deszyfr = DES1.deszyfrujBlok(deszyfr);

            if(i == maxindex - 1){
                for(int j = 0;j < (8-delta)*8; j++){
                    Operacje.setBit(wyj,j + (i * 64),Operacje.getBit(deszyfr,j));
                }
            }else {

                for (int j = 0; j < 64; j++) {
                    Operacje.setBit(wyj, j + (i * 64), Operacje.getBit(deszyfr, j));
                }
            }
        }

        return wyj;
    }


}
