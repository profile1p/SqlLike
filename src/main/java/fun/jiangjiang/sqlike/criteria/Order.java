package fun.jiangjiang.sqlike.criteria;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import fun.jiangjiang.sqlike.criteria.Order.ByField;

/**
 * @author lingxiao.li
 * @date 2020/9/5
 */
public class Order implements Iterable<ByField> {

    private final boolean nullFirst;
    private final int nullCompareInt;
    private final List<ByField> byFields;

    Order(boolean nullFirst, ByField... byFields) {
        if (ArrayUtils.isEmpty(byFields)) {
            throw new IllegalArgumentException("byFields must not be null!");
        }
        this.nullFirst = nullFirst;
        this.nullCompareInt = nullFirst ? 1 : -1;
        this.byFields = Arrays.asList(byFields);
    }

    Order(boolean nullFirst, Iterable<ByField> byFields) {
        this.nullFirst = nullFirst;
        this.nullCompareInt = nullFirst ? 1 : -1;
        this.byFields = StreamSupport.stream(byFields.spliterator(), false).collect(Collectors.toUnmodifiableList());
        if (CollectionUtils.isEmpty(this.byFields)) {
            throw new IllegalArgumentException("byFields must not be null!");
        }
    }

    public static Order by(boolean nullFirst, ByField... byFields) {
        return new Order(nullFirst, byFields);
    }

    public static Order by(boolean nullFirst, Iterable<ByField> iterable) {
        return new Order(nullFirst, iterable);
    }

    public static Order by(boolean nullFirst, String... fields) {
        return Order.by(nullFirst, Direction.ASC, fields);
    }

    public static Order by(boolean nullFirst, Direction direction, String... fields) {
        return Order.by(nullFirst, Stream.ofNullable(fields)
                .flatMap(Arrays::stream)
                .map(field -> new ByField(field, direction))
                .collect(Collectors.toList()));
    }

    public Order and(Order order) {
        final ArrayList<ByField> list = new ArrayList<>(this.byFields);
        list.addAll(order.byFields);
        return Order.by(this.nullFirst, list);
    }

    public Comparator<Object> render() {
        return render(byFields.iterator());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Comparator<Object> render(Iterator<ByField> byFieldIterator) {
        final ByField byField = byFieldIterator.next();
        final String fieldName = byField.field;
        Comparator<Object> comparator;
        comparator = (o1, o2) -> {
            if (Objects.isNull(o1) || Objects.isNull(o2)) {
                return Objects.isNull(o1) && Objects.isNull(o2)
                        ? 0
                        : Objects.isNull(o1) ? nullCompareInt : -nullCompareInt;
            }
            final Class<?> clz = o1.getClass();
            try {
                // TODO:llx 缓存反射
                final Field field = clz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(o1) instanceof Comparable c1 && field.get(o2) instanceof Comparable c2
                        ? c1.compareTo(c2) : 0;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };
        if (byField.direction == Direction.DESC) {
            comparator = comparator.reversed();
        }
        if (byFieldIterator.hasNext()) {
            comparator = comparator.thenComparing(render(byFieldIterator));
        }
        return comparator;
    }

    @Override
    public Iterator<ByField> iterator() {
        return byFields.iterator();
    }

    public enum Direction {
        ASC, DESC
    }

    public static record ByField(String field, Direction direction) {
        // empty block
    }
}
