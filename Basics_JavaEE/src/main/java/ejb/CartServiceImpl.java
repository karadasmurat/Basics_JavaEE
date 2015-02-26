// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CartServiceImpl.java

package ejb;

import entity.Cart;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

// Referenced classes of package ejb:
//            CartService

public class CartServiceImpl
    implements CartService
{

    public CartServiceImpl()
    {
        configuration = (new Configuration()).configure();
        StandardServiceRegistryBuilder builder = (new StandardServiceRegistryBuilder()).applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public void persistCart(Cart cart)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(cart);
        session.getTransaction().commit();
        session.close();
    }

    private Configuration configuration;
    private SessionFactory sessionFactory;
}
