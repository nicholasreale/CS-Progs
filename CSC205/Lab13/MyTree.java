public class MyTree{
	public static void main( String Args[]) throws CloneNotSupportedException{
		BinarySearchTree t = new BinarySearchTree();
		t.insert(new KeyedItem("M"));
		t.insert(new KeyedItem("J"));
		t.insert(new KeyedItem("W"));
		t.insert(new KeyedItem("D"));
		t.insert(new KeyedItem("L"));
		t.insert(new KeyedItem("S"));
		t.insert(new KeyedItem("Z"));
		t.insert(new KeyedItem("F"));
		t.insert(new KeyedItem("T"));
		t.inorder();
		t.preorder();
		BinarySearchTree j = new BinarySearchTree();
		j = ((BinarySearchTree)t.clone());
		if(t.duplicateCheck(j))
			System.out.println("They're Duplicates");
		else
			System.out.println("They're Not Duplicates");
	}
}
