#include <iostream>
#include <cstring>
using namespace std;

struct RecType{
	int age;
	float weight;
	char gender;
};
int main(){

RecType* recPtr = new RecType;
recPtr -> age = 25; 
recPtr -> weight = 190;
recPtr -> gender = 'M';
recPtr -> age += 5;

cout <<"Three feilds pointed to by recPtr:"<< recPtr -> age << " " << recPtr -> weight << " " << recPtr -> gender << endl;
delete recPtr;
recPtr = NULL;

char *strPtr = new char[50];
strcpy(strPtr, "Operating Systems");
cout << "strPtr points to:" << strPtr<<endl;

int lowerCt = 0;
for( int i = 0; i< strlen(strPtr); i++){
	if(islower(strPtr[i]))
		lowerCt ++;
}
cout << "the number of lowercase letters in strPtr is:" << lowerCt << endl;

strPtr += 10;
cout << "Printing strPtr after adding 10:" << strPtr<< endl;

int num = 5; 
int* intptr = NULL;
//intptr = &num;
*intptr *=10;
cout << "intPtr ponts to:"<< *intptr << endl;
 
return 0;
}
