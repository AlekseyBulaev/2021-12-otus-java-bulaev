package ru.otus;

import org.junit.jupiter.api.Test;
import ru.otus.model.Log;

public class ProxyTest {

    @Test
    public void test() {
       interface Some {
           void doSomething(String param);
           void doSomething(String param, int value);
       }

       class Impl implements Some {

           @Override
           @Log
           public void doSomething(String param) {
               System.out.println(param);
           }

           @Override
           public void doSomething(String param, int value) {
               System.out.println(param + " " + value);
           }
       }

       Impl instance = new Impl();

       instance.doSomething("param");
       instance.doSomething("param", 2);

    }
}
