package tree;

class Node {
	int key;
	String  name;
        int age;
	Node left;
	Node right;
	Node(int k, String nm, int a,Node ll, Node rr)
		{ key = k;
		  name = nm;
                  age=a;
		  left = ll; right = rr; }
	public int readKey(){return key;}
	public String readName(){return name;}
	public int readAge(){return age;}
	}