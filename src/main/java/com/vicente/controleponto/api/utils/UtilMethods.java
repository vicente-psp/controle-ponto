package com.vicente.controleponto.api.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

public class UtilMethods {
	
	public static void main(String[] args) {
		/*
		System.out.println("(62)98499-4081 => " + isValidTelefone("(62)98499-4081"));
		System.out.println("6298499-4081 => " + isValidTelefone("6298499-4081"));
		System.out.println("(62)984994081 => " + isValidTelefone("(62)984994081"));
		System.out.println("62984994081 => " + isValidTelefone("62984994081"));
		System.out.println("984994081 => " + isValidTelefone("984994081"));
		System.out.println("9849940818 => " + isValidTelefone("9849940818"));
		System.out.println("(62)98499-40481 => " + isValidTelefone("(62)98499-40481"));
		*/
		System.out.println(passwordEncoder("adm"));
	}

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}

		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
	
	public static boolean isValidEmail(String email) {
		if (StringUtils.isEmpty(email)) {
			return false;
		}
    try {
        InternetAddress emailAddress = new InternetAddress(email);
        emailAddress.validate();
    } catch (AddressException ex) {
        return false;
    }
    return true;
	}
	
	public static boolean isValidTelefone(String telefone) {
		if (StringUtils.isEmpty(telefone)) {
			return false;
		}
		return telefone.matches("(\\(?\\d{2}\\)?)?(\\d{4,5}\\-\\d{4})");
	}
	
	public static String passwordEncoder(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

}
