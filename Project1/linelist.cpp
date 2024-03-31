
#include <iostream>
#include "linelist.h"

LineList::LineList()
{
	LineNode *line = new LineNode;
	LineNode *line2 = new LineNode;

	line -> info = topMessage;
	currLine = line;
	currLineNum = 0;
	length = 0;
	line2 -> info = bottomMessage;
	currLine -> back = NULL;
	currLine -> next = line2;
	line2 -> back = line;
	line2 -> next = NULL;
}

void LineList::goToTop() 
{
	// Post : Advances currLine to line 1
	while(currLine -> info  != topMessage){
		currLine = currLine -> back;
	}
	currLine = currLine -> next;
	currLineNum = 1;
}

void LineList::goToBottom() 
{
	// Post : Advances currLine to last line
	while(currLine -> info != bottomMessage){
		currLine = currLine -> next; 
	}
	currLine = currLine -> back;
	currLineNum = length;
}

void LineList::insertLine(string newLine)
{
	// post : newLine has been inserted after the current line
	LineNode *newLn = new LineNode;
	newLn -> info = newLine;
	newLn -> next = currLine -> next;
	currLine -> next = newLn;
	newLn -> back = currLine;
	
	currLineNum++;
	currLine = newLn;
	length++;
}

void LineList::deleteLine()
{
	// post : deletes the current line leaving currentLine
	//	  pointing to line following
	LineNode *followingLn = currLine -> back;	
	if(followingLn -> info == topMessage && followingLn -> next -> info == bottomMessage){
		currLineNum = 0;
		length = 0;
	}
	else if (followingLn -> info == topMessage){
		currLine -> back -> next = currLine -> next;
		currLine -> next -> back = currLine -> back;
		delete currLine;	
		currLine = followingLn->next;
		currLineNum --;
		length --;
	}
	else{	
		currLine -> back -> next = currLine -> next;
		currLine -> next -> back = currLine -> back;
		delete currLine;
		currLine = followingLn;
		currLineNum--;
		length --;
	} 
}

void LineList::printList() 
{
	int lineNum = 1;
	
	LineNode *mark = currLine;
	int markNum = currLineNum;

	goToTop();
	
	LineNode *Line = currLine;

	while(Line -> info != bottomMessage)
	{
		cout << lineNum << "> " << Line -> info <<endl;
		Line = Line -> next;
		lineNum++;
	}
	
	currLine = mark;
	currLineNum = markNum;
}

string LineList::getCurrLine() const
{
	return currLine-> info;
}

void LineList::moveNextLine()
{
	// Post : Advances currLine (unless already at last line)
	if (currLineNum == 0){}
	else if (currLine -> next -> info != bottomMessage){
		currLine = currLine -> next;
		currLineNum ++; 
	}
}

void LineList::movePrevLine()
{
	// Post : Advances currLine (unless already at last line)
	if(currLineNum == 0){}
	else if(currLine -> back -> info != topMessage){
		currLine = currLine -> back;
		currLineNum --;
	}
}

int LineList::getCurrLineNum() const
{
	return currLineNum;
}

int LineList::getLength() const
{
	return length;
}
