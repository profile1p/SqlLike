package fun.jiangjiang.sqlike.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fun.jiangjiang.sqlike.criteria.Group;
import fun.jiangjiang.sqlike.criteria.Limit;
import fun.jiangjiang.sqlike.criteria.Order;
import fun.jiangjiang.sqlike.criteria.where.Where;

/**
 * @author lingxiao.li
 * @date 2020/9/5
 */
public class Query {

    public final static Query INSTANCE = new Query();

    private Query() {
    }

    public <T> List<T> query(List<T> data, Where where, Order order, Group group, Limit limit) {
        if (Objects.requireNonNull(data).isEmpty()) {
            return new ArrayList<>(data);
        }
        Stream<T> stream = data.stream();
        if (Objects.nonNull(where)) {
            stream = stream.filter(where.render());
        }
        if (Objects.nonNull(order)) {
            stream = stream.sorted(order.render());
        }
        if (Objects.nonNull(group)) {
            stream = stream.filter(group.render());
        }
        if (Objects.nonNull(limit)) {
            stream = stream.skip(limit.offset()).limit(limit.length());
        }
        return stream.collect(Collectors.toList());
    }
}
