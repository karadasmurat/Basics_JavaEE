// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CartService.java

package ejb;

import entity.Cart;

public interface CartService
{

    public abstract void persistCart(Cart cart);
}
