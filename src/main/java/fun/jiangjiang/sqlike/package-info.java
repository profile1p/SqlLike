/**
 * 用Java实现一个类似SQL查询的服务，参数模型自己设计：
 * <p>入参：
 * <p> - 类比某张表内全量数据
 * <p> - 过滤条件，支持多个，与/或
 * <p> - 排序，支持多个，以及升序倒序
 * <p> - 分组，支持多个
 * <p> - 最大返回结果数
 * <p>返回：
 * <p> - 查询结果
 * <p>大概这样：
 *
 * <p>   List<Object> query(List<Object> data, Where where, OrderBy orderBy, GroupBy groupBy, Limit limit) {
 * <p>       //你的代码实现。。。
 * <p>   }
 * <p>
 * <p>作业要求：
 * <p>1. 在个人github上新建一个工程，完成后提交该项目的URL
 * <p>2. 需要包含测试代码，测试覆盖率报告(加分项)
 * <p>3. 自由发挥，重点展现自己代码功底，包括不限于 可读性/可测性/可维护性/性能等等。有些细节如果时间紧张可以封装甚至MOCK掉。
 * <p>4. 时间要求：2-3日内，越快越好（加分项）
 */
package fun.jiangjiang.sqlike;