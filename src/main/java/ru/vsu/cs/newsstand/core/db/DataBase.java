package ru.vsu.cs.newsstand.core.db;


import ru.vsu.cs.newsstand.annotation.Singleton;
import ru.vsu.cs.newsstand.core.domain.Event;
import ru.vsu.cs.newsstand.core.domain.PrintedMatter;
import ru.vsu.cs.newsstand.core.domain.PrintedMatterType;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Singleton
public class DataBase {

    private final AtomicLong lastId = new AtomicLong(0);

    private final Map<Long, PrintedMatter> printedMatters = new HashMap<>();
    private final AtomicLong eventId = new AtomicLong(0);
    private final Map<Long, Event> eventLog = new HashMap<>();


    public PrintedMatter addPrintedMatter(PrintedMatter printedMatter) {
        Long id = lastId.incrementAndGet();
        printedMatter.setId(id);
        printedMatters.put(id, printedMatter);
        return printedMatter;
    }

    public PrintedMatter getPrintedMatter(Long id) {
        if (!printedMatters.containsKey(id)) return null;
        return printedMatters.get(id);
    }

    public List<PrintedMatter> getAllPrintedMatterByType(PrintedMatterType type) {
        return printedMatters.values().stream().filter(e -> e.getType() == type).collect(Collectors.toList());
    }

    public List<PrintedMatter> getAllPrintedMatter() {
        return new ArrayList<>(printedMatters.values());
    }

    public boolean deletePrintedMatter(Long id) {
        if (!printedMatters.containsKey(id)) return false;
        printedMatters.remove(id);
        return true;
    }

    public PrintedMatter updatePrintedMatter(PrintedMatter printedMatter) {
        if (printedMatter.getId() == null) return null;
        if (!printedMatters.containsKey(printedMatter.getId())) return null;
        printedMatters.replace(printedMatter.getId(), printedMatter);
        return printedMatter;
    }

    public Event addEvent(Event event) {
        Long id = eventId.getAndAdd(1);
        event.setId(id);
        eventLog.put(id, event);
        return event;
    }

    public Event getEvent(Long id){
        if (!eventLog.containsKey(id)) return null;
        return eventLog.get(id);
    }

    public List<Event> getAllEvent(){
        return new LinkedList<>(eventLog.values());
    }

    public boolean deleteEvent(Long id){
        if (!eventLog.containsKey(id)) return false;
        eventLog.remove(id);
        return true;
    }

    public Event updateEvent(Event event){
        if (!eventLog.containsKey(event.getId())) return null;
        eventLog.replace(event.getId(), event);
        return event;
    }

}
