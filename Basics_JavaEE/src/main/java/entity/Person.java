// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Person.java

package entity;


// Referenced classes of package entity:
//            Gender, Status

public class Person
{

    public Person()
    {
        gender = Gender.MALE;
        status = Status.APPROVED;
    }

    public Person(String arg0, Gender arg1)
    {
        name = arg0;
        gender = arg1;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    private int id;
    private String name;
    private Gender gender;
    private Status status;
}
