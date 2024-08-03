package com.tiffanytimbric.play.hello;

import com.tiffanytimbric.play.LangUtil;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class HelloWorldFunction implements Function<List<String>, ResultStatus> {

    public ResultStatus apply(
            @Nullable final List<String> args
    ) {
        if (CollectionUtils.isEmpty(args)) {
            System.out.println("Hi.");

            return ResultStatus.Success;
        }

        final Optional<String> firstCliArgOpt = LangUtil.getArgument(0, args);
        if (firstCliArgOpt.isEmpty()) {

            return ResultStatus.Failure
                    .addMessage("At least one argument must be provided.");
        }

        System.out.printf("Hi, %s.%n", firstCliArgOpt.get());

        return ResultStatus.Success;
    }

}