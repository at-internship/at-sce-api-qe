package com.at.globalclasses.domain;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

public class QARandomData {


    Lorem lorem = new LoremIpsum().getInstance();

    public int positiveInt() {
        int number;
        number = (int)( Math.random() * 10000 + 1);
        return number;
    }

    public double positiveDouble() {
        double number;
        number = (Math.random() * 10000 + 1);
        return number;
    }

    public int negativeInt() {
        int number;
        number = (int) ((-1) * (Math.random() * 10000 + 1));
        return number;
    }

    public double negativeDouble() {
        double number;
        number = (-1) * (Math.random() * 10000 + 1);
        return number;
    }

    public int correctRangeInt(int lower, int upper) {
        int number;
        number = (int) (Math.random() * upper + lower);
        return number;
    }

    public int incorrectRangeInt(int lower, int upper) {
        int number;
        number = (int) (Math.random() *(upper+1000) + upper);
        return number;
    }

    public double correctRangeDouble(int lower, int upper) {
        double number;
        number = (Math.random() * upper + lower);
        return number;
    }

    public double incorrectRangeDouble(int lower, int upper) {
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
