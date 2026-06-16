package com.tiffanytimbric.play.j26;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class OrderService {

    private final Inventory inventory;

    public OrderService(@Nonnull final Inventory inventory) {
        if (inventory == null) {
            throw new IllegalArgumentException("Parameter \"inventory\" must be non-null.");
        }

        this.inventory = inventory;
    }

    @Nonnull
    public Optional<OrderResponse> handleOrder(@Nullable final Order order) throws InterruptedException {
        if (order == null) {
            return Optional.empty();
        }

        // 1. Open a new scope using a try-with-resources statement and a Joiner policy
        // 'awaitAllSuccessfulOrThrow' ensures if any subtask fails, the scope fails.
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.awaitAllSuccessfulOrThrow())) {

            // 2. Fork subtasks concurrently (executes on virtual threads by default)
            final Subtask<User> userTask = scope.fork(() -> fetchUser(order.userId()).orElseThrow());

            final List<Subtask<InventoryItem>> inventoryTasks = new ArrayList<>();
            order.items().forEach(item -> {
                inventoryTasks.add(scope.fork(() -> checkInventory(item.id()).orElseThrow()));
            });

            // 3. Wait for all subtasks to complete or for a failure short-circuit
            // In Java 25+, join() throws an unchecked FailedException if a subtask fails
            scope.join();

            // 4. Safely process results using .get() (guaranteed to be finished here)
            final User user = userTask.get();
            final List<Item> items = inventoryTasks.stream()
                    .map(Subtask::get)
                    .map(inventoryItem -> {
                        inventoryItem.quantity = inventoryItem.quantity - 1;

                        return inventoryItem.item;
                    })
                    .toList();

            return Optional.of(new OrderResponse(order.id(), user.id(), items));
        }
        // Scope automatically closes here, safely terminating any dangling threads
    }

    @Nonnull
    private Optional<User> fetchUser(@Nullable final UUID userId) {
        if (userId == null) {
            return Optional.empty();
        }

        return Optional.of(new User(userId));
    }

    @Nonnull
    private Optional<InventoryItem> checkInventory(@Nullable final UUID itemId) {
        if (itemId == null) {
            return Optional.empty();
        }
        if (!inventory.items().containsKey(itemId)) {
            return Optional.empty();
        }

        return Optional.of(
                inventory.items().get(itemId)
        );
    }
}
