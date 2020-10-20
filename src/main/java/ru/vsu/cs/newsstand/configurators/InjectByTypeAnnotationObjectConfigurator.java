package ru.vsu.cs.newsstand.configurators;

import lombok.SneakyThrows;
import ru.vsu.cs.newsstand.annotation.InjectByType;
import ru.vsu.cs.newsstand.structure.ApplicationContext;

import java.lang.reflect.Field;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator{
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()){
            if (field.isAnnotationPresent(InjectByType.class)){
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(t, object);
            }
        }
    }
}
