package authentication;

import java.util.InputMismatchException;

public class ValidaCpf {

	public static boolean isCpf(String cpf) {
		//considera-se erro CPF's formados por uma sequencia de numeros iguais
		if( cpf.length() != 11 || cpf.matches("^(0{11}|1{11}|2{11}|3{11}|4{11}|5{11}|6{11}|7{11}|8{11}|9{11})$") ) {
				return false;
		}
		
		char dig10 = 0, dig11 = 0;
		int sm, i, r, num, peso;
		//"try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			//Calculo de 1o. Digito verificador
			sm = 0;
			peso = 10;
			for ( i = 0; i < 9; i++) {
				//converte o i-esimo caractere do CPF em um numero:
				//por exemplo, transforma o caractere '0' no inteiro 0
				//(48 eh a posicao de '0' na tabela ASCII)
				num = (int) ( cpf.charAt(i) - 48 );
				sm = sm + ( num * peso );
				peso = peso - 1;
			}
			
			r = 11 - ( sm % 11 );
			if ( (r == 10) || (r == 11) ){
				dig10 = '0';
			} else {
				dig10 = ( char )( r + 48 );
			}
			
			//Calculo do 2o Digito verificador
			sm = 0;
			peso = 11;
			for ( i = 0; i < 10; i++){
				num = (int) cpf.charAt(i) - 48;
				sm = sm + ( num * peso );
				peso = peso - 1;
			}
			
			r = 11 - (sm % 11);
			if( (r == 10) || (r == 11) ){
				dig11 = '0';
			} else {
				dig11 = (char) ( r + 48 );
			}
			
			//verifica se os digitos calculados conferem com os digitos informados
			if( dig10 == cpf.charAt( 9 ) && dig11 == cpf.charAt(10)) {
				return true;
			} else {
				return false;
			}
		} catch ( NumberFormatException erro ) {
			return false;
		}
	}
	
	public static String imprimeCPF( String cpf ) {
		return cpf.substring(0, 3) + "." + cpf.substring( 3, 6 ) +
				"." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
	}
}
