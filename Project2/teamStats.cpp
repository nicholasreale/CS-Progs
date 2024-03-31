#include <fstream>
#include <cstdlib>
#include <cctype>
#include <iostream>
#include <string>
#include "baseball.h"
#include <bits/stdc++.h>

void Start(int argc, char *argv[], BaseballForest& S); 
void SepName(string pName, string& fName, string& lName);
void FixAndInput(string lineIn, BaseballForest& S);
void PrintMenu();
void GetCmd(char& command);
void searchP(BaseballForest S);
void searchB(BaseballForest S);

int main(int argc, char *argv[]){
			
	BaseballForest S;
	char command;
	
	Start(argc, argv, S);
	do{
		PrintMenu();
		GetCmd(command);
		switch(command){
			case 'P':
				S.nameOrder();
				break;
			case 'B':
				S.bavgOrder();
				break;
			case 'S':
				searchP(S);
				break;
			case 'R':
				searchB(S);
				break;
			case 'C':
				cout << "There are " << S.countNodes() << " Players" << endl;
				break;
			case 'M':
				S.makeEmpty();
				break;
		}
		cin.ignore();
		cin.get();
	}
	while(command != 'E');
	return 0;
}

void Start(int argc, char *argv[], BaseballForest& S){
	//Pre: Command Line Args and Empty Tree Class
	//Pst: Full Forest
	if(argc == 1){
		cout << "File Not Input" << endl;
		exit(1);
	}
	if(argc < 2){
		cout << "Too Many Args Try Again" << endl;
		exit(1);
	}
	else{
		ifstream inFile;
		string lineIn;
		inFile.open(argv[1]);
		if(inFile.fail()){
			cout<< "File Not Found" << endl;
			exit(1);
		}
		while(getline(inFile, lineIn)){
			FixAndInput(lineIn, S);
		}
	}
}

void SepName(string pName, string& fName, string& lName){
	//Pre: Full Name
	//Post: fName and lName contain the fitst and last name in the correct format
	size_t NameSep = pName.find(", ");
	if (NameSep < 100000){
		char firstL;
		lName = pName.substr(0, NameSep);
		fName = pName.substr(NameSep+2);
		transform(fName.begin() + 1, fName.end(), fName.begin()+1, [](unsigned char c){return tolower(c);});
		transform(lName.begin() + 1, lName.end(), lName.begin()+1, [](unsigned char c){return tolower(c);});
		firstL = toupper(lName[0]);
		lName = firstL + lName.substr(1);
		firstL = toupper(fName[0]);
		fName = firstL + fName.substr(1);	
	}
	else{
		fName = "///";
		lName = "///";
	} 
}

void FixAndInput(string lineIn, BaseballForest& S){
	//Pre: Raw Line In
	//Post: Line Added to the trees
	size_t front = lineIn.find("<b>");//add 3
	size_t endOfName = lineIn.find(".");//sub 4
	size_t frontOfBavg = lineIn.find(" .");//add 1
	size_t back = lineIn.find("</b>");//sub 1

	
	if (front < 1000000){
	
		string fName;
		string lName;
		string pName = lineIn.substr(front+3, (endOfName-front)-3);
		SepName(pName, fName, lName);
		pName = lName + ", " + fName;

		string bavg = lineIn.substr(frontOfBavg+1,(back-frontOfBavg)-1);
		float flAvg = stof(bavg);
		
		S.insertName(pName, flAvg);
		S.insertBavg(pName, flAvg);
	} 
} 
void PrintMenu(){
	//Post: theres a menu now
	cout<<"((((((((     BASEBALL TREE STATS     ))))))))"<< endl;
	cout<<"P) Print A Team Report Sorted By Player Name "<< endl;
	cout<<"B) Print A Team Report By High Batting Avg   "<< endl;
	cout<<"S) Search For An Indiviual Player            "<< endl;
	cout<<"R) Search By Batting Avg Range               "<< endl;
	cout<<"C) Count Number of Players In Name Tree      "<< endl;
	cout<<"M) Free Up All Dynamic Memory From Both Trees"<< endl;
	cout<<"E) Exit Software                             "<< endl;
	cout<<"---------------------------------------------"<< endl;
	cout<<"OPTION => ";


}
void GetCmd(char& command){
	//Pre: Empty command variable
	//Post: Variable Filled with user Input
	char userInput[255];
	cin >> userInput;
	if(userInput != NULL && userInput[1] != ' '){
		command = toupper(userInput[0]);
	}
	else{
		command = 'X';
	}
}

float parseFloat(){
	//Post: Grabs User Input
	string inLine;
	cin.ignore();
	getline(cin, inLine);
	return atof(inLine.c_str());
}
void searchP(BaseballForest S){
	//Pre:Baseball object
	//Post:Shows Serch value based on UI
	string inLine;
	string fName;
	string lName;
	cout<<"   Search Last, First =>";
	cin.ignore();
	getline(cin, inLine);
	SepName(inLine, fName, lName);
	string pName = lName + ", " + fName;
	if (S.find(pName))
		S.printLine(pName);
	else
		cout<<"Player Not Found" << endl; 
}
void searchB(BaseballForest S){
	//Pre: BaseballForest object
	//Post: Shows Search Results based on UI
	cout <<"   Input Min Bavg =";
	float min = parseFloat();

	cout<< "   Input Max Bavg =";
	float max = parseFloat();

	S.bavgOrder(min, max);	
}
