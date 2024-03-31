using namespace std;
#include <string>

struct TreeNode;  // Forward Declaration 
typedef TreeNode *TreePtr;
struct TreeNode
{
        string name;
        TreePtr leftName;
        TreePtr rightName;
        TreePtr leftBavg;
        TreePtr rightBavg;
	float   bavg;
};

class BaseballForest
{
public:
	BaseballForest();
	~BaseballForest();
  	bool find(string pname) const; //calls findAddr; returns true if FindAddr != NULL
	void insertName(string pname, float pavg);
	void insertBavg(string pname, float pavg);
	void nameOrder() const; //printout alphabetically
	void bavgOrder() const; //printout by highest batting averages
	void bavgOrder(float min, float max) const; //printout of all players > min AND < max
	void printLine(string pname) const; //calls findAddr; print data at address returned if != NULL
	void makeEmpty(); //garbage collection of all dynamic memory pointed to by root AND root2 cleaned up
	int countNodes() const; //returns size of tree pointed to by root
private:
	TreePtr root;  //root of the BST ordered by name
	TreePtr root2; //root of the BST ordered by batting average

        TreePtr findAddr(TreePtr t, string pname) const; //returns NULL if not found
	void nameOrder (TreePtr t) const;
	void bavgOrder (TreePtr t) const;
	void bavgOrder (TreePtr t, float min, float max) const;
	void insertName (TreePtr& t, string pname, float pavg);
	void insertBavg (TreePtr& t, string pname, float pavg);
	void makeEmpty (TreePtr& root); 
	int countNodes(TreePtr t) const;
};


