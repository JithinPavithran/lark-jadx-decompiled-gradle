package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

@JNINamespace("net::android")
public class AndroidKeyStore {
    private static String getPrivateKeyClassName(PrivateKey privateKey) {
        return privateKey.getClass().getName();
    }

    private static boolean privateKeySupportsCipher(PrivateKey privateKey, String str) {
        try {
            Cipher.getInstance(str).init(1, privateKey);
            return true;
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException unused) {
            return false;
        } catch (Exception e) {
            Log.m264556e("AndroidKeyStore", "Exception while checking support for " + str + ": " + e, new Object[0]);
            return false;
        }
    }

    private static boolean privateKeySupportsSignature(PrivateKey privateKey, String str) {
        try {
            Signature.getInstance(str).initSign(privateKey);
            return true;
        } catch (InvalidKeyException | NoSuchAlgorithmException unused) {
            return false;
        } catch (Exception e) {
            Log.m264556e("AndroidKeyStore", "Exception while checking support for " + str + ": " + e, new Object[0]);
            return false;
        }
    }

    private static byte[] encryptWithPrivateKey(PrivateKey privateKey, String str, byte[] bArr) {
        try {
            Cipher instance = Cipher.getInstance(str);
            try {
                instance.init(1, privateKey);
                return instance.doFinal(bArr);
            } catch (Exception e) {
                Log.m264556e("AndroidKeyStore", "Exception while encrypting input with " + str + " and " + privateKey.getAlgorithm() + " private key (" + privateKey.getClass().getName() + "): " + e, new Object[0]);
                return null;
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e2) {
            Log.m264556e("AndroidKeyStore", "Cipher " + str + " not supported: " + e2, new Object[0]);
            return null;
        }
    }

    private static byte[] signWithPrivateKey(PrivateKey privateKey, String str, byte[] bArr) {
        try {
            Signature instance = Signature.getInstance(str);
            try {
                instance.initSign(privateKey);
                instance.update(bArr);
                return instance.sign();
            } catch (Exception e) {
                Log.m264556e("AndroidKeyStore", "Exception while signing message with " + str + " and " + privateKey.getAlgorithm() + " private key (" + privateKey.getClass().getName() + "): " + e, new Object[0]);
                return null;
            }
        } catch (NoSuchAlgorithmException e2) {
            Log.m264556e("AndroidKeyStore", "Signature algorithm " + str + " not supported: " + e2, new Object[0]);
            return null;
        }
    }
}
