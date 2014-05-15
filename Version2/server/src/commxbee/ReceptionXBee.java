/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commxbee;

import static commxbee.Communicator_Console.DASH_ASCII;

/**
 *
 * @author thibeaua
 */
public class ReceptionXBee extends Thread{
    
    //private GUI windows = null;
    private Communicator_Console com = null;
    private volatile int rssi[] = new int[3];
    private char addr[] = new char[3];
    private char objet = 3;
    private int nbreCapteur = 3;
    
    public ReceptionXBee(){
       this.com = new Communicator_Console();
       addr[0]=0;
       addr[1]=1;
       addr[2]=2;
       
       com.searchForPorts();
        com.connect();
        if (com.getConnected() == true)
        {
            if (com.initIOStream() == true)
            {
                com.initListener();
            }
        }
    }
    
    public int getRSSI(int c){
        return(rssi[c]);
    }
    
    @Override
    public void run(){
        int capteurCourant = 0;
        long start;
        while(true){
            if(capteurCourant!=0){
                com.writeData(new TrameXBee(addr[capteurCourant],(char)0,(char)71,(char)0,(char)0));
                while(!com.trameAvailable());
                //System.out.println("rssiAvailable");
                char newRSSI = com.lireTrame().getM2();
                if(newRSSI != 0)
                    rssi[capteurCourant]=newRSSI;
                //System.out.println(rssi[capteurCourant]);
                capteurCourant=(capteurCourant+1)%nbreCapteur;
                com.rssiAvailable = false;
                
                
            }else{
                //debug : affichage tableau rssi
                System.out.println("capteur0 : " + (int)rssi[0] + "\ncapteur1 : "+(int) rssi[1]+"\ncapteur2 : "+(int) rssi[2]);
                com.writeData(new TrameXBee(objet,(char)0,(char)65,(char)0,(char)0));
                //System.out.println("trame envoyé");
                while(!com.trameAvailable());
                //System.out.println("trame reçu");
                start=System.nanoTime(); 
                while((System.nanoTime()-start)<1000000000); 
                //System.out.println("+++");
                com.writeData("+++");
                while(!com.OK);
                com.OK = false;
                com.attRSSI = true;
                com.writeData("ATDB");
                com.writeData(Character.toString((char)DASH_ASCII));
                while(!com.getRssiAvailable());
                //System.out.println("rssiAvailable");
                com.writeData("ATCN");
                com.writeData(Character.toString((char)DASH_ASCII));
                while(!com.OK);
                com.OK = false;
                rssi[capteurCourant]=com.rssi;
                //System.out.println(com.rssi);
                //System.out.println(rssi[capteurCourant]);
                start=System.nanoTime(); 
                while((System.nanoTime()-start)<1000000000); 
                capteurCourant=(capteurCourant+1)%nbreCapteur;
                com.rssiAvailable = false;
                
                //com.writeData(trame.toString());
                
            }
            
            }
    }
    
    
}

