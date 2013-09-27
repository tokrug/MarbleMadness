package runners;

import java.io.File;
import java.net.URL;
import java.util.Date;

import application.Application;

import math.BCircle;
import math.Circle;
import math.SRay;
import math.Vector4;
import math.Vector4D;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		URL url = Test.class.getResource("/models/exbut.txt");
//		System.out.println(url.getPath());
//		
//		System.out.println(System.getProperty("file.separator"));
//		
//		Test.class.getResource("/textures/");
//		
//		File file = new File (Test.class.getResource("/textures/").getFile());
//		
//		System.out.println(file.getPath());
		
		//System.out.println(System.getProperty("java.class.path"));
		
		// to samo w c++ zabiera 1.2 sekundy bez optymalizacji, a 1 s z -O3 -march=native
		
		Date start = new Date();
		
		for (int i = 0; i < 150000000; i++) {
			testttt lalal = new testttt();
			lalal.regenerate();
			
		}
		
		Date end = new Date();
		
		System.out.println((end.getTime() - start.getTime())/1000.0);

	}

}
