package com.tiffanytimbric.play;

import com.tiffanytimbric.play.hello.HelloWorldFunction;
import com.tiffanytimbric.play.hello.ResultStatus;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import java.util.List;

public class Main {

    private final HelloWorldFunction helloWorldFunction;

    public Main() {
        this(new HelloWorldFunction());
    }

    public Main(
            @Nullable final HelloWorldFunction helloWorldFunction
    ) {
        this.helloWorldFunction = helloWorldFunction;
    }

    public static void main(
            @Nullable final String... args
    ) {
        final List<String> argsList = ArrayUtils.isEmpty(args) ? List.of() : List.of(args);

        final ResultStatus resultStatus = new Main().helloWorldFunction.apply(argsList);

        if (resultStatus.ordinal() != 0) {
            System.err.println(resultStatus.getMessage());
        }

        System.exit(resultStatus.ordinal());
    }

}
