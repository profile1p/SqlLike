package fun.jiangjiang.sqlike.criteria;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author lingxiao.li
 * @date 2020/9/5
 */
public class Group implements Iterable<String> {

    private final List<String> fields;

    Group(String... fields) {
        if (ArrayUtils.isEmpty(fields)) {
            throw new IllegalArgumentException("fields must not be null!");
        }
        this.fields = Arrays.asList(fields);
    }

    Group(Iterable<String> iterable) {
        this.fields = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toUnmodifiableList());
        if (CollectionUtils.isEmpty(this.fields)) {
            throw new IllegalArgumentException("fields must not be null!");
        }
    }

    public static Group by(String... fields) {
        return new Group(fields);
    }

    public static Group by(Iterable<String> iterable) {
        return new Group(iterable);
    }

    public Group and(Group group) {
        final ArrayList<String> list = new ArrayList<>(fields);
        list.addAll(group.fields);
        return Group.by(list);
    }

    public Predicate<Object> render() {
        return new Predicate<>() {

            private Object preObj;
            private List<Object> preValues;

            @Override
            public boolean test(Object o) {
                boolean keep = true;
                final Class<?> clz = o.getClass();
                final List<Object> values = fields.stream()
                        .map(name -> {
                            try {
                                final Field field = clz.getDeclaredField(name);
                                field.setAccessible(true);
                                return field.get(o);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .collect(Collectors.toList());
                if (Objects.nonNull(preObj) && Objects.nonNull(preValues)) {
                    keep = !preValues.equals(values);
                }
                preObj = o;
                preValues = values;
                return keep;
            }
        };
    }

    @Override
    public Iterator<String> iterator() {
        return fields.iterator();
    }
}
