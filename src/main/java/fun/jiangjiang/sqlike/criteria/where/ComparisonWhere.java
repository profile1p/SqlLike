package fun.jiangjiang.sqlike.criteria.where;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * @author lingxiao.li
 * @date 2020/9/6
 */
public class ComparisonWhere extends AbstractWhere implements Where {

    private final ComparisonOperator operator;
    private final Expression leftExpression;
    private final Expression rightExpression;

    ComparisonWhere(ComparisonOperator operator, Expression leftExpression, Expression rightExpression) {
        this.operator = operator;
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public Predicate<Object> render() {
        return o -> operator.render().test(leftExpression.value(o), rightExpression.value(o));
    }

    @SuppressWarnings("unchecked")
    enum ComparisonOperator {
        EQUAL {
            @Override
            BiPredicate<Object, Object> render() {
                return Objects::equals;
            }
        },
        GREATER_THAN {
            @SuppressWarnings("rawtypes")
            @Override
            BiPredicate<Object, Object> render() {
                return (o1, o2) -> o1 instanceof Comparable c1 && o2 instanceof Comparable c2 && c1.compareTo(c2) > 0;
            }
        },
        GREATER_THAN_OR_EQUAL {
            @SuppressWarnings("rawtypes")
            @Override
            BiPredicate<Object, Object> render() {
                return (o1, o2) -> o1 instanceof Comparable c1 && o2 instanceof Comparable c2 && c1.compareTo(c2) >= 0;
            }
        },
        LESS_THAN {
            @SuppressWarnings("rawtypes")
            @Override
            BiPredicate<Object, Object> render() {
                return (o1, o2) -> o1 instanceof Comparable c1 && o2 instanceof Comparable c2 && c1.compareTo(c2) < 0;
            }
        },
        LESS_THAN_OR_EQUAL {
            @SuppressWarnings("rawtypes")
            @Override
            BiPredicate<Object, Object> render() {
                return (o1, o2) -> o1 instanceof Comparable c1 && o2 instanceof Comparable c2 && c1.compareTo(c2) <= 0;
            }
        };

        abstract BiPredicate<Object, Object> render();
    }
}
