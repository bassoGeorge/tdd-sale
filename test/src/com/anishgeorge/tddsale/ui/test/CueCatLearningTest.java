package com.anishgeorge.tddsale.ui.test;

import java.io.*;

public class CueCatLearningTest {
    public static void main(String[] args) throws IOException {
        try(
                BufferedReader cueCat = new BufferedReader(new InputStreamReader(System.in))
        ) {
            cueCat.lines().forEach(System.out::println);
        }
    }
}
