package ru.vsu.cs.newsstand.configurators;

import ru.vsu.cs.newsstand.structure.ApplicationContext;

public interface ObjectConfigurator {
    void configure(Object t, ApplicationContext context);
}
