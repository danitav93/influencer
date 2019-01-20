package Utility;

public class Method {

	public static int parseInt(String string) {
		if (string.equals("")||string.equals(" ")||string==null) {
			return 0;
		}

		if (!string.contains(".")) {
			return Integer.parseInt(string);
		}
		String[] pointSplitted=string.split("\\.");
		int sum=0;
		int j=1;
		for (int i=1;i<=pointSplitted.length;i++) {
			sum=sum+Integer.parseInt(pointSplitted[pointSplitted.length-i])*j;
			j=j*1000;
		}
		return sum;
	}


	public static int parseRankPost(String string) {

		String[] pointSplitted=string.split(" ");
		return parseInt(pointSplitted[1]);
	}
	
}
