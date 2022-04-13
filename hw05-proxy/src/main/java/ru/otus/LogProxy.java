package ru.otus;


import ru.otus.model.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LogProxy {


    private LogProxy() {
    }

    static TestLogging createTestLogging(TestLogging instance) {
        InvocationHandler handler = new ProxyInstance(instance);
        return (TestLogging) Proxy.newProxyInstance(LogProxy.class.getClassLoader(),
                new Class<?>[]{TestLogging.class}, handler);
    }

    static class ProxyInstance implements InvocationHandler {
        private final TestLogging testLoggingInstance;
        private final Set<String> methods;

        ProxyInstance(TestLogging testLoggingInstance) {
            this.testLoggingInstance = testLoggingInstance;
            methods = Arrays.stream(testLoggingInstance.getClass().getMethods())
                    .filter(m -> m.isAnnotationPresent(Log.class))
                    .map(Method::toString)
                    .collect(Collectors.toSet());
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (hasLogging(method)) {
                if (args != null && args.length > 0) {
                    String result = Arrays.stream(args).reduce((x, it) -> "params: " + x + ", " + it.toString()).get().toString();
                    System.out.println("invoking method: " + method.getName() + " " + result);
                } else {
                    System.out.println("invoking method: " + method.getName());
                }
            }
            return method.invoke(testLoggingInstance, args);
        }

        private boolean hasLogging(Method method) {
            return methods.stream().anyMatch(mthd -> mthd.equals(method.toString()));
        }

        @Override
        public String toString() {
            return "TestLogging{" +
                    "myClass=" + testLoggingInstance +
                    '}';
        }
    }
}
