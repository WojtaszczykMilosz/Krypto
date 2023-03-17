package pl.model;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class INPUT {
    public byte[] tekst;
    public byte[] klucz;

    public void wczytajTekst()  {
        System.out.println("Podaj tekst");
        Scanner sc= new Scanner(System.in);
        String input;
        input = sc.nextLine();

        try {
            tekst = input.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }


    public void wczytajKlucz()  {
        System.out.println("Podaj klucz");
        Scanner sc= new Scanner(System.in);
        String input;
        input = sc.nextLine();

        try {
            klucz = input.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void wypiszBity(byte[] bytes){
        BigInteger bi = new BigInteger(bytes);
        for(int i = (bytes.length * 8) - 1; i >= 0; i--){

            if((i + 1) % 8 == 0 && i != (bytes.length * 8) - 1){
                System.out.print(" ");

            }
            System.out.print(bi.testBit(i) ? 1 : 0);
        }
        System.out.println();
    }
    public void wypiszStringa(byte[] bytes){
        String s = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(s);
    }
    public byte[] wczytajZpliku(String plik){
        try (
                InputStream sin = new FileInputStream(plik);
        ) {

            long rozmiar = new File(plik).length();
            byte[] bytes = new byte[(int) rozmiar];

            sin.read(bytes);

            return bytes;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    public void zapiszDoPliku(String plik, byte[] bytes){
        try (
                OutputStream sou = new FileOutputStream(plik);
        ) {
            sou.write(bytes);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public byte[] XOR(byte[] bytes1, byte[] bytes2){
        BigInteger b1 = new BigInteger(bytes1);
        BigInteger b2 = new BigInteger(bytes2);

        return b1.xor(b2).toByteArray();
    }

    public boolean sprawdzKlucz(){
        if(klucz.length == 8){
            return true;
        }else {
            return false;
        }
    }

    public byte[] zwroc64bity(int numer, byte[] bytes){

        int maxindex = bytes.length / 8;
        if(((bytes.length / 8 != 0) && (numer > maxindex)) || ((bytes.length / 8 == 0) && (numer >= maxindex))){
            return null;
        }

        byte[] out = new byte[8];

        if((bytes.length / 8 == 0) || (bytes.length / 8 != 0 && numer < maxindex)) {
            for(int i = 0; i < 8; i++){
                out[i] = bytes[i + (numer * 8)];
            }

        }else{
            int delta = bytes.length - (numer * 8);
            for(int i = 0; i < delta; i++){
                out[i] = bytes[i + (numer * 8)];
            }

            for(int i = delta; i < 8; i++){
                if(i == 7) {
                    out[i] = (byte)(8 - delta);
                }else {
                    out[i] = 0;
                }
            }
        }
        return out;
    }
}
