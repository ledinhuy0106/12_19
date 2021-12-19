package service.product;

import model.Category;
import model.Product;
import org.springframework.data.domain.Page;
import service.IGeneralService;

import java.awt.print.Pageable;

public interface IProductService extends IGeneralService<Product> {

    public Iterable<Product> findAllByCategory(Category category);

    public Iterable<Product> findByName(String name);

}