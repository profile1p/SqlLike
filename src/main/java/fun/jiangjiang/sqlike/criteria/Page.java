package fun.jiangjiang.sqlike.criteria;

/**
 * @author lingxiao.li
 * @date 2020/9/5
 */
public interface Page extends Limit {

    /**
     * @return 返回页码编号
     */
    long pageNum();

    /**
     * @return 返回每页大小
     */
    long getPageSize();

    static Page of(long pageSize) {
        return of(0, pageSize);
    }

    static Page of(long pageNum, long pageSize) {
        return new PageImpl(pageNum, pageSize);
    }
}
