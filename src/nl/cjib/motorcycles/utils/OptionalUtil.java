package nl.cjib.motorcycles.utils;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalUtil {
    /**
     * zorgt ervoor, dat er bij een collector ook 1 result wordt teruggegeven
     * @param <T> bevat de te controleren Collector
     * @return 1 resultaat
     */
    public static <T> Collector<T, ?, Optional<T>> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> list.size() == 1 ? Optional.of(list.get(0)) : Optional.empty()
        );
    }

    public static <T> Collector<T, ?, Stream<T>> toShuffledStream() {
        return Collectors.collectingAndThen(Collectors.toList(), collected -> {
            Collections.shuffle(collected);
            return collected.stream();
        });
    }
}
