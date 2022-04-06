package ru.otus;


import ru.otus.model.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

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

        ProxyInstance(TestLogging testLoggingInstance) {
            this.testLoggingInstance = testLoggingInstance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (hasLogging(method)){
            String result = Arrays.stream(args).reduce((x, it) -> "params: " + x + ", " + it.toString()).get().toString();
            System.out.println("invoking method: " + method.getName() + " " + result);}
            return method.invoke(testLoggingInstance, args);
        }

        private boolean hasLogging(Method method) {
            List<Method> methods = Arrays.stream(testLoggingInstance.getClass().getMethods()).toList();
            return methods.stream().anyMatch(mthd -> mthd.getName().equals(method.getName())
                    && mthd.getParameterCount() == method.getParameterCount()
                    && Arrays.equals(mthd.getParameterTypes(), method.getParameterTypes()));
        }

        @Override
        public String toString() {
            return "TestLogging{" +
                    "myClass=" + testLoggingInstance +
                    '}';
        }
    }
}
