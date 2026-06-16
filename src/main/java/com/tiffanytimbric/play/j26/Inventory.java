package com.tiffanytimbric.play.j26;

import java.util.HashMap;
import java.util.UUID;

public record Inventory(UUID id, HashMap<UUID, InventoryItem> items) {
}
