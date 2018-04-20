package ru.sbt.jschool.session5.problem2.data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author NIzhikov
 */
public class Cat extends Animal {
    public Long mas;
    Set<String> set = new HashSet<>();
    public int age;

    public Cat(String name, int age, Long mas) {
        super(name);
        set.add("Java");
        set.add("instanceof");
        set.add("OOP");
        this.age = age;
        this.mas = mas;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
