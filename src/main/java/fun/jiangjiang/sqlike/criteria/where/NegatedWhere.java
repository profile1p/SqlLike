package fun.jiangjiang.sqlike.criteria.where;

import java.util.function.Predicate;

/**
 * @author lingxiao.li
 * @date 2020/9/6
 */
public class NegatedWhere extends AbstractWhere implements Where {

    private final Where where;

    NegatedWhere(Where where) {
        this.where = where;
    }

    @Override
    public boolean isNegated() {
        return !where.isNegated();
    }

    @Override
    public Predicate<Object> render() {
        return where.render().negate();
    }
}
