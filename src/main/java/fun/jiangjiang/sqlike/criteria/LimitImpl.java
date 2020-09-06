package fun.jiangjiang.sqlike.criteria;

/**
 * @author lingxiao.li
 * @date 2020/9/5
 */
public class LimitImpl extends AbstractLimit implements Limit {

    LimitImpl(long offset, long length) {
        super(offset, length);
    }
}
