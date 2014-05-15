/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commxbee;

/**
 *
 * @author thibeaua
 */
public class Console_Fichier extends Thread{
    private Communicator_Console com = null;
    private ReceptionXBee machine = null;
    
    
    private void createObjects()
    {
        
        machine = new ReceptionXBee();
        //com = new Communicator_Console();
        //machine = new ReceptionXBee(this, communicator);
        //keybindingController = new KeybindingController(this);
    }
    
    public void run(){
        this.createObjects();
        
        /*com.searchForPorts();
        com.connect();
        if (com.getConnected() == true)
        {
            if (com.initIOStream() == true)
            {
                com.initListener();
            }
        }*/
        
        machine.start();
        while(true);
    }       
    
    public static void main(String args[]) {
          Console_Fichier c = new Console_Fichier();
          c.start();
    }
    
    
    
    
    
}
