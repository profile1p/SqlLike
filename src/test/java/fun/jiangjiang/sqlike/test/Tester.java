package fun.jiangjiang.sqlike.test;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import fun.jiangjiang.sqlike.criteria.Group;
import fun.jiangjiang.sqlike.criteria.Limit;
import fun.jiangjiang.sqlike.criteria.Order;
import fun.jiangjiang.sqlike.criteria.Order.ByField;
import fun.jiangjiang.sqlike.criteria.Order.Direction;
import fun.jiangjiang.sqlike.criteria.where.Expression;
import fun.jiangjiang.sqlike.criteria.where.Where;
import fun.jiangjiang.sqlike.query.Query;

/**
 * @author lingxiao.li
 * @date 2020/9/5
 */
public class Tester {

    private static final List<TestData> testList;
    private static final TestData d1, d2, d3, d4, d5, d6, d7, d8, d9, d10;

    // d1  = {id=1  , name='names'         , startTime=2020-08-17T14:11:30.146, endTime=2020-08-27T14:11:30.146, num1=11   , num2=22   }
    // d2  = {id=2  , name='have names'    , startTime=2020-09-01T14:11:30.146, endTime=2020-08-27T14:11:30.146, num1=11   , num2=22   }
    // d3  = {id=3  , name='has names'     , startTime=2020-08-17T14:11:30.146, endTime=2020-08-27T14:11:30.146, num1=66   , num2=22   }
    // d4  = {id=4  , name='split na mes'  , startTime=2020-08-17T14:11:30.146, endTime=2020-08-27T14:11:30.146, num1=null , num2=22   }
    // d5  = {id=5  , name='other words'   , startTime=2020-08-17T14:11:30.146, endTime=2020-08-27T14:11:30.146, num1=null , num2=22   }
    // d6  = {id=6  , name=''              , startTime=2020-08-17T14:11:30.146, endTime=2020-08-27T14:11:30.146, num1=11   , num2=22   }
    // d7  = {id=7  , name='null'          , startTime=2020-08-17T14:11:30.146, endTime=2020-08-27T14:11:30.146, num1=11   , num2=22   }
    // d8  = {id=8  , name='other names'   , startTime=2020-08-17T14:11:30.146, endTime=2020-08-27T14:11:30.146, num1=11   , num2=22   }
    // d9  = {id=9  , name='12345'         , startTime=2020-08-17T14:11:30.146, endTime=2020-08-27T14:11:30.146, num1=11   , num2=22   }
    // d10 = {id=10 , name='null'          , startTime=2020-08-17T14:11:30.146, endTime=2020-08-27T14:11:30.146, num1=11   , num2=22   }
    static {
        final LocalDateTime now = LocalDateTime.now();
        d1 = TestData.newBuilder().id(1L).name("names").startTime(now.minusDays(20))
                .endTime(now.minusDays(10)).num1(11L).num2(22L).build();
        d2 = TestData.newBuilder().id(2L).name("have names").startTime(now.minusDays(5))
                .endTime(now.minusDays(10)).num1(11L).num2(22L).build();
        d3 = TestData.newBuilder().id(3L).name("has names").startTime(now.minusDays(20))
                .endTime(now.minusDays(10)).num1(66L).num2(22L).build();
        d4 = TestData.newBuilder().id(4L).name("split na mes").startTime(now.minusDays(20))
                .endTime(now.minusDays(10)).num1(null).num2(22L).build();
        d5 = TestData.newBuilder().id(5L).name("other words").startTime(now.minusDays(20))
                .endTime(now.minusDays(10)).num1(null).num2(22L).build();
        d6 = TestData.newBuilder().id(6L).name("").startTime(now.minusDays(20))
                .endTime(now.minusDays(10)).num1(11L).num2(22L).build();
        d7 = TestData.newBuilder().id(7L).name(null).startTime(now.minusDays(20))
                .endTime(now.minusDays(10)).num1(11L).num2(22L).build();
        d8 = TestData.newBuilder().id(8L).name("other names").startTime(now.minusDays(20))
                .endTime(now.minusDays(10)).num1(11L).num2(22L).build();
        d9 = TestData.newBuilder().id(9L).name("12345").startTime(now.minusDays(20))
                .endTime(now.minusDays(10)).num1(11L).num2(22L).build();
        d10 = TestData.newBuilder().id(10L).name(null).startTime(now.minusDays(20))
                .endTime(now.minusDays(10)).num1(11L).num2(22L).build();
        testList = List.of(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10);
    }

    private final Query query = Query.INSTANCE;

    @Before
    public void formatPrintTestList() {
        final AtomicInteger counter = new AtomicInteger();
        System.out.println("==== TestList ================================");
        testList.stream()
                .map(TestData::toString)
                .map(s -> String.format("%1$-6s", "// d" + counter.incrementAndGet()) + " = " + s.substring(8))
                .forEach(System.out::println);
        System.out.println("==== TestList ================================");
        System.out.println();
        System.out.println();
    }

    @Test
    public void testFormal() throws NoSuchFieldException {
        System.out.println("==== testFormal ================================");
        final Expression nameExpression = Expression.of(TestData.class, "name");
        Where where = nameExpression.regexLike(".*names.*");
        final Expression num1Expression = Expression.of(TestData.class, "num1");
        final Expression num2Expression = Expression.of(TestData.class, "num2");
        where = where.and(num1Expression.lte(num2Expression));
        Order order = Order.by(false, Direction.ASC, "startTime");
        order = order.and(Order.by(true, new ByField("id", Direction.DESC)));
        Group group = Group.by("startTime");
        final Limit limit = Limit.of(2);
        List<TestData> result;
        List<TestData> answer;
        int answerId = 0;
        result = this.query.query(testList, where, null, null, null);
        answer = List.of(d1, d2, d8);
        printAnswer(++answerId, result, answer);
        result = this.query.query(testList, where, order, null, null);
        answer = List.of(d8, d1, d2);
        printAnswer(++answerId, result, answer);
        result = this.query.query(testList, where, order, group, null);
        answer = List.of(d8, d2);
        printAnswer(++answerId, result, answer);
        result = this.query.query(testList, where, order, null, limit);
        answer = List.of(d8, d1);
        printAnswer(++answerId, result, answer);
    }

    private void printAnswer(Integer id, List<TestData> result, List<TestData> answer) {
        System.out.println("==== answer " + id + " " + (Objects.equals(result, answer) ? "right" : "wrong") + " ====");
        System.out.println("result:");
        Stream.ofNullable(result)
                .flatMap(Collection::stream)
                .forEach(System.out::println);
        System.out.println("answer:");
        Stream.ofNullable(answer)
                .flatMap(Collection::stream)
                .forEach(System.out::println);
        System.out.println();
    }
}
