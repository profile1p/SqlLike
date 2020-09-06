package fun.jiangjiang.sqlike.criteria.where;

import java.util.function.Predicate;

/**
 * @author lingxiao.li
 * @date 2020/9/6
 */
public class LikeWhere extends AbstractWhere implements Where {

    private final Expression expression;
    private final String regex;

    public LikeWhere(Expression expression, String regex) {
        this.expression = expression;
        this.regex = regex;
    }

    @Override
    public Predicate<Object> render() {
        return o -> expression.value(o) instanceof String s && s.matches(regex);
    }
}
