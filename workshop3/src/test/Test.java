package test;

public class Test {

	public static void main(String[] atgs) {
		String id = "1";
		testSubstring(id);
	}
	
	public static void testSubstring(String id) {
		System.out.println("00000".substring(id.length()) + id);
	}
	
	public static void testFor(String id) {
		String pad = "";
		for (int i = 0; i < 5 - id.length(); i++) {
			pad += "0";
		}
		pad += id;
		System.out.println(pad);
	}
	
	

}
