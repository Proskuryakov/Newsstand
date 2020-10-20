package ru.vsu.cs.newsstand.core.dao;

import ru.vsu.cs.newsstand.core.domain.PrintedMatter;
import ru.vsu.cs.newsstand.core.domain.PrintedMatterType;

import java.util.List;

public interface IPrintedMatterDataService extends IDataService<PrintedMatter> {

    List<PrintedMatter> getAllByType(PrintedMatterType type);

}
