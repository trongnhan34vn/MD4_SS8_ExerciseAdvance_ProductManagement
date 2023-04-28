package service.product;

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
                product.setDescription(resultSet.getString("Description"));
                product.setPrice(resultSet.getFloat("Price"));
                product.setQuantity(resultSet.getInt("Quantity"));
                product.setStatus(resultSet.getBoolean("Status"));
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
        if(findById(product.getProductId()) == null) {
            saveToDB(product);
        } else {
            updateToDB(product);
        }
    }

    private void saveToDB(Product product) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Product (ProductName, Description, CatalogID, Price, Quantity, Status) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getCatalog().getCatalogId());
            preparedStatement.setFloat(4, product.getPrice());
            preparedStatement.setInt(5, product.getQuantity());
            preparedStatement.setBoolean(6, product.isStatus());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    private void updateToDB(Product product) {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            PreparedStatement preparedStatement;

            preparedStatement = conn.prepareStatement("UPDATE Product SET ProductName = ?, Description = ?, CatalogID = ?, Price = ?, Quantity = ?, Status = ? WHERE ProductID = ?");
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getCatalog().getCatalogId());
            preparedStatement.setFloat(4, product.getPrice());
            preparedStatement.setInt(5, product.getQuantity());
            preparedStatement.setBoolean(6, product.isStatus());
            preparedStatement.setInt(7, product.getProductId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
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
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM Product WHERE ProductID = " + id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public List<Product> searchByName(String search) {
        List<Product> listSearch = new ArrayList<>(findAll());
        for (Product product:listSearch) {
            System.out.println(product.getProductName().toLowerCase().contains(search.toLowerCase()));
        }
        return listSearch;
    }
}
