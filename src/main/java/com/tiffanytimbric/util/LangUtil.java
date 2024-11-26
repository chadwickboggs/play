package com.tiffanytimbric.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class LangUtil {

    @Nonnull
    public static <T> Optional<T> opt(
            @Nullable final T obj
    ) {
        if (obj == null) {
            return optNull(obj);
        }

        return Optional.of(obj);
    }

    @Nonnull
    public static <T> Optional<T> optNull(
            @Nullable final T obj
    ) {
        return Optional.ofNullable(obj);
    }

    @Nonnull
    public static <T> Optional<T> optEmpty(
            @Nonnull final Class<T> clazz
    ) {
        return Optional.empty();
    }

}
