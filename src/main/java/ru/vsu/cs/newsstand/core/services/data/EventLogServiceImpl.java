package ru.vsu.cs.newsstand.core.services.data;

import ru.vsu.cs.newsstand.annotation.InjectByType;
import ru.vsu.cs.newsstand.annotation.Singleton;
import ru.vsu.cs.newsstand.core.dao.IDataService;
import ru.vsu.cs.newsstand.core.db.DataBase;
import ru.vsu.cs.newsstand.core.domain.Event;

import java.util.List;

@Singleton
public class EventLogServiceImpl implements IDataService<Event> {

    @InjectByType
    private DataBase db;

    @Override
    public Event add(Event event) {
        return db.addEvent(event);
    }

    @Override
    public Event get(Long id) {
        return db.getEvent(id);
    }

    @Override
    public List<Event> getAll() {
        return db.getAllEvent();
    }

    @Override
    public boolean delete(Long id) {
        return db.deleteEvent(id);
    }

    @Override
    public Event update(Event event) {
        return db.updateEvent(event);
    }
}
