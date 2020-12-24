package ru.vsu.cs.newsstand.web.servelets;

import lombok.SneakyThrows;
import ru.vsu.cs.newsstand.core.domain.Book;
import ru.vsu.cs.newsstand.core.domain.Magazine;
import ru.vsu.cs.newsstand.core.domain.Newspaper;
import ru.vsu.cs.newsstand.core.domain.PrintedMatter;
import ru.vsu.cs.newsstand.web.WebController;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/table")
public class TablePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();

        String action = req.getParameter("action");
        if (action != null && action.equals("display")) {
            String type = req.getParameter("type");
            String parameter = req.getParameter("parameter");
            String direction = req.getParameter("direction");

            if (type == null || type.equals("ALL")) {
                List<PrintedMatter> allPrintedMatters = WebController.getAllPrintedMatters(parameter, direction);
                servletContext.setAttribute("printedMatters", allPrintedMatters);
            } else {
                switch (type) {
                    case "NEWSPAPER":
                        List<Newspaper> allNewspaper = WebController.getAllNewspaper(parameter, direction);
                        servletContext.setAttribute("printedMatters", allNewspaper);
                        break;
                    case "MAGAZINE":
                        List<Magazine> allMagazine = WebController.getAllMagazine(parameter, direction);
                        servletContext.setAttribute("printedMatters", allMagazine);
                        break;
                    case "BOOK":
                        List<Book> allBook = WebController.getAllBook(parameter, direction);
                        servletContext.setAttribute("printedMatters", allBook);
                        break;
                }
            }
        } else {
            List<PrintedMatter> allPrintedMatters = WebController.getAllPrintedMatters();
            servletContext.setAttribute("printedMatters", allPrintedMatters);
        }

        servletContext.getRequestDispatcher("/table.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if (action != null) {
            switch (action) {
                case "delete":
                    WebController.deleteById(req.getParameter("id"));
                    resp.sendRedirect("/table");
                    break;
                case "update":
                    String id = req.getParameter("id");
                    String type = req.getParameter("type");
                    String name = req.getParameter("name");
                    String price = req.getParameter("price");
                    String number = req.getParameter("number");
                    String date = req.getParameter("date");
                    String pageCount = req.getParameter("pageCount");
                    String author = req.getParameter("author");
                    String publishingHouse = req.getParameter("publishingHouse");
                    boolean updateResult = WebController.update(id, type, name, price, number, date, pageCount, author, publishingHouse);
                    req.setAttribute("updateResult", updateResult);
                    req.getRequestDispatcher("/table.jsp").forward(req, resp);
                    break;
            }
        }
    }
}
