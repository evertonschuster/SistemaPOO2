package br.edu.udc.sistemas.poo2.infra;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
	
	public static Long readLong() throws Exception {
		Scanner sc = new Scanner(System.in);
		return sc.nextLong();
		
	}
	
	public static Double readDouble() throws Exception {
		Scanner sc = new Scanner(System.in);
		return sc.nextDouble();
    }
	
	
	public static boolean validaCPF(String CPF) throws ExceptionValidacao {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		    if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
		        CPF.equals("22222222222") || CPF.equals("33333333333") ||
		        CPF.equals("44444444444") || CPF.equals("55555555555") ||
		        CPF.equals("66666666666") || CPF.equals("77777777777") ||
		        CPF.equals("88888888888") || CPF.equals("99999999999") ||
		       (CPF.length() != 11))
		       return(false);
		 
		    char dig10, dig11;
		    int sm, i, r, num, peso;
		 
		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		    try {
		// Calculo do 1o. Digito Verificador
		      sm = 0;
		      peso = 10;
		      for (i=0; i<9; i++) {              
		// converte o i-esimo caractere do CPF em um numero:
		// por exemplo, transforma o caractere '0' no inteiro 0         
		// (48 eh a posicao de '0' na tabela ASCII)         
		        num = (int)(CPF.charAt(i) - 48); 
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }
		 
		      r = 11 - (sm % 11);
		      if ((r == 10) || (r == 11))
		         dig10 = '0';
		      else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
		 
		// Calculo do 2o. Digito Verificador
		      sm = 0;
		      peso = 11;
		      for(i=0; i<10; i++) {
		        num = (int)(CPF.charAt(i) - 48);
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }
		 
		      r = 11 - (sm % 11);
		      if ((r == 10) || (r == 11))
		         dig11 = '0';
		      else dig11 = (char)(r + 48);
		 
		// Verifica se os digitos calculados conferem com os digitos informados.
		      if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
		         return(true);
		      else return(false);
		    } catch (InputMismatchException erro) {
		        return(false);
		    }
		  }

	
	
	public static boolean validaCNPJ(String CNPJ) throws ExceptionValidacao {
		// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
		    if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
		        CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
		        CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
		        CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
		        CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
		       (CNPJ.length() != 14))
		       return(false);
		 
		    char dig13, dig14;
		    int sm, i, r, num, peso;
		 
		// "try" - protege o código para eventuais erros de conversao de tipo (int)
		    try {
		// Calculo do 1o. Digito Verificador
		      sm = 0;
		      peso = 2;
		      for (i=11; i>=0; i--) {
		// converte o i-ésimo caractere do CNPJ em um número:
		// por exemplo, transforma o caractere '0' no inteiro 0
		// (48 eh a posição de '0' na tabela ASCII)
		        num = (int)(CNPJ.charAt(i) - 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10)
		           peso = 2;
		      }
		 
		      r = sm % 11;
		      if ((r == 0) || (r == 1))
		         dig13 = '0';
		      else dig13 = (char)((11-r) + 48);
		 
		// Calculo do 2o. Digito Verificador
		      sm = 0;
		      peso = 2;
		      for (i=12; i>=0; i--) {
		        num = (int)(CNPJ.charAt(i)- 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10)
		           peso = 2;
		      }
		 
		      r = sm % 11;
		      if ((r == 0) || (r == 1))
		         dig14 = '0';
		      else dig14 = (char)((11-r) + 48);
		 
		// Verifica se os dígitos calculados conferem com os dígitos informados.
		      if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
		         return(true);
		      else return(false);
		    } catch (InputMismatchException erro) {
		        return(false);
		    }
		  }	  
		  
		  public static String validaData(String data) throws ExceptionValidacao {
		        DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
		        df.setLenient (false); // aqui o pulo do gato
		        try {
		            return df.parse (data).toString();
		        } catch (ParseException ex) {
		        	throw new ExceptionValidacao("Data Invalida!");
		        }
		  }	  
		  
		  
//		  public static boolean validaPassword(final String password) throws ExceptionValidacao {
//			  char x = 'x';
//			  System.out.println(x >= 'a' && x <= 'z');
//			return true;
//		 
//		  }
		  public static boolean validaPassword (String senha) throws ExceptionValidacao {			  
			  if (senha.length() < 8) return false;

			    boolean achouNumero = false;
			    boolean achouMaiuscula = false;
			    boolean achouMinuscula = false;
			    for (char c : senha.toCharArray()) {
			         if (c >= '0' && c <= '9') {
			             achouNumero = true;
			         } else if (c >= 'A' && c <= 'Z') {
			             achouMaiuscula = true;
			         } else if (c >= 'a' && c <= 'z') {
			             achouMinuscula = true;
			         } 
			    }
			    return achouNumero && achouMaiuscula && achouMinuscula;
			}
	  
		  public static String geradorDeToString(String srt[], Integer p[]) {
			  
			  if(srt.length != p.length) {
				  return "";
			  }
			  String texto  = new String();
			  
			  for(int n = 0;n < srt.length; n++) {
				  for(int i =0; i < p[n]; i++) {
					  if(srt[n].length() <= i) {
						  texto += " ";
					  }else {
						  texto += srt[n].substring(i, i+1);
					  }
				  }
				  if(n >= srt.length -1) {
					  return texto; 
				  }
				  texto += " | ";
			  }
			  return texto; 
		  }
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
}