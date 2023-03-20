package pl.model;


import java.util.Arrays;

public class Operacje {

    /**
     * @param wej wejściowa tablica bajtów
     * @param permutacja tablica indeksów po permutacji
     * @param ileBajt długość zwracanej tablicy
     * @return zwraca tablice bajtów, w której bajty z tablicy wej są umieszczone w kolejności zgodnej z indeksami z tablicy permutacja
     */
    public static byte[] permutacja(byte[] wej, byte[] permutacja, int ileBajt){

        byte[] wyj = new byte[ileBajt];

        for (int i = 0; i < permutacja.length; i++) {

            setBit(wyj,i,getBit(wej, permutacja[i]-1));

        }

        return wyj;
    }

    /**
     * @param wej tablica wejściowa
     * @param pos pozycja, której bit chcemy sprawdzić
     * @return zwraca wartość bitu w tablicy wej na pozycji pos
     */
    public static int getBit(byte[] wej, int pos){

        int ktoryBajt = pos / 8;
        int ktoryBit = pos % 8;
        byte bajt = wej[ktoryBajt];
        return ((bajt << (ktoryBit)) & 128) > 0 ? 1 : 0;
    }

    /**
     * @param wej wejściowa tablica bajtów, której bit chcemy zmienić
     * @param pos pozycja, której bit chcemy sprawdzić
     * @param wartosc wartość, którą chcemy ustawić
     */
    public static void setBit(byte[] wej, int pos, int wartosc){

        int ktoryBajt = pos / 8;
        int ktoryBit = pos % 8;
        byte bajt = wej[ktoryBajt];
        if(wartosc == 0){
            bajt = (byte)(~(128 >> ktoryBit) & bajt);
        }else {
            bajt = (byte)((128 >> ktoryBit) | bajt);
        }
        wej[ktoryBajt] = bajt;


    }

    /**
     * @param wej wejściowa tablica bajtów
     * @param od określa, od której pozycji tablicy wej chcemy zacząć kopiowanie
     * @param ile określa ile bitów chcemy skopiować
     * @param ilaBajt określa długość zwracanej tablicy bajtów
     * @return zwraca kopie części tablicy wej
     */

    public static byte[] skopiujBity(byte[] wej, int od, int ile, int ilaBajt) {

        byte[] wyj = new byte[ilaBajt];

        for (int i = 0; i < ile; i++) {

            setBit(wyj,i, getBit(wej,od+i));

        }

        return wyj;
    }

    /**
     * @param wej wejściowa tablica bajtów
     * @param przesuniecie określa długość przesunięcia
     * @return zwraca tablicę ośmiu bajtów, w której pierwsze 28 bitów jest odzwierciedleniem przeusnięcia w lewo o określoną ilość pozycji,
     * pierwszych 28 bitów tablicy wejściowej
     */

    public static byte[] przesunieciePolowekKlucza(byte[] wej, int przesuniecie){

        byte[] wyj = Arrays.copyOf(wej,4);

        for(int i = 0; i < przesuniecie; i++){

            int bit = Operacje.getBit(wyj,0);

            for(int j = 0; j < 27; j++){
                setBit(wyj,j,getBit(wyj,(j + 1)));

            }
            setBit(wyj,27,bit);
        }

        return wyj;
    }

    /**
     * @param wej wejściowa tablica bajtów
     * @param numer indeks bitów, które mają zostać zwrócone
     * @return zwraca określone 6 kolejnych bitów tablicy wejściowej, w postaci tablicy jednego bajtu, w którym dwa pierwsze bity są zerowe
     */
    public static byte[] zwroc6bitow(byte[] wej, int numer){
        byte[] wyj = {0};

        for(int i = 0; i < wej.length; i++){
            Operacje.setBit(wyj,i + 2,Operacje.getBit(wej,i + (numer * 6)));
        }

        return wyj;
    }

    /**
     * @param l lewa cześć łączonych bajtów
     * @param p prawa cześć łączonych bajtów
     * @param ile określa ile bitów z każdej tablicy bajtów ma zostać użytych do połączenia
     * @param ileBajt długość zwracanej tablicy bajtów
     * @return zwraca tablicę bajtów która powstała w wyniku powstania @param ile bajtów z każdej z podanych tablic
     */
    public static byte[] polaczDwieTablice(byte[] l, byte[] p, int ile, int ileBajt){

        byte[] wyj = new byte[ileBajt];

        for(int i = 0; i < ile; i++){
            Operacje.setBit(wyj,i,Operacje.getBit(l,i));
        }

        for(int i = 0;i < ile; i++){
            Operacje.setBit(wyj,ile + i,Operacje.getBit(p,i));
        }

        return wyj;
    }

    /**
     * @param bytes1 pierwsza tablica bajtów
     * @param bytes2 pruga tablica bajtów
     * @param ileBajt długość zwracanej tablicy bajtów
     * @return tablica bajtów, której bity powstały w wyniku XORowania odpowiednich bitów tablic 1 i 2
     */
    public static byte[] XOR(byte[] bytes1, byte[] bytes2, int ileBajt){

        byte[] wyj = new byte[ileBajt];

        for(int i = 0; i < ileBajt; i++){
            wyj[i] = (byte)(bytes1[i] ^ bytes2[i]);
        }

        return wyj;

    }

    /**
     * @param numer indeks kolejnych 64 bitów tablicy wejściowej
     * @param wej wejściowa tablica bajtów
     * @return zwraca określone kolejne 64 bitów tablicy wejściowej. Jeżeli jest to konieczne tablica jest uzupełniana zerami
     */
    public static byte[] zwroc64bity(int numer, byte[] wej){

        int maxindex = wej.length / 8;

        if(numer > maxindex){
            return null;
        }

        byte[] wyj = new byte[8];

        if((wej.length % 8 == 0) || (numer < maxindex)) {
            for(int i = 0; i < 8; i++){
                wyj[i] = wej[i + (numer * 8)];
            }

        }else{
            int delta = wej.length - (numer * 8);
            for(int i = 0; i < delta; i++){
                wyj[i] = wej[i + (numer * 8)];
            }

            for(int i = delta; i < 8; i++){
                wyj[i] = 0;
            }
        }
        return wyj;
    }


}
