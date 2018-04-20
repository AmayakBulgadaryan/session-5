package ru.sbt.jschool.session5.problem2.data;


import sun.rmi.rmic.iiop.Type;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author NIzhikov
 */
public class Dog extends Animal {
    private String bread;
    private int[][] ints = {{1,2,3,4,5},{11,22,33,44,55}};


    public Dog(String name, String bread) {
        super(name);
        this.bread = bread;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }
}
