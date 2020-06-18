package com.example.stream;

import com.example.common.Dish;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author liq
 * @date 2019/11/30
 */
public class StreamTest {


    /**
     * Collector<T, A, R>
     * T 收集项目的泛型
     * A 累加器的类型，收集过程中用于累加部分结果的类型
     * R 收集操作得到的对象
     *
     * @param <T>
     */
    private static class toListCollection<T> implements Collector<T, List<T>, List<T>> {
        /**
         * A function that creates and returns a new mutable result container.
         * 建立新的结果容器
         *
         * @return a function which returns a new, mutable result container
         */
        @Override
        public Supplier<List<T>> supplier() {
            return ArrayList::new;
        }

        /**
         * A function that folds a value into a mutable result container.
         *
         * @return a function which folds a value into a mutable result container
         */
        @Override
        public BiConsumer<List<T>, T> accumulator() {

            return List::add;
        }

        /**
         * A function that accepts two partial results and merges them.  The
         * combiner function may fold state from one argument into the other and
         * return that, or may return a new result container.
         *
         * @return a function which combines two partial results into a combined
         * result
         */
        @Override
        public BinaryOperator<List<T>> combiner() {
            return (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            };
        }

        /**
         * Perform the final transformation from the intermediate accumulation type
         * {@code A} to the final result type {@code R}.
         *
         * <p>If the characteristic {@code IDENTITY_TRANSFORM} is
         * set, this function may be presumed to be an identity transform with an
         * unchecked cast from {@code A} to {@code R}.
         *
         * @return a function which transforms the intermediate result to the final
         * result
         */
        @Override
        public Function<List<T>, List<T>> finisher() {
            return Function.identity();
        }

        /**
         * Returns a {@code Set} of {@code Collector.Characteristics} indicating
         * the characteristics of this Collector.  This set should be immutable.
         *
         * @return an immutable set of collector characteristics
         */
        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
        }
    }


    public static class PrimeNumberCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
        /**
         * A function that creates and returns a new mutable result container.
         *
         * @return a function which returns a new, mutable result container
         */
        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            return () -> new HashMap<Boolean, List<Integer>>() {
                {
                    put(true, new ArrayList<Integer>());
                    put(false, new ArrayList<Integer>());
                }
            };
        }

        /**
         * A function that folds a value into a mutable result container.
         *
         * @return a function which folds a value into a mutable result container
         */
        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
            return null;
        }

        /**
         * A function that accepts two partial results and merges them.  The
         * combiner function may fold state from one argument into the other and
         * return that, or may return a new result container.
         *
         * @return a function which combines two partial results into a combined
         * result
         */
        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
            return null;
        }

        /**
         * Perform the final transformation from the intermediate accumulation type
         * {@code A} to the final result type {@code R}.
         *
         * <p>If the characteristic {@code IDENTITY_TRANSFORM} is
         * set, this function may be presumed to be an identity transform with an
         * unchecked cast from {@code A} to {@code R}.
         *
         * @return a function which transforms the intermediate result to the final
         * result
         */
        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
            return null;
        }

        /**
         * Returns a {@code Set} of {@code Collector.Characteristics} indicating
         * the characteristics of this Collector.  This set should be immutable.
         *
         * @return an immutable set of collector characteristics
         */
        @Override
        public Set<Characteristics> characteristics() {
            return null;
        }
    }


    public static void main(String[] args) {

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        System.out.println("== 收集器===");
        menu.forEach(System.out::println);

        // 规约
        Integer collect = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        Integer collect1 = menu.stream().mapToInt(Dish::getCalories).sum();
        Integer collect2 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));

        IntStream.range(2, 10).forEach(System.out::println);

        List<Integer> collect3 = menu.stream().map(Dish::getCalories).collect(Collectors.toList());


    }
}
