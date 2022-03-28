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

    static TestLogging createTestLogging() {
        InvocationHandler handler = new ProxyInstance(new TestLoggingImpl());
        return (TestLogging) Proxy.newProxyInstance(LogProxy.class.getClassLoader(),
                new Class<?>[]{TestLogging.class}, handler);
    }

    static class ProxyInstance implements InvocationHandler {
        private final TestLogging myClass;

        ProxyInstance(TestLogging myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (hasLogging(method)){
            String result = Arrays.stream(args).reduce((x, it) -> "params: " + x + ", " + it.toString()).get().toString();
            System.out.println("invoking method: " + method.getName() + " " + result);}
            return method.invoke(myClass, args);
        }

        private boolean hasLogging(Method method) throws ClassNotFoundException{
            List<Method> methods = Arrays.stream(Class.forName("ru.otus.TestLoggingImpl").getMethods())
                    .filter(mthd -> Arrays.stream(mthd.getAnnotations()).anyMatch(ann -> ann.annotationType() == Log.class))
                    .toList();
            return methods.stream().anyMatch(mthd -> mthd.getName().equals(method.getName())
                    && mthd.getParameterCount() == method.getParameterCount()
                    && Arrays.equals(mthd.getParameterTypes(), method.getParameterTypes()));
        }

        @Override
        public String toString() {
            return "TestLogging{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}
