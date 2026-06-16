package com.tiffanytimbric.play.j26;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Main {

    static void main(@Nullable final String... args) throws InterruptedException {
        final HashMap<UUID, InventoryItem> inventoryItems = new HashMap<>();
        final Item hairDryerItem = new Item(UUID.randomUUID(), "Hair Dryer");
        final InventoryItem hairDryerInventoryItem = new InventoryItem(hairDryerItem, 10);
        inventoryItems.put(hairDryerItem.id(), hairDryerInventoryItem);

        final UUID inventoryId = UUID.randomUUID();
        final Inventory inventory = new Inventory(inventoryId, inventoryItems);

        final UUID orderId = UUID.randomUUID();
        final UUID userId = UUID.randomUUID();
        final List<Item> orderItems = List.of(hairDryerItem);
        final Order order = new Order(orderId, userId, orderItems);

        final Optional<OrderResponse> orderResponse = new OrderService(inventory)
                .handleOrder(order);

        System.out.println(orderResponse);
    }

}
