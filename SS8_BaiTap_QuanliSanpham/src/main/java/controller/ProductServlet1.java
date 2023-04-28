package controller;

import model.Catalog;
import model.Product;
import service.catalog.CatalogServiceIMPL;
import service.catalog.ICatalogService;
import service.product.IProductService;
import service.product.ProductServiceIMPL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/productServlet")
public class ProductServlet1 extends HttpServlet {
    IProductService productService = new ProductServiceIMPL();
    ICatalogService catalogService = new CatalogServiceIMPL();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "update":
                showUpdateForm(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            case "search":
                System.out.println("in");
                searchByName(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "update":
                updateProduct(request,response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;

        }
    }
    private void searchByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        List<Product> listSearch = productService.searchByName(search);
        request.setAttribute("catalogList", catalogService.findAll());
        request.setAttribute("productList", listSearch);
        request.getRequestDispatcher("view/Home1.jsp").forward(request,response);
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idDel = Integer.parseInt(request.getParameter("id"));
        productService.remove(idDel);
        request.setAttribute("message", "Delete Product Success!");
        request.getRequestDispatcher("view/DeleteForm.jsp").forward(request,response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int catId = Integer.parseInt(request.getParameter("catalog"));
        Catalog catalog = catalogService.findById(catId);
        float price = Float.parseFloat(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Product updateProduct = new Product(id, name, description, null, catalog, price, quantity, status);
        productService.save(updateProduct);
        request.setAttribute("message", "Update Product Success!");
        request.getRequestDispatcher("view/CreateForm.jsp").forward(request, response);
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = productService.findAll();
        int id;
        if (productList.isEmpty()) {
            id = 1;
        } else {
            id = productList.get(productList.size() - 1).getProductId() + 1;
        }
        String name = request.getParameter("name");
        int cataId = Integer.parseInt(request.getParameter("catalog"));
        Catalog catalog = catalogService.findById(cataId);
        String description = request.getParameter("description");
        float price = Float.parseFloat(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Product newProduct = new Product(id, name, description, null, catalog, price, quantity, status);
        productService.save(newProduct);
        request.setAttribute("message", "Create Product Success!");
        request.getRequestDispatcher("view/CreateForm.jsp").forward(request, response);
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = productService.findAll();
        request.setAttribute("productList", productList);
//        response.sendRedirect("view/Home1.jsp");
        request.getRequestDispatcher("view/Home1.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listCatalogs", catalogService.findAll());
        request.getRequestDispatcher("view/CreateForm.jsp").forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idDel = Integer.parseInt(request.getParameter("id"));
        Product delProduct = productService.findById(idDel);
        request.setAttribute("deleteProduct", delProduct);
        request.getRequestDispatcher("view/DeleteForm.jsp").forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idU = Integer.parseInt(request.getParameter("id"));
        Product updateProduct = productService.findById(idU);
        request.setAttribute("updateProduct", updateProduct);
        request.setAttribute("listCatalog", catalogService.findAll());
        request.getRequestDispatcher("view/UpdateForm.jsp").forward(request, response);
    }
}
