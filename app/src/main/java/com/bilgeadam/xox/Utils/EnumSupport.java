package com.bilgeadam.xox.Utils;

public class EnumSupport {

    public static Enum createRandomValue(Enum[] enums){

        return enums[(int) (Math.round(Math.random() * (enums.length -1)))];
    }
}
