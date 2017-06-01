package com.example.renpeng.banyou;

/**
 * Created by renpeng on 17/6/1.
 */
public class User {

    private static String name;

    public static String getUserName(){
        return name;
    }

    public static void registerName(String name){
        User.name = name;
    }
}
