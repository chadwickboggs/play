package com.tiffanytimbric.play;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public final class LangUtil {

    @Nonnull
    public static Optional<String> getArgument(
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
