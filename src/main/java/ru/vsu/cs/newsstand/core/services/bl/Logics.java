package ru.vsu.cs.newsstand.core.services.bl;

import ru.vsu.cs.newsstand.annotation.InjectByType;
import ru.vsu.cs.newsstand.annotation.Singleton;
import ru.vsu.cs.newsstand.core.db.DataBase;
import ru.vsu.cs.newsstand.core.domain.Action;
import ru.vsu.cs.newsstand.core.domain.Event;
import ru.vsu.cs.newsstand.core.domain.PrintedMatter;
import ru.vsu.cs.newsstand.core.domain.PrintedMatterType;
import ru.vsu.cs.newsstand.core.services.data.EventLogServiceImpl;
import ru.vsu.cs.newsstand.core.services.data.PrintedMatterDataServiceImp;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Singleton
public class Logics {

    @InjectByType
    private DataBase db;
    @InjectByType
    private PrintedMatterDataServiceImp printedMatterDataService;
    @InjectByType
    private EventLogServiceImpl eventLogService;


    public PrintedMatter createPrintedMatter(Integer count, PrintedMatter printedMatter){
        PrintedMatter createdPrintedMatter = printedMatterDataService.add(printedMatter);
        for (int i = 1; i < count; i++) {
            printedMatterDataService.add(printedMatter.copy());
        }

        Event event = createEvent(Action.RECEIVED, createdPrintedMatter, count);
        eventLogService.add(event);

        return createdPrintedMatter;
    }

    private Event createEvent(Action action, PrintedMatter createdPrintedMatter, Integer count){

        BigDecimal priceSum = createdPrintedMatter.getPrice().multiply(BigDecimal.valueOf(count));
        Calendar dataTime = new GregorianCalendar();
        return new Event(action, count, createdPrintedMatter, dataTime, priceSum);

    }

    public List<PrintedMatter> getAllNewspaper() {
        return printedMatterDataService.getAllByType(PrintedMatterType.NEWSPAPER);
    }

    public List<PrintedMatter> getAllMagazine() {
        return printedMatterDataService.getAllByType(PrintedMatterType.MAGAZINE);
    }

    public List<PrintedMatter> getAllBook() {
        return printedMatterDataService.getAllByType(PrintedMatterType.BOOK);
    }

    public List<PrintedMatter> getAllPrintedMatter() {
        return printedMatterDataService.getAll();
    }

    public List<Event> getAllEvent() {
        return eventLogService.getAll();
    }
}
