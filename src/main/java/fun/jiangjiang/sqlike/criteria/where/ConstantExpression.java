package fun.jiangjiang.sqlike.criteria.where;

/**
 * @author lingxiao.li
 * @date 2020/9/6
 */
public class ConstantExpression extends AbstractExpression implements Expression {

    private final Object constant;
    private final boolean comparable;

    ConstantExpression(Object constant) {
        this.constant = constant;
        comparable = constant instanceof Comparable;
    }

    @Override
    public Object value(Object obj) {
        return constant;
    }

    @Override
    public boolean isComparable() {
        return comparable;
    }
}
