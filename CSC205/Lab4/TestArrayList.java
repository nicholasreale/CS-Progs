import java.util.*;
public class TestArrayList
{
    public static void main(String[] args)
    {
        ArrayList<String> myArrayList = new ArrayList<String>(10);
        myArrayList.add("Python");
        for(int i=0; i < 5; i++){
                myArrayList.add("java");
        } for (int i=0; i < 4; i++){
                myArrayList.add("C++");
        }
        System.out.println("My Array List");
        printArrayList(myArrayList);
        System.out.println("After Deleting Java");
        delete(myArrayList,"java");
        printArrayList(myArrayList);
        System.out.println("occurances of C++: "+count(myArrayList, "C++"));





    }

    private static void printArrayList(ArrayList<String> myArrayList)
    {
        // Pre  : myArrayList has been initialized
        // Post : The elements of Vector v are printed to the screen on separate lines
        for(int i =0; i < myArrayList.size(); i++){
                System.out.println(myArrayList.get(i));
        }
    }

    private static void delete(ArrayList<String> myArrayList, Object key)
    {
        // Pre  : myArrayList has been initialized
        // Post : All copies of key are removed from myArrayList
    	int occ = count(myArrayList,key);
       for(int i = 0; i<occ;i++)
    	myArrayList.remove(key);
    }

    private static int count(ArrayList<String> myArrayList, Object key)
    {
        // Pre  : myArrayList has been initialized
        // Post : number of occurrences of key is computed and returned
    	int occ = 0;
    	for (int i = 0; i < myArrayList.size(); i++) {
			if(myArrayList.get(i)==key) {
				occ++;
			}
		}
    	return occ;
    }
}
