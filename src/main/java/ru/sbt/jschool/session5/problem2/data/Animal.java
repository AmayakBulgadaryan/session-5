package ru.sbt.jschool.session5.problem2.data;

/**
 * @author NIzhikov
 */
public class Animal {
   public final String name;
   public String testField;

    public Animal(String name) {
        this.name = name;
        testField = "testValue";
    }

    public String getName() {
        return name;
    }
}
