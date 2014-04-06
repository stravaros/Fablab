package App;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindowAdapter extends WindowAdapter {
	
	public MyWindowAdapter(){
		}
	
	@Override
	public void windowClosing(WindowEvent e){
		new Thread(new Runnable() {
			public void run(){
				System.exit(0);
			}
		}).start();
	}
	
}
