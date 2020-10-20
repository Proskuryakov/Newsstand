package ru.vsu.cs.newsstand;

import ru.vsu.cs.newsstand.core.ui.ConsoleInterface;
import ru.vsu.cs.newsstand.structure.Application;
import ru.vsu.cs.newsstand.structure.ApplicationContext;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = Application.run("ru.vsu.cs.newsstand", new HashMap<>());
        ConsoleInterface consoleInterface = context.getObject(ConsoleInterface.class);
        consoleInterface.start();
    }

}
