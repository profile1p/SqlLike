package fun.jiangjiang.sqlike.criteria.where;

import fun.jiangjiang.sqlike.criteria.where.ComparisonWhere.ComparisonOperator;

/**
 * @author lingxiao.li
 * @date 2020/9/6
 */
public abstract class AbstractExpression implements Expression {

    AbstractExpression() {
    }

    @Override
    public Where isNull() {
        return new IsNullWhere(this);
    }

    @Override
    public Where notNull() {
        return new IsNullWhere(this).not();
    }

    @Override
    public Where equalsTo(Object obj) {
        return equalsTo(new ConstantExpression(obj));
    }

    @Override
    public Where equalsTo(Expression expression) {
        return new ComparisonWhere(ComparisonOperator.EQUAL, this, expression);
    }

    @Override
    public Where in(Object... objs) {
        return new InWhere(this, objs);
    }

    @Override
    public Where in(Iterable<Object> iterable) {
        return new InWhere(this, iterable);
    }

    @Override
    public Where in(Expression... expressions) {
        return new InWhere(this, expressions);
    }

    @Override
    public Where regexLike(String regex) {
        return new LikeWhere(this, regex);
    }

    @Override
    public Where gt(Comparable<Object> comparable) {
        return gt(new ConstantExpression(comparable));
    }

    @Override
    public Where gt(Expression expression) {
        return new ComparisonWhere(ComparisonOperator.GREATER_THAN, this, expression);
    }

    @Override
    public Where lt(Comparable<Object> comparable) {
        return lt(new ConstantExpression(comparable));
    }

    @Override
    public Where lt(Expression expression) {
        return new ComparisonWhere(ComparisonOperator.LESS_THAN, this, expression);
    }

    @Override
    public Where gte(Comparable<Object> comparable) {
        return gte(new ConstantExpression(comparable));
    }

    @Override
    public Where gte(Expression expression) {
        return new ComparisonWhere(ComparisonOperator.GREATER_THAN_OR_EQUAL, this, expression);
    }

    @Override
    public Where lte(Comparable<Object> comparable) {
        return lte(new ConstantExpression(comparable));
    }

    @Override
    public Where lte(Expression expression) {
        return new ComparisonWhere(ComparisonOperator.LESS_THAN_OR_EQUAL, this, expression);
    }
}
