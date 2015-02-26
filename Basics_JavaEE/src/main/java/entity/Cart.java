// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Cart.java

package entity;

import java.util.HashSet;
import java.util.Set;

// Referenced classes of package entity:
//            Item

public class Cart
{

    public Cart()
    {
        items1 = new HashSet();
    }

    public Cart(String name)
    {
        this();
        this.name = name;
    }

    public void addItem(Item item)
    {
        items1.add(item);
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public double getTotal()
    {
        return total;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set getItems1()
    {
        return items1;
    }

    public void setItems1(Set items1)
    {
        this.items1 = items1;
    }

    private long id;
    private double total;
    private String name;
    private Set items1;
}
