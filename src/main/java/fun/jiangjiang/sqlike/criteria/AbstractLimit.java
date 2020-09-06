package fun.jiangjiang.sqlike.criteria;

/**
 * @author lingxiao.li
 * @date 2020/9/5
 */
public abstract class AbstractLimit implements Limit {

    /**
     * 结果集合中的查询开始位置偏移量,从0开始
     */
    private final long offset;
    /**
     * 结果集合的最大总长度
     */
    private final long length;

    protected AbstractLimit(long offset, long length) {
        this.offset = offset;
        this.length = length;
    }

    @Override
    public long offset() {
        return offset;
    }

    @Override
    public long length() {
        return length;
    }
}
