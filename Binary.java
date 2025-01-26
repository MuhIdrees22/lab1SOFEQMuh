package com.ontariotechu.sofe3980U;

import java.util.Scanner;

/**
 * Unsigned integer Binary variable
 */
public class Binary {
	private String number = "0"; // string containing the binary value '0' or '1'

	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0";
			return;
		}
		for (char ch : number.toCharArray()) {
			if (ch != '0' && ch != '1') {
				this.number = "0";
				return;
			}
		}
		this.number = number.replaceFirst("^0+", "");
		if (this.number.isEmpty()) {
			this.number = "0";
		}
	}

	public String getValue() {
		return this.number;
	}

	public static Binary add(Binary num1, Binary num2) {
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		int carry = 0;
		StringBuilder num3 = new StringBuilder();
		while (ind1 >= 0 || ind2 >= 0 || carry != 0) {
			int sum = carry;
			if (ind1 >= 0) sum += num1.number.charAt(ind1--) - '0';
			if (ind2 >= 0) sum += num2.number.charAt(ind2--) - '0';
			carry = sum / 2;
			num3.insert(0, sum % 2);
		}
		return new Binary(num3.toString());
	}

	public static Binary or(Binary num1, Binary num2) {
		int maxLength = Math.max(num1.number.length(), num2.number.length());
		StringBuilder result = new StringBuilder();
		num1 = new Binary(String.format("%" + maxLength + "s", num1.number).replace(' ', '0'));
		num2 = new Binary(String.format("%" + maxLength + "s", num2.number).replace(' ', '0'));
		for (int i = 0; i < maxLength; i++) {
			result.append((num1.number.charAt(i) == '1' || num2.number.charAt(i) == '1') ? '1' : '0');
		}
		return new Binary(result.toString());
	}

	public static Binary and(Binary num1, Binary num2) {
		int maxLength = Math.max(num1.number.length(), num2.number.length());
		StringBuilder result = new StringBuilder();
		num1 = new Binary(String.format("%" + maxLength + "s", num1.number).replace(' ', '0'));
		num2 = new Binary(String.format("%" + maxLength + "s", num2.number).replace(' ', '0'));
		for (int i = 0; i < maxLength; i++) {
			result.append((num1.number.charAt(i) == '1' && num2.number.charAt(i) == '1') ? '1' : '0');
		}
		return new Binary(result.toString());
	}

	public static Binary multiply(Binary num1, Binary num2) {
		Binary result = new Binary("0");
		for (int i = num2.number.length() - 1; i >= 0; i--) {
			if (num2.number.charAt(i) == '1') {
				String shifted = num1.number + "0".repeat(num2.number.length() - 1 - i);
				result = add(result, new Binary(shifted));
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter first binary number: ");
		Binary binary1 = new Binary(scanner.next());
		System.out.print("Enter second binary number: ");
		Binary binary2 = new Binary(scanner.next());
		scanner.close();

		System.out.println("Addition: " + add(binary1, binary2).getValue());
		System.out.println("Bitwise OR: " + or(binary1, binary2).getValue());
		System.out.println("Bitwise AND: " + and(binary1, binary2).getValue());
		System.out.println("Multiplication: " + multiply(binary1, binary2).getValue());
	}
}
