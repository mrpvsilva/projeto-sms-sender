package Control;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.ServicosBean;

public class ServicoControlTest {

	ServicosControl sc = new ServicosControl();

	@Test
	public void Cadastrar() {
		ServicosBean sb = new ServicosBean();

		sb.setAtivoitem(true);
		sb.setDescricaoitem("descricao do item1");
		sb.setNomeitem("item1");
		sb.setVlrcomercialitem(1000.0);
		sb.setVlrcustoitem(500.0);

		String esperado = null;
		assertEquals(esperado, sc.Cadastrar(sb));
	}

}
