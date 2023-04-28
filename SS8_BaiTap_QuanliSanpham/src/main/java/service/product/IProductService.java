package service.product;

import model.Product;
import service.IGenericeService;

import java.util.List;

public interface IProductService extends IGenericeService<Product> {
    List<Product> searchByName(String search);
}
