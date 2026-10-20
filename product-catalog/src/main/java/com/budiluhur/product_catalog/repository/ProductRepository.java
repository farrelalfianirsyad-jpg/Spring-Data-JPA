
package com.budiluhur.product_catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.budiluhur.product_catalog.model.Product;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, String> {

    // Mencari produk berdasarkan nama,
    // tanpa membedakan huruf besar dan kecil
    List<Product> findByNameContainingIgnoreCase(String keyword);
}
