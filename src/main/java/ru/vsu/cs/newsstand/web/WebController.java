package ru.vsu.cs.newsstand.web;

import lombok.SneakyThrows;
import ru.vsu.cs.newsstand.core.db.domains.SortParameter;
import ru.vsu.cs.newsstand.core.domain.Book;
import ru.vsu.cs.newsstand.core.domain.Magazine;
import ru.vsu.cs.newsstand.core.domain.Newspaper;
import ru.vsu.cs.newsstand.core.domain.PrintedMatter;
import ru.vsu.cs.newsstand.core.services.Logics;

import java.math.BigDecimal;
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

    public static boolean deleteById(String idParam) {
        try {
            Long id = Long.parseLong(idParam);
            return logics.deletePrintedMatter(id);
        } catch (Exception e) {
            return false;
        }
    }

    public static PrintedMatter addNewspaper(
            String name,
            String strPrice,
            String strNumber,
            String strDate,
            String strCount
    ) {
        Integer count = Integer.parseInt(strCount);
        Newspaper newspaper = getNewNewspaper(name, strPrice, strNumber, strDate);
        return logics.createPrintedMatter(count, newspaper);
    }

    public static PrintedMatter addMagazine(
            String name,
            String strPrice,
            String strNumber,
            String strDate,
            String strPageCount,
            String strCount
    ) {
        Integer count = Integer.parseInt(strCount);
        Magazine magazine = getNewMagazine(name, strPrice, strNumber, strDate, strPageCount);
        return logics.createPrintedMatter(count, magazine);
    }

    public static PrintedMatter addBook(
            String name,
            String strPrice,
            String author,
            String publishingHouse,
            String strPageCount,
            String strCount
    ) {
        Integer count = Integer.parseInt(strCount);
        Book book = getNewBook(name, strPrice, author, publishingHouse, strPageCount);
        return logics.createPrintedMatter(count, book);
    }

    public static boolean updateNewspaper(
            String strId,
            String name,
            String strPrice,
            String strNumber,
            String strDate
    ) {
        Newspaper newspaper = getNewNewspaper(name, strPrice, strNumber, strDate);
        newspaper.setId(Long.valueOf(strId));
        return logics.updatePrintedMatter(newspaper) != null;
    }

    public static boolean updateMagazine(
            String strId,
            String name,
            String strPrice,
            String strNumber,
            String strDate,
            String strPageCount
    ) {
        Magazine magazine = getNewMagazine(name, strPrice, strNumber, strDate, strPageCount);
        magazine.setId(Long.valueOf(strId));
        return logics.updatePrintedMatter(magazine) != null;
    }

    public static boolean updateBook(
            String strId,
            String name,
            String strPrice,
            String author,
            String publishingHouse,
            String strPageCount
    ) {
        Book book = getNewBook(name, strPrice, author, publishingHouse, strPageCount);
        book.setId(Long.valueOf(strId));
        return logics.updatePrintedMatter(book) != null;
    }

    public static boolean update(
            String strId,
            String strType,
            String name,
            String strPrice,
            String strNumber,
            String strDate,
            String strPageCount,
            String author,
            String publishingHouse
    ) {
        switch (strType) {
            case "NEWSPAPER":
                return updateNewspaper(strId, name, strPrice, strNumber, strDate);
            case "MAGAZINE":
                return updateMagazine(strId, name, strPrice, strNumber, strDate, strPageCount);
            case "BOOK":
                return updateBook(strId, name, strPrice, author, publishingHouse, strPageCount);
        }
        return false;
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

    private static Newspaper getNewNewspaper(String name, String strPrice, String strNumber, String strDate) {
        try {
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(strPrice));
            Calendar date = string2data(strDate);
            Integer number = Integer.parseInt(strNumber);

            return new Newspaper(name, price, number, date);
        } catch (Exception e) {
            return null;
        }
    }

    private static Magazine getNewMagazine(String name, String strPrice, String strNumber, String strDate, String strPageCount) {
        try {
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(strPrice));
            Calendar date = string2data(strDate);
            Integer number = Integer.parseInt(strNumber);
            Integer numberOfPage = Integer.parseInt(strPageCount);

            return new Magazine(name, price, number, date, numberOfPage);
        } catch (Exception e) {
            return null;
        }

    }

    private static Book getNewBook(String name, String strPrice, String author, String publishingHouse, String strPageCount) {
        try {
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(strPrice));
            Integer numberOfPage = Integer.parseInt(strPageCount);

            return new Book(name, price, author, publishingHouse, numberOfPage);
        } catch (Exception e) {
            return null;
        }
    }
}
