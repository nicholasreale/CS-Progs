#include <iostream>
#include "BinaryHeap.h"

int main(){
	BinaryHeap<char> PQ(50);
	for(int i = char('A'); i< char('K'); i++){
		PQ.insert( char(i) );
	}


	PQ.deleteMin(); 
	//PQ.helper();
	cout << "Leaves: ";
	PQ.PrintLeaves();
	cout << endl;

	cout << "Height: " << PQ.Height() << endl;
	cout << "findMax: " << PQ.findMax() << endl;
	return 0;
}
