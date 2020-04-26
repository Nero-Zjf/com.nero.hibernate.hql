package po;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//Person与Address的关系是1对N
@Entity//声明该类是一个Hibernate的持久化类
@Table(name = "person")//指定该类映射的表，name是表名
public class Person {
    @Id //指定该类的唯一标识，通常映射到数据表的主键
    //指定主键的生成策略，其中strategy属性指定了主键生成策略为IDENTITY策略，也就是采用MySQL自动增长的主键生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne(targetEntity = IdCard.class)
    @JoinColumn(name = "idcard_id", nullable = false)
    //指定级联操作策略，此处表示对Person实体的所有持久化操作都会级联到它关联的Address实体
    @Cascade(CascadeType.ALL)
    private IdCard idCard;
    //声明Person类型关联Address，关系为1-N
    @OneToMany(targetEntity = Address.class)
    @JoinColumn(name = "person_id", nullable = false)
    //指定级联操作策略，此处表示对Person实体的所有持久化操作都会级联到它关联的Address实体
    @Cascade(CascadeType.ALL)
    private Set<Address> address = new HashSet<Address>();

    public Person() {

    }

    public Person(String name, IdCard idCard) {
        this.name = name;
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + '\"' +
                ", \"idCard\":" + idCard +
                ", \"address\":" + address +
                '}';
    }
}
