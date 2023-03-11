package pl.model;

public class Main {
    public static void main(String[] args) {
        byte curr;
        byte x;
        x = 2;

        curr = (byte) (x >>> 1);
        curr =(byte)  (curr & 1);
        System.out.println(x >>> 1);
        System.out.println(curr);
    }
}