package ru.vsu.cs.newsstand.structure;

import lombok.SneakyThrows;
import ru.vsu.cs.newsstand.annotation.PostConstruct;
import ru.vsu.cs.newsstand.configurators.ObjectConfigurator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class ObjectFactory {

    private final ApplicationContext context;
    private List<ObjectConfigurator> configurators = new LinkedList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;

        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> impClass) {
        T t = create(impClass);

        configure(t);

        invokeInit(impClass, t);

        return t;
    }

    private <T> T create(Class<T> implClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return implClass.getDeclaredConstructor().newInstance();
    }

    private <T> void configure(T t){
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
    }

    private <T> void invokeInit(Class<T> implClass, T t) throws InvocationTargetException, IllegalAccessException {
        for(Method method : implClass.getMethods()){
            if (method.isAnnotationPresent(PostConstruct.class)){
                method.invoke(t);
            }
        }
    }
}
