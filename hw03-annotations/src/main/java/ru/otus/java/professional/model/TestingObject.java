package ru.otus.java.professional.model;

import java.lang.reflect.Method;
import java.util.Set;

//СВОЙ объект класса-теста
public record TestingObject(
        Method method,
        Set<Method> beforeAnnotationMethods,
        Set<Method> afterAnnotationMethods
){}
