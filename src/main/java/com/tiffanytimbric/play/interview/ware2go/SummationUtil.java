package com.tiffanytimbric.play.interview.ware2go;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;


public final class SummationUtil {

    @SafeVarargs
    @Nonnull
    public static List<Pair<Integer, Long>> sum(
            @Nullable final List<Pair<Integer, Long>>... inputLists
    ) {
        if (ArrayUtils.isEmpty(inputLists)) {
            return new ArrayList<>();
        }

        final TreeMap<Integer, Pair<Integer, Long>> summations = new TreeMap<>();

        Arrays.stream(inputLists)
                .filter(CollectionUtils::isNotEmpty)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .forEach(pair -> {
                    final Integer pairKey = pair.getLeft();

                    if (summations.containsKey(pairKey)) {
                        summations.put(
                                pairKey,
                                Pair.of(
                                        pairKey,
                                        summations.get(pairKey).getRight() + pair.getRight()
                                )
                        );
                    }
                    else {
                        summations.put(pairKey, pair);
                    }
                });

        return summations.values().stream().toList();
    }

}
