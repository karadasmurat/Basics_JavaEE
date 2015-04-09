package basic;

public class EqualityTest {
	
	public static void main(String[] args){
		
		System.out.println("Equality test:");
		
		Item item01 = new Item();
		Item item02 = new Item("same text");
		Item item03 = new Item();
		Item item04 = new Item("same text");
		Item item05 = new Item("other text");
		
		System.out.println("Checking: " + item01.toString() +" vs itself " + item01.toString() );
		System.out.println(item01.equals(item01)+"\n");
		
		System.out.println("Checking: " + item01.toString() +" vs " + item02.toString() );
		System.out.println(item01.equals(item02)+"\n");
		
		System.out.println("Checking: " + item01.toString() +" vs " + item03.toString() );
		System.out.println(item01.equals(item03)+"\n");
		
		System.out.println("Checking: " + item01.toString() +" vs " + item04.toString() );
		System.out.println(item01.equals(item04)+"\n");
		
		System.out.println("Checking: " + item01.toString() +" vs " + item05.toString() );
		System.out.println(item01.equals(item05)+"\n");
		
		
		System.out.println("Checking: " + item02.toString() +" vs itself " + item02.toString() );
		System.out.println(item02.equals(item02)+"\n");
		
		System.out.println("Checking: " + item02.toString() +" vs " + item01.toString() );
		System.out.println(item02.equals(item01)+"\n");
		
		System.out.println("Checking: " + item02.toString() +" vs " + item03.toString() );
		System.out.println(item02.equals(item03)+"\n");
		
		System.out.println("Checking: " + item02.toString() +" vs " + item04.toString() );
		System.out.println(item02.equals(item04)+"\n");
		
		System.out.println("Checking: " + item02.toString() +" vs " + item05.toString() );
		System.out.println(item02.equals(item05)+"\n");
		
		System.out.println("Checking: " + item05.toString() +" vs " + item02.toString() );
		System.out.println(item05.equals(item02)+"\n");
			
	}

}
