#include <iostream>
#include <cstdlib>
#include <fstream>
#include <limits.h>
#include <vector>
#include <algorithm>
#include <iomanip>
using namespace std;
#include "graph.h"
#include "HashTable.h"
#include "LinkedList.h"
#include "queue.h"

struct Input{
	string Origin;
	string Destination;
	int TripLength;
};

struct dElement{
	string Name = "///";
	bool Mark = false;
	int tDist = INT_MAX;
	string pVertex = "N/A";
	bool operator <= (dElement Element2) const {
		return (tDist <= Element2.tDist);
	}
};

void ParseFile( int argc, char *argv[], Queue<Input>& Inqueue); 
void sepIn(string lineIn, Queue<Input>& Inqueue);
void MakeGraph(Graph<string>& G, Queue<Input> data, vector<string> &Names, int& numVert, HashTable<string>& vExist);
void dijkstra(Graph<string> G, vector<dElement>& E, int currIndex);
void getStart(vector<dElement>& E, HashTable<string> vExist, int& currIndex);
void printCities(int numVerts, vector<string> E);
void printOutput(vector<dElement> E);
int getNextIndex(vector<dElement> E, string NextName);
int findMin(vector<dElement> E);

int main (int argc, char *argv[]){
	int currIndex;
	Graph<string> myGraph(50);
	Queue<string> myQ(50);
	vector<string> Names(50);
	HashTable<string> vExist("///",50);

	int numVert = 0;
	Queue<Input> FQueue(50);

	ParseFile(argc, argv, FQueue);
	MakeGraph(myGraph, FQueue, Names, numVert, vExist);

	vector<dElement> Element(numVert);
	string copy[numVert];

	for(int i = 0; i < numVert; i++){
		Element.at(i).Name = Names.at(i);
		//copy[i] = Names.at(i);
		//cout << Element.at(i).Name <<endl;
	}
	sort(Names.begin(), Names.begin() + numVert);
	printCities(numVert, Names);
	getStart(Element, vExist, currIndex); 
	dijkstra(myGraph, Element, currIndex);
	printOutput(Element);
}

void ParseFile(int argc, char *argv[], Queue<Input>& Inqueue){
	//Pre: Takes Command Line Inputs
	//Post: Puts them in a queue in a useable format
	if(argc == 1){
		cout << "File Not Input" << endl;
		exit(1);
	}
	if(argc > 2){
		cout << "Too Many Command Line Arguments" << endl;
		exit(1);
	}
	else {
		ifstream chkTarget;
		string lineIn;
		chkTarget.open(argv[1]);
		if(chkTarget.fail()){
			cout << "File Not Found" <<endl;
		}
		while(getline(chkTarget, lineIn)){
			sepIn(lineIn, Inqueue);
		}
	}
}

void sepIn(string lineIn, Queue<Input>& Inqueue){
	//Pre: Takes a line from the infile
	//Post:	converts it to useable information and loads it into a queue
	Input currIn;
	string num;
	size_t firstColen = lineIn.find(';');
	size_t secondColen = lineIn.find(';', firstColen+1);
	currIn.Origin = lineIn.substr(0, firstColen);
	//cout<< currIn.Origin << endl;
	currIn.Destination = lineIn.substr(firstColen+1, (secondColen - firstColen - 1));
	//cout<< currIn.Destination <<endl;
	num = lineIn.substr(secondColen+1);
	currIn.TripLength = atoi(num.c_str());
	//cout<< currIn.TripLength << endl;
	if(Inqueue.isFull())
		cout << "Too Many Inputs" <<endl;
	else{
		Inqueue.enqueue(currIn);
	}
}

void MakeGraph( Graph<string>& G, Queue<Input> data, vector<string> &Names, int& numVert, HashTable<string>& vExist){
	//Pre: takes the input data
	//Post: Makes a Graph object and a HashTable of all the verticies
	Input Add;
	while(!data.isEmpty()){
		Add = data.dequeue();
		if(vExist.find(Add.Origin) == "///"){
			vExist.insert(Add.Origin);
			G.AddVertex(Add.Origin);
			Names.at(numVert) = Add.Origin;
			numVert++;
			//cout << Add.Origin << " Added as A Verticie" << endl;
		}
		if(vExist.find(Add.Destination) == "///"){
			vExist.insert(Add.Destination);
			G.AddVertex(Add.Destination);
			Names.at(numVert) = Add.Destination;
			numVert++;
			//cout << Add.Destination << " Added as A Verticie2" <<endl;
		}
		G.AddEdge(Add.Origin, Add.Destination, Add.TripLength);
	}
}

void dijkstra(Graph<string> G, vector<dElement>& E, int currIndex){
		//Pre: Takes the Graph and Vector of all the table information
		//Post:	Fills out the table information and sorts the vector

		//cout<< currIndex <<endl;
		Queue<string> vertex;
		int weight;
		int nextIndex;
		int lastIndex;
		G.MarkVertex(E.at(currIndex).Name);
		dElement Hold = E.at(currIndex);
		E.at(currIndex) = E.at(0);
		E.at(0) = Hold;
		//cout << E.at(currIndex).Name << endl;
		//cout << E.at(0).Name <<endl;
		currIndex = 0;
		while(currIndex < E.size()-1){
			G.GetToVertices(E.at(currIndex).Name, vertex);
			while(!vertex.isEmpty()){
				nextIndex = getNextIndex(E,vertex.getFront());
				if (nextIndex != -1){
					weight = G.WeightIs(E.at(currIndex).Name, vertex.getFront());
					if(E.at(nextIndex).Mark == false && E.at(nextIndex).tDist > (E.at(currIndex).tDist + weight)){
						E.at(nextIndex).tDist = E.at(currIndex).tDist + weight;
						E.at(nextIndex).pVertex= E.at(currIndex).Name;
					}
				}
				else{
					cout << "Error" <<endl;
					exit(1);
				}
				vertex.dequeue();
				
			}
			//cout << currIndex << endl;
			lastIndex = currIndex;
			currIndex = findMin(E);
			//cout << currIndex << endl;
			Hold = E.at(currIndex);
			if (lastIndex < E.size()){
				E.at(currIndex) = E.at(lastIndex+1);
				E.at(lastIndex+1) = Hold;
				currIndex = lastIndex+1;
			}
			E.at(currIndex).Mark = true;
			G.MarkVertex(E.at(currIndex).Name);
		//	currIndex = 10000;
		}
		 
}

void getStart(vector<dElement>& E, HashTable<string> vExist, int& currIndex){
	//Pre: Hash Table of Verticies And Vector of elements
	//Post: User input start location is filled out and the index of it is marked
	string UI;
	bool fin;
	while(fin != true){
		cout << setw(30) <<"		    Please Enter Your Starting Vertex: ";
		getline(cin,UI);
		if(vExist.find(UI) != "///")
			for( int i = 0; i <= E.size(); i++){
				if (E.at(i).Name == UI){
					E.at(i).Mark = true;
					E.at(i).tDist = 0;
					currIndex = i;
					//cout << E.at(i).Name << " Has Been Selected" << endl;
					return;
				} 
			}
	}
}
void printCities(int numVerts, vector<string> E){
	//Post: it prints the cities menu
	cout << "		    ^^^^^^^^^^^^^^^^^^^^^	DIJKSTRA'S ALGORITHM	^^^^^^^^^^^^^^^^^^^^^	   " <<endl;
	cout << endl;
	cout << setw(30) <<"		    A Weighted Graph Has Been Built For These " << numVerts << " Cities:" <<endl;
	cout << endl;
	int l = 0;
	for (int i = 0; i < numVerts; i++){
		if (l == 3){
			cout << endl;
			l = 0;
		}
		cout << setw(30) <<E.at(i);
		l++;
	}
	cout << endl << endl;
}
void printOutput( vector<dElement> E){
	//Post:Prints the sorted vector of all the elements
	cout<< "--------------------------------------------------------------------------------------------------------     "<< endl;
	cout<< setw(30) <<"Vertex" << setw(30) << "Distance"<<setw(30) <<"Previous"<< endl;
	cout << endl;
	for(int i = 0; i < E.size() ; i++){
		cout << setw(30) <<E.at(i).Name << setw(30) << E.at(i).tDist << setw(30) << E.at(i).pVertex << endl;
	}
}
int getNextIndex(vector<dElement> E, string NextName){
	//Pre: vector of Elements and name of the next Element
	//Post: Index of the Element in the vector
	for(int i = 0; i<= E.size(); i++){
		if(E.at(i).Name == NextName)
			return i;
	}
	return -1;
}

int findMin(vector<dElement> E){
	//Pre: Vector With all the table elements
	//Post:	Finds the Element with the minimum total distance.
	int currMin = E.size()-1;
	for(int i = 0; i < E.size(); i++){
		if(E.at(currMin).tDist > E.at(i).tDist && E.at(i).Mark != true){ 
			currMin = i;
		}
	}
	//cout << E.at(currMin).tDist << endl;
	return currMin;
}

