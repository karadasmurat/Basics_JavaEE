// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PwdTester.java

package util;

import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

// Referenced classes of package util:
//            PasswordEncryptionService

public class PwdTester
{

    public PwdTester()
    {
    }

    public static void main(String args[])
    {
        PasswordEncryptionService pes = new PasswordEncryptionService();
        char myPass[] = "12345".toCharArray();
        try
        {
            byte mySalt[] = pes.generateSalt();
            byte myHashedPass[] = pes.getEncryptedPassword("12345", mySalt);
            System.out.println((new StringBuilder("hash: ")).append(new String(myHashedPass)).toString());
            boolean check = pes.authenticate("12345", myHashedPass, mySalt);
            System.out.println((new StringBuilder("check: ")).append(Boolean.toString(check)).toString());
            check = pes.authenticate("12355", myHashedPass, mySalt);
            System.out.println((new StringBuilder("check: ")).append(Boolean.toString(check)).toString());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(InvalidKeySpecException e)
        {
            e.printStackTrace();
        }
    }
}
