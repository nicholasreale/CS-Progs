#include <fstream>
#include <cstdlib>
#include <cctype>
#include <iostream>
#include <string>
#include "baseball.h"
#include <bits/stdc++.h>



BaseballForest::BaseballForest(){
	root = NULL;
	root2 = NULL;
}
BaseballForest::~BaseballForest(){
	cout<<"DESTRUCTOR CALLED"<<endl;
	//makeEmpty(root);
	//makeEmpty(root2);	
}
bool BaseballForest::find(string pname)const{
	if(findAddr(root, pname) != NULL)
		return true;
	else
		return false;
}
void BaseballForest::insertName(string pname, float pavg){
	insertName(root, pname, pavg);
}
void BaseballForest::insertBavg(string pname, float pavg){
	insertBavg(root2, pname,pavg);
}
void BaseballForest::nameOrder()const{
	if (root == NULL)
		cout<<"Tree Is Empty"<< endl;
	else
		nameOrder(root);
}
void BaseballForest::bavgOrder() const{
	if (root2 == NULL)
		cout<< "Tree Is Empty" <<endl;
	else
		bavgOrder(root2);
}
void BaseballForest::bavgOrder(float min, float max) const{
	if(root2 == NULL)
		cout<< "Tree is Empty" << endl;
	else
		bavgOrder(root2, min, max);
}
void BaseballForest::printLine(string pname) const{
	TreePtr prt = findAddr(root, pname);
	cout << "\t" << setw(20) << (prt -> name).c_str() << setw(15) << (prt -> bavg) <<endl;
}
void BaseballForest::makeEmpty(){
	makeEmpty(root);
	makeEmpty(root2);
}
int BaseballForest::countNodes() const{
	return countNodes(root);
}
//start of privates
TreePtr BaseballForest::findAddr(TreePtr t, string pname) const{
	if(t == NULL)
		return NULL;
	else if(t->name == pname)
		return t;
	else if(t -> name < pname)
		return findAddr(t -> rightName, pname);
	else
		return findAddr(t-> leftName, pname);	
}
void BaseballForest::nameOrder(TreePtr t) const{
	if(t== NULL)
		return;
	else{
		nameOrder(t->leftName);
		printLine(t-> name);
		nameOrder(t->rightName);
	} 
}
void BaseballForest::bavgOrder(TreePtr t) const{
	if(t == NULL)
		return;
	else{
		bavgOrder(t-> rightBavg);
		printLine(t-> name);
		bavgOrder(t-> leftBavg);
	}
}
void BaseballForest::bavgOrder(TreePtr t,float min,float max) const{
	if(t==NULL)
		return;
	else if( t -> bavg > max)
		return;
	if(t -> bavg < min)
		return;
	else{
		bavgOrder(t-> rightBavg, min, max);
		printLine(t-> name);
		bavgOrder(t-> leftBavg, min, max);
	}		
}
void BaseballForest::insertName(TreePtr& t, string pname, float pavg){
	if(t == NULL){
		t = new TreeNode;
		t -> name = pname;
		t -> bavg = pavg;
		t -> leftName = NULL;
		t -> rightName = NULL;
		t -> leftBavg = NULL;
		t -> rightBavg = NULL;
	}
	else if(pname < t -> name)
		insertName(t->leftName,pname,pavg);
	else
		insertName(t->rightName,pname,pavg); 
}
void BaseballForest::insertBavg(TreePtr& t, string pname, float pavg){
	if(t == NULL){
		t = new TreeNode;
		t-> name = pname;
		t-> bavg = pavg;
		t-> leftName = NULL;
		t-> rightName = NULL;
		t-> leftBavg = NULL;
		t -> rightBavg = NULL;
	}
	else if ( pavg > t -> bavg)
		insertBavg( t -> rightBavg, pname, pavg);
	else
		insertBavg(t -> leftBavg, pname, pavg);
}
void BaseballForest::makeEmpty (TreePtr& root){
	if (root == NULL)
		return;
		makeEmpty(root -> leftName);
		makeEmpty(root -> rightName);
		makeEmpty(root -> leftBavg);
		makeEmpty(root -> rightBavg);
		delete root;
		root = NULL;
	return;
}
int BaseballForest::countNodes (TreePtr t) const{
	if(t == NULL)
		return 0;
	else if(t->leftName == NULL && t->rightName !=NULL)
		return countNodes(t-> rightName) + 1;
	else if(t->leftName != NULL && t-> rightName ==NULL)
		return countNodes(t-> leftName) + 1;
	else
		return countNodes(t ->leftName) + countNodes(t-> rightName) + 1;
}

