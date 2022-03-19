package ru.otus.java.professional;

import ru.otus.java.professional.annotations.After;
import ru.otus.java.professional.annotations.Before;
import ru.otus.java.professional.annotations.Test;
import ru.otus.java.professional.model.CustomAnnotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.otus.java.professional.model.CustomAnnotations.*;

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

public class Framework<T> {

    public String start(String className) {
        AtomicInteger failed = new AtomicInteger(0);
        AtomicInteger passed = new AtomicInteger(0);
        try {
            Class<?> targetClass = Class.forName(className);
            Map<CustomAnnotations, Collection<Method>> testingObjects = populateFields(targetClass.getDeclaredMethods());
            invokeMethods(targetClass, testingObjects, failed, passed);
            return printResult(failed, passed);
        } catch (ClassNotFoundException ex) {
            System.out.printf("Error: %s" + " Not found%n", className);
            ex.printStackTrace();
        }
        return printResult(failed, passed);
    }

    private String printResult(AtomicInteger failed, AtomicInteger passed) {
        return String.format("TOTAL= %s \nFAILED= %s \nPASSED= %s", failed.get() + passed.get(), failed.get(), passed.get());
    }

    private void invokeMethods(Class<?> targetClass,
                               Map<CustomAnnotations, Collection<Method>> testingObjects,
                               AtomicInteger failed, AtomicInteger passed) {
        testingObjects.get(TEST).forEach(obj -> {
            try {
                T instanceClass = (T) targetClass.newInstance();
                try {
                    testingObjects.get(BEFORE).forEach(before -> invokeMethod(instanceClass, before));
                    obj.invoke(instanceClass);
                    testingObjects.get(AFTER).forEach(after -> invokeMethod(instanceClass, after));
                    passed.addAndGet(1);
                } catch (Exception e) {
                    failed.addAndGet(1);
                    e.printStackTrace();
                    testingObjects.get(AFTER).forEach(after -> invokeMethod(instanceClass, after));
                }
            } catch (IllegalAccessException | InstantiationException e) {
                failed.addAndGet(1);
                e.printStackTrace();
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

    private Map<CustomAnnotations, Collection<Method>> populateFields(Method[] candidates) {
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

        return Map.of(BEFORE, beforeAnnotationMethods, AFTER, afterAnnotationMethods, TEST, testAnnotationMethods);
    }
}
