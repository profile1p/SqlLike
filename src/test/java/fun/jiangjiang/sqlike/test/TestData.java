package fun.jiangjiang.sqlike.test;

import java.time.LocalDateTime;

/**
 * @author lingxiao.li
 * @date 2020/9/6
 */
public class TestData {

    private Long id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long num1;
    private Long num2;

    private TestData(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setStartTime(builder.startTime);
        setEndTime(builder.endTime);
        setNum1(builder.num1);
        setNum2(builder.num2);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(TestData copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.name = copy.getName();
        builder.startTime = copy.getStartTime();
        builder.endTime = copy.getEndTime();
        builder.num1 = copy.getNum1();
        builder.num2 = copy.getNum2();
        return builder;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "id=" + String.format("%1$-3s", id) +
                ", name=" + String.format("%1$-16s", '\'' + name + '\'') +
                ", startTime=" + String.format("%1$.23s", startTime)+
                ", endTime=" + String.format("%1$.23s", endTime)+
                ", num1=" + String.format("%1$-5s", num1)+
                ", num2=" + String.format("%1$-5s", num2)+
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getNum1() {
        return num1;
    }

    public void setNum1(Long num1) {
        this.num1 = num1;
    }

    public Long getNum2() {
        return num2;
    }

    public void setNum2(Long num2) {
        this.num2 = num2;
    }

    public static final class Builder {

        private Long id;
        private String name;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Long num1;
        private Long num2;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder num1(Long num1) {
            this.num1 = num1;
            return this;
        }

        public Builder num2(Long num2) {
            this.num2 = num2;
            return this;
        }

        public TestData build() {
            return new TestData(this);
        }
    }
}
