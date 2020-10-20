package ru.vsu.cs.newsstand.core.services.bl;

import ru.vsu.cs.newsstand.annotation.InjectByType;
import ru.vsu.cs.newsstand.annotation.Singleton;
import ru.vsu.cs.newsstand.core.domain.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Singleton
public class Controller {

    @InjectByType
    private Logics logics;

    public String addBook(
            String name,
            BigDecimal price,
            String author,
            String publishingHouse,
            int countOfPage,
            int count) {
        Book book = new Book(name, price, author, publishingHouse, countOfPage);
        PrintedMatter createdBook = logics.createPrintedMatter(count, book);
        return createdBook.toString();
    }

    public String addMagazine(
            String name,
            BigDecimal price,
            Integer number,
            Calendar calendar,
            int countOfPage,
            int count) {
        Magazine magazine = new Magazine(name, price, number, calendar, countOfPage);
        PrintedMatter createdMagazine = logics.createPrintedMatter(count, magazine);
        return createdMagazine.toString();
    }

    public String addNewspaper(String name, BigDecimal price, Integer number, Calendar calendar, int count) {
        Newspaper newspaper = new Newspaper(name, price, number, calendar);
        PrintedMatter createdNewspaper = logics.createPrintedMatter(count, newspaper);
        return createdNewspaper.toString();
    }

    public String getAllNewspaper() {
        List<PrintedMatter> allNewspaper = logics.getAllNewspaper();
        return listToString(allNewspaper);
    }

    public String getAllMagazines() {
        List<PrintedMatter> allMagazine = logics.getAllMagazine();
        return listToString(allMagazine);
    }

    public String getAllBooks() {
        List<PrintedMatter> allBook = logics.getAllBook();
        return listToString(allBook);
    }

    public String getAllPrintedMatters() {
        return listToString(logics.getAllPrintedMatter());
    }

    private <T> String listToString(List<T> list){
        StringBuilder sb = new StringBuilder();
        list.forEach(e -> sb.append(e.toString()).append(System.lineSeparator()));
        return sb.toString();
    }

    public String buyNewspaper(int id, int count) {
        return "Пока не реализовано";
    }

    public String buyMagazine(int id, int count) {
        return "Пока не реализовано";
    }

    public String buyBook(int id, int count) {
        return "Пока не реализовано";
    }

    public String getEventLog() {
        List<Event> events = logics.getAllEvent();
        return listToString(events);
    }

}
