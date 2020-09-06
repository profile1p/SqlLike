package fun.jiangjiang.sqlike.criteria.where;

import java.util.Objects;

/**
 * @author lingxiao.li
 * @date 2020/9/6
 */
public abstract class AbstractWhere implements Where {

    AbstractWhere() {
    }

    @Override
    public boolean isNegated() {
        return false;
    }

    @Override
    public Where not() {
        return new NegatedWhere(this);
    }

    @Override
    public Where and(Where... wheres) {
        final int oldLength = Objects.requireNonNull(wheres).length;
        final Where[] allWheres = new Where[oldLength + 1];
        allWheres[0] = this;
        System.arraycopy(wheres, 0, allWheres, 1, oldLength);
        return new AndWhere(allWheres);
    }

    @Override
    public Where or(Where... wheres) {
        final int length = Objects.requireNonNull(wheres).length + 1;
        final Where[] allWheres = new Where[length];
        allWheres[0] = this;
        System.arraycopy(wheres, 0, allWheres, 1, length);
        return new OrWhere(allWheres);
    }
}
