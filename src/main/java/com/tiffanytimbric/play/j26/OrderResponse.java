package com.tiffanytimbric.play.j26;

import java.util.List;
import java.util.UUID;

public record OrderResponse(UUID orderId, UUID userId, List<Item> items) {
}
