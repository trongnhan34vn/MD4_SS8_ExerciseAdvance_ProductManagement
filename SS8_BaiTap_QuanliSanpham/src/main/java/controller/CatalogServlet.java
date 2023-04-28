package controller;

import model.Catalog;
import service.catalog.CatalogServiceIMPL;
import service.catalog.ICatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CatalogServlet", value = "/catalogServlet")
public class CatalogServlet extends HttpServlet {
    private final ICatalogService catalogService = new CatalogServiceIMPL();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                break;
            case "update":
                break;
            case "delete":
                break;
            default:
                listCatalog(request, response);
                break;
        }
    }

    private void listCatalog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Catalog> catalogList = catalogService.findAll();
        request.setAttribute("catalogList", catalogList);
        request.getRequestDispatcher("productServlet?action=show").forward(request,response);
    }

}
