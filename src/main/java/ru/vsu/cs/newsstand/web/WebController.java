package ru.vsu.cs.newsstand.web;

import ru.vsu.cs.newsstand.core.db.domains.SortParameter;
import ru.vsu.cs.newsstand.core.domain.Book;
import ru.vsu.cs.newsstand.core.domain.Magazine;
import ru.vsu.cs.newsstand.core.domain.Newspaper;
import ru.vsu.cs.newsstand.core.domain.PrintedMatter;
import ru.vsu.cs.newsstand.core.services.Logics;

import java.util.List;

public class WebController {

    private static Logics logics;

    public static List<PrintedMatter> getAllPrintedMatters() {
        return logics.getAllPrintedMatter();
    }

    public static List<PrintedMatter> getAllPrintedMatters(String parameter, String direction) {
        return logics.getAllPrintedMatter(getSortParameter(parameter), checkDirection(direction));
    }

    public static List<Newspaper> getAllNewspaper(String parameter, String direction) {
        return logics.getAllNewspaper(getSortParameter(parameter), checkDirection(direction));
    }

    public static List<Magazine> getAllMagazine(String parameter, String direction) {
        return logics.getAllMagazine(getSortParameter(parameter), checkDirection(direction));
    }

    public static List<Book> getAllBook(String parameter, String direction) {
        return logics.getAllBook(getSortParameter(parameter), checkDirection(direction));
    }

    public static void deleteById(String idParam){
        try{
            Long id = Long.parseLong(idParam);
            logics.deletePrintedMatter(id);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SortParameter getSortParameter(String parameter){
        if(parameter == null) return SortParameter.NAME;
        switch (parameter){
            case "PRICE":
                return SortParameter.PRICE;
            case "TYPE":
                return SortParameter.TYPE;
            default:
                return SortParameter.NAME;
        }
    }

    private static boolean checkDirection(String direction){
        if(direction == null) return false;
        return direction.equals("DESC");
    }

    public static void setLogics(Logics logics) {
        WebController.logics = logics;
    }
}
