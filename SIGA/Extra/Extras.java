package Extra;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Dominio.Usuario;

public class Extras {
	ArrayList<String> lista;

	private static Usuario _usuarioLogado;
	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3,
			2 };

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

	public static String FormatDate(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
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

	public static boolean validarCPFCNPJ(String cpfCnpj) {

		if ((cpfCnpj == null))
			return false;

		cpfCnpj = cpfCnpj.replace(".", "").replace("/", "").replace("-", "")
				.replace(" ", "");

		if (cpfCnpj.length() == 11)
			return isValidCPF(cpfCnpj);

		if (cpfCnpj.length() == 14)
			return isValidCNPJ(cpfCnpj);

		return false;

	}

	private static boolean isValidCPF(String cpf) {
		Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0, 9) + digito1.toString()
				+ digito2.toString());
	}

	private static boolean isValidCNPJ(String cnpj) {

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1,
				pesoCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString()
				+ digito2.toString());
	}

	public static boolean isValidEmail(String email) {

		if (email == null || email.isEmpty())
			return false;

		return Pattern
				.compile(
						"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
								+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
				.matcher(email).matches();

	}

	public static boolean isValidaCep(String cep) {
		if (cep.length() == 8) {

			cep = cep.substring(0, 5) + "-" + cep.substring(5, 8);
		}

		return Pattern.compile("[0-9]{5}-[0-9]{3}").matcher(cep).matches();

	}

	public static void main(String[] args) {
		// System.out.println("deve ser false: " + validarCPFCNPJ(null));
		// System.out.println("deve ser false: " + validarCPFCNPJ(""));
		// System.out.println("deve ser false: " + validarCPFCNPJ("123"));
		// System.out.println("deve ser false: "
		// + validarCPFCNPJ("125369874444444444"));
		// System.out.println("deve ser false: "
		// + validarCPFCNPJ("859.058.362-73"));
		// System.out
		// .println("deve ser true: " + validarCPFCNPJ("859.058.362-72"));
		// System.out.println("deve ser false: "
		// + validarCPFCNPJ("15.274.758/0001-93"));
		System.out.println("deve ser false: " + Extras.isValidEmail(""));
		System.out.println("deve ser false: " + Extras.isValidEmail(null));
		System.out.println("deve ser false: " + Extras.isValidEmail("sfsfdsf"));
		System.out.println("deve ser false: " + Extras.isValidEmail("@"));
		System.out.println("deve ser false: " + Extras.isValidEmail("adddaa@"));
		System.out.println("deve ser false: "
				+ Extras.isValidEmail("adddaa@fdsfsfs"));
		System.out.println("deve ser false: "
				+ Extras.isValidEmail("adddaa@fdsfsfs."));
		System.out.println("deve ser true: "
				+ Extras.isValidEmail("adddaa@fdsfsfs.com"));
		System.out.println("deve ser true: "
				+ Extras.isValidEmail("mrpvsilva@gmail.com.br"));

		System.out.println("deve ser false: "
				+ Extras.isValidaCep("mrpvsilva@gmail.com.br"));

		System.out.println("deve ser false: " + Extras.isValidaCep("35353"));

		System.out
				.println("deve ser false: " + Extras.isValidaCep("362632111"));
		System.out.println("deve ser true: " + Extras.isValidaCep("67145280"));

	}
}
