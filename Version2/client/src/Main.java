
import java.net.InetAddress;

import Client.ClientToServer;
import App.MainAPP;

public class Main {

	public static void main(String argv[]) throws Exception {
		InetAddress adresseServeur = InetAddress.getByName(argv[0]);
		ClientToServer cl = new ClientToServer(adresseServeur);
		// MainAPP app = new MainAPP(cl);
		MainAPP app = new MainAPP();
		app.setVisible(true);
	}
}