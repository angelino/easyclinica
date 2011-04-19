package br.com.easyclinica.infra.gravatar;

import java.security.MessageDigest;

public class GravatarImage {
	private String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

	public String getUrl(String email, int size) {
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");
			byte[] md5hash = new byte[32];
			md.update(email.getBytes("utf-8"), 0, email.length());
			md5hash = md.digest();
			
			
			return "http://www.gravatar.com/avatar/" + convertToHex(md5hash) + "?s=" + size;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
