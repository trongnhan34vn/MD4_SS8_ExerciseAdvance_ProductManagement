package service.catalog;

import model.Catalog;
import util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CatalogServiceIMPL implements ICatalogService {
    @Override
    public List<Catalog> findAll() {
        List<Catalog> catalogList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Catalog");
            ResultSet resultSet = preparedStatement.executeQuery(); // hứng kết quả từ câu lệnh truy vấn
            while (resultSet.next()) {
                Catalog catalog = new Catalog();
                catalog.setCatalogId(resultSet.getInt("CatalogId"));
                catalog.setCatalogName(resultSet.getString("CatalogName"));
                catalogList.add(catalog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return catalogList;
    }

    @Override
    public void save(Catalog catalog) {
        List<Catalog> catalogList = findAll();
        if (findById(catalog.getCatalogId()) == null) {
            catalogList.add(catalog);
        } else {
            catalogList.set(catalogList.indexOf(findById(catalog.getCatalogId())),catalog);
        }
    }

    @Override
    public Catalog findById(int id) {
        List<Catalog> catalogList = findAll();
        for (Catalog catalog : catalogList) {
            if (catalog.getCatalogId() == id)
                return catalog;
        }
        return null;
    }

    @Override
    public void remove(int id) {

    }
}
