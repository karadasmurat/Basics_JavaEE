// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PasswordHash.java

package util;

import java.io.PrintStream;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHash
{

    public PasswordHash()
    {
    }

    public static String createHash(String password)
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        return createHash(password.toCharArray());
    }

    public static String createHash(char password[])
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        SecureRandom random = new SecureRandom();
        byte salt[] = new byte[32];
        random.nextBytes(salt);
        byte hash[] = pbkdf2(password, salt, 20000, 24);
        return (new StringBuilder(String.valueOf(toHex(salt)))).append(":").append(toHex(hash)).toString();
    }

    public static boolean validatePassword(String password, String correctHash)
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        return validatePassword(password.toCharArray(), correctHash);
    }

    public static boolean validatePassword(char password[], String correctHash)
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String params[] = correctHash.split(":");
        int iterations = 20000;
        byte salt[] = fromHex(params[0]);
        byte hash[] = fromHex(params[1]);
        byte testHash[] = pbkdf2(password, salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    private static boolean slowEquals(byte a[], byte b[])
    {
        int diff = a.length ^ b.length;
        for(int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];

        return diff == 0;
    }

    private static byte[] pbkdf2(char password[], byte salt[], int iterations, int bytes)
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return skf.generateSecret(spec).getEncoded();
    }

    private static byte[] fromHex(String hex)
    {
        byte binary[] = new byte[hex.length() / 2];
        for(int i = 0; i < binary.length; i++)
            binary[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);

        return binary;
    }

    private static String toHex(byte array[])
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = array.length * 2 - hex.length();
        if(paddingLength > 0)
            return (new StringBuilder(String.valueOf(String.format((new StringBuilder("%0")).append(paddingLength).append("d").toString(), new Object[] {
                Integer.valueOf(0)
            })))).append(hex).toString();
        else
            return hex;
    }

    public static void main(String args[])
    {
        try
        {
            for(int i = 0; i < 10; i++)
                System.out.println(createHash("p\r\nassw0Rd!"));

            boolean failure = false;
            System.out.println("Running tests...");
            for(int i = 0; i < 100; i++)
            {
                String password = (new StringBuilder()).append(i).toString();
                String hash = createHash(password);
                String secondHash = createHash(password);
                System.out.println((new StringBuilder("\npassword: ")).append(password).toString());
                System.out.println((new StringBuilder("hash: ")).append(hash).toString());
                if(hash.equals(secondHash))
                {
                    System.out.println("FAILURE: TWO HASHES ARE EQUAL!");
                    failure = true;
                }
                if(validatePassword(password, hash))
                    System.out.println((new StringBuilder("Validated: ")).append(password).toString());
                String wrongPassword = (new StringBuilder()).append(i + 1).toString();
                if(validatePassword(wrongPassword, hash))
                {
                    System.out.println("FAILURE: WRONG PASSWORD ACCEPTED!");
                    failure = true;
                }
                if(!validatePassword(password, hash))
                {
                    System.out.println("FAILURE: GOOD PASSWORD NOT ACCEPTED!");
                    failure = true;
                }
            }

            if(failure)
                System.out.println("TESTS FAILED!");
            else
                System.out.println("TESTS PASSED!");
        }
        catch(Exception ex)
        {
            System.out.println((new StringBuilder("ERROR: ")).append(ex).toString());
        }
    }

    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    public static final int SALT_BYTE_SIZE = 32;
    public static final int HASH_BYTE_SIZE = 24;
    public static final int PBKDF2_ITERATIONS = 20000;
    public static final int ITERATION_INDEX = 0;
    public static final int SALT_INDEX = 0;
    public static final int PBKDF2_INDEX = 1;
}
