package ru.vsu.cs.newsstand.web.servelets;

import ru.vsu.cs.newsstand.core.domain.PrintedMatter;
import ru.vsu.cs.newsstand.web.WebController;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        String name = req.getParameter("name");
        String number = req.getParameter("number");
        String date = req.getParameter("date");
        String price = req.getParameter("price");
        String count = req.getParameter("count");
        String pageCount = req.getParameter("pageCount");
        String author = req.getParameter("author");
        String publishingHouse = req.getParameter("publishingHouse");

        PrintedMatter createdPrintedMatter = null;

        switch (action){
            case "addNewspaper":
                createdPrintedMatter = WebController.addNewspaper(name, price, number, date, count);
                break;
            case "addMagazine":
                createdPrintedMatter = WebController.addMagazine(name, price, number, date, pageCount, count);
                break;
            case "addBook":
                createdPrintedMatter = WebController.addBook(name, price, author, publishingHouse, pageCount, count);
                break;
        }

        if(createdPrintedMatter != null){
            req.setAttribute("createdPrintedMatter", createdPrintedMatter);
            req.setAttribute("error", false);
        }else{
            req.setAttribute("error", true);
        }
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }
}
