package com.kh.ros;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTests {

    @Mock
    private MenuService menuService;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testPlaceOrder_Success() {
        Long menuItemId = 1L;
        int quantity = 2;
        PaymentDetails paymentDetails = new PaymentDetails("1234567890", "12/24", "123");

        MenuItem menuItem = new MenuItem(menuItemId, "Burger", 5, 99.0);
        when(menuService.getMenuItemById(menuItemId)).thenReturn(menuItem);
        when(paymentService.processPayment(paymentDetails, 198)).thenReturn(true);

        String result = orderService.placeOrder(menuItemId, quantity, paymentDetails);

        assertEquals("Order placed successfully.", result);
        verify(menuService).getMenuItemById(menuItemId);
        verify(paymentService).processPayment(paymentDetails, 198.0);
        verify(menuService).updateMenuItem(argThat(item -> item.getStock() == 3));
    }

    @Test
    void testPlaceOrder_OutOfStock() {
        Long menuItemId = 1L;
        int quantity = 10;
        PaymentDetails paymentDetails = new PaymentDetails("1234567890", "12/24", "123");

        MenuItem menuItem = new MenuItem(menuItemId, "Burger", 5, 99.0);
        when(menuService.getMenuItemById(menuItemId)).thenReturn(menuItem);

        String result = orderService.placeOrder(menuItemId, quantity, paymentDetails);
        assertEquals("Item is out of stock.", result);
        verify(paymentService, never()).processPayment(any(),anyDouble());
    }

    @Test
    void testPlaceOrder_PaymentFailure() {
        Long menuItemId = 1L;
        int quantity = 2;
        PaymentDetails paymentDetails = new PaymentDetails("1234567890", "12/24", "123");

        MenuItem menuItem = new MenuItem(menuItemId, "Burger", 5, 99.0);
        when(menuService.getMenuItemById(menuItemId)).thenReturn(menuItem);
        when(paymentService.processPayment(any(PaymentDetails.class), anyDouble())).thenReturn(false);

        String result = orderService.placeOrder(menuItemId, quantity, paymentDetails);
        assertEquals("Payment failed. Order not placed.", result);
        verify(menuService).getMenuItemById(menuItemId);
        verify(paymentService).processPayment(any(PaymentDetails.class), anyDouble());
        verify(menuService, never()).updateMenuItem(menuItem);

    }

    @Test
    void testPlaceOrder_ItemNotFound() {
        // Arrange
        Long menuItemId = 1L;
        int quantity = 2;
        PaymentDetails paymentDetails = new PaymentDetails("1234567890", "12/24", "123");

        when(menuService.getMenuItemById(menuItemId)).thenReturn(null);

        String result = orderService.placeOrder(menuItemId, quantity, paymentDetails);

        assertEquals("Item is out of stock.", result);
        verify(menuService).getMenuItemById(menuItemId);
        verify(paymentService, never()).processPayment(any(), anyDouble());
        verify(menuService, never()).updateMenuItem(any());
    }
}