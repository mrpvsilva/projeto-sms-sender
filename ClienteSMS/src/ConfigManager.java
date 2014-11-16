import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

public class ConfigManager {

	private static String filename = "appconfig.properties";

	public static Properties GetProp() {
		try {
			Properties props = new Properties();
			FileInputStream file = new FileInputStream(filename);
			props.load(file);
			return props;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return null;
		}

	}

	public static void SalvarProp(Properties prop) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			prop.store(fos, null);
			fos.flush();
			fos.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,
					"Falha ao salvar as configurações: " + ex.getMessage());
		}

	}

}
