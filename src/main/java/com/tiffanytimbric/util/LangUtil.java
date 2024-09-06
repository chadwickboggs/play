package com.tiffanytimbric.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class LangUtil {

    @Nonnull
    public static <T> Optional<T> opt(
            @Nullable final T value
    ) {
        return Optional.ofNullable(value);
    }

}
