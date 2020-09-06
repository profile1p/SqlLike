package fun.jiangjiang.sqlike.criteria.where;

import java.util.function.Predicate;

/**
 * @author lingxiao.li
 * @date 2020/9/6
 */
public class IsNullWhere extends AbstractWhere implements Where {

    private final Expression expression;

    public IsNullWhere(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Predicate<Object> render() {
        return o -> expression.value(o) == null;
    }
}
