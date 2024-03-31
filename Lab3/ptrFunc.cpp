#include <iostream>

using namespace std;

bool EqCheck( double *ptr1, double *ptr2);
bool SameAd( double *ptr1, double *ptr2);

int main(){
	double *ptr1 = new double;
	*ptr1 = 15;
	double *ptr2 = new double;
	*ptr2 = 15;
	cout << "it is " << EqCheck( ptr1, ptr2) << " that the value is equal" << endl;
	cout << "it is " << SameAd( ptr1, ptr2) << " that the addresses are the same" << endl;
return 0; 
}

bool EqCheck( double *ptr1, double *ptr2){
	if (*ptr1 == *ptr2)
		return true;
	else
		return false;
}

bool SameAd( double *ptr1, double *ptr2){
	if (ptr1 == ptr2)
		return true;
	else 
		return false; 
}
