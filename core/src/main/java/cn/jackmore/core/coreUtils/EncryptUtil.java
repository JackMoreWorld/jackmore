package cn.jackmore.core.coreUtils;

import org.springframework.util.StringUtils;

public class EncryptUtil {

	public static String toHex(byte input[]) {
		if (input == null)
			return null;
		StringBuffer output = new StringBuffer(input.length * 2);
		for (int i = 0; i < input.length; i++) {
			int current = input[i] & 0xff;
			if (current < 16)
				output.append("0");
			output.append(Integer.toString(current, 16));
		}

		return output.toString();
	}

	public static byte[] toByteArray(String hex) {
		if (StringUtils.isEmpty(hex) || hex.length()%2==1)
			return null;
		byte[] output = new byte[hex.length() / 2];
		for (int i = 0; i < output.length; i++) {
			String data = hex.substring(i*2, i*2+2);
			Integer value =Integer.valueOf(data, 16);
			output[i] = (byte)(value & 0xff);
		}

		return output;
	}










}
