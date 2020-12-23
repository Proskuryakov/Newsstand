package ru.vsu.cs.newsstand.core.services.data;

import lombok.SneakyThrows;
import ru.vsu.cs.newsstand.annotation.InjectByType;
import ru.vsu.cs.newsstand.annotation.Singleton;
import ru.vsu.cs.newsstand.core.dao.IPrintedMatterDataService;
import ru.vsu.cs.newsstand.core.db.PostgreDataBase;
import ru.vsu.cs.newsstand.core.db.domains.DataBasePrintedMatter;
import ru.vsu.cs.newsstand.core.db.domains.SortParameter;
import ru.vsu.cs.newsstand.core.domain.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class PrintedMatterDataServiceImp implements IPrintedMatterDataService {

    @InjectByType
    private PostgreDataBase db;

    @Override
    public List<PrintedMatter> getAllByType(PrintedMatterType type, SortParameter sortParameter, boolean isDesc) {

        String orderBy = getOrderByParameter(sortParameter, isDesc);

        String sql = "select * from printed_matters " +
                "where type_id = " +
                "(select id from printed_matter_types where name = '" +
                type + "')";
        if(sortParameter != SortParameter.TYPE){
            sql += " order by " + getOrderByParameter(sortParameter, isDesc);
        }

        ResultSet rs = db.execute(sql);

        return splitAndFillList(rs, type, false);
    }

    @SneakyThrows
    @Override
    public PrintedMatter add(PrintedMatter printedMatter) {

        String sql = getInsertSqlQueryByPrintedMatterType(printedMatter);

        ResultSet resultSet = db.execute(sql);

        resultSet.next();

        Long id = resultSet.getLong("id");
        printedMatter.setId(id);

        return printedMatter;
    }

    @Override
    public PrintedMatter get(Long id) {
        String sql = "select pm.id," +
                "       pm.name," +
                "       pm.price," +
                "       pmt.name as type," +
                "       pm.author," +
                "       pm.publishing_house," +
                "       pm.page_count," +
                "       pm.publishing_date," +
                "       pm.number" +
                "from printed_matters pm" +
                "         join printed_matter_types pmt" +
                "              on pmt.id = pm.type_id" +
                "where pm.id = " + id + ";";
        ResultSet rs = db.execute(sql);
        return getPrintedMatter(rs);
    }

    @Override
    public List<PrintedMatter> getAll() {


        String sql = "select pm.id, " +
                "pm.name, " +
                "pm.price, " +
                "pmt.name as type, " +
                "pm.author, " +
                "pm.publishing_house, " +
                "pm.page_count, " +
                "pm.publishing_date, " +
                "pm.number " +
                "from printed_matters pm " +
                "join printed_matter_types pmt " +
                "on pmt.id = pm.type_id;";

        ResultSet rs = db.execute(sql);

        return splitAndFillList(rs, null, true);
    }

    @Override
    public List<PrintedMatter> getAll(SortParameter sortParameter, boolean isDesc) {

        String orderBy = getOrderByParameter(sortParameter, isDesc);

        String sql = "select pm.id, " +
                "pm.name, " +
                "pm.price, " +
                "pmt.name as type, " +
                "pm.author, " +
                "pm.publishing_house, " +
                "pm.page_count, " +
                "pm.publishing_date, " +
                "pm.number " +
                "from printed_matters pm " +
                "join printed_matter_types pmt " +
                "on pmt.id = pm.type_id " +
                "order by " + orderBy;

        ResultSet rs = db.execute(sql);

        return splitAndFillList(rs, null, true);
    }

    @SneakyThrows
    @Override
    public boolean delete(Long id) {
        String sql = "delete from printed_matters where id = " + id + " returning name;";
        ResultSet rs = db.execute(sql);
        rs.next();
        String name = rs.getString("name");
        return ! name.equals("");
    }

    @Override
    public PrintedMatter update(PrintedMatter printedMatter) {

        DataBasePrintedMatter dbPrintedMatter = new DataBasePrintedMatter(printedMatter);

        String sql = "update printed_matters" +
                "set name = " + dbPrintedMatter.getName() +
                "set price = " + dbPrintedMatter.getPrice() +
                "set type_id = (select from printed_matter_types id where name = '" + printedMatter.getType() + "')" +
                "set author = " + dbPrintedMatter.getAuthor() +
                "set publishing_house = " + dbPrintedMatter.getPublishingHouse() +
                "set page_count = " + dbPrintedMatter.getPageCount() +
                "set publishing_date = " + dbPrintedMatter.getPublishingDate() +
                "set number = " + dbPrintedMatter.getNumber() +
                "where id = " + dbPrintedMatter.getId() + ";";

        db.execute(sql);

        return printedMatter;
    }

    @SneakyThrows
    private List<PrintedMatter> splitAndFillList(ResultSet rs, PrintedMatterType type, boolean isDifferentType) {
        List<PrintedMatter> printedMatters = new LinkedList<>();

        while (rs.next()) {
            printedMatters.add(getPrintedMatter(rs, type, isDifferentType));
        }

        return printedMatters;
    }

    @SneakyThrows
    private PrintedMatter getPrintedMatter(ResultSet rs, PrintedMatterType type, boolean isDifferentType) {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        BigDecimal price = rs.getBigDecimal("price");

        if (isDifferentType) {
            type = PrintedMatterType.valueOf(rs.getString("type"));
        }

        Integer page_count, number;

        Calendar publishing_date = Calendar.getInstance();
        switch (type) {
            case BOOK:
                String author = rs.getString("author");
                String publishing_house = rs.getString("publishing_house");
                page_count = rs.getInt("page_count");

                return new Book(id, name, price, author, publishing_house, page_count);
            case MAGAZINE:
                publishing_date.setTime(rs.getDate("publishing_date"));
                number = rs.getInt("number");
                page_count = rs.getInt("page_count");
                return new Magazine(id, name, price, number, publishing_date, page_count);
            case NEWSPAPER:
                publishing_date.setTime(rs.getDate("publishing_date"));
                number = rs.getInt("number");
                return new Newspaper(id, name, price, number, publishing_date);
        }
        return null;
    }

    private PrintedMatter getPrintedMatter(ResultSet rs) {
        return getPrintedMatter(rs, null, true);
    }

    private String getInsertSqlQueryByPrintedMatterType(PrintedMatter printedMatter) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into printed_matters ");

        switch (printedMatter.getType()) {
            case NEWSPAPER:
                Newspaper newspaper = (Newspaper) printedMatter;

                sb.append("(name, price, type_id, publishing_date, number) values (");
                sb.append("'").append(newspaper.getName()).append("', ");
                sb.append(newspaper.getPrice()).append(", ");
                sb.append("(select id from printed_matter_types id where name = 'NEWSPAPER'), ");
                sb.append("'").append(newspaper.getStringDate()).append("', ");
                sb.append(newspaper.getNumber()).append(")");
                break;
            case MAGAZINE:
                Magazine magazine = (Magazine) printedMatter;

                sb.append("(name, price, type_id, publishing_date, number, page_count) values (");
                sb.append("'").append(magazine.getName()).append("', ");
                sb.append(magazine.getPrice()).append(", ");
                sb.append("(select id from printed_matter_types id where name = 'MAGAZINE'), ");
                sb.append("'").append(magazine.getStringDate()).append("', ");
                sb.append(magazine.getNumber()).append(", ");
                sb.append(magazine.getNumberOfPage()).append(")");
                break;
            case BOOK:
                Book book = (Book) printedMatter;

                sb.append("(name, price, type_id, author, publishing_house, page_count) values (");
                sb.append("'").append(book.getName()).append("', ");
                sb.append(book.getPrice()).append(", ");
                sb.append("(select id from printed_matter_types id where name = 'BOOK'), ");
                sb.append("'").append(book.getAuthor()).append("', ");
                sb.append("'").append(book.getPublishingHouse()).append("', ");
                sb.append(book.getNumberOfPage()).append(")");
                break;
        }

        sb.append("returning id;");

        return sb.toString();
    }

    private String getOrderByParameter(SortParameter sortParameter, boolean isDesc){
        String param = sortParameter.toString().toLowerCase();
        String direction = isDesc ? "desc" : "asc";
        return  param + " " + direction;
    }

}
