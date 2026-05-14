package com.shopease.controller;

import com.shopease.model.Order;
import com.shopease.model.User;
import com.shopease.service.CartService;
import com.shopease.service.OrderService;
import com.shopease.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired private OrderService orderService;
    @Autowired private CartService cartService;
    @Autowired private UserService userService;

    private User getUser(UserDetails userDetails) {
        return userService.findByEmail(userDetails.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping
    public String myOrders(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = getUser(userDetails);
        model.addAttribute("orders", orderService.getOrdersByUser(user));
        return "user/orders";
    }

    @GetMapping("/checkout")
    public String checkoutPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = getUser(userDetails);
        model.addAttribute("cart", cartService.getOrCreateCart(user));
        model.addAttribute("user", user);
        return "user/checkout";
    }

    @PostMapping("/place")
    public String placeOrder(@AuthenticationPrincipal UserDetails userDetails,
                             @RequestParam String shippingAddress,
                             @RequestParam String paymentMethod,
                             RedirectAttributes redirectAttributes) {
        User user = getUser(userDetails);
        try {
            Order order = orderService.placeOrder(user, shippingAddress, paymentMethod);
            redirectAttributes.addFlashAttribute("success",
                "Order #" + order.getId() + " placed successfully!");
            return "redirect:/orders";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/orders/checkout";
        }
    }

    @GetMapping("/{id}")
    public String orderDetail(@PathVariable Long id,
                              @AuthenticationPrincipal UserDetails userDetails,
                              Model model) {
        User user = getUser(userDetails);
        Order order = orderService.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));

        // Ensure user can only see their own orders (unless admin)
        if (!order.getUser().getId().equals(user.getId()) &&
            !userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/orders";
        }
        model.addAttribute("order", order);
        return "user/order-detail";
    }
}
