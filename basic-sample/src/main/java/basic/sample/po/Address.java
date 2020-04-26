package basic.sample.po;

import javax.persistence.*;

@Entity//声明该类是一个Hibernate的持久化类
@Table(name = "address")//指定该类映射的表，name是表名
public class Address {
    @Id //指定该类的唯一标识，通常映射到数据表的主键
    //指定主键的生成策略，其中strategy属性指定了主键生成策略为IDENTITY策略，也就是采用MySQL自动增长的主键生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String street;
    private String num;

    public Address() {
    }

    public Address(String street, String num) {
        this.street = street;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"street\":\"" + street + '\"' +
                ", \"num\":\"" + num + '\"' +
                '}';
    }
}
