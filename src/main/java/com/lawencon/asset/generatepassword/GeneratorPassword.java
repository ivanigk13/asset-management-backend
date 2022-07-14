package com.lawencon.asset.generatepassword;

public class GeneratorPassword {

	public String generatePassword() {
		String code = "0123456789QWERTYUIOPASDFGHJKLZXCVBNM";
		StringBuilder builder = new StringBuilder(8);
		for (int i = 0; i < 8; i++) {
			int index = (int)(code.length()*Math.random());
			builder.append(code.charAt(index));
		}
		return builder.toString();
	}
}
