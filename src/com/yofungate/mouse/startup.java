package com.yofungate.mouse;
import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

public class startup{
	public static MouseLocate Pframe = new MouseLocate();
	public static Instructions Iframe = new Instructions();
	public static SubMain Mframe = new SubMain();
	
	public static void main(String[] args) throws AWTException{
        Pframe.setVisible(false);
        Pframe.setSize(300, 270);
        Pframe.setFocusable(true);
        Pframe.setMinimumSize(new Dimension(300, 60));
        Pframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Iframe.setVisible(false);
        Iframe.setSize(340, 140);
        Iframe.setResizable(false);
        Iframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Mframe.setVisible(true);
        Mframe.setSize(275, 140);
        Mframe.setResizable(false);
        Mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
