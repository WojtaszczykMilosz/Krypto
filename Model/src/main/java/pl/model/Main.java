package pl.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        byte [] b = {-5};
        BigInteger nig = new BigInteger(1,b);
        System.out.println(nig);


    }
}
