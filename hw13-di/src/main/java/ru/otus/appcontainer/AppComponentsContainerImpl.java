package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.IntStream;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    // isAssignableFrom
    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();
    private final int MAXIMUM_ORDER = 5;

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        // You code here...
        var methods = Arrays.stream(configClass.getMethods())
                .filter(method -> method.isAnnotationPresent(AppComponent.class)).toList();

        methods.stream().filter(method -> method.
                        getAnnotation(AppComponent.class).order() == 0)
                .toList()
                .forEach(this::createZeroLevelInstance);

        IntStream.range(1, MAXIMUM_ORDER).forEach(idx -> methods.stream().filter(method -> method.
                        getAnnotation(AppComponent.class).order() == idx)
                .toList()
                .forEach(this::createFirstLevelInstance));

        fillAppComponentsByName(methods);
    }

    private void fillAppComponentsByName(List<Method> methods) {
        methods.forEach(method -> {
            var obj = appComponents.stream()
                    .filter(comp -> method.getReturnType().isAssignableFrom(comp.getClass()))
                    .findFirst().get();
            var id = method.getAnnotation(AppComponent.class).name();
            appComponentsByName.put(id, obj);
        });
    }

    private Object createZeroLevelInstance(Method method) {
        var obj = appComponents.stream()
                .filter(bean -> method.getDeclaringClass().isAssignableFrom(bean.getClass())).findFirst().orElseGet( () -> {
                    try {
                        return method.invoke(method.getDeclaringClass().getDeclaredConstructor().newInstance());
                    } catch (Exception e) {
                        throw new RuntimeException("Can't instantiate a zeroLevelInstance for class:"
                                + method.getDeclaringClass().getName(), e);

                }});

        appComponents.add(obj);
        return obj;
    }

    private Object createFirstLevelInstance(Method method) {
        var params = method.getParameterTypes();
        List<Object> paramObjects = new LinkedList<>();
        Arrays.stream(params).forEach(param -> {
            var obj = appComponents.stream().filter(component -> param.isAssignableFrom(component.getClass())).findFirst();
            obj.ifPresent(paramObjects::add);
        });
        Object result;
        try {
            result = method.invoke(method.getDeclaringClass().getDeclaredConstructor().newInstance(), paramObjects.toArray());
        } catch (Exception e) {
            throw new RuntimeException("Can't instantiate a firstLevelInstance for class:"
                    + method.getDeclaringClass().getName(), e);
        }
        appComponents.add(result);
        return result;
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        return (C) appComponents.stream()
                .filter(component -> componentClass.isAssignableFrom(component.getClass()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }
}
