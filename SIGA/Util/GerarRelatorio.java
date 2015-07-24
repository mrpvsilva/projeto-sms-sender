package Util;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import javax.persistence.EntityManager;

import org.eclipse.persistence.sessions.Session;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class GerarRelatorio {

	public static void gerarRelatorio(TipoRelatorio tipoRelatorio,
			Map<String, Object> parametros) throws JRException {
		InputStream path;

		EntityManager entityManager = Factory.createEntityManager();
		entityManager.getTransaction().begin();
		Connection connection = entityManager.unwrap(Connection.class);

		JasperReport report;
		JasperPrint print = null;
		switch (tipoRelatorio) {
		case ORCAMENTO_FORMATURA:
			path = GerarRelatorio.class
					.getResourceAsStream("/Relatorios/Orcamento_Formatura_.jasper");
			//report =   JasperCompileManager.compileReport(path);
			report =(JasperReport) JRLoader.loadObject(path); 
			print = JasperFillManager
					.fillReport(report, parametros, connection);

			break;

		// case "Dia":
		// path = GerarRelatorio.class
		// .getResourceAsStream("/relatorios/vendasdia.jrxml");
		// report = JasperCompileManager.compileReport(path);

		// print = JasperFillManager
		// .fillReport(report, null, new JRBeanCollectionDataSource(
		// db.Vendas.buscarVendasDia()));

		// break;

		}

		JasperViewer.viewReport(print, false);
		entityManager.getTransaction().commit();

	}

}
