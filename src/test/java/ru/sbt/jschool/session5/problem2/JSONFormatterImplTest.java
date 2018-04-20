package ru.sbt.jschool.session5.problem2;

import org.junit.Test;
import ru.sbt.jschool.session5.problem2.data.Cat;
import ru.sbt.jschool.session5.problem2.data.Dog;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class JSONFormatterImplTest {
    JSONFormatterImpl jsonFormatter = new JSONFormatterImpl();
    @Test
    public void primitiveDataTest() throws Exception {
        assertEquals("\"JavaSchool\"", jsonFormatter.marshall(new String("JavaSchool"), 0));
        assertEquals("115", jsonFormatter.marshall(115, 0));
        assertEquals("115.5", jsonFormatter.marshall(115.5, 0));
        assertEquals("l", jsonFormatter.marshall('l', 0));
        assertEquals("true", jsonFormatter.marshall(true, 0));

        Date date = new Date();
        date.setYear(118);
        date.setMonth(10);
        date.setDate(25);
        assertEquals("25.11.2018", jsonFormatter.marshall(date, 0));

        Calendar c = new GregorianCalendar(2018, 10, 25);
        assertEquals("25.11.2018", jsonFormatter.marshall(c, 0));
    }

    @Test
    public void anotherDataTest() throws Exception{
      Cat cat = new Cat("PushIsTik", 5, 3L);
      String expected = "{\n    \"mas\": 3\n\n    \"set\": [\n        \"Java\",\n        \"OOP\",\n"+
        "        \"instanceof\"\n    ]\n\n    \"age\": 5\n\n    \"name\": \"PushIsTik\"\n\n    \"testField\": \"testValue\"\n}";

      assertEquals(expected, jsonFormatter.marshall(cat, 0));

      Dog dog = new Dog("Jack", "Labrador");
      expected = "{\n    \"bread\": \"Labrador\"\n\n    \"ints\": [\n        [\n            1,\n            2,\n            3,\n"+
              "            4,\n"+
              "            5\n        ]\n        [\n            11,\n            22,\n            33,\n            44,\n"+
        "            55\n        ]\n    ]\n\n    \"name\": \"Jack\"\n\n    \"testField\": \"testValue\"\n}";

      assertEquals(expected, jsonFormatter.marshall(dog, 0));
    }

}