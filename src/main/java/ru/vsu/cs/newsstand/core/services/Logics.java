package ru.vsu.cs.newsstand.core.services;

import ru.vsu.cs.newsstand.annotation.InjectByType;
import ru.vsu.cs.newsstand.annotation.Singleton;
import ru.vsu.cs.newsstand.core.dao.IPrintedMatterDataService;
import ru.vsu.cs.newsstand.core.db.DataBase;
import ru.vsu.cs.newsstand.core.db.domains.SortParameter;
import ru.vsu.cs.newsstand.core.domain.*;
import ru.vsu.cs.newsstand.core.services.data.EventLogServiceImpl;
import ru.vsu.cs.newsstand.core.services.data.PrintedMatterDataServiceImp;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class Logics {

    @InjectByType
    private IPrintedMatterDataService printedMatterDataService;
    @InjectByType
    private EventLogServiceImpl eventLogService;


    public PrintedMatter createPrintedMatter(Integer count, PrintedMatter printedMatter) {
        PrintedMatter createdPrintedMatter = printedMatterDataService.add(printedMatter);
        for (int i = 1; i < count; i++) {
            printedMatterDataService.add(printedMatter.copy());
        }

        Event event = createEvent(Action.RECEIVED, createdPrintedMatter, count);
        eventLogService.add(event);

        return createdPrintedMatter;
    }

    private Event createEvent(Action action, PrintedMatter createdPrintedMatter, Integer count) {

        BigDecimal priceSum = createdPrintedMatter.getPrice().multiply(BigDecimal.valueOf(count));
        Calendar dataTime = new GregorianCalendar();
        return new Event(action, count, createdPrintedMatter, dataTime, priceSum);

    }

    public List<PrintedMatter> getAllNewspaper() {
        return printedMatterDataService.getAllByType(PrintedMatterType.NEWSPAPER, SortParameter.NAME, false);
    }

    public List<Newspaper> getAllNewspaper(SortParameter sortParameter, boolean isDesc) {
        List<PrintedMatter> allByType =
                printedMatterDataService.getAllByType(PrintedMatterType.NEWSPAPER, sortParameter, isDesc);
        List<Newspaper> newspapers = allByType.stream().map(n -> (Newspaper) n).collect(Collectors.toList());
        return newspapers;
    }

    public List<PrintedMatter> getAllMagazine() {
        return printedMatterDataService.getAllByType(PrintedMatterType.MAGAZINE, SortParameter.NAME, false);
    }

    public List<Magazine> getAllMagazine(SortParameter sortParameter, boolean isDesc) {
        List<PrintedMatter> allByType =
                printedMatterDataService.getAllByType(PrintedMatterType.MAGAZINE, sortParameter, isDesc);
        List<Magazine> magazines = allByType.stream().map(m -> (Magazine) m).collect(Collectors.toList());
        return magazines;
    }

    public List<PrintedMatter> getAllBook() {
        return printedMatterDataService.getAllByType(PrintedMatterType.BOOK, SortParameter.NAME, false);
    }

    public List<Book> getAllBook(SortParameter sortParameter, boolean isDesc) {
        List<PrintedMatter> allByType =
                printedMatterDataService.getAllByType(PrintedMatterType.BOOK, sortParameter, isDesc);
        List<Book> books = allByType.stream().map(m -> (Book) m).collect(Collectors.toList());
        return books;
    }

    public List<PrintedMatter> getAllPrintedMatter() {
        return printedMatterDataService.getAll(SortParameter.NAME, false);
    }

    public List<PrintedMatter> getAllPrintedMatter(SortParameter sortParameter, boolean isDesc) {
        return printedMatterDataService.getAll(sortParameter, isDesc);
    }

    public List<Event> getAllEvent() {
        return eventLogService.getAll();
    }

    public boolean deletePrintedMatter(Long id) {
        return printedMatterDataService.delete(id);
    }

    public PrintedMatter updatePrintedMatter(Long id) {
        return null;
    }
}
