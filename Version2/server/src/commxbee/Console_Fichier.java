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
    }
    
    public void run(){
        this.createObjects();
        machine.start();
        while(true);
    }       
    
    public static void main(String args[]) {
          Console_Fichier c = new Console_Fichier();
          c.start();
    }

	public Communicator_Console getCom() {
		return com;
	}

    
    
    
    
}
