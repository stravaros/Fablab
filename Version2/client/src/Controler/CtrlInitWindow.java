package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.Semaphore;

import Client.ClientToServer;
import Model.Mdl;
import View.FrameMenu;
import View.InitWindow;



public class CtrlInitWindow implements ActionListener {
	private Mdl mdl;
	private InitWindow iw;
	
	public CtrlInitWindow(Mdl m, InitWindow initWindow) {
		mdl = m;
		iw = initWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("coucou "+e.getActionCommand());

		switch (e.getActionCommand()){
		case  "Create sensor" :
			System.out.println("coucou "+e.getActionCommand());
			mdl.addCapteur( iw.getSensorX(), iw.getSensorY());

			break;
		case  "Start" :
			System.out.println("coucou "+e.getActionCommand());
			try {
				Semaphore sem1 = new Semaphore(1, true);
				ClientToServer cl = new ClientToServer(InetAddress.getByName(InitWindow.getJtfServer().getText()), mdl, sem1);
				cl.lancement();
			} catch (UnknownHostException e1) {
				System.err.println("Mauvaise adresse serveur");
				e1.printStackTrace();
			} catch (SocketException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;
		case  "Position X" :
			iw.getJtfX().setText("");
			System.out.println("coucou "+e.getActionCommand());
			break;
		
		case  "Position Y" :
			System.out.println("coucou "+e.getActionCommand());
			break;
		
	}
	
	}
}

