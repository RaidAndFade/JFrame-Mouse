package com.yofungate.mouse;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MouseLocate extends JFrame implements ActionListener, MouseListener, MouseMotionListener, MouseWheelListener, KeyListener, ComponentListener {
	int t, c, r, s=20, f=0, stype=0;
	Point start,end;
	String out="RED";
	Object[] colorList = {"RED","ORANGE","YELLOW","GREEN","BLUE","PINK","ERASER","CUSTOM"};
	ArrayList<Object> ColorList = new ArrayList<Object>(Arrays.asList(colorList));
	boolean clear;
	Color customcolor = new Color(0, 0, 0);
	long nextSecond=System.currentTimeMillis()+1000;
	int framesInCurrentSecond, frameInLastSecond;
	JPanel colorContainer;
	private JSlider redSlider, greenSlider, blueSlider;
	public static BasicStroke stroke1,stroke2;
	Color[] colors = {Color.RED,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.BLUE,Color.PINK,Color.WHITE,Color.black};
	private boolean nc;
	private boolean mrotc;
	private static final long serialVersionUID = 1L;
    public MouseLocate () {
    	ChangeHandler event = new ChangeHandler();
    	redSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
    	redSlider.addChangeListener(event);
    	redSlider.setMaximum(255);
    	redSlider.setPaintLabels(true);
    	redSlider.setPaintTicks(true);
    	redSlider.setMajorTickSpacing(25);
    	redSlider.setMinorTickSpacing(5);
    	redSlider.setPaintTrack(false);
    	
    	redSlider.setLocation(91,1);
    	redSlider.setSize(new Dimension(59,19));
    	redSlider.setMaximumSize(new Dimension(59,19));
    	redSlider.setMinimumSize(new Dimension(59,19));
    	redSlider.setFocusable(false);

    	greenSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
    	greenSlider.addChangeListener(event);
    	greenSlider.setMaximum(255);
    	greenSlider.setPaintLabels(true);
    	greenSlider.setPaintTicks(true);
    	greenSlider.setMajorTickSpacing(25);
    	greenSlider.setMinorTickSpacing(5);
    	greenSlider.setPaintTrack(false);
    	
    	greenSlider.setLocation(151,1);
    	greenSlider.setSize(new Dimension(59,19));
    	greenSlider.setMaximumSize(new Dimension(59,19));
    	greenSlider.setMinimumSize(new Dimension(59,19));
    	greenSlider.setFocusable(false);
    	
    	blueSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
    	blueSlider.addChangeListener(event);
    	blueSlider.setMaximum(255);
    	blueSlider.setPaintLabels(true);
    	blueSlider.setPaintTicks(true);
    	blueSlider.setMajorTickSpacing(25);
    	blueSlider.setMinorTickSpacing(5);
    	blueSlider.setPaintTrack(false);
    	
    	blueSlider.setLocation(211,1);
    	blueSlider.setSize(new Dimension(59,19));
    	blueSlider.setMaximumSize(new Dimension(59,19));
    	blueSlider.setMinimumSize(new Dimension(59,19));
    	blueSlider.setFocusable(false);
    	
        
    	//setUndecorated(true);
    	colorContainer = new JPanel();
        colorContainer.setLayout(null);
	    colorContainer.add(redSlider);
	    colorContainer.add(blueSlider);
	    colorContainer.add(greenSlider);
	    colorContainer.setFocusable(false);
	    add(colorContainer);
    	setVisible(true);
        setSize(275, 270);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(this);
    	addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addComponentListener(this);
    }
    public void update(Graphics g) {
    	Graphics offgc;
    	Image offscreen = null;
    	Dimension d = size();

    	// create the offscreen buffer and associated Graphics
    	offscreen = createImage(d.width, d.height);
    	offgc = offscreen.getGraphics();
    	// clear the exposed area
    	offgc.setColor(getBackground());
    	offgc.fillRect(0, 0, d.width, d.height);
    	offgc.setColor(getForeground());
    	// do normal redraw
    	paint(offgc);
    	// transfer offscreen to window
    	g.drawImage(offscreen, 0, 0, this);
        }
    
    @Override
    public void paint(Graphics g) {
    	g.create();
    	Clear();
    	FPS();
    	ColorSelect();
        BrushSize();
    	drawCircle();
    	RepaintComponents();
    	g.dispose();
    }

    private void RepaintComponents() {
    	Graphics2D g =(Graphics2D) colorContainer.getGraphics();
        g.setColor(Color.white);
		g.drawRect(90,0, 181,20);
        g.setColor(Color.black);
    	g.drawRect(90,0, 181,20);
    	g.drawLine(150,0, 150,20);
    	g.drawLine(210,0, 210,20);
    	redSlider.repaint();
    	greenSlider.repaint();
    	blueSlider.repaint();
	}
	public void actionPerformed(ActionEvent ae) {

    }
    public void FPS(){
    	Graphics2D g =(Graphics2D) colorContainer.getGraphics();
    	long currentTime = System.currentTimeMillis();
		if (currentTime > nextSecond) {
            nextSecond += 1000;
             frameInLastSecond = framesInCurrentSecond;
            framesInCurrentSecond = 0;
        }
        framesInCurrentSecond++;
        g.setColor(Color.white);
		g.fillRect(50, 0, 40, 20);
        g.setColor(Color.black);
    	g.drawRect(50, 0, 40, 20);
        g.drawString(frameInLastSecond + " fps", 52,15);
    }
    public void BrushSize(){
    	Graphics2D g = (Graphics2D) colorContainer.getGraphics();

    	g.setColor(Color.white);
    	g.fillRect(30, 0, 20, 20);
    	g.setColor(Color.black);
    	g.drawRect(30, 0, 20, 20);
    	g.drawString(Integer.toString(s), 32, 15);
    }
    public void ColorSelect(){
    	Graphics2D g = (Graphics2D) colorContainer.getGraphics();
    	if(mrotc){
    	if(c>colors.length-1){c=0;}
    	if(c<0){c=colors.length-1;}
    	redSlider.setValue(colors[c].getRed());
    	greenSlider.setValue(colors[c].getGreen());
    	blueSlider.setValue(colors[c].getBlue());
    	mrotc=false;
    	}
		
		System.out.println(c);
		g.setColor(Color.white);
		g.fillRect(0, 0, 40, 20);
		g.setColor(Color.black);
		g.drawRect(0, 0, 40, 20);
		g.setColor(customcolor);
		g.fillRect(1, 1, 38, 19);
    	
    }
    public void drawCircle() {
        Graphics2D g = (Graphics2D)this.getGraphics();
        if(r==1){
        stroke2 = new BasicStroke(s,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        stroke1 = new BasicStroke(s,BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);
        g.setColor(customcolor);
        switch (stype){
        case 0 : g.setStroke(stroke2);break;
        case 1 : g.setStroke(stroke1);break;
        }
        g.drawLine(start.x, start.y, end.x, end.y);
        }
    }
    public void Clear(){
    	
    	if(clear){
    		clear=false;
    		Graphics2D g = (Graphics2D)this.getGraphics();
    		g.setColor(Color.white);
    		g.fillRect(0,0,10000,10000);
    	}
    }
    public void mouseClicked(MouseEvent e) {
    	end = start;
		start = e.getPoint();
		r=1;
   	 	repaint();
    }
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {
    	r=0;
    	start = e.getPoint();
    	repaint();
    }
    public void mouseDragged(MouseEvent e) {
   		 end = start;
   		 start = e.getPoint();
   		 r=1;
   	     repaint();
    }
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int rot = e.getWheelRotation();
		mrotc = true;
		c = c+rot;
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println(arg0.getKeyCode());
		//brush size
		if(arg0.isShiftDown()){
			   if(arg0.getKeyCode()==38){s=s+20;}
		       if(arg0.getKeyCode()==40&&s-20>=0){s=s-20;}
		}else if(arg0.isControlDown()){
			   if(arg0.getKeyCode()==38){s=s+1;}
			   if(arg0.getKeyCode()==40&&s-1>=0){s=s-1;}
		}else{ if(arg0.getKeyCode()==38){s=s+5;}
			   if(arg0.getKeyCode()==40&&s-5>0){s=s-5;}}
		//clearing
		if(arg0.isShiftDown()){
			if(arg0.getKeyCode()==32){clear=true;repaint();}
		}else if(arg0.isControlDown()){
			if(arg0.getKeyCode()==32){clear=true;repaint();}
		}else{
			if(arg0.getKeyCode()==32){s=20;repaint();}}
		//repaint it all
		if(arg0.getKeyCode()==38||arg0.getKeyCode()==40){
			repaint();
		}
		if(arg0.getKeyCode()==83){
			try {
	            
	            Robot robot = new Robot();
	            // Capture the screen shot of the area of the screen defined by the rectangle
	            BufferedImage bi=robot.createScreenCapture(new Rectangle(7,29,getWidth()-14,getHeight()-36));
	            ImageIO.write(bi, "jpg", new File("../imageTest.jpg"));
	            
	        } catch (AWTException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		if(arg0.getKeyCode()==84){
			switch (stype){
			case 0 : stype=1;break;
			case 1 : stype=0;break;
			}
		}
		}
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	@Override
	public void componentHidden(ComponentEvent e) {}
	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void componentResized(ComponentEvent e) {
		clear=true;
		repaint();
	}
	@Override
	public void componentShown(ComponentEvent e) {}        
	public class ChangeHandler implements ChangeListener {

		

		@Override
		public void stateChanged(ChangeEvent arg0) {
			int r = redSlider.getValue();
		    int g = greenSlider.getValue();
		    int b = blueSlider.getValue();
		    customcolor = new Color(r, g, b);
		    repaint();
		}
	}
}