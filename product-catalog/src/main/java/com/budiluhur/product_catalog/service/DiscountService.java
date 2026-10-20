
package com.budiluhur.product_catalog.service;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    // Menghitung harga produk setelah diskon
    public double calculateDiscountedPrice(
            double originalPrice,
            double discountPercentage) {

        // Validasi persentase diskon
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException(
                    "Diskon harus antara 0 sampai 100 persen"
            );
        }

        // Menghitung harga setelah diskon
        return originalPrice -
                (originalPrice * discountPercentage / 100);
    }
}
