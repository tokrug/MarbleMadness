package runners;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;

public class JOGLTest {

	public static void main(String[] args)
	{
	    Frame frame = new Frame("Hello World");
	    GLCanvas canvas = new GLCanvas(new GLCapabilities());
	    frame.add(canvas);
	    frame.setSize(300, 300);
	    frame.setBackground(Color.WHITE);

	    frame.addWindowListener(new WindowAdapter()
	    {
	        @Override
			public void windowClosing(WindowEvent e)
	        {
	            System.exit(0);
	        }
	    });
	    frame.setVisible(true);
	}
	
}
