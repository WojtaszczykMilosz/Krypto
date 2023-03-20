package pl.model;

public class DES {

    private byte[] klucz;
    private byte[][] podklucze;

    private final static byte[] permutacjaPC1 = {57, 49, 41, 33, 25, 17,  9,
                   1, 58, 50, 42, 34, 26, 18,
                  10,  2, 59, 51, 43, 35, 27,
                  19, 11,  3, 60, 52, 44, 36,
                  63, 55, 47, 39, 31, 23, 15,
                   7, 62, 54, 46, 38, 30, 22,
                  14,  6, 61, 53, 45, 37, 29,
                  21, 13,  5, 28, 20, 12,  4};

    private final static byte[] permutacjaPC2 = {14, 17, 11, 24,  1,  5,  3, 28,
                  15,  6, 21, 10, 23, 19, 12,  4,
                  26,  8, 16,  7, 27, 20, 13,  2,
                  41, 52, 31, 37, 47, 55, 30, 40,
                  51, 45, 33, 48, 44, 49, 39, 56,
                  34, 53, 46, 42, 50, 36, 29, 32};

    private final static byte[] przesuniecia = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    private final static byte [] permutacjaRozszerzajaca = { 32,   1,  2,  3,  4,  5,  4,  5,
                    6,   7,  8,  9,  8,  9, 10, 11,
                   12,  13, 12, 13, 14, 15, 16, 17,
                   16,  17, 18, 19, 20, 21, 20, 21,
                   22,  23, 24, 25, 24, 25, 26, 27,
                   28,  29, 28, 29, 30, 31, 32,  1};

    private final static byte[] pBlok = {16, 7, 20, 21, 29, 12, 28, 17,
             1, 15, 23, 26,  5, 18, 31, 10,
             2,  8, 24, 14, 32, 27,  3,  9,
            19, 13, 30,  6, 22, 11,  4, 25};

    private final byte[][] sBoksy = {
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

            { 7, 13, 14, 3,  0,  6,  9, 10,  1, 2, 8,  5, 11, 12,  4, 15,
             13,  8, 11, 5,  6, 15,  0,  3,  4, 7, 2, 12,  1, 10, 14,  9,
             10,  6,  9, 0, 12, 11,  7, 13, 15, 1, 3, 14,  5,  2,  8,  4,
             3,  15,  0, 6, 10,  1, 13,  8,  9, 4, 5, 11, 12,  7,  2, 14},

            { 2, 12,  4,  1,  7, 10, 11,  6,  8,  5,  3, 15, 13, 0, 14,  9,
             14, 11,  2, 12,  4,  7, 13,  1,  5,  0, 15, 10,  3, 9,  8,  6,
              4,  2,  1, 11, 10, 13,  7,  8, 15,  9, 12,  5,  6, 3,  0, 14,
             11,  8, 12,  7,  1, 14,  2, 13,  6, 15,  0,  9, 10, 4,  5,  3},

            { 12,  1, 10, 15, 9,  2,  6,  8,  0, 13,  3,  4, 14,  7,  5, 11,
              10, 15,  4,  2, 7, 12,  9,  5,  6,  1, 13, 14,  0, 11,  3,  8,
               9, 14, 15,  5, 2,  8, 12,  3,  7,  0,  4, 10,  1, 13, 11,  6,
               4,  3,  2, 12, 9,  5, 15, 10, 11, 14,  1,  7,  6,  0,  8, 13},

            { 4, 11,  2, 14, 15, 0,  8, 13,  3, 12, 9,  7,  5, 10, 6,  1,
             13,  0, 11,  7,  4, 9,  1, 10, 14,  3, 5, 12,  2, 15, 8,  6,
              1,  4, 11, 13, 12, 3,  7, 14, 10, 15, 6,  8,  0,  5, 9,  2,
              6, 11, 13,  8,  1, 4, 10,  7,  9,  5, 0, 15, 14,  2, 3, 12},

            { 13,  2,  8, 4,  6, 15, 11,  1, 10,  9,  3, 14,  5,  0, 12,  7,
               1, 15, 13, 8, 10,  3,  7,  4, 12,  5,  6, 11,  0, 14,  9,  2,
               7, 11,  4, 1,  9, 12, 14,  2,  0,  6, 10, 13, 15,  3,  5,  8,
               2,  1, 14, 7,  4, 10,  8, 13, 15, 12,  9,  0,  3,  5,  6, 11}
    };


    public DES(byte[] klucz) {
        this.klucz = klucz;
    }

    public DES() {}


    public byte[] permutacjaPBlok(byte[] wej){

        return Operacje.permutacja(wej, pBlok,4 );
    }


    public byte[] permutacjaRozszerzajaca(byte[] wej){

        return Operacje.permutacja(wej, permutacjaRozszerzajaca, 6);

    }

    public byte[] permutacjaPC1(byte[] wej){

        return Operacje.permutacja(wej, permutacjaPC1, 7);

    }

    public byte[] permutacjaPC2(byte[] wej){

        return Operacje.permutacja(wej, permutacjaPC2, 6);

    }

    public void generujPodklucze(){

        byte[] klucz56 = permutacjaPC1(klucz);
        byte[] lewaCzescKlucza = Operacje.skopiujBity(klucz56,0,28,4);
        byte[] prawaCzescKlucza = Operacje.skopiujBity(klucz56,28,28,4);
        byte[][] wygenerowaneKlucze = new byte[16][48];
        byte[] kluczPoPrzesunieciu;

        for (int i = 0; i < 16; i++) {
            lewaCzescKlucza = Operacje.przesunieciePolowekKlucza(lewaCzescKlucza, przesuniecia[i]);
            prawaCzescKlucza = Operacje.przesunieciePolowekKlucza(prawaCzescKlucza, przesuniecia[i]);
            kluczPoPrzesunieciu = Operacje.polaczDwieTablice(lewaCzescKlucza,prawaCzescKlucza,28,7);
            wygenerowaneKlucze[i] = permutacjaPC2(kluczPoPrzesunieciu);

        }
        podklucze = wygenerowaneKlucze;

    }

    public byte[] operacjaSBoks(byte[] wej){

        byte[] wyj = new byte[4];
        byte[] kol = {0};
        byte[] wie = {0};
        for(int i = 0; i < 8; i++){
            byte[] szostka = Operacje.zwroc6bitow(wej,i);
            byte[] czworka = new byte[1];

             Operacje.setBit(wie,6,Operacje.getBit(szostka,2));
             Operacje.setBit(wie,7,Operacje.getBit(szostka,7));
             Operacje.setBit(kol,4,Operacje.getBit(szostka,3));
             Operacje.setBit(kol,5,Operacje.getBit(szostka,4));
             Operacje.setBit(kol,6,Operacje.getBit(szostka,5));
             Operacje.setBit(kol,7,Operacje.getBit(szostka,6));


             czworka[0] = sBoksy[i][16 * wie[0] + kol[0]];

             Operacje.setBit(wyj, (i * 4),Operacje.getBit(czworka,4));
             Operacje.setBit(wyj,1 + (i * 4),Operacje.getBit(czworka,5));
             Operacje.setBit(wyj,2 + (i * 4),Operacje.getBit(czworka,6));
             Operacje.setBit(wyj,3 + (i * 4),Operacje.getBit(czworka,7));



        }

        return wyj;
    }

    public byte[] szyfrujBlok(byte[] wej){



        byte[] l = Operacje.skopiujBity(wej,0,32,4);
        byte[] p = Operacje.skopiujBity(wej,32,32,4);

        for(int i = 0; i < 16; i++){
            byte[] wynikRundy = runda(l,p,i);
            l = p;
            p = wynikRundy;
        }
        return Operacje.polaczDwieTablice(p,l,32 ,8 );

    }

    public byte[] runda(byte[] l, byte[] p,int i){
        byte[] r = permutacjaRozszerzajaca(p);
        byte[] xor = Operacje.XOR(r,podklucze[i],6 );
        byte[] s = operacjaSBoks(xor);
        byte[] pp = permutacjaPBlok(s);
        return  Operacje.XOR(pp,l, 4);

    }

    public byte[] deszyfrujBlok(byte[] wej){

        byte[] l = Operacje.skopiujBity(wej,0,32,4);
        byte[] p = Operacje.skopiujBity(wej,32,32,4);

        for(int i = 15; i >= 0; i--){
            byte[] wynikRundy = runda(l,p,i);
            l = p;
            p = wynikRundy;
        }
        return Operacje.polaczDwieTablice(p,l,32 ,8 );

    }


}
