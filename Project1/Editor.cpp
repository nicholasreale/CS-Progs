#include <iostream>
#include "linelist.h"
#include <fstream>
#include <cstdlib>
using namespace std; 


void Start(int argc, char *argv[], LineList& L);
void LdFile(char *argv[], LineList& L);
void GetCmd (LineList& L, char& command);
void InsertLine(LineList& L);

int main(int argc, char *argv[]){
	// Variable Declerations That Will Pass Through All The Methods
	LineList L;
	char command;
	
	//Start Up Commands
	Start(argc, argv, L);
	L.printList();

	do{
		// Main Program Operation
		GetCmd(L, command);

		switch(command){
			case 'I':
				InsertLine(L); 
				break;
			case 'D':
				L.deleteLine();
				break;
			case 'L':
				L.printList();
				break;
			case 'P':
				L.movePrevLine();
				break;
			case 'N':
				L.moveNextLine();
				break;
		}

	}
	while (command != 'E'); 
	LdFile(argv, L);
	return 0;
}

void Start (int argc, char *argv[], LineList& L){
	//Pre: Command Line Args and Empty LineList
	//Post: Full LineList filled with the file on the command line
	int indexNo = 0;
	if(argc == 1){
		cout << "File Not Input" << endl;
		exit(1);
	}
	if(argc < 2){
		cout << "Too many Command Line Arguments Try Again" << endl;
		exit(1);
	}
	else{
		ifstream inFile;
		string Line;
		inFile.open(argv[1]);
		if (inFile.fail()){
			cout<< "File Not Found" << endl;
			exit(1);
		}
		while(getline(inFile,Line)){
			L.insertLine(Line);
		}
		inFile.close();
	}		
} 

void LdFile(char *argv[], LineList& L){
	//Pre: fileName from command line and the edited linelist
	//Post: Will edit the file on disc and fill it with the contents of the line list
	ofstream outFile;
	outFile.open(argv[1]);
	
	L.goToTop();
	int lineNum = L.getCurrLineNum();

	while(lineNum < (L.getLength()+1)){
		outFile<< L.getCurrLine() << endl;
		L.moveNextLine();
		lineNum++;	
	}
	
	outFile.close();
}

void GetCmd(LineList& L, char& command){
	//Sets the command char using user input
	char userInput[255];
	cout << L.getCurrLineNum() << "> ";
	cin >> userInput;
	if(userInput != NULL && userInput[1] != ' '){
		command = toupper(userInput[0]); 
	}
	else{
		command = 'X';
	}	
}

void InsertLine(LineList& L){
	//Pre: LineList is brought in
	//Post: Adds a Line after the current line
	int lineNum = L.getCurrLineNum() + 1;
	string hold;
	
	if (L.getCurrLineNum() > L.getLength())
		lineNum --;

	cout << lineNum << "> ";
	
	cin.ignore();
	getline(cin, hold);
	L.insertLine(hold);
}
