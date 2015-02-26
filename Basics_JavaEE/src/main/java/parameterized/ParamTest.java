package parameterized;

public class ParamTest {

	public static void main(String[] args) {

		Holder<String> strHolder = new Holder<String>();
		strHolder.setContents("Hello world!");
		
		Holder<Integer> intHolder = new Holder<Integer>();
		intHolder.setContents(111);
		
		System.out.println("strHolder contents: " + strHolder.getContents());
		System.out.println("intHolder contents: " + intHolder.getContents());
		

	}

}
