
package com.budiluhur.product_catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.budiluhur.product_catalog.exception.ProductNotFoundException;
import com.budiluhur.product_catalog.model.Product;
import com.budiluhur.product_catalog.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final DiscountService discountService;

    // Constructor Injection
    public ProductService(
            ProductRepository productRepository,
            DiscountService discountService) {

        this.productRepository = productRepository;
        this.discountService = discountService;
    }

    // Mendapatkan semua produk
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Mendapatkan produk berdasarkan ID
    public Product getProductById(String id)
            throws ProductNotFoundException {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Produk dengan ID '" + id
                                        + "' tidak ditemukan!"));
    }

    // Menambahkan produk
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Mencari produk berdasarkan nama
    public List<Product> searchProductsByName(String keyword) {
        return productRepository
                .findByNameContainingIgnoreCase(keyword);
    }

    // Menghitung harga setelah diskon
    public double getDiscountedProductPrice(
            String id, double discount)
            throws ProductNotFoundException {

        Product product = getProductById(id);

        return discountService.calculateDiscountedPrice(
                product.getPrice(), discount);
    }

    // Memperbarui produk
    public Product updateProduct(
            String id, Product updatedProduct)
            throws ProductNotFoundException {

        Product existingProduct = getProductById(id);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());

        return productRepository.save(existingProduct);
    }

    // Menghapus produk
    public void deleteProduct(String id)
            throws ProductNotFoundException {

        Product existingProduct = getProductById(id);

        productRepository.delete(existingProduct);
    }
}
