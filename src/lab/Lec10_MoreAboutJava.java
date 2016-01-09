package lab;

import java.util.*;


public class Lec10_MoreAboutJava {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test2();
	}

	
	
	/*
	 * Generics
	 * 
	 * provide a way to re-use the same code with different input types
	 * similar to formal parameters used in method declarations, the difference is that 
	 * the inputs to formal parameters are values, while the inputs to type parameters are types
	 * 
	 * Why do we need Generics ?
	 * (1) Elimination of casts, elimination of errors.
	 *   
	 */
	public static void test1() {
		// before generic is available
		List list = new ArrayList();
		list.add("hello");
		list.add("world");
		// cast is needed here because it is a list of Objects
		String s = (String)list.get(0);
		System.out.println(s);
	
		// after generic is available, after java5
		List<String> list2 = new ArrayList<String>();
		list2.add("hello");
		list2.add("world");
		String s2 = list2.get(0);
		// we don't need to do cast because we know it is a list of String
		System.out.println("------------------------------------");
		// for each and iterator
		for(Object o: list) {
			String cur = (String)o;
			System.out.println(cur);
		}
		
		for(String cur2: list2) {
			System.out.println(cur2);
		}
		
		for(Iterator<String> iter = list2.iterator(); iter.hasNext();) {
			String cur3 = iter.next();
			System.out.println(cur3);
		}
		System.out.println("------------------------------------");
		
		// Strong type check at compile time
		/*
		 * java object is determined at run-time. If not using generics, the checking will be delayed at run-time
		 * 
		 * A java compiler applies strong type checking to generic code and 
		 * issues errors if the code violates type safety.
		 * 
		 * compile time error is always better than run time error
		 */
		
		/*
		 * Re-use the same code for generic types
		 */
	
	}
	
	public static void test2() {
		/*
		 * Inheritance and SubTypes
		 */
		String s = "abc";
		Object o = s;
		Box<String> box = new Box<String>("abc");
		// box.set(1);  compile error, integer is NOT string
		
		
		// this is legal, because box if a box of Objects
		Box<Object> box2 = new Box<Object>("abc");
		box2.set(1);
		box2.set(new Box<String>("abc"));
		
		
		// Legal ? Is a box of String also a box of Object ? 
		Box<String> strBox = new Box<String>("abc");
		// Box<Object> objBox = strBox; // compile error here
		String str = strBox.get();
		
		/*
		 * Give two concrete type A and B (for example, Object and String), 
		 * MyClass<A> has no relationship to MyClass<B>, regardless of whether or not A and B are related. 
		 * The common parent of MyClass<A> and MyClass<B> is Object
		 */
		
	}
}


class Box <T> {
	T t;
	public Box(T t) {
		this.t = t;
	}
	public T get() {
		return t;
	}
	
	public void set(T t) {
		this.t = t;
	}
 }