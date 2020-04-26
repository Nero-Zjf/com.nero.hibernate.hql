import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import po.Address;
import po.IdCard;
import po.Person;
import util.HibernateUtil;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TestJunit {
    private final Logger logger = Logger.getLogger(String.valueOf(TestJunit.class));

    @Test
    public void testCase1() {
        Assert.assertEquals(6, 6, 0);
    }

    @Before
    public void init() {
        //插入用户
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        Set<Address> addressSet = new HashSet<Address>();
        addressSet.add(new Address("erw", "12312"));
        addressSet.add(new Address("werwr", "34234"));
        Person user1 = new Person("nero", new IdCard("3333", new Date()));
        user1.setAddress(addressSet);
        session.persist(user1);
        Person user2 = new Person("tom", new IdCard("4444", new Date()));
        session.persist(user2);
        Person user3 = new Person("sam", new IdCard("5555", new Date()));
        session.persist(user3);
        Person user4 = new Person("ving", new IdCard("6666", new Date()));
        session.persist(user4);

        ts.commit();
        session.close();
    }

    //HQL的一个简单demo
    @Test
    public void testBasicHQL() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        List list = session.createQuery("select p from Person p where p.idCard.cardNum = :cardNum") //使用HQL语句创建Query对象
                .setString("cardNum", "3333") //为指定参数赋值，这里使用:param的参数占位符
                .list(); //将Query对象转为List集合
        logger.info("testBasicHQL-------------------------");
        session.flush();
        Assert.assertEquals(list.size(), 1);
        for (Object person : list) {
            logger.info(person.toString());
        }

        list = session.createQuery("select p from Person p join p.address a where a.street = ?")
                .setString(0, "erw") // 这里使用索引占位符
                .list();

        Assert.assertEquals(list.size(), 1);
        for (Object person : list) {
            logger.info(person.toString());
        }
        ts.commit();
        session.close();
    }

    //
    @Test
    public void test() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        List list = session.createQuery("select p.name,p.idCard from Person p where p.idCard.cardNum = :cardNum") //使用HQL语句创建Query对象
                .setString("cardNum", "3333") //为指定参数赋值，这里使用:param的参数占位符
                .list(); //将Query对象转为List集合
        logger.info("testBasicHQL-------------------------");
        session.flush();
        Assert.assertEquals(list.size(), 1);
        for (Object person : list) {
            logger.info(person.toString());
        }

        list = session.createQuery("select p from Person p join p.address a where a.street = ?")
                .setString(0, "erw") // 这里使用索引占位符
                .list();

        Assert.assertEquals(list.size(), 1);
        for (Object person : list) {
            logger.info(person.toString());
        }
        ts.commit();
        session.close();
    }

    @After
    public void clear() {
        logger.info("清空数据表");
        //删除所有用户
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction ts = session.beginTransaction();
        session.createQuery("delete Address").executeUpdate();
        session.createQuery("delete Person").executeUpdate();

        ts.commit();
        session.close();
    }
}
