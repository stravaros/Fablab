package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu.Separator;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class FrameMenu extends JPanel{
	
	public FrameMenu () {
		super(new GridBagLayout());
		JButton jb = new JButton ("Button1");
		jb.setSize(150, 50);
        jb.setMaximumSize(jb.getSize());
        jb.setPreferredSize(new Dimension(150,50));
        this.add(jb);
        
        JSeparator sp = new JSeparator(SwingConstants.HORIZONTAL);
        
        this.add(sp);
        
		
		JButton jbb = new JButton ("Button2");
		jbb.setSize(150, 50);
        jbb.setMaximumSize(jbb.getSize());
        jbb.setPreferredSize(new Dimension(150,50));
		this.add(jbb);
	}

}
