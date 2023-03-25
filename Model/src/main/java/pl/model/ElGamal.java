package pl.model;

import java.math.BigInteger;
import java.util.Random;

public class ElGamal {
    public BigInteger p;
    public BigInteger g;
    public BigInteger x;
    public BigInteger y;
    public BigInteger pMinus1;
    public BigInteger k;
    public int dlugoscP = 264;

    public ElGamal() {
        this.p = BigInteger.probablePrime(dlugoscP, new Random());

        this.g = new BigInteger(dlugoscP-5,new Random());

        this.x = new BigInteger(dlugoscP-5,new Random());

        this.pMinus1 = p.subtract(BigInteger.ONE);

        this.y = this.g.modPow(x,p);

        do{
            this.k = new BigInteger(dlugoscP-1,new Random());

        }while(!this.k.gcd(pMinus1).equals(BigInteger.ONE));
    }

    public byte[] szyfrujBlok(byte[] wej){

        BigInteger c2 = new BigInteger(1,wej).multiply(y.modPow(k,p)).mod(p);
        return Operacje.bigToArray(c2,34);
    }
    public byte[] deszyfrujBlok(BigInteger c1, BigInteger c2){

        BigInteger wynik = c2.multiply(c1.modPow(x,p).modInverse(p)).mod(p);
        return Operacje.bigToArray(wynik,32);
    }
    public byte[] szyfrujWiadomosc(byte[] wej){
        BigInteger c1 = g.modPow(k,p);

        int maxindex = wej.length / 32;
        int ilezer = 32 - wej.length % 32;
        byte [] wyj;
        if(wej.length % 32 == 0){
            wyj = new byte[maxindex * 34 + 34];
            maxindex--;
        }else {
            wyj = new byte[(maxindex + 1) * 34 + 34 + 1];
            wyj[(maxindex + 1) * 34 + 34 ] = (byte) ilezer;
        }
        byte[] szyfr;
        byte[] C = Operacje.bigToArray(c1,34);
        for(int i = 0;i<272;i++){
            Operacje.setBit(wyj,i,Operacje.getBit(C,i));
        }
        for(int i = 0;i<=maxindex;i++){
            szyfr = szyfrujBlok(Operacje.zwroc256bity(i,wej));
            for(int j =0;j<272;j++){
                Operacje.setBit(wyj,272 + j+(i * 272),Operacje.getBit(szyfr,j));
            }
        }

        return wyj;
    }

    public byte[] deszyfrujWiadomosc(byte[] wej){
        int ilezer = 0;
        int maxindex = wej.length / 34 - 1;
        if(wej.length % 34 != 0){
            maxindex = (wej.length - 1)/ 34 - 1 ;
            ilezer = wej[(maxindex+1) * 34];
        }
        int rozmair = ((maxindex) * 32 - ilezer);

        byte[] wyj = new byte[rozmair];


        byte[] help = new byte[34];
        for (int i = 0;i<272;i++){
            Operacje.setBit(help,i,Operacje.getBit(wej,i));

        }
        BigInteger C1 = new BigInteger(help);


        for(int i =1;i<=maxindex;i++){
            help = deszyfrujBlok(C1,new BigInteger(Operacje.zwroc272bity(i,wej)));
            if(i == maxindex && wej.length % 34 != 0){

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