// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Item.java

package entity;


// Referenced classes of package entity:
//            Cart

public class Item
{

    public Item()
    {
    }

    public Item(String itemId, Cart c)
    {
        this.itemId = itemId;
        cartOfItem = c;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getItemId()
    {
        return itemId;
    }

    public void setItemId(String itemId)
    {
        this.itemId = itemId;
    }

    public double getItemTotal()
    {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal)
    {
        this.itemTotal = itemTotal;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public Cart getCartOfItem()
    {
        return cartOfItem;
    }

    public void setCartOfItem(Cart cartOfItem)
    {
        this.cartOfItem = cartOfItem;
    }

    private long id;
    private String itemId;
    private double itemTotal;
    private int quantity;
    private Cart cartOfItem;
}
