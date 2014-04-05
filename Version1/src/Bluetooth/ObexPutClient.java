package Bluetooth;


import java.io.IOException;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.obex.*;

import com.intel.bluetooth.RemoteDeviceHelper;

public class ObexPutClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        String serverURL;
        if ((args != null) && (args.length > 0)) {
            serverURL = args[0];
        } else {
            String[] searchArgs = null;
            // Connect to OBEXPutServer from examples
            // searchArgs = new String[] { "11111111111111111111111111111123" };
            ServicesSearch.main(searchArgs);
            if (ServicesSearch.serviceFound.size() == 0) {
                System.out.println("OBEX service not found");
                return;
            }
            // Select the first service found
            serverURL = (String)ServicesSearch.serviceFound.elementAt(0);
            //serverURL="btgoep://406F2A45FE3E:3;authenticate=false;encrypt=false;master=false";
        }

        System.out.println("Connecting to " + serverURL);

        /*ClientSession clientSession = (ClientSession) Connector.open(serverURL);
        HeaderSet hsConnectReply = clientSession.connect(null);
        if (hsConnectReply.getResponseCode() != ResponseCodes.OBEX_HTTP_OK) {
            System.out.println("Failed to connect");
            return;
        }*/
       while (true) {
        	try {
        	System.out.println("force "+RemoteDeviceHelper.readRSSI(RemoteDeviceDiscovery.devicesDiscovered.firstElement()));
        	}catch(IOException e){
        		continue;
        	}
        }

       /* HeaderSet hsOperation = clientSession.createHeaderSet();
        hsOperation.setHeader(HeaderSet.NAME, "Hello.txt");
        hsOperation.setHeader(HeaderSet.TYPE, "text");

        //Create PUT Operation
        Operation putOperation = clientSession.put(hsOperation);

        // Send some text to server
        byte data[] = "Debranche ton bluetooth connard!!!!".getBytes("iso-8859-1");
        OutputStream os = putOperation.openOutputStream();
        os.write(data);
        os.close();

        putOperation.close();*/

        /*clientSession.disconnect(null);

        clientSession.close();*/
    }
}