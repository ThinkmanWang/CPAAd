package com.thinkman.thinkutils;

import java.util.Random;

public class RandomUtils {

    public static int random() {
        Random rand = new Random();
        return rand.nextInt();
    }

    public static int random(int nMin, int nMax) {
        Random random = new Random();
        int nRand = random.nextInt(nMax) % (nMax - nMin + 1) + nMin;

        return nRand;
    }
}
