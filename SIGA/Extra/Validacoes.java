package Extra;

public class Validacoes {

	private static boolean resp = false;
	
	public static boolean ValidaCpfCnpj(String cpfcnpj){
		
		// Verifica se é um cnpj ou cpf
		if(cpfcnpj.contains("/")){
			
			// substitui a máscaras do cnpj pelo vazio e concatenar a string
			cpfcnpj = Extras.FormatCnpjCpf(cpfcnpj);
			
			// Verifica se tem menos de 13 dígitos ou o valor está em branco
			if(cpfcnpj.length()<13 || cpfcnpj.isEmpty())
				resp = false;
			else
				resp = true;
			
		}else{

			// substitui a máscaras do cpf pelo vazio e concatenar a string			
			cpfcnpj = Extras.FormatCnpjCpf(cpfcnpj);

			// Verifica se tem menos de 11 dígitos ou o valor está em branco			
			if(cpfcnpj.length() <11 || cpfcnpj.isEmpty())
				resp = false;
			else
				resp = true;
			
		}
		// retorna se está apto pra ser cadastrado ou não
		return resp;
	}
	
	public static boolean ValidaTelefone(String fone){

		// substitui os caracteres
		fone = Extras.FormatFone(fone);
		
		// Validação se o número é menor que 8 ou 9 dígitos ou se é vazio
		if(fone.length()<8 && fone.length() < 9 || fone.isEmpty())
			resp = false;
		else
			resp = true;
				
		return resp;
	}
	
	public static boolean ValidaCep(String cep){
		
		// substitui os caracteres
		cep = Extras.FormatCep(cep);
		
		// Validação se tem menos caracter ou se está vazio
		if(cep.length()<8 || cep.isEmpty())
			resp = false;
		else
			resp = true;
		
		return resp;
	}
	
	
}
