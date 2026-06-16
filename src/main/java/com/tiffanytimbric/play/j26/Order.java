package com.tiffanytimbric.play.j26;

import java.util.List;
import java.util.UUID;

public record Order(UUID id, UUID userId, List<Item> items) {
}
