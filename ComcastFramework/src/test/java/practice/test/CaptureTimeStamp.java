package practice.test;

import java.util.Date;

public class CaptureTimeStamp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String fulltime= new Date().toString();
		String time=fulltime.replace(" ", "_").replace(":", "_"); //to replace the spaces and colon with underscore
		System.out.println(fulltime);
		System.out.println(time);
	}

}
