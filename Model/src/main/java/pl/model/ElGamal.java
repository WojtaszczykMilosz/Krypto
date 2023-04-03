package pl.model;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class ElGamal {
    public BigInteger p;
    public BigInteger g;
    public BigInteger x;
    public BigInteger y;
    public BigInteger pMinus1;

    public String getP() {
        return p.toString(16);
    }

    public String getG() {
        return g.toString(16);
    }

    public String getX() {
        return x.toString(16);
    }

    public String getY() {
        return y.toString(16);
    }

    public int dlugoscP = 264;

    public ElGamal() {
        generujKlucze();
    }

    public void generujKlucze() {
        this.p = BigInteger.probablePrime(dlugoscP, new Random());

        this.g = new BigInteger(dlugoscP-5,new Random());

        this.x = new BigInteger(dlugoscP-5,new Random());

        this.pMinus1 = p.subtract(BigInteger.ONE);

        this.y = this.g.modPow(x,p);

    }

    public void ustawKlucze(BigInteger p,BigInteger g,BigInteger x, BigInteger y) throws IOException {

        if (p.compareTo(this.p) == 0 && g.compareTo(this.g) == 0 && x.compareTo(this.x) == 0 && y.compareTo(this.y) == 0) {
            return;
        }

        if (p.bitLength() != dlugoscP) {
            throw new IOException("p musi być długości 264 bitów");
        } else if (!p.isProbablePrime(5)) {
            throw new IOException("p musi być liczba pierwsza");
        } else if (p.compareTo(g) == -1 ) {
            throw new IOException("g musi być mniejsza niż p - 1");
        } else if (p.compareTo(x) == -1)  {
            throw new IOException("x musi być mniejsza niż p - 1");
        } else if (y.compareTo(g.modPow(x,p)) != 0) {
            throw new IOException("y musi być równe g^x mod p");
        }

        this.p = p;
        this.pMinus1 = p.subtract(BigInteger.ONE);
        this.g = g;
        this.x = x;
        this.y = y;
    }

    public byte[] szyfrujBlok(byte[] wej, BigInteger k){

        BigInteger c2 = new BigInteger(1,wej).multiply(y.modPow(k,p)).mod(p);
        return Operacje.bigToArray(c2,33);
    }
    public byte[] deszyfrujBlok(BigInteger c1, BigInteger c2){

        BigInteger wynik = c2.multiply(c1.modPow(x,p).modInverse(p)).mod(p);
        return Operacje.bigToArray(wynik,32);
    }
    public byte[] szyfrujWiadomosc(byte[] wej){

        BigInteger k;
        do{
            k = new BigInteger(dlugoscP-1,new Random());

        }while(!k.gcd(pMinus1).equals(BigInteger.ONE));

        BigInteger c1 = g.modPow(k,p);

        int maxindex = wej.length / 32;
        int ilezer = 32 - wej.length % 32;
        byte [] wyj;
        if(wej.length % 32 == 0){
            wyj = new byte[maxindex * 33 + 33];
            maxindex--;
        }else {
            wyj = new byte[(maxindex + 1) * 33 + 33 + 1];
            wyj[(maxindex + 1) * 33 + 33 ] = (byte) ilezer;
        }
        byte[] szyfr;
        byte[] C = Operacje.bigToArray(c1,33);
        for(int i = 0;i<264;i++){
            Operacje.setBit(wyj,i,Operacje.getBit(C,i));
        }
        for(int i = 0;i<=maxindex;i++){
            szyfr = szyfrujBlok(Operacje.zwrocBajty(i,wej,32), k);
            for(int j =0;j<264;j++){
                Operacje.setBit(wyj,264 + j+(i * 264),Operacje.getBit(szyfr,j));
            }
        }

        return wyj;
    }

    public byte[] deszyfrujWiadomosc(byte[] wej){
        int ilezer = 0;
        int maxindex = wej.length / 33 - 1;
        if(wej.length % 33 != 0){
            maxindex = (wej.length - 1)/ 33 - 1 ;
            ilezer = wej[(maxindex+1) * 33];
        }
        int rozmair = ((maxindex) * 32 - ilezer);

        byte[] wyj = new byte[rozmair];


        byte[] help = new byte[33];
        for (int i = 0;i<264;i++){
            Operacje.setBit(help,i,Operacje.getBit(wej,i));

        }
        BigInteger C1 = new BigInteger(1,help);


        for(int i =1;i<=maxindex;i++){
            help = deszyfrujBlok(C1,new BigInteger(1,Operacje.zwrocBajty(i,wej,33)));
            if(i == maxindex && wej.length % 33 != 0){

                for(int j = 0;j< 256 - (ilezer * 8);j++){
                    Operacje.setBit(wyj,j+((i -1) * 256),Operacje.getBit(help,j + (ilezer * 8)));
                }

            }else{
                for(int j =0;j<256;j++) {
                    Operacje.setBit(wyj,j+((i -1) * 256),Operacje.getBit(help,j));
                }
            }
        }


        return wyj;
    }

}