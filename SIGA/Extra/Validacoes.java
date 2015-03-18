package Extra;

public class Validacoes {

	private static boolean resp = false;
	
	public static boolean ValidaCpfCnpj(String cpfcnpj){
		
		// Verifica se � um cnpj ou cpf
		if(cpfcnpj.contains("/")){
			
			// substitui a m�scaras do cnpj pelo vazio e concatenar a string
			cpfcnpj = Extras.FormatCnpjCpf(cpfcnpj);
			
			// Verifica se tem menos de 13 d�gitos ou o valor est� em branco
			if(cpfcnpj.length()<13 || cpfcnpj.isEmpty())
				resp = false;
			else
				resp = true;
			
		}else{

			// substitui a m�scaras do cpf pelo vazio e concatenar a string			
			cpfcnpj = Extras.FormatCnpjCpf(cpfcnpj);

			// Verifica se tem menos de 11 d�gitos ou o valor est� em branco			
			if(cpfcnpj.length() <11 || cpfcnpj.isEmpty())
				resp = false;
			else
				resp = true;
			
		}
		// retorna se est� apto pra ser cadastrado ou n�o
		return resp;
	}
	
	public static boolean ValidaTelefone(String fone){

		// substitui os caracteres
		fone = Extras.FormatFone(fone);
		
		// Valida��o se o n�mero � menor que 8 ou 9 d�gitos ou se � vazio
		if(fone.length()<8 && fone.length() < 9 || fone.isEmpty())
			resp = false;
		else
			resp = true;
				
		return resp;
	}
	
	public static boolean ValidaCep(String cep){
		
		// substitui os caracteres
		cep = Extras.FormatCep(cep);
		
		// Valida��o se tem menos caracter ou se est� vazio
		if(cep.length()<8 || cep.isEmpty())
			resp = false;
		else
			resp = true;
		
		return resp;
	}
	
	
}
