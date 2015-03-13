package generics;

public class GenericsTest {	
	
	public static void main(String[] args){
		
		GenericsTest gt = new GenericsTest();
		gt.run();
		
	}
	
	private void run(){
		
		Box<String> boxSTR = new Box<String>();
		Box<Integer> boxINT = new Box<Integer>();
		
		boxSTR.setContent("MK");
		boxINT.setContent(13);
		
		String s = boxSTR.getContent(); //no cast required
		Integer i = boxINT.getContent();
		
		System.out.println("String box contents: " + s);
		System.out.println("Integer box contents: " + i);
		
	}

}
