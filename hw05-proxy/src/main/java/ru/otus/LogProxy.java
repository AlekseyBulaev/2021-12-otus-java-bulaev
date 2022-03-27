package ru.otus;

import ru.otus.model.Log;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LogProxy {

    public static void invokeWithLogging(String className) throws Exception {
        Class<?> loggingClass = Class.forName(className);
        Class<?> loggingInterface = null;
        if (loggingClass.getInterfaces().length != 1) {
            throw new RuntimeException("Количество наследуемых интерфейсов != 1");
        } else {
            loggingInterface = loggingClass.getInterfaces()[0];
        }
        List<Method> loggingMethods = getLoggingMethods(loggingClass);
        validate(loggingMethods, loggingInterface);

        loggingMethods.forEach(LogProxy::invokeWithProxy);
    }

    private static void invokeWithProxy(Method method) {


    }

    private static void validate(List<Method> loggingMethods, Class<?> loggingInterface) {
        if(!Arrays.stream(loggingInterface.getDeclaredMethods()).allMatch(loggingMethods::contains)) {
            throw new RuntimeException("Методы в интерфейсе и наследнике не совпадают");
        }
    }

    //Первая реализация, считаем, что все методы одного интерфейса реализованы в классе
    private static List<Method> getLoggingMethods(Class<?> loggingClass) {

        List<Class<?>> interfaces = Arrays.stream(loggingClass.getInterfaces()).toList();

        List<Method> methods = Arrays.stream(loggingClass.getDeclaredMethods())
                .filter(method ->
                        Arrays.stream(method.getAnnotations())
                                .anyMatch(methodAnnotation -> Log.class == methodAnnotation.annotationType()))
                .toList();

        return interfaces.stream().collect(Collectors.toMap(in -> in, methods.stream().filter(method -> method.getClass() == in)))

    }
