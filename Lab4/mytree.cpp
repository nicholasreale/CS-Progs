#include <iostream>
#include "bst.h"

using namespace std;
template <class Comparable> 
void checkbal(BinarySearchTree<Comparable> t){
	if(t.isBalanced())
		cout<< "Tree Balanced" << endl;
	else
		cout<< "Tree Unbalanced" <<endl;
}
int main(){
	
	BinarySearchTree<int> t(0);
	
	t.insert(6);
	t.insert(2);
	t.insert(8);
	t.insert(1);
	t.insert(4);
	t.insert(3);

	t.postOrder();
	
	cout << "The height is:" << t.height() << endl;
	
	checkbal(t);
	t.insert(9); 
	checkbal(t);
	return 0;
}
