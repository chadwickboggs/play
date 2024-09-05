package com.tiffanytimbric.play.interview.delbridge;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BracketTypesData {

    public List<String> openBrackets = new java.util.ArrayList<>();
    public List<String> closeBrackets = new java.util.ArrayList<>();
    public List<Pair<String, String>> bracketTypes = new java.util.ArrayList<>();

    public BracketTypesData() {
    }

    public BracketTypesData(
            @Nullable final String bracketTypes
    ) {
        if (StringUtils.isBlank(bracketTypes)) {
            return;
        }

        final String[] split = bracketTypes.split(",");
        if (ArrayUtils.isEmpty(split)) {
            return;
        }

        Arrays.stream(split).toList().forEach(bracketType ->
                {
                    final String openBracket = bracketType.substring(0, 1);
                    final String closeBracket = bracketType.substring(1, 2);

                    openBrackets.add(openBracket);
                    closeBrackets.add(closeBracket);
                    this.bracketTypes.add(
                            Pair.of(openBracket, closeBracket)
                    );
                }
        );
    }

    public Optional<Pair<String, String>> pairOf(
            @Nullable final String closeBracket
    ) {
        if (StringUtils.isBlank(closeBracket)) {
            throw new IllegalArgumentException(
                    "Parameter `closeBracket` must be non-null and non-blank."
            );
        }

        return bracketTypes.stream()
                .filter(bracketPair ->
                        bracketPair.getRight().equals(closeBracket)
                )
                .findFirst();
    }

}
