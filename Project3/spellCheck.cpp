//Nicholas Reale
//Spell Check
#include <algorithm>
#include <iostream>
#include <fstream>
#include <cstdlib>
#include <sstream>
#include <string>
#include <cctype>
#include <queue>
using namespace std;
#include "HashTable.h"
#include "BinarySearchTree.h"
struct BNode{
	string word;
	int lineNum;
	bool operator < (BNode otherStruct) const {
		return (word < otherStruct.word);
	}

};

typedef BinarySearchTree<BNode> BST;
ostream & operator << (ostream & out, BNode & temp) {
	out << temp.word << " " << temp.lineNum;
	return out;
}

void fillDict( HashTable& Dict);
void setup(int argc, char *argv[], queue<BNode>& wrong, HashTable Dict);
void findInput(string LineIn, HashTable Dict, queue<BNode>& Wrong, int& lineNum);
void PrintMenu(BNode TgtWord);
void GetCmd(char& command);
void SuggestWord(string word, HashTable Dict);
string spellCheck(string word, HashTable Dict);
void printToFile(BST WrongWords);
BNode getNextWord(queue<BNode>& Q, HashTable Ignore);
char doctorLine(char in);
void addDict(string Word, HashTable& Dict);

int main(int argc, char *argv[]){
	//Vagirable Declorations
	BNode NV = {"///", -1};
	char command;
	BNode TgtWord;	
	//Object Declorations
	queue<BNode> q;
	BST wrongWords(NV);
	HashTable Dict(500);
	HashTable Ignore(500);
	//Program Setup
	fillDict(Dict);
	setup(argc, argv, q, Dict);
	TgtWord = getNextWord(q, Ignore);
	//Program control
	do{
		PrintMenu(TgtWord);
		GetCmd(command);
		switch(command){
			case 'A':
				addDict(TgtWord.word, Dict);
				wrongWords.insert(TgtWord);
				TgtWord = getNextWord(q, Ignore);
				break;
			case 'I':
				Ignore.insert(TgtWord.word);
			case 'G':
				wrongWords.insert(TgtWord);
				TgtWord = getNextWord(q,Ignore);
				break;
			case 'S':
				SuggestWord(TgtWord.word, Dict);
				break;
		} 
	}while(command != 'Q' && TgtWord.word != "///");
	//Printing to outfile
	printToFile(wrongWords);
	return 0;
}

void setup(int argc, char *argv[], queue<BNode>& wrong, HashTable Dict){
	//Pre: Command Line arguments, and empty objects that will be refrenced later
	//Post: Queue filled with all of the words that the program will say is wrong, and a hashtable that is filled with all the words in dict.txt.
	if(argc == 1){
		cout << "File Not Input" << endl;
		exit(1);
	}
	if(argc > 2){
		cout << "Too Many Command Line Arguments Try Again" <<endl;
		exit(1);
	}
	else {
		ifstream chkTarget;
		string lineIn;
		int lineNum = 1;
		chkTarget.open(argv[1]);
		if(chkTarget.fail()){
			cout << "File Not Found" << endl;
			exit(1);
		}
		while(getline(chkTarget, lineIn)){
			findInput(lineIn, Dict, wrong, lineNum);
		}
	}
};

void findInput(string LineIn, HashTable Dict, queue<BNode>& Wrong, int& lineNum){
	//Pre: Line from a file to be spell checked, the dictionary table, the Queue of wrong words and a line count
	//Post: a Queue filled with all the BNodoes that the program is going to spell check.
	transform(LineIn.begin(), LineIn.end(), LineIn.begin(), doctorLine);
	//cout << LineIn <<endl;
	stringstream linecheck(LineIn);
	string word;
	BNode s = {"///", lineNum};
	while(linecheck >> word) {
		if(Dict.find(word) == false){
			s.word = word;
			//cout << s << endl;
			Wrong.push(s);
		}	
	}
	lineNum++;
}

void fillDict(HashTable& Dict){
	//Pre: empty Dict table
	//Post: filled Dict with all words in Dict.txt
	ifstream textF;
	textF.open("dict.txt");
	string lineIn;
	if(textF.fail()){	
	}
	else{
		while(getline(textF, lineIn)){
			Dict.insert(lineIn);
		}
	}
	textF.close();
}

void PrintMenu(BNode TgtWord){
//Pre: BNode of current word
//Post: Prints Menu
cout<< "!@#$%^&*(){}    THE SPELL CHECKER PROGRAM    !@#$%^&*(){}" << endl;
cout<< TgtWord.word << " On Line "<< TgtWord.lineNum << " Was Not Found In Dictionary" << endl;
cout<< "A) Add the Word To Dictionary"<<endl;
cout<< "I) Ignore Word, and Skip Future References" << endl;
cout<< "G) Go On To Next Word" <<endl;
cout<< "S) Serch For A Suggested Spelling"<<endl;
cout<< "Q) Quit Spell Checking File" <<endl;
cout << endl;
cout << "Selection : ";
}

void GetCmd(char& command){
//Pre: empty or used command char
//Post: Command char filled with users next command	
	char userInput[255];
	cin >> userInput;

	if(userInput != NULL && userInput[1] != ' '){
		command = toupper(userInput[0]);
	}
	else{
		command = 'X';
	}
}


void SuggestWord(string word, HashTable Dict){
//Pre: incorrect word and Dict table
//Post: word is suggested if a simmilar word is found in the dictionary
cout<< endl;
string suggestion = spellCheck(word, Dict);
if (suggestion != "///")
	cout << "Suggested Spelling : " << suggestion << endl;
else
	cout << "No Suggested Spellings Found in Dictionary." << endl;
cout << endl;
cout << "Please Hit Return to Continue...";
cin.ignore();
cin.get();
//cin.ignore();	
}

string spellCheck(string word, HashTable Dict){
//Pre: Word to be spell checked and dictionary
//Post: Suggested word or not found value depending on if a word was found in the dict
	int f = 0;
	for (int b = 1; b <= (word.size() -1); b++){
		swap(word[f], word[b]);
		if(Dict.find(word) == true)
			return word;
		swap(word[f], word[b]);
		f++;
	}
	return "///";
}
void printToFile(BST WrongWords){
//Pre: BST full of all the words that the program has interacted with(like the sample)
//Post: File full of the words that were interacted with by the program
	ofstream BSTout;
	BSTout.open("notfound.txt");
	WrongWords.printTree(BSTout);
}
char doctorLine(char in){
//Called in the transform function
//Pre: char that could be a symbol or lowercase letter
//Post: either a pass through or a corrected char to an acceptable format for the program
	if (in <= 'Z' && in >= 'A')
		return in - ('Z' -'z');
	if (in <= '@')
		return ' ';
	if (in <= '\''&& in >= '[')
		return ' ';
	if (in >= '{')
		return ' ';
	return in;

}

BNode getNextWord(queue<BNode>& Q, HashTable Ignore){
//Pre: Queue of wrong words and the ignore table
//Post: The next BNode the program will interact with
	if(!Q.empty()){
		BNode next = Q.front();
		Q.pop();
	
		if (Ignore.find(next.word) == true)
			return 	getNextWord(Q, Ignore);
		else
			return next;
	}
	else{
		cout<< "The Spell Checking Is Complete Exiting File." << endl;
		BNode NV = {"///",-1};
		return NV;
	}
}

void addDict(string Word, HashTable& Dict){
//Pre: a word that will be appended to the dictionary
//Post: the dict file is appended and the hash table has the word in it.
	Dict.insert(Word);
	ofstream dict("dict.txt", ios::app);
	dict << Word << endl;
	dict.close();
}
