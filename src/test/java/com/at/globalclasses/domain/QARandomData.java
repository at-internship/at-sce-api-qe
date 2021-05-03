package com.at.globalclasses.domain;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

public class QARandomData {


    Lorem lorem = new LoremIpsum().getInstance();

    public static int positiveInt() {
        int number;
        number = (int)( Math.random() * 10000 + 1);
        return number;
    }

    public static double positiveDouble() {
        double number;
        number = (Math.random() * 10000 + 1);
        return number;
    }

    public static int negativeInt() {
        int number;
        number = (int) ((-1) * (Math.random() * 10000 + 1));
        return number;
    }

    public static double negativeDouble() {
        double number;
        number = (-1) * (Math.random() * 10000 + 1);
        return number;
    }

    public static int correctRangeInt(int lower, int upper) {
        int number;
        number = (int) (Math.random() * upper + lower);
        return number;
    }

    public static int incorrectRangeInt(int lower, int upper) {
        int number;
        number = (int) (Math.random() *(upper+1000) + upper);
        return number;
    }

    public static double correctRangeDouble(int lower, int upper) {
        double number;
        number = (Math.random() * upper + lower);
        return number;
    }

    public static double incorrectRangeDouble(int lower, int upper) {
        double number;
        number = (Math.random() * (upper+1000) + upper);
        return number;
    }

    public static String randomString() {
        Lorem lorem = new LoremIpsum().getInstance();
        String word;
        word = lorem.getWords(1, 1);
        return word;
    }

}
