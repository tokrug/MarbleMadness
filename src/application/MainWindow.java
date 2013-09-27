package application;

import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;
import java.io.IOException;

import input.controllers.ControllersManager;
import javax.media.opengl.GL;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;

public class MainWindow {
	
	JFrame _frame;
	GLCanvas _canvas;
	
	public MainWindow() {
		_frame = new JFrame("MarbleMadnessClone");
		_frame.setSize(200, 200);
		_frame.setUndecorated(true);
		GraphicsDevice graphicDev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		//if (graphicDev.isFullScreenSupported()) {
		//	graphicDev.setFullScreenWindow(_frame);
		//}
		graphicDev.setFullScreenWindow(_frame);
		_canvas = new GLCanvas(new GLCapabilities());
		_canvas.setSize(200, 200);
		_frame.add(_canvas);
		// komenda do shella (w linuxie, wylaczenie key-repeat)
		// do ominiecia w windowsie ustawiac locki na akcje
		if (System.getProperty("os.name").toLowerCase().contains("linux")) {
			Runtime mycommand=java.lang.Runtime.getRuntime();
			try {
				mycommand.exec("xset -r");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		_canvas.requestFocus();
		//autorepeat on
		_frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent we) {
				if (System.getProperty("os.name").toLowerCase().contains("linux")) {
					Runtime mycommand=java.lang.Runtime.getRuntime();
					try {
						mycommand.exec("xset r");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
			
		});
		
		hideCursor();
		
	}

	public void setFocusListener(ControllersManager eventmgr) {
		_canvas.addFocusListener(eventmgr);
		_frame.addFocusListener(eventmgr);
	}
	
	public void setEventListener(ControllersManager eventmgr) {
		_frame.addKeyListener(eventmgr);
		_canvas.addKeyListener(eventmgr);
		_canvas.addMouseListener(eventmgr);
		_canvas.addMouseMotionListener(eventmgr);
		_canvas.addMouseWheelListener(eventmgr);
	}
	
	public void removeEventListener(ControllersManager eventmgr) {
		_frame.removeKeyListener(eventmgr);
		_canvas.removeKeyListener(eventmgr);
		_canvas.removeMouseListener(eventmgr);
		_canvas.removeMouseMotionListener(eventmgr);
		_canvas.removeMouseWheelListener(eventmgr);
	}
	
	public void showWindow() {
		_frame.setVisible(true);
	}
	
	public GLCanvas getCanvas() {
		return _canvas;
	}
	
	public int getXposition() {
		return _frame.getX();
	}
	
	public int getYposition() {
		return _frame.getY();
	}
	
	public int getWidth() {
		return _frame.getWidth();
	}
	
	public int getHeight() {
		return _frame.getHeight();
	}
	
	public GL getGL() {
		return _canvas.getGL();
	}
	
	public void displayFrame() {
		_canvas.display();
	}
	
	public void addRenderer(Renderer renderEngine) {
		_canvas.addGLEventListener(renderEngine);
	}
	
	public void hideCursor() {
		int[] pixels = new int[16 * 16];
		Image image = Toolkit.getDefaultToolkit().createImage(
		        new MemoryImageSource(16, 16, pixels, 0, 16));
		Cursor transparentCursor =
		        Toolkit.getDefaultToolkit().createCustomCursor
		             (image, new Point(0, 0), "invisibleCursor");
		_canvas.setCursor(transparentCursor);
	}

}
