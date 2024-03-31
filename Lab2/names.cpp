
#include <string>
#include <iostream>
using namespace std;

void BreakDown (string name, string& first, string& last, string& mi);
int main()
{
	string name, first, last, mi;

	cout << "Name? <Last, First MI.> ";
	getline (cin, name);
		
	BreakDown (name, first, mi, last);

	cout << "First Name Entered :  " << first << endl;
	cout << "Last Name Entered :  " << last << endl;
	cout << "Middle Initial Entered :  " << mi << endl;
	return 0;
}

void BreakDown (string name, string& first, string& mi, string& last)
{
	// pre  : name is initialized with a full name
	// post : first, mi, and last contain the individual components
        //        of that name
	size_t firstGap = name.find(',');
	cout<< firstGap<<endl;
	size_t secondGap = name.find(' ',firstGap+2);
	cout<< secondGap<<endl;
	size_t perloc = name.find('.');
	cout<< perloc <<endl; 
	first = name.substr(firstGap+2, (secondGap-firstGap-2));
	mi = name.substr(secondGap, (perloc-secondGap));
	last = name.substr(0, firstGap);

} 
