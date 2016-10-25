package com.yofungate.mouse;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;


public class SubMain extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel label;
	private JButton start;
	
	public SubMain() {
		super("YFG Painting Tool");  
		setLayout(new FlowLayout());
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		start = new JButton();
		
		label = new JLabel("Welcome To The YFG Painting Tool.");
		start.setText("Start "+"\""+"Painting"+"\"");
		
		panel.add(label,BorderLayout.NORTH);
		panel.add(start,BorderLayout.SOUTH);
		add(panel);
		EventHandler h = new EventHandler();
		start.addActionListener(h);
	}
	
	private class EventHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==start){
				startup.Iframe.setVisible(true);
				startup.Iframe.setBounds(startup.Iframe.getBounds());
				startup.Mframe.setVisible(false);
			}
		}
	}
}
