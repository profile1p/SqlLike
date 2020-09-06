package fun.jiangjiang.sqlike.criteria.where;

import java.lang.reflect.Field;

/**
 * @author lingxiao.li
 * @date 2020/9/6
 */
public class FieldExpression extends AbstractExpression implements Expression {

    private final Class<?> clz;
    private final String fieldName;
    private final Field field;
    private final boolean comparable;

    FieldExpression(Class<?> clz, String fieldName) throws NoSuchFieldException {
        this.clz = clz;
        this.fieldName = fieldName;
        field = clz.getDeclaredField(fieldName);
        field.setAccessible(true);
        comparable = Comparable.class.isAssignableFrom(field.getDeclaringClass());
    }

    @Override
    public Object value(Object obj) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isComparable() {
        return comparable;
    }
}
