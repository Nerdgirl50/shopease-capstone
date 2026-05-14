package com.shopease.controller;

import com.shopease.model.Order;
import com.shopease.model.Product;
import com.shopease.service.OrderService;
import com.shopease.service.ProductService;
import com.shopease.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private ProductService productService;
    @Autowired private OrderService orderService;
    @Autowired private UserService userService;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("totalProducts", productService.countProducts());
        model.addAttribute("totalOrders", orderService.countOrders());
        model.addAttribute("totalUsers", userService.countUsers());
        model.addAttribute("totalRevenue", orderService.getTotalRevenue());
        model.addAttribute("recentOrders", orderService.getAllOrders());
        return "admin/dashboard";
    }

    // ---- Product Management ----
    @GetMapping("/products")
    public String manageProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/products";
    }

    @GetMapping("/products/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productService.getAllCategories());
        return "admin/product-form";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        model.addAttribute("categories", productService.getAllCategories());
        return "admin/product-form";
    }

    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute Product product,
                              RedirectAttributes redirectAttributes) {
        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("success", "Product saved successfully!");
        return "redirect:/admin/products";
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("success", "Product removed.");
        return "redirect:/admin/products";
    }

    // ---- Order Management ----
    @GetMapping("/orders")
    public String manageOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/orders";
    }

    @PostMapping("/orders/status")
    public String updateOrderStatus(@RequestParam Long orderId,
                                    @RequestParam String status,
                                    RedirectAttributes redirectAttributes) {
        orderService.updateStatus(orderId, Order.OrderStatus.valueOf(status));
        redirectAttributes.addFlashAttribute("success", "Order status updated.");
        return "redirect:/admin/orders";
    }

    // ---- User Management ----
    @GetMapping("/users")
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }
}
