package controller;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.product.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String listProducts(Model model, String search) {
        Iterable<Product> productList;
        if (search == null) {
            productList = productService.findAll();
        } else {
            productList = productService.findByName(search);
        }
        model.addAttribute("list", productList);
        return "/product/list";
    }

    @GetMapping("/create-product")
    public String showFormCreate() {
        return "/product/create";
    }

    @PostMapping("/create")
    public String create(Product product) {
        productService.save(product);
        return "/product/create";
    }

    @GetMapping("/edit-product/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.findById(id);
        if (product != null) {
            Product product1 = product.get();
            model.addAttribute("product", product1);
            return "/product/edit";
        } else {
            return "/product/list";
        }
    }

    @PostMapping("/edit-product")
    public String update(Product product) {
        productService.save(product);
        return "/product/edit";
    }

    @GetMapping("/delete-product/{id}")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            Product product1 = product.get();
            model.addAttribute("product", product1);
            return "/product/delete";
        } else {
            return "/product/list";
        }
    }

    @PostMapping("/delete-product")
    public String deleteProduct(Product product) {
        productService.remove(product.getId());
        return "redirect:/products";
    }
}
