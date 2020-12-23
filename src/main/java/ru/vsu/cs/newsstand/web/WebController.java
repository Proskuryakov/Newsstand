package ru.vsu.cs.newsstand.web;

import ru.vsu.cs.newsstand.core.domain.PrintedMatter;
import ru.vsu.cs.newsstand.core.services.bl.Logics;

import java.util.List;

public class WebController {

    private static Logics logics;

    public static List<PrintedMatter> getAllPrintedMatters() {
        return logics.getAllPrintedMatter();
    }

    public static void setLogics(Logics logics) {
        WebController.logics = logics;
    }
}
