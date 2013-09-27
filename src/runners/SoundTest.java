package runners;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;

public class SoundTest {

	public static void main(String[] args){
		
		Player pl = null;
		try {
			pl = Manager.createRealizedPlayer(new URL("file:///D:\\Muzyka\\05.Pray!.mp3"));
			pl.getGainControl().setLevel(0.0f);
		} catch (NoPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotRealizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float x = 1.0f;
		pl.start();
		pl.getGainControl().setLevel(1.0f);
		
		
		
		while (true) {
//			x-=0.0000002f;
//			pl.getGainControl().setLevel(x);
		}
		
	}
	
}
