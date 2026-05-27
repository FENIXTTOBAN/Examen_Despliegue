package pe.edu.upeu.api_orders.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upeu.api_orders.model.Order;
import pe.edu.upeu.api_orders.repository.OrderRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService service;

    @Test
    void testCreateOrderWithDiscount() {
        // monto > 1000 - si aplica
        Order order = new Order();
        order.setCustomer("Jhan Arly");
        order.setAmount(1200.0);

        when(repository.save(any(Order.class))).thenAnswer(i -> i.getArguments()[0]);

        Order savedOrder = service.createOrder(order);

        assertNotNull(savedOrder);
        assertEquals(1080.0, savedOrder.getAmount());
        verify(repository, times(1)).save(order);
    }

    @Test
    void testCreateOrderWithoutDiscount() {
        // monto <= 1000 - no apliquixion
        Order order = new Order();
        order.setCustomer("Prueba Sin Descuento");
        order.setAmount(800.0);

        when(repository.save(any(Order.class))).thenAnswer(i -> i.getArguments()[0]);

        Order savedOrder = service.createOrder(order);

        assertNotNull(savedOrder);
        assertEquals(800.0, savedOrder.getAmount());
        verify(repository, times(1)).save(order);
    }

    @Test
    void testGetAllOrders() {
        Order order1 = new Order(1L, "Cliente 1", 500.0);
        Order order2 = new Order(2L, "Cliente 2", 1500.0);
        when(repository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> list = service.getAllOrders();

        assertEquals(2, list.size());
        verify(repository, times(1)).findAll();
    }
}