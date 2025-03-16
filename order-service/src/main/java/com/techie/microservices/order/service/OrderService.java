package com.techie.microservices.order.service;

import com.techie.microservices.order.client.InventoryClient;
import com.techie.microservices.order.dto.OrderRequest;
import com.techie.microservices.order.event.OrderPlacedEvent;
import com.techie.microservices.order.model.Order;
import com.techie.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest request) {
        log.info("Start - Placing Order");
        boolean inProductInStock = inventoryClient.isInStock(request.skuCode(), request.quantity());

        if (inProductInStock) {

            Order order = this.fromRequest(request);
            orderRepository.save(order);

            // Send the message to kafka topic

            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmail(request.userDetails().email());
            orderPlacedEvent.setFirstName(request.userDetails().firstName());
            orderPlacedEvent.setLastName(request.userDetails().lastName());

            log.info("Start - Sending OrderPlacedEvent {} to kafka topic order-placed", orderPlacedEvent);
            //kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("End - Sending OrderPlacedEvent {} to kafka topic order-placed", orderPlacedEvent);
        } else {
            log.error("Product with skuCode {} is not in stock", request.skuCode());
            throw new RuntimeException(String.format("Product with skuCode %s is not in stock", request.skuCode()));
        }
    }

    public List<Order> getOrders() {
        log.info("Start - Getting Orders");
        return orderRepository.findAll();
    }

    private Order fromRequest(OrderRequest request) {
        return Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .quantity(request.quantity())
                .price(request.price())
                .skuCode(request.skuCode())
                .build();
    }
}
