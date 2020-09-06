package fun.jiangjiang.sqlike.criteria.where;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author lingxiao.li
 * @date 2020/9/6
 */
public class InWhere extends AbstractWhere implements Where {

    private final Expression sourceExpression;
    private final List<Expression> expressions;

    public InWhere(Expression sourceExpression, Object... objs) {
        this.sourceExpression = sourceExpression;
        expressions = Stream.ofNullable(Objects.requireNonNull(objs))
                .flatMap(Arrays::stream)
                .map(ConstantExpression::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public InWhere(Expression sourceExpression, Iterable<Object> iterable) {
        this.sourceExpression = sourceExpression;
        expressions = StreamSupport.stream(iterable.spliterator(), false)
                .map(ConstantExpression::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public InWhere(Expression sourceExpression, Expression... expressions) {
        this.sourceExpression = sourceExpression;
        this.expressions = Arrays.asList(expressions);
    }

    @Override
    public Predicate<Object> render() {
        return o -> {
            final Object sourceValue = sourceExpression.value(o);
            return expressions.stream()
                    .map(exp -> exp.value(o))
                    .anyMatch(obj -> Objects.equals(sourceValue, obj));
        };
    }
}
