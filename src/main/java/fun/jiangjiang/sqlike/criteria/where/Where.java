package fun.jiangjiang.sqlike.criteria.where;

import java.util.function.Predicate;

/**
 * @author lingxiao.li
 * @date 2020/9/5
 */
public interface Where {

    boolean isNegated();

    Where not();

    Where and(Where... wheres);

    Where or(Where... wheres);

    Predicate<Object> render();
}
