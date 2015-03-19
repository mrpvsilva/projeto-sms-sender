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
	
	public static String FormatDDD(String fone){
		
		if(fone.isEmpty())
			fone="";
		else
			fone = fone.substring(0, 2);
		
		return fone;
	}
	
	public static String FormatFoneBD(String fone){
		
		if(fone.isEmpty())
			fone = "";
		else if(fone.length()==10)
			fone = fone.substring(1, 9);
		else
			fone = fone.substring(1, 10);
			
		return fone;
	}
	
	public static double FormatVlrMoneyBD(String valor){
		
		valor = valor.replace("R$","").replace("_", "").replace(".","").replace(",", ".").trim();
		
		double val = Double.parseDouble(valor);		
		return val;
	}
	
	public static String FormatVlrMoney(String valor){
		
		valor = valor.replace("R$","").replace("_", "").replace(",", ".").replace(".","").trim();
				
		return valor;
	}
}
