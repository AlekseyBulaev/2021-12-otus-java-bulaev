package ru.otus.java.professional;

import ru.otus.java.professional.annotations.After;
import ru.otus.java.professional.annotations.Before;
import ru.otus.java.professional.annotations.Test;
import ru.otus.java.professional.model.TestingObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
    Создать "запускалку теста". На вход она должна получать имя класса с тестами,
    в котором следует найти и запустить методы отмеченные аннотациями и пункта 1.
    Алгоритм запуска должен быть следующий::
    метод(ы) Before текущий метод Test метод(ы) After
    для каждой такой "тройки" надо создать СВОЙ объект класса-теста.
    Исключение в одном тесте не должно прерывать весь процесс тестирования.
    На основании возникших во время тестирования исключений вывести статистику выполнения тестов
    (сколько прошло успешно, сколько упало, сколько было всего)
    "Запускалка теста" не должна иметь состояние,
    но при этом весь функционал должен быть разбит на приватные методы.
    Надо придумать, как передавать информацию между методами.

 */

public class CustomFramework<T> {

    public String start(String className) {
        AtomicInteger failed = new AtomicInteger(0);
        AtomicInteger passed = new AtomicInteger(0);
        try {
            Class<?> targetClass = Class.forName(className);
            T instanceClass = (T) targetClass.newInstance();
            Set<TestingObject> testingObjects = populateFields(targetClass.getDeclaredMethods());
            invokeMethods(instanceClass, testingObjects, failed, passed);
            return printResult(failed, passed);
        } catch (ClassNotFoundException ex) {
            System.out.printf("Error: %s" + " Not found%n", className);
            ex.printStackTrace();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return printResult(failed, passed);
    }

    private String printResult(AtomicInteger failed, AtomicInteger passed) {
        return String.format("TOTAL= %s \nFAILED= %s \nPASSED= %s", failed.get() + passed.get(), failed.get(), passed.get());
    }

    private void invokeMethods(T instanceClass,
                               Set<TestingObject> testingObjects,
                               AtomicInteger failed, AtomicInteger passed) {
        testingObjects.forEach(obj -> {
            try {
                obj.beforeAnnotationMethods().forEach(before -> invokeMethod(instanceClass, before));
                obj.method().invoke(instanceClass);
                obj.afterAnnotationMethods().forEach(after -> invokeMethod(instanceClass, after));
                passed.addAndGet(1);
            } catch (Exception e) {
                failed.addAndGet(1);
                e.printStackTrace();
                obj.afterAnnotationMethods().forEach(after -> invokeMethod(instanceClass, after));
            }
        });
    }

    private void invokeMethod(T instanceClass, Method before) {
        try {
            before.invoke(instanceClass);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Set<TestingObject> populateFields(Method[] candidates) {
        Set<Method> beforeAnnotationMethods = new HashSet<>();
        Set<Method> afterAnnotationMethods = new HashSet<>();
        List<Method> testAnnotationMethods = new ArrayList<>();
        Arrays.stream(candidates).forEach(method -> Arrays.stream(method.getAnnotations()).forEach(annotation -> {
            if (Before.class.getName().equals(annotation.annotationType().getName())) {
                beforeAnnotationMethods.add(method);
            }
            if (After.class.getName().equals(annotation.annotationType().getName())) {
                afterAnnotationMethods.add(method);
            }
            if (Test.class.getName().equals(annotation.annotationType().getName())) {
                testAnnotationMethods.add(method);
            }
        }));
        Set<TestingObject> testingObjects = new HashSet<>();

        //
       testAnnotationMethods.forEach(method ->
               testingObjects.add(new TestingObject(method, beforeAnnotationMethods, afterAnnotationMethods)));
       return testingObjects;
    }
}
