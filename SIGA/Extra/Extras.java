package Extra;

import java.util.ArrayList;

public class Extras {
	ArrayList<String> lista;
	
	public ArrayList<String> getSimNao(){
		lista = new ArrayList<>();
		
		lista.add("SIM");
		lista.add("NAO");
				
		return lista;
	}
	
	public ArrayList<String> Status(){
		lista = new ArrayList<>();
		
		lista.add("ATIVO");
		lista.add("INATIVO");
		
		return lista;
	}
	
	public ArrayList<String> getOperadora(){
		lista = new ArrayList<>();
		
		lista.add("TIM");
		lista.add("OI");
		lista.add("VIVO");
		lista.add("CLARO");
				
		return lista;
	}
	
	public static String FormatCnpjCpf(String cnpjcpf){
		
		if(cnpjcpf.contains("/"))
			cnpjcpf = cnpjcpf.replace(".", "").replace("/", "").replace("-", "").trim();
		else
			cnpjcpf = cnpjcpf.replace(".", "").replace("-", "").trim();
		
		return cnpjcpf;
	}
	
	public static String FormatCep(String cep){
		cep = cep.replace(".","").replace("-","").trim();
		
		return cep;
	}
	
	public static String FormatFone(String fone){
		fone = fone.replace("(","").replace(")","").replace("-", "").trim();
		
		return fone;
	}
	
}
