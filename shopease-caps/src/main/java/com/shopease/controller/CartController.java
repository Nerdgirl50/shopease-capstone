package com.shopease.controller;

import com.shopease.model.Cart;
import com.shopease.model.User;
import com.shopease.service.CartService;
import com.shopease.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired private CartService cartService;
    @Autowired private UserService userService;

    private User getUser(UserDetails userDetails) {
        return userService.findByEmail(userDetails.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping
    public String viewCart(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = getUser(userDetails);
        Cart cart = cartService.getOrCreateCart(user);
        model.addAttribute("cart", cart);
        return "user/cart";
    }

    @PostMapping("/add")
    public String addToCart(@AuthenticationPrincipal UserDetails userDetails,
                            @RequestParam Long productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            RedirectAttributes redirectAttributes) {
        User user = getUser(userDetails);
        cartService.addToCart(user, productId, quantity);
        redirectAttributes.addFlashAttribute("success", "Item added to cart!");
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCart(@AuthenticationPrincipal UserDetails userDetails,
                             @RequestParam Long itemId,
                             @RequestParam int quantity) {
        User user = getUser(userDetails);
        cartService.updateQuantity(user, itemId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@AuthenticationPrincipal UserDetails userDetails,
                                 @RequestParam Long itemId) {
        User user = getUser(userDetails);
        cartService.removeFromCart(user, itemId);
        return "redirect:/cart";
    }
}
