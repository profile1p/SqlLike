package fun.jiangjiang.sqlike.criteria.where;

/**
 * @author lingxiao.li
 * @date 2020/9/6
 */
public interface Expression {

    static Expression of(Class<?> clz, String field) throws NoSuchFieldException {
        return new FieldExpression(clz, field);
    }

    static Expression of(Object obj) {
        return new ConstantExpression(obj);
    }

    Where isNull();

    Where notNull();

    Where equalsTo(Object obj);

    Where equalsTo(Expression expression);

    Where in(Object... objs);

    Where in(Iterable<Object> iterable);

    Where in(Expression... expressions);

    Where regexLike(String regex);

    Where gt(Comparable<Object> comparable);

    Where gt(Expression expression);

    Where lt(Comparable<Object> comparable);

    Where lt(Expression expression);

    Where gte(Comparable<Object> comparable);

    Where gte(Expression expression);

    Where lte(Comparable<Object> comparable);

    Where lte(Expression expression);

    Object value(Object obj);

    boolean isComparable();
}
