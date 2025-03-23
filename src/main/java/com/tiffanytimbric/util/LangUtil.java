package com.tiffanytimbric.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
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

    @Nonnull
    public static Optional<String> item(
            int index, @Nullable final List<String> args
    ) {
        if (
                index < 0
                        || CollectionUtils.isEmpty(args)
                        || args.size() < index + 1
                        || StringUtils.isBlank(args.get(0))
        ) {
            return Optional.empty();
        }

        return Optional.of(args.get(index));
    }
}
