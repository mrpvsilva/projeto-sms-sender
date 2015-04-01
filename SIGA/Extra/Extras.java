package Extra;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Dominio.Usuario;

public class Extras {
	ArrayList<String> lista;

	private static Usuario _usuarioLogado;
	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3,2 };

	public static Usuario getUsuarioLogado() {
		return _usuarioLogado;
	}

	public static void setUsuarioLogado(Usuario _usuarioLogado) {
		Extras._usuarioLogado = _usuarioLogado;
	}

	public ArrayList<String> getSimNao() {
		lista = new ArrayList<>();

		lista.add("SIM");
		lista.add("NAO");

		return lista;
	}

	public ArrayList<String> Status() {
		lista = new ArrayList<>();

		lista.add("ATIVO");
		lista.add("INATIVO");

		return lista;
	}

	public ArrayList<String> getOperadora() {
		lista = new ArrayList<>();

		lista.add("TIM");
		lista.add("OI");
		lista.add("VIVO");
		lista.add("CLARO");

		return lista;
	}

	public static String FormatCnpjCpf(String cnpjcpf) {

		if (cnpjcpf.contains("/"))
			cnpjcpf = cnpjcpf.replace(".", "").replace("/", "")
					.replace("-", "").trim();
		else
			cnpjcpf = cnpjcpf.replace(".", "").replace("-", "").trim();

		return cnpjcpf;
	}

	public static String FormatCep(String cep) {
		cep = cep.replace(".", "").replace("-", "").trim();

		return cep;
	}

	public static String FormatFone(String fone) {
		fone = fone.replace("(", "").replace(")", "").replace("-", "").trim();

		return fone;
	}

	public static String FormatDDDBD(String fone) {
		return fone.substring(0, 2);
	}

	public static String FormatNumeroBD(String fone) {

		return fone.substring(2, fone.length());
	}

	public static double FormatVlrMoneyBD(String valor) {

		valor = valor.replace("R$", "").replace("_", "").replace(".", "")
				.replace(",", ".").trim();

		double val = Double.parseDouble(valor);
		return val;
	}

	public static String FormatVlrMoney(String valor) {

		valor = valor.replace("R$", "").replace("_", "").replace(",", ".")
				.replace(".", "").trim();

		return valor;
	}

	public static String FormatFoneBD(String fone) {
		return fone.replace("(", "").replace(")", "").replace("-", "").trim();
	}

	public static String FormatDate(Date date) {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date);
	}

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static boolean isValidCPF(String cpf) {
		cpf = cpf.replace(".", "").replace("/", "").replace("-", "");
		if ((cpf == null) || (cpf.length() != 11))
			return false;

		Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0, 9) + digito1.toString()
				+ digito2.toString());
	}

	public static boolean isValidCNPJ(String cnpj) {
		cnpj = cnpj.replace(".", "").replace("/", "").replace("-", "");
		if ((cnpj == null) || (cnpj.length() != 14))
			return false;

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1,
				pesoCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString()
				+ digito2.toString());
	}

	public static void main(String[] args) {
		System.out.println(Extras.FormatDDDBD("91981231126"));
		System.out.println(Extras.FormatNumeroBD("91981231126"));
	}
}
