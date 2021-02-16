package tree;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*; //This is new

class TreeDisplay extends JFrame implements ActionListener{
	private JLabel l0 = new JLabel("Display Hash Table");
	private JTextArea tArea = new JTextArea("");
	private JScrollPane scrollPane = new JScrollPane(tArea); 
	private BSTree tree;
	private Node head;
	private Node tail;
	private JButton b1=new JButton("Close");
    private MyFrame2 parent;


//CONSTRUCTOR
	public TreeDisplay(JFrame p){
		super("");
        parent = (MyFrame2) p;
        tree=parent.readTree();
        head=tree.readHead();
        tail=tree.readTail();
		Container content=getContentPane();
		content.setLayout(null);
		Font f=new Font("TimesRoman", Font.BOLD,20);
		l0.setFont(f);  b1.setFont(f); 
		content.add(l0);  content.add(scrollPane);	content.add(b1);
		l0.setBounds(5,5,250,50);
		scrollPane.setBounds(5,50,260,390);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		b1.setBounds(90,450,100,40);
		b1.addActionListener(this);
		this.printTable();
        
		setSize(290,540);    setVisible(true);}

  public void printTable() {
	      tArea.setLineWrap(true);
		  traverse(head.right);
  }


void    traverse(Node t)
	{
	  if (t != tail)
		{
		
		traverse(t.left);
		visit(t);
        traverse(t.right); }
	}

   
String   search(int key){
           return "Not Found";}

void     visit(Node t)
	{ 
	   tArea.append( "   [" + t.key + " : " + t.name+" : "+ t.age +  "]\n");
	  }

	public void actionPerformed(ActionEvent e){

	  	Object target=e.getSource();
	 	if (target==b1)
	 		{    this.setVisible(false);
	 	      	 parent.setVisible(true);
	         }
          }
}

