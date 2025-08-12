package ru.husnim.spring_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import ru.husnim.spring_shop.dto.ProductDTO;
import ru.husnim.spring_shop.model.Category;
import ru.husnim.spring_shop.model.Product;
import ru.husnim.spring_shop.repository.CategoryRepository;
import ru.husnim.spring_shop.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Товар не найден"));
    }
    
    public Product getProductByName(String name) {
        return productRepository.findByName(name).orElseThrow(() -> new RuntimeException("Товар не найден"));
    }
    
    public List<Product> getProductsByCategoryName(String name) {
        // Находим категорию по ID
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Категория не найдена"));
        
        // Возвращаем продукты, относящиеся к этой категории
        return productRepository.findAll().stream()
                .filter(product -> product.getCategories().contains(category))
                .collect(Collectors.toList());
    }

    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        
        // Присваиваем категории продукту
        if (productDTO.getCategoryIds() != null) {
            Set<Category> categories = productDTO.getCategoryIds().stream()
                    .map(categoryId -> categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new RuntimeException("Категория не найдена")))
                    .collect(Collectors.toSet());
            product.setCategories(categories);
        }
        
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Товар не найден"));
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        
        // Обновляем категории продукта
        if (productDTO.getCategoryIds() != null) {
            Set<Category> categories = productDTO.getCategoryIds().stream()
                    .map(categoryId -> categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new RuntimeException("Категория не найдена")))
                    .collect(Collectors.toSet());
            product.setCategories(categories);
        }
        
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
}
