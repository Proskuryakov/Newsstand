package ru.vsu.cs.newsstand.core.services.data;

import ru.vsu.cs.newsstand.annotation.InjectByType;
import ru.vsu.cs.newsstand.annotation.Singleton;
import ru.vsu.cs.newsstand.core.dao.IPrintedMatterDataService;
import ru.vsu.cs.newsstand.core.db.DataBase;
import ru.vsu.cs.newsstand.core.domain.PrintedMatter;
import ru.vsu.cs.newsstand.core.domain.PrintedMatterType;

import java.util.List;

@Singleton
public class PrintedMatterDataServiceImp implements IPrintedMatterDataService {

    @InjectByType
    private DataBase db;

    @Override
    public List<PrintedMatter> getAllByType(PrintedMatterType type) {
        return db.getAllPrintedMatterByType(type);
    }

    @Override
    public PrintedMatter add(PrintedMatter printedMatter) {
        return db.addPrintedMatter(printedMatter);
    }

    @Override
    public PrintedMatter get(Long id) {
        return db.getPrintedMatter(id);
    }

    @Override
    public List<PrintedMatter> getAll() {
        return db.getAllPrintedMatter();
    }

    @Override
    public boolean delete(Long id) {
        return db.deletePrintedMatter(id);
    }

    @Override
    public PrintedMatter update(PrintedMatter printedMatter) {
        return db.updatePrintedMatter(printedMatter);
    }
}
