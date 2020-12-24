package ru.vsu.cs.newsstand.web;

import lombok.SneakyThrows;
import ru.vsu.cs.newsstand.core.db.domains.SortParameter;
import ru.vsu.cs.newsstand.core.domain.Book;
import ru.vsu.cs.newsstand.core.domain.Magazine;
import ru.vsu.cs.newsstand.core.domain.Newspaper;
import ru.vsu.cs.newsstand.core.domain.PrintedMatter;
import ru.vsu.cs.newsstand.core.services.Logics;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class WebController {

    private static Logics logics;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d-M-y");

    public static void setLogics(Logics logics) {
        WebController.logics = logics;
    }

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

    public static void deleteById(String idParam) {
        try {
            Long id = Long.parseLong(idParam);
            logics.deletePrintedMatter(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PrintedMatter addNewspaper(
            String name,
            String strPrice,
            String strNumber,
            String strDate,
            String strCount
    ) {
        try {
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(strPrice));
            Calendar date = string2data(strDate);
            Integer number = Integer.parseInt(strNumber);
            Integer count = Integer.parseInt(strCount);

            Newspaper newspaper = new Newspaper(name, price, number, date);
            return logics.createPrintedMatter(count, newspaper);
        } catch (Exception e) {
            return null;
        }
    }

    public static PrintedMatter addMagazine(
            String name,
            String strPrice,
            String strNumber,
            String strDate,
            String strPageCount,
            String strCount
    ) {
        try {
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(strPrice));
            Calendar date = string2data(strDate);
            Integer number = Integer.parseInt(strNumber);
            Integer count = Integer.parseInt(strCount);
            Integer numberOfPage = Integer.parseInt(strPageCount);

            Magazine magazine = new Magazine(name, price, number, date, numberOfPage);
            return logics.createPrintedMatter(count, magazine);
        } catch (Exception e) {
            return null;
        }
    }

    public static PrintedMatter addBook(
            String name,
            String strPrice,
            String author,
            String publishingHouse,
            String strPageCount,
            String strCount
    ) {
        try {
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(strPrice));

            Integer count = Integer.parseInt(strCount);
            Integer numberOfPage = Integer.parseInt(strPageCount);

            Book book = new Book(name, price, author, publishingHouse, numberOfPage);
            return logics.createPrintedMatter(count, book);
        } catch (Exception e) {
            return null;
        }
    }


    private static SortParameter getSortParameter(String parameter) {
        if (parameter == null) return SortParameter.NAME;
        switch (parameter) {
            case "PRICE":
                return SortParameter.PRICE;
            case "TYPE":
                return SortParameter.TYPE;
            default:
                return SortParameter.NAME;
        }
    }

    private static boolean checkDirection(String direction) {
        if (direction == null) return false;
        return direction.equals("DESC");
    }

    @SneakyThrows
    private static Calendar string2data(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DATE_FORMAT.parse(date));
        return calendar;
    }

}
