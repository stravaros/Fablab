package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.sun.org.apache.xpath.internal.FoundIndex;

import Capteur.Capteur;
import Controler.CtrlInitWindow;
import Model.Mdl;

public class InitWindow extends JFrame implements Observer { // fenetre
																// initialisation
	private static InitWindow initWindow;
	private static JPanel panel;
	private static JFrame frame;
	private static Mdl mdl;
	private static boolean alreadyInstanciated = false;
	private static JTextField jtfX;
	private static JTextField jtfY;
	private static JTextField jtfServer;
	private static JTextArea text;
	private static AbstractButton bRun;

	private InitWindow() {
		panel = new JPanel(new GridLayout(0, 2));
		frame = new JFrame("Detection Initialization");
		frame.setResizable(false);
		frame.setSize(new Dimension(400, 300));

		JPanel leftPanel = new JPanel(new GridLayout(3, 0));
		JPanel panelServer = new JPanel(new GridLayout(2, 0));
		Border borderServer = BorderFactory
				.createTitledBorder("Server configuration");
		panelServer.setBorder(borderServer);

		jtfServer = new JTextField("Server Adress");
		panelServer.add(jtfServer);
		jtfServer.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jtfServer.setText("");
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});

		AbstractButton startButton = new JButton("Connect");
		panelServer.add(startButton);
		startButton.addActionListener(new CtrlInitWindow(mdl, this));
		leftPanel.add(panelServer);

		JPanel panelConfig = new JPanel(new GridLayout(3, 0));
		Border borderConfig = BorderFactory
				.createTitledBorder("Sensors Configuration");
		panelConfig.setBorder(borderConfig);

		// POS X
		jtfX = new JTextField("X Position");
		panelConfig.add(jtfX);
		jtfX.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jtfX.setText("");
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});

		// POS Y
		jtfY = new JTextField("Y Position");
		panelConfig.add(jtfY);
		jtfY.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jtfY.setText("");
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		AbstractButton bCreate = new JButton("Create sensor");
		bCreate.addActionListener(new CtrlInitWindow(mdl, this));
		panelConfig.add(bCreate);
		AbstractButton bSend = new JButton("Send");
		bSend.addActionListener(new CtrlInitWindow(mdl, this));
		panelConfig.add(bSend);
		leftPanel.add(panelConfig);
		
		bRun = new JButton("Run");
		bRun.addActionListener(new CtrlInitWindow(mdl, this));
		leftPanel.add(bRun);
		bRun.setVisible(false);
		panel.add(leftPanel, BorderLayout.CENTER);

		// Panel de droite (texte)
		JPanel panelStatus = new JPanel(new GridLayout(0, 1));
		Border borderStatus = BorderFactory.createTitledBorder("Status");
		panelStatus.setBorder(borderStatus);
		text = new JTextArea();
		panelStatus.add(text);
		text.setEditable(false);
		text.append("initialization...");
		panel.add(panelStatus);

		frame.add(panel);
	}

	public static InitWindow getInstance(Mdl m) {
		if (!alreadyInstanciated) {
			alreadyInstanciated = true;
			mdl = m;
			initWindow = new InitWindow();
			mdl.addObserver(initWindow);
		}
		frame.setVisible(true);
		return initWindow;
	}

	public int getSensorX() {
		try {
			return Integer.parseInt(jtfX.getText());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this,
					"Invalid X value. \n Please retype.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return -10000;
	}

	public int getSensorY() {
		try {
			return Integer.parseInt(jtfY.getText());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this,
					"Invalid Y value. \n Please retype.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return -10000;
	}

	public JTextField getJtfX() {
		return jtfX;
	}

	public JTextField getJtfY() {
		return jtfY;
	}

	public static JTextField getJtfServer() {
		return jtfServer;
	}

	@Override
	public void update(Observable o, Object arg) {
		switch ((String) (arg)) {
		case "addCapteur":
			JOptionPane.showMessageDialog(this, "Sensor successfully added",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			text.append("\nSensor successfully added");
			break;
		case "pbAddCapteur":
			JOptionPane.showMessageDialog(this, "Sensor not added", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		case "pbConnection":
			JOptionPane
					.showMessageDialog(
							this,
							"Server connection impossible.\n Please check server adress validity",
							"Error", JOptionPane.ERROR_MESSAGE);
			break;
		case "send":
			JOptionPane.showMessageDialog(this, "Data sent to the server",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			text.append("\nData sent to the server");
			bRun.setVisible(true);
			

			break;
		case "notConnected":
			JOptionPane
					.showMessageDialog(
							this,
							"Client not connected to the server.\n Please type a valid server adress and press START",
							"Error", JOptionPane.ERROR_MESSAGE);
			break;
		case "nbCapteurServeur":
			JOptionPane.showMessageDialog(this, "Server connection successful",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			text.append("\nConnected to the server\nServer sensor number: "
					+ mdl.getNbCapteurServeur());
			break;
		case "alreadyConnected":
			JOptionPane.showMessageDialog(this,
					"Client already connected to the server", "Error",
					JOptionPane.ERROR_MESSAGE);
			break;
		case "sensorListSize":
			JOptionPane.showMessageDialog(this,
							"Sensor list has only " +mdl.getListCapteur().size() +" sensors.\nPlease enter at least 3 sensors and re-send.",
							"Error", JOptionPane.ERROR_MESSAGE);
			break;
		case "run":
			JOptionPane.showMessageDialog(this, "Localization started",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			text.append("\nLocalization started");
			bRun.setText("Stop");
			bRun.setName("Stop");
			break;
		case "stop":
			JOptionPane.showMessageDialog(this, "Localization stopped",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			text.append("\nLocalization stopped");
			bRun.setName("Run");
			bRun.setVisible(false);
			break;

		}
	}
}