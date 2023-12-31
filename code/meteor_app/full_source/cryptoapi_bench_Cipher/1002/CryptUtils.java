package org.alfresco.plugin.digitalSigning.utils;

import java.io.InputStream;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Crypt and decrypt util class.
 * 
 * @author Emmanuel ROUX
 */
public class CryptUtils {

	private static InputStream encryptOrDecrypt(final String key, final int mode, final InputStream is) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			return cis;
			//doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			//CipherOutputStream cos = new CipherOutputStream(os, cipher);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			return cis;
			//doCopy(is, cos);
		} else {
			return null;
		}
	}

}