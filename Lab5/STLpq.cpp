#include <queue>
#include <iostream>


using namespace std;

int main() {
	priority_queue<string> pq;
	
	pq.push("Joe");
	pq.push("James");
	pq.push("Aaron");
	pq.push("Jake");
	pq.push("Padro");
	pq.push("Gerald");
	pq.push("Gretchen");
	pq.push("Nick");
	pq.push("CJ");
	pq.push("Kim");
	string Out;
	while (pq.empty() == false ){
		Out = pq.top();
		pq.pop();
		cout << Out << endl;
	}
	
	return 0;
}
