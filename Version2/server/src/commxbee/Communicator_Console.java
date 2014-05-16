/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commxbee;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class Communicator_Console implements SerialPortEventListener {
	// for containing the ports that will be found
	private Enumeration ports = null;
	// map the port names to CommPortIdentifiers
	private HashMap portMap = new HashMap();
	// this is the object that contains the opened port
	private CommPortIdentifier selectedPortIdentifier = null;
	private SerialPort serialPort = null;
	// input and output streams for sending and receiving data
	private InputStream input = null;
	private OutputStream output = null;
	// just a boolean flag that i use for enabling
	// and disabling buttons depending on whether the program
	// is connected to a serial port or not
	private boolean bConnected = false;
	// the timeout value for connecting with the port
	final static int TIMEOUT = 2000;
	// some ascii values for for certain things
	final static int SPACE_ASCII = 32;
	final static int DASH_ASCII = 13;// 45
	final static int NEW_LINE_ASCII = 10;
	// varialbes rajoutées par moi
	public volatile boolean OK = false;
	public volatile int rssi;
	public int i = 0;
	public volatile boolean rssiAvailable = false;
	byte precedent;
	public boolean attRSSI = false;
	// a string for recording what goes on in the program
	String logText = "";
	TrameXBee[] bufferTrame;
	volatile int a;
	volatile int b;

	public Communicator_Console() {
		bufferTrame = new TrameXBee[10];
		int a = 0;
		int b = 0;
	}

	public boolean trameAvailable() {
		return (a != b);
	}

	public TrameXBee lireTrame() {
		TrameXBee result = bufferTrame[a];
		a = (a + 1) % 10;
		return (result);
	}

	// search for all the serial ports
	// pre: none
	// post: adds all the found ports to a combo box on the GUI
	public void searchForPorts() {
		int i = 0;
		System.out.println("\nPorts dispos :");

		ports = CommPortIdentifier.getPortIdentifiers();

		while (ports.hasMoreElements()) {
			CommPortIdentifier curPort = (CommPortIdentifier) ports
					.nextElement();

			// get only serial ports
			if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				i++;
				System.out.println(i + " => " + curPort.getName());
				portMap.put(i, curPort);
			}
		}
	}

	// connect to the selected port in the combo box
	// pre: ports are already found by using the searchForPorts method
	// post: the connected comm port is stored in commPort, otherwise,
	// an exception is generated
	public void connect() {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nChoix du port : ");
		selectedPortIdentifier = (CommPortIdentifier) portMap.get(sc.nextInt());

		CommPort commPort = null;

		try {
			// the method below returns an object of type CommPort
			commPort = selectedPortIdentifier
					.open("TigerControlPanel", TIMEOUT);
			// the CommPort object can be casted to a SerialPort object
			serialPort = (SerialPort) commPort;

			// for controlling GUI elements
			setConnected(true);

			// logging
			logText = selectedPortIdentifier.getName()
					+ " opened successfully.";
			System.out.println(logText);

			// CODE ON SETTING BAUD RATE ETC OMITTED
			// XBEE PAIR ASSUMED TO HAVE SAME SETTINGS ALREADY

			// enables the controls on the GUI if a successful connection is
			// made
			// window.keybindingController.toggleControls();
		} catch (PortInUseException e) {
			logText = selectedPortIdentifier.getName() + " is in use. ("
					+ e.toString() + ")";
			System.out.println(logText);
		} catch (Exception e) {
			logText = "Failed to open " + selectedPortIdentifier.getName()
					+ "(" + e.toString() + ")";
			System.out.println(logText);
		}
	}

	// open the input and output streams
	// pre: an open port
	// post: initialized intput and output streams for use to communicate data
	public boolean initIOStream() {
		// return value for whather opening the streams is successful or not
		boolean successful = false;

		try {
			//
			input = serialPort.getInputStream();
			output = serialPort.getOutputStream();

			successful = true;
			return successful;
		} catch (IOException e) {
			logText = "I/O Streams failed to open. (" + e.toString() + ")";
			System.out.println(logText);
			return successful;
		}
	}

	// starts the event listener that knows whenever data is available to be
	// read
	// pre: an open serial port
	// post: an event listener for the serial port that knows when data is
	// recieved
	public void initListener() {
		try {
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (TooManyListenersException e) {
			logText = "Too many listeners. (" + e.toString() + ")";
			System.out.println(logText);
		}
	}

	// disconnect the serial port
	// pre: an open serial port
	// post: clsoed serial port
	public void disconnect() {
		// close the serial port
		try {
			serialPort.removeEventListener();
			serialPort.close();
			input.close();
			output.close();
			setConnected(false);

			logText = "Disconnected.";
			System.out.println(logText);
		} catch (Exception e) {
			logText = "Failed to close " + serialPort.getName() + "("
					+ e.toString() + ")";
			System.out.println(logText);
		}
	}

	final public boolean getConnected() {
		return bConnected;
	}

	public void setConnected(boolean bConnected) {
		this.bConnected = bConnected;
	}

	// what happens when data is received
	// pre: serial event is triggered
	// post: processing on the data it reads
	@Override
	public void serialEvent(SerialPortEvent evt) {
		if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				int buffer1 = input.read();
				int buffer2 = input.read();
				if (attRSSI) {
					rssi = conv(buffer1) * 16 + conv(buffer2);
					input.read();
					rssiAvailable = true;
					attRSSI = false;
				} else if (buffer1 == 0x4F) {
					if (buffer2 == 0x4B) {
						OK = true;
						input.read();
					}
				} else {
					bufferTrame[b] = new TrameXBee((char) buffer1,
							(char) buffer2, (char) input.read(),
							(char) input.read(), (char) input.read());
					b = (b + 1) % 10;
				}
			} catch (Exception e) {
				logText = "Failed to read data. (" + e.toString() + ")";
				System.out.println(logText);
			}
		}
	}

	private char conv(int D) {
		switch (D) {
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		case '0':
			return 0;
		case 'A':
			return 10;
		case 'B':
			return 11;
		case 'C':
			return 12;
		case 'D':
			return 13;
		case 'E':
			return 14;
		case 'F':
			return 15;
		default:
			return 0;

		}
	}

	public boolean getRssiAvailable() {
		return this.rssiAvailable;
	}

	// method that can be called to send data
	// pre: open serial port
	// post: data sent to the other device

	public void writeData(String data) {
		char chaine[];
		chaine = data.toCharArray();
		try {
			for (int i = 0; i < data.length(); i++) {
				output.write(chaine[i]);
			}

			output.flush();
			// this is a delimiter for the data
		} catch (Exception e) {
			logText = "Failed to write data. (" + e.toString() + ")";
			System.out.println(logText);
		}

	}

	public void writeData(TrameXBee trame) {
		try {
			char[] data = trame.getTrame();
			for (int i = 0; i < data.length; i++) {
				output.write(data[i]);
			}
			output.flush();
			// this is a delimiter for the data
		} catch (Exception e) {
			logText = "Failed to write data. (" + e.toString() + ")";
			System.out.println(logText);
		}

	}
}
