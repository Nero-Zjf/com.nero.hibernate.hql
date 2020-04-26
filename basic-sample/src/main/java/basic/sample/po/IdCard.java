package basic.sample.po;

import javax.persistence.*;
import java.util.Date;

@Entity//声明该类是一个Hibernate的持久化类
@Table(name = "idcard")//指定该类映射的表，name是表名
public class IdCard {
    @Id //指定该类的唯一标识，通常映射到数据表的主键
    //指定主键的生成策略，其中strategy属性指定了主键生成策略为IDENTITY策略，也就是采用MySQL自动增长的主键生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "card_num")
    private String cardNum;
    private Date birth;

    public IdCard() {
    }

    public IdCard(String cardNum, Date birth) {
        this.cardNum = cardNum;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"cardNum\":\"" + cardNum + '\"' +
                ", \"birth\":" + birth +
                '}';
    }
}
