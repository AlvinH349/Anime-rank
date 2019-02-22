import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.HashMap;

public class outermk {
	
	private static ArrayList<LinkedList<Pair>> theTable;
	
	
	static class Pair{
		
		private String str;
		private Integer value;
		
	//Construct pair
	Pair(String str, Integer value){
		this.str=str;
		this.value=value;
	}
	
	
	
	//Returns key
	String getKey() {
		return str;
	}
	
	
	
	// Returns  value
	Integer getValue() {
		return value;
	}
	
	
	
	//Change value to new value and returns  old value.
	Integer setValue(Integer value) {
		Integer temp= value;
		this.value=value;
		return temp;
	}
	
	
	}// close pair
	
	
	

	//constructor
	outermk(int nums){
		theTable=new ArrayList<LinkedList<Pair>>();
		
		for (int i = 0; i < nums; i++) {
			theTable.add(new LinkedList<Pair>());
		}
		
	}
	
	
	
	
	
	
	//  find bucket in hash table with String argument that contains key
	private static int whichBucket(String key) {
		// hash funct find bucket in hash table
		return Math.abs(key.hashCode() % theTable.size()); 
	}
	
	
	
	
	
	
	
	/*
	 insert this key-value pair into map
If key already exists in map, change its value to "value"
If key is not there, insert this key-value pair into map.
	 */
	public void put(String key, Integer value) {
		
		Pair p=null;
		
			
			// find where key is
			int nb = whichBucket(key); 
			int got_it=0;
			
			
			
			// go through linked list
			for (int i = 0; i < theTable.get(nb).size(); i++) { 
				
				//create p and have it contain a number bucket 
				 p = theTable.get(nb).get(i);
				 
					
				 // if found, update value
				//If key already exists in the map, change its value to "value"
				if (  key.equals(p.getKey())  ) {
					System.out.println("before change value: "+p.getKey()+", "+p.getValue() );
					p.setValue(value); 
					System.out.println("After change value: "+p.getKey()+", "+p.getValue() );
					got_it++;
				}//close if
				
				
			}//close for loop linklist
			
			
			
			
			// if not found, add it
			if (got_it==0) {
				theTable.get(nb).add(new Pair(key, value)); 
			System.out.println("Pair added: "+key+", "+value );
			}
			
			
		
		
		
		
	}//close put
	
	
	
	
	
	
	//returns true iff key already exists in the map.
	boolean containsKey(String key) {
		
//have key of whichbucket to equal nb (substitute)		
int nb = whichBucket(key);
		
//go through linklist
		for (int i = 0; i < theTable.get(nb).size(); i++) { 
			
			//create p and have it contain number bucket 
			Pair p = theTable.get(nb).get(i); 
			
			// if key found return true
			if (p.getKey().equals(key)) 
				return true;
			
		}
		
		
		return false;
	}//close containsKey
	
	
	
	
	
	
	
	
	/*
	 returns true iff value appears in the map. 
	
	 */
	boolean containsValue(Integer value) {
		
		//nested for loop
		//go through arraylist
		for (int i = 0; i < theTable.size(); i++) { 
			
			//go thorugh linklist
			for (int j = 0; j < theTable.get(i).size(); j++) { 
				
				//create a Pair to input the current one
				Pair p = theTable.get(i).get(j); 
				
				//compare current one with value
				if (p.getValue().equals(value) ) {
					return true;
				}
				
			}//inner for
			
		}//outer for
		
		//if not found return false
		return false;
	}// close containsValue
	
	
	
	
	
	
	
	
	//If key is in the map, remove it. 
	boolean remove (Object key){
		
		
		if(key instanceof String) {
			
			String key1= (String)key;
			
			//contains requested key through whichBucket
			int nb = whichBucket(key1);
			
			
			
			// go through linklist within arraylist
			for (int i = 0; i < theTable.get(nb).size(); i++) { 
				
				//Create a Pair list that contains requested key through nb
				LinkedList<Pair> curr = theTable.get(nb); 
				
				// checking if key in hashmap equals key 
				if (key1.equals(theTable.get(nb).get(i).getKey())) { 
					curr.remove(i); 
					return true;
				}//close inner if
				
				
			}//close for loop linklist
			
		
		// if nothing is found, return false
		return false;
		
		
		
	}// close if instanceof
	
	//if it is not instanceof, return false
	return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	//If key is in map, return associated value. If key is NOT in map, return null.
	//If key is in map, remove it. 
	Integer get(String key) {
		
		
			
			//contains requested key through whichBucket
			int nb = whichBucket(key);
			
			
			
			// go through  linklist inside arraylist
			for (int i = 0; i < theTable.get(nb).size(); i++) { 
				
				// checking if key in hashmap equals requested key 
				if (  key.equals( theTable.get(nb).get(i).getKey() )  ) 
					return theTable.get(nb).get(i).getValue();
				
			}//close for
			
		
		
		
		
		// if nothing is found, return false
		return null;
	}
	
	
	
	

	
	
	//Returns Set of all pairs in map.
	Set<Pair> entrySet(){
		
		//create something to add Set of all pairs in map.
		Set<Pair> set_of_pairs= new HashSet<>();
		
		
		for (int i = 0; i < theTable.size(); i++) { 
			
			for (int j = 0; j < theTable.get(i).size(); j++) 
				set_of_pairs.add(theTable.get(i).get(j)); 
			
		}
		
		
		return set_of_pairs;
	}
	
	
	
	
	
	//Returns list of all of associated values in map.
	List<Integer> values(){
List<Integer> set_of_values = new ArrayList<>();
		
//go through arraylist
		for (int i = 0; i < theTable.size(); i++) { 
			
			//go through linklist to find it
			for (int j = 0; j < theTable.get(i).size(); j++) 
				 set_of_values.add(theTable.get(i).get(j).getValue()); 
			
			
		}
		return  set_of_values;
	}
	
	
	
	
	
	
	//Returns set of all keys in map.
	Set<String> keySet(){
		
		

		//create set that can keep hold of all keys in map.
		Set<String> set_of_keys = new HashSet<>();
		


		//go through arraylist 
		for (int i = 0; i < theTable.size(); i++) { 
			
			//go through the linked list
			for (int j = 0; j < theTable.get(i).size(); j++) 
				set_of_keys.add(theTable.get(i).get(j).getKey());
			
			
		}//outer for
		
		
		//Returns set of all keys in map.
		return set_of_keys;
	}
	
	
	
	
	
	
	
	//returns number of elements in map
	int size() {
		return theTable.size();
	}
	
	
	
	
	
	
	//returns true iff number of elements in map is 0.
	boolean isEmpty() {
		return theTable.size()==0;
	}
	
	
	
	
	
	
	//toString
	public String toString() {
		StringBuilder strbuilder= new StringBuilder();
		
		//for loop arraylist
		for (int i = 0; i < theTable.size(); i++) { 
			
			//for loop linklist
			for (int j = 0; j < theTable.get(i).size(); j++) {
				strbuilder.append(theTable.get(i).get(j).getKey());
				strbuilder.append(" , ");
				strbuilder.append(theTable.get(i).get(j).getValue()); 
				strbuilder.append("\n");
			}//for loop close linklist
			
		}//close for loop arraylist
		
		
		
		return strbuilder.toString();
		
	}
	

	
	
	
	public static void main(String args[]) {
		
		//create the table data
		outermk datatable= new outermk(5);
		
		
		//test isEmpty
		System.out.println("test isEmpty============================");
		System.out.println(datatable.isEmpty());
		
		
		
		System.out.println("\n\n\n");
		
		
		
		//add the pairs of word and values
		datatable.put("your name", 903);
		datatable.put("your name", 100);
		datatable.put("Fullmetal Alchemist: Brotherhood", 5706);
		datatable.put("Steins;Gate", 4580);
		datatable.put("Clannad", 5156);
		datatable.put("Code Geass", 7802);
		datatable.put("Spirited Away", 10486);
		datatable.put("A Silent Voice", 370);
		datatable.put("Cowboy Bebop", 12170);
		
		
		
		
		
		
		System.out.println("\n\n\n");
		
		
		
		//test toString
		System.out.println("test toString============================");
		System.out.println(datatable.toString());
		
		
		
		
		System.out.println("\n\n\n");
		
		
		
		//test remove 
		System.out.println("test remove============================");
		System.out.print("Cowboy Bebop " );
		System.out.println(datatable.remove("Cowboy Bebop") );
		System.out.print("Transformers " );
		System.out.println(datatable.remove("Transformers") );
		System.out.print("123 " );
		System.out.println(datatable.remove(123) );
		
		
		System.out.println("\n\n\n");
		
		
		//test toString
		System.out.println("test toString============================");
		System.out.println(datatable.toString());
				
				
				
				
		System.out.println("\n\n\n");
				
				
		
		//test containsKey
		System.out.println("test containsKey============================");
		System.out.print("Spirited Away " );
		System.out.println(datatable.containsKey("Spirited Away" ));
		System.out.print("MegaMan " );
		System.out.println(datatable.containsKey("MegaMan" ));
		
		
		System.out.println("\n\n\n");
		
		
		
		//test containsValue
		System.out.println("test containsValue============================");
		System.out.print("4580 ");
		System.out.println( datatable.containsValue(4580) );
		System.out.print("100000 " );
		System.out.println( datatable.containsValue(100000) );
		
		
		
		
		System.out.println("\n\n\n");
		
		
		
		
		//test keySet
		System.out.println("test keySet==============================");
		System.out.println(datatable.keySet());
		
		
		
		
		System.out.println("\n\n\n");
		
		
		
		//test values
		System.out.println("test values==============================");
		System.out.println(datatable.values());
		
		
		
		System.out.println("\n\n\n");
		
		
		
		
		//test get
		System.out.println("test get==============================");
		System.out.println("Code Geass"+"   "+datatable.get("Code Geass"));
		System.out.println("Silent Voice"+"   "+datatable.get("Silent Voice"));
		
		
		
		
		System.out.println("\n\n\n");
		
		
		
		
		//test size
		System.out.println("test size==============================");
		System.out.println(datatable.size());
		
		
		
		System.out.println("\n\n\n");
		
		
		
		//test entrySet
		System.out.println("test entrySet==============================");
		System.out.println(datatable.entrySet());
	}
	
	

}
