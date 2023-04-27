package service.product;

import config.Config;
import model.Catalog;
import model.Product;
import service.catalog.CatalogServiceIMPL;
import service.catalog.ICatalogService;
import util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceIMPL implements IProductService{
    ICatalogService catalogService = new CatalogServiceIMPL();
    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Product");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("ProductID"));
                product.setProductName(resultSet.getString("ProductName"));
                int catalogID = resultSet.getInt("CatalogID");
                for (Catalog catalog : catalogService.findAll()) {
                    if (catalog.getCatalogId() == catalogID) {
                        product.setCatalog(catalog);
                        break;
                    }
                }
                product.setPrice(resultSet.getFloat("Price"));
                product.setQuantity(resultSet.getInt("Quantity"));
                product.setStatus(resultSet.getBoolean("Status"));
                Config.numberFormat.format(product.getPrice());
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return productList;
    }

    @Override
    public void save(Product product) {
        List<Product> productList = findAll();
        if(findById(product.getProductId()) == null) {
            productList.add(product);
        } else {
            productList.set(productList.indexOf(findById(product.getProductId())),product);
        }
    }

    @Override
    public Product findById(int id) {
        List<Product> productList = findAll();
        for (Product product : productList) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void remove(int id) {

    }
}
