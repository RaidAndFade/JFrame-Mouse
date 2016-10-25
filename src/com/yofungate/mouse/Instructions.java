package com.yofungate.mouse;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Instructions extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel,extpanel;
	private JLabel text1,text2,text3,text4;
	private JButton back;
	
	public Instructions() {
		super("YFG Painting Tool Instructions");  
		setLayout(new FlowLayout());
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		extpanel = new JPanel();
		extpanel.setLayout(new BorderLayout());
		back = new JButton();
		text1 = new JLabel("Simply Click any mouse button to draw");
		text2 = new JLabel("Mouse Scroll up and down to change colors");
		text3 = new JLabel("Up/Down Changes brush size.hold shift=20, ctrl=1, n/a=5");
		text4 = new JLabel("Shift Space resets canvas. Space resets brush size");
		back.setText("Ok.");

		extpanel.add(text3,BorderLayout.NORTH);
		extpanel.add(text4,BorderLayout.CENTER);
		extpanel.add(back,BorderLayout.SOUTH);
		
		panel.add(text1,BorderLayout.NORTH);
		panel.add(text2,BorderLayout.CENTER);
		panel.add(extpanel,BorderLayout.SOUTH);
		add(panel);
		EventHandler h = new EventHandler();
		back.addActionListener(h);
	}	
	private class EventHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==back){
				startup.Pframe.setVisible(true);
				startup.Pframe.clear=true;
				dispose();
			}
		}
	}
}