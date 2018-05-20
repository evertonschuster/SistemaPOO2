package br.edu.udc.sistemas.poo2.infra;

import java.util.Scanner;

public class IOTools {

	public static void clrscr() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	public static Character getche() throws Exception {
		Scanner sc = new Scanner(System.in);
		return sc.next().charAt(0);
	}

	public static Character getch() throws Exception {
		return IOTools.getche();
	}
	
	public static String readString() throws Exception {
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}

	public static Integer readInteger() throws Exception {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}
}