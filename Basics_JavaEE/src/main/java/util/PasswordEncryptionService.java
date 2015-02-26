// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PasswordEncryptionService.java

package util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordEncryptionService
{

    public PasswordEncryptionService()
    {
    }

    public boolean authenticate(String attemptedPassword, byte encryptedPassword[], byte salt[])
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        byte encryptedAttemptedPassword[] = getEncryptedPassword(attemptedPassword, salt);
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }

    public byte[] getEncryptedPassword(String password, byte salt[])
        throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String algorithm = "PBKDF2WithHmacSHA1";
        int derivedKeyLength = 160;
        int iterations = 20000;
        java.security.spec.KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
        return f.generateSecret(spec).getEncoded();
    }

    public byte[] generateSalt()
        throws NoSuchAlgorithmException
    {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte salt[] = new byte[8];
        random.nextBytes(salt);
        return salt;
    }
}
