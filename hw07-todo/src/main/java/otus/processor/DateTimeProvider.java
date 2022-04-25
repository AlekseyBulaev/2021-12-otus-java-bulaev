package otus.processor;

import java.time.LocalDateTime;

@FunctionalInterface
public interface DateTimeProvider {
    long getTime();
}
