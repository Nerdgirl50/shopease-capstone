package com.shopease.config;

import com.shopease.model.Product;
import com.shopease.model.User;
import com.shopease.repository.ProductRepository;
import com.shopease.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired private UserRepository userRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Seed Admin
        if (!userRepository.existsByEmail("admin@shopease.com")) {
            User admin = new User("Admin", "admin@shopease.com",
                passwordEncoder.encode("admin123"), "ROLE_ADMIN");
            admin.setPhone("9999999999");
            admin.setAddress("ShopEase HQ, India");
            userRepository.save(admin);
        }

        // Seed Demo User
        if (!userRepository.existsByEmail("user@shopease.com")) {
            User user = new User("Demo User", "user@shopease.com",
                passwordEncoder.encode("user123"), "ROLE_USER");
            user.setPhone("8888888888");
            user.setAddress("123, Demo Street, Bhubaneswar, Odisha");
            userRepository.save(user);
        }

        // Seed Products
        if (productRepository.count() == 0) {
            productRepository.save(new Product("Apple iPhone 15", "Latest Apple iPhone with A16 chip, 48MP camera, Dynamic Island.",
                new BigDecimal("79999"), 50, "Electronics",
                "https://via.placeholder.com/300x300?text=iPhone+15"));

            productRepository.save(new Product("Samsung Galaxy S24", "Flagship Samsung with Snapdragon 8 Gen 3, 200MP camera.",
                new BigDecimal("74999"), 40, "Electronics",
                "https://via.placeholder.com/300x300?text=Galaxy+S24"));

            productRepository.save(new Product("Sony WH-1000XM5", "Industry-leading noise cancelling wireless headphones.",
                new BigDecimal("24999"), 30, "Electronics",
                "https://via.placeholder.com/300x300?text=Sony+Headphones"));

            productRepository.save(new Product("Nike Air Max 270", "Comfortable running shoes with Max Air cushioning.",
                new BigDecimal("8999"), 100, "Footwear",
                "https://via.placeholder.com/300x300?text=Nike+Air+Max"));

            productRepository.save(new Product("Levi's 501 Jeans", "Classic straight-fit jeans in premium denim.",
                new BigDecimal("3499"), 80, "Clothing",
                "https://via.placeholder.com/300x300?text=Levis+Jeans"));

            productRepository.save(new Product("HP Laptop 15s", "Intel Core i5, 8GB RAM, 512GB SSD, 15.6 inch FHD display.",
                new BigDecimal("54999"), 25, "Electronics",
                "https://via.placeholder.com/300x300?text=HP+Laptop"));

            productRepository.save(new Product("Prestige Rice Cooker", "Electric rice cooker with 1.8L capacity.",
                new BigDecimal("1299"), 60, "Kitchen",
                "https://via.placeholder.com/300x300?text=Rice+Cooker"));

            productRepository.save(new Product("Adidas Track Jacket", "Lightweight track jacket for sports and casual wear.",
                new BigDecimal("2999"), 70, "Clothing",
                "https://via.placeholder.com/300x300?text=Adidas+Jacket"));

            productRepository.save(new Product("Instant Pot Duo", "7-in-1 electric pressure cooker, 6 Quart.",
                new BigDecimal("5999"), 35, "Kitchen",
                "https://via.placeholder.com/300x300?text=Instant+Pot"));

            productRepository.save(new Product("Puma Running Shoes", "Lightweight breathable running shoes for daily training.",
                new BigDecimal("4499"), 90, "Footwear",
                "https://via.placeholder.com/300x300?text=Puma+Shoes"));
        }

        System.out.println("✅ ShopEase data seeded successfully!");
        System.out.println("👤 Admin Login: admin@shopease.com / admin123");
        System.out.println("👤 User Login:  user@shopease.com  / user123");
    }
}
