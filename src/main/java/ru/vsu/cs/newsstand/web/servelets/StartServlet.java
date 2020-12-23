package ru.vsu.cs.newsstand.web.servelets;

import ru.vsu.cs.newsstand.core.services.Logics;
import ru.vsu.cs.newsstand.structure.Application;
import ru.vsu.cs.newsstand.structure.ApplicationContext;
import ru.vsu.cs.newsstand.web.WebController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(value = "/", loadOnStartup = 0)
public class StartServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext context = Application.run("ru.vsu.cs.newsstand", new HashMap<>());
        WebController.setLogics(context.getObject(Logics.class));
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/main");
    }

}
