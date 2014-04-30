package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;

import Client.ClientToServer;
import Model.Mdl;
import View.FrameMenu;
import View.InitWindow;

public class CtrlInitWindow implements ActionListener {
	private Mdl mdl;
	private InitWindow iw;
	private static ClientToServer cl;

	public CtrlInitWindow(Mdl m, InitWindow initWindow) {
		mdl = m;
		iw = initWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("coucou " + e.getActionCommand());

		switch (e.getActionCommand()) {
		case "Create sensor":
			System.out.println("coucou " + e.getActionCommand());
			mdl.addCapteur(iw.getSensorX(), iw.getSensorY());
			break;
		case "Connect":
			System.out.println("coucou " + e.getActionCommand());
			mdl.connect();
			break;
		case "Send":
			System.out.println("coucou " + e.getActionCommand());
			mdl.send();
			break;
		case "Run":
			System.out.println("coucou " + e.getActionCommand());
			mdl.run();
			break;

		}

	}
}
