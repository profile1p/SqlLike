package fun.jiangjiang.sqlike.criteria;

/**
 * @author lingxiao.li
 * @date 2020/9/5
 */
public class PageImpl extends AbstractLimit implements Page {

    private final long pageNum;
    private final long pageSize;

    public PageImpl(long pageNum, long pageSize) {
        super(pageNum * pageSize, pageSize);
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    @Override
    public long pageNum() {
        return pageNum;
    }

    @Override
    public long getPageSize() {
        return pageSize;
    }
}
