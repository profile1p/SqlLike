package fun.jiangjiang.sqlike.criteria;

/**
 * @author lingxiao.li
 * @date 2020/9/5
 */
public interface Limit {

    /**
     * @return 返回查询结果便宜量
     */
    long offset();

    /**
     * @return 返回查询结果最大长度
     */
    long length();

    static Limit of(long length) {
        return of(0, length);
    }

    static Limit of(long offset, long length) {
        return new LimitImpl(offset, length);
    }
}
