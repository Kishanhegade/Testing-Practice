package com.kh.ros;

public class OrderService {
    private final MenuService menuService;
    private final PaymentService paymentService;

    public OrderService(MenuService menuService, PaymentService paymentService) {
        this.menuService = menuService;
        this.paymentService = paymentService;
    }

    String placeOrder(Long menuItemId, int quantity, PaymentDetails paymentDetails) {
        MenuItem menuItem = menuService.getMenuItemById(menuItemId);

        if (menuItem == null || menuItem.getStock() < quantity) {
            return "Item is out of stock.";
        }

        boolean paymentSuccessful = paymentService.processPayment(paymentDetails, menuItem.getPrice() * quantity);

        if (!paymentSuccessful) {
            return "Payment failed. Order not placed.";
        }

        menuItem.setStock(menuItem.getStock() - quantity);
        menuService.updateMenuItem(menuItem);

        return "Order placed successfully.";
    }


}
