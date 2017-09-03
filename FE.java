import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class FE implements ActionListener
{
	JFrame f1,f2,f3;       JButton b1,b2,b3,b4,b5;          JPanel p1,p2,p3,p4,p5;     JLabel l1;          CardLayout cl;
	ResultSet rs;		   String a,b,c,d,c1;               String a1[],c3[];          JProgressBar jbr;   ButtonGroup bg;
	JLabel l2,l3,l4,l5;    JTextField jt1,t1,t2,t3,t4;	    JRadioButton j1,j2,j3,j4;
    int correct=0,wrong=0,unattempt=0;
    int finish=0;
    int m,count=1,i=0;
    boolean reviewClicked=false;	 ArrayList list; String array[]=new String[20]; int arr[]=new int[20]; int arr1[]=new int[20];
	Statement stmt;

	public FE()
	{
		f1=new JFrame();
		f1.setSize(300,200);
		FlowLayout fl=new FlowLayout(FlowLayout.CENTER,50,50);
		f1.setLayout(fl);
		b1=new JButton("Start");
		b2=new JButton("Exit");
		f1.add(b1);  f1.add(b2);
		f1.setVisible(true);
		b1.addActionListener(this);
		b2.addActionListener(this);

		f3=new JFrame();
		f3.setSize(1600,600);
		a1=new String[20];
	    c3=new String[20];


		jbr=new JProgressBar(0,20);
		jbr.setBounds(40,40,200,30);

		f3.add(jbr);



		p2=new JPanel(new GridLayout(5,2));
		l2=new JLabel("Total No. of Questions");
		t1=new JTextField(20);
		l3=new JLabel("No. of Correct Answers");
		t2=new JTextField(20);
		l4=new JLabel("No. of Wrong Answers");
		t3=new JTextField(20);
		l5=new JLabel("No. of Unattempt Questions");
		t4=new JTextField(20);

		p2.add(l2); p2.add(t1); p2.add(l3); p2.add(t2);p2.add(l4); p2.add(t3);p2.add(l5); p2.add(t4);

		b1=new JButton("Review");
		b2=new JButton("Exit");
		p2.add(b1);
	    p2.add(b2);
		b1.addActionListener(this);
		b2.addActionListener(this);
		p5=new JPanel();
		p5.add(p2);
	    f3.add(p5);

	   	f2=new JFrame();
		f2.setSize(700,300);
	    cl=new CardLayout();
		JTextField t1=new JTextField(30);
		p2=new JPanel();
		p2.setLayout(cl);


		GridLayout gl=new GridLayout(1,3);
        p1=new JPanel(gl);
		b3=new JButton("Prev");
		b4=new JButton("Next");
		b5=new JButton("Finish");
		p1.add(b3);
		p1.add(b4);
		p1.add(b5);
		f2.add(p1,BorderLayout.SOUTH);


		GridLayout gl1=new GridLayout(6,0);
	    p3=new JPanel(gl1);
	    l1=new JLabel();



	    j1=new JRadioButton();
	    j2=new JRadioButton();
	    j3=new JRadioButton();
	    j4=new JRadioButton();
	    bg=new ButtonGroup();

	   	bg.add(j1);  bg.add(j2); bg.add(j3); bg.add(j4);
	    p3.add(l1);	p3.add(j1);   p3.add(j2); p3.add(j3); p3.add(j4);


		p2.add(p3,"mohit");
		f2.add(p2);


	try
	{
		 list=new ArrayList();
		for(int i=0;i<20;i++)
		{
			list.add(i+"");
		}


		Collections.shuffle(list);


		list.toArray(array);
	    for(int i=0;i<20;i++)
	    {

	    	arr[i]=Integer.parseInt(array[i]);
	    	arr1[i]=arr[i]+1;
	    	System.out.println(arr1[i]);


	    }
	}
		catch(Exception e)
		{
			e.getMessage();
		}

	}


	public void actionPerformed(ActionEvent e)
	{
		String str=e.getActionCommand();
		try
		{
			 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	         Connection con=DriverManager.getConnection("Jdbc:Odbc:Online");
		     stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}

	    if(str.equals("Start"))
		{
			b3.setEnabled(false);
			   try
		    	{
			     	rs=stmt.executeQuery("SELECT * FROM Questions");

			     		rs.absolute(arr1[i]);

		    			  System.out.println(i+"  "+1+"  "+arr1[i]);
		   				  String question=rs.getString("Question");
	        			  String q="Q."+1+question;
	        	          a=rs.getString("A");   b=rs.getString("B");   c=rs.getString("C"); d=rs.getString("D");
	                   	  l1.setText(q);
	        	          j1.setText(a);  j2.setText(b);  j3.setText(c);  j4.setText(d);
	        	          c3[i]=rs.getString("Correct_Answer");




		     	}
				catch(Exception e1)
		        {
			        e1.printStackTrace();
		        }

						b3.addActionListener(this);
           			    b4.addActionListener(this);
           				b5.addActionListener(this);
            			p2.setVisible(true);

			         	f2.setVisible(true);
	                    f1.setVisible(false);
		}



	if(str.equals("Next"))
	{
		 			if(j1.isSelected())
	    			{
	    				a1[i]="A";
	    			}

	    			if(j2.isSelected())
	    			{
	    				a1[i]="B";
	    			}

	    			if(j3.isSelected())
	    			{
	    				a1[i]="C";

	    			}
	    			if(j4.isSelected())
	    			{
	 					a1[i]="D";

	    			}

	    		if(a1[i+1]==null)
	    		{
	    			bg.clearSelection();
					j1.setSelected(false);
					j2.setSelected(false);
					j3.setSelected(false);
					j4.setSelected(false);
	    		}
		b3.setEnabled(true);

		 ++i;


		  			   if(a1[i]=="A")
	                   {
	                   	j1.setSelected(true);
	                   }
	                   if(a1[i]=="B")
	                   {
	                   	j2.setSelected(true);
	                   }
	                   if(a1[i]=="C")
	                   {
	                   	j3.setSelected(true);
	                   }
	                   if(a1[i]=="D")
	                   {
	                   	j4.setSelected(true);
	                   }




	                  	if(reviewClicked==false)
								 {
	                   		try
							{

								 rs=stmt.executeQuery("SELECT * FROM Questions");
								 if(arr1[i]==20)
								 {


								 }


								 rs.absolute(arr1[i]);
		                	      String question=rs.getString("Question");
	                              String a=rs.getString("A");   String b=rs.getString("B");  String c=rs.getString("C"); String d=rs.getString("D");
		    			  			int p=i+1;
		    			  			System.out.println(i+"  "+p+" "+arr1[i]);
		    			   		  String q="Q."+p+question;

	        					  l1.setText(q);
	        	                  j1.setText(a);  j2.setText(b);  j3.setText(c);  j4.setText(d);
	        	                  c3[i]=rs.getString("Correct_Answer");
	        	                	if(p==20)
	        	                	{
	        	                		b4.setEnabled(false);
	        	                	}



								 }


					catch(Exception e1)
		             {
			            e1.printStackTrace();
		             }

			}
				else
				{
						try
							{
								 rs=stmt.executeQuery("SELECT * FROM Questions");

								  rs.absolute(arr1[i]);

		                	      String question=rs.getString("Question");
	                              String a=rs.getString("A");   String b=rs.getString("B");  String c=rs.getString("C"); String d=rs.getString("D");

		    			  		 int p=i+1;
		    			   		  String q="Q."+p+question;
	        					  l1.setText(q);
	        	                  j1.setText(a);  j2.setText(b);  j3.setText(c);  j4.setText(d);
	        	                  c3[i]=rs.getString("Correct_Answer");
	        	                  if(a1[i-1]!=null || a1[i-1]==null)
	        	                  {
	        	                  	j1.setForeground(Color.black);
	        	                  	j2.setForeground(Color.black);
	        	                  	j3.setForeground(Color.black);
	        	                  	j4.setForeground(Color.black);

	        	                  }
	        	                   if(a1[i]==null)
	        	       				  {
	        	         				if(c3[i].equals("A"))
	    	 							j1.setForeground(Color.green);
	    	 							if(c3[i].equals("B"))
	    	 							j2.setForeground(Color.green);
	    	 							if(c3[i].equals("C"))
	    	 							j3.setForeground(Color.green);
	    	 							if(c3[i].equals("D"))
	    	 							j4.setForeground(Color.green);
	        	        			 }
	        	        			 if(a1[i]!=null)
	        	        			 {
	        	         				if(a1[i].equals(c3[i]))
	        	         					{
	        	         				if(c3[i].equals("A"))
	    	 							j1.setForeground(Color.green);
	    	 							if(c3[i].equals("B"))
	    	 							j2.setForeground(Color.green);
	    	 							if(c3[i].equals("C"))
	    	 							j3.setForeground(Color.green);
	    	 							if(c3[i].equals("D"))
	    	 							j4.setForeground(Color.green);
	        	         				}
	        	         				else
	        	         				{
	        	         					if(!a1[i].equals(c3[i]))
	        	         					{
	        	      							if(a1[i].equals("A"))
	    	 									{
	    	 									j1.setForeground(Color.red);
	    	 									if(c3[i].equals("A"))
	    	 									j1.setForeground(Color.green);
	    	 									if(c3[i].equals("B"))
	    	 									j2.setForeground(Color.green);
	    	 									if(c3[i].equals("C"))
	    	 									j3.setForeground(Color.green);
	    	 									if(c3[i].equals("D"))
	    	 									j4.setForeground(Color.green);
	    	 									}
	    	 									if(a1[i].equals("B"))
	    	 									{
	    	 									j2.setForeground(Color.red);
	    	 									if(c3[i].equals("A"))
	    	 									j1.setForeground(Color.green);
	    	 									if(c3[i].equals("B"))
	    	 									j2.setForeground(Color.green);
	    	 									if(c3[i].equals("C"))
	    	 									j3.setForeground(Color.green);
	    	 									if(c3[i].equals("D"))
	    	 									j4.setForeground(Color.green);
	    	 									}
	    	 									if(a1[i].equals("C"))
	    	 									{
	    	 									j3.setForeground(Color.red);
	    	 									if(c3[i].equals("A"))
	    	 									j1.setForeground(Color.green);
	    	 									if(c3[i].equals("B"))
	    	 									j2.setForeground(Color.green);
	    	 									if(c3[i].equals("C"))
	    	 									j3.setForeground(Color.green);
	    	 									if(c3[i].equals("D"))
	    	 									j4.setForeground(Color.green);
	    	 									}

	    	 									if(a1[i].equals("D"))
	    	 									{
	    	 									j4.setForeground(Color.red);
	    	 									if(c3[i].equals("A"))
	    	 									j1.setForeground(Color.green);
	    	 									if(c3[i].equals("B"))
	    	 									j2.setForeground(Color.green);
	    	 									if(c3[i].equals("C"))
	    	 									j3.setForeground(Color.green);
	    	 									if(c3[i].equals("D"))
	    	 									j4.setForeground(Color.green);
	    	 									}
	        	         					}
	        	         				}

	        	        			 }
	        	                	if(p==20)
	        	                	{
	        	                		b4.setEnabled(false);
	        	                	}

	        	         }


					catch(Exception e1)
		             {
			            e1.printStackTrace();
		             }

				}
		     cl.next(p2);
		 }

   if(str.equals("Prev"))
	{
					if(j1.isSelected())
	    			{
	    				a1[i]="A";
	    			}

	    			if(j2.isSelected())
	    			{
	    				a1[i]="B";
	    			}

	    			if(j3.isSelected())
	    			{
	    				a1[i]="C";

	    			}
	    			if(j4.isSelected())
	    			{
	 					a1[i]="D";

	    			}

	    			b4.setEnabled(true);
			--i;

					   if(a1[i]==null)
					   {
					   	System.out.println(i+a1[i]);
					   	bg.clearSelection();
					   	j1.setSelected(false);
					   	j2.setSelected(false);
					  // 	j3.setSelected(true);
					   	j4.setSelected(false);
					   }

			 		   if(a1[i]=="A")
	                   {
	                   	j1.setSelected(true);
	                   }
	                   if(a1[i]=="B")
	                   {
	                   	j2.setSelected(true);
	                   }
	                   if(a1[i]=="C")
	                   {
	                   	j3.setSelected(true);
	                   }
	                   if(a1[i]=="D")
	                   {
	                   	j4.setSelected(true);
	                   }


	            if(reviewClicked==false)
				 {
	          		try
					{
					 rs=stmt.executeQuery("SELECT * FROM Questions");
		           	 	rs.absolute(arr1[i]);
	                  	 String Qno=rs.getString("Question_No");
	                  	  int q1=Integer.parseInt(Qno);
		                  q1=i+1;

		                  String question=rs.getString("Question");
	        	          String q="Q."+q1+question;
	                      String a=rs.getString("A");   String b=rs.getString("B");  String c=rs.getString("C"); String d=rs.getString("D");
	        			  l1.setText(q);
	        	          j1.setText(a);  j2.setText(b);  j3.setText(c);  j4.setText(d);
	        	          c3[i]=rs.getString("Correct_Answer");
	        	          if(i==0)
	        	          {
	        	          	b3.setEnabled(false);
	        	          }


				}
					catch(Exception e1)
		             {
			            e1.printStackTrace();
		             }
				 }
				 else
				 {
				 	try
					{
					 //rs=stmt.executeQuery("SELECT * FROM Questions");
		           	 rs.absolute(arr1[i]);
	                  //	System.out.println("Mohit Bansal");
	                  	  String Qno=rs.getString("Question_No");
	                  	  int q1=Integer.parseInt(Qno);
		                  q1=i+1;
		                  String question=rs.getString("Question");
	        	          String q="Q."+q1+question;
	                      String a=rs.getString("A");   String b=rs.getString("B");  String c=rs.getString("C"); String d=rs.getString("D");
	        			  l1.setText(q);
	        	          j1.setText(a);  j2.setText(b);  j3.setText(c);  j4.setText(d);
	        	          c3[i]=rs.getString("Correct_Answer");
	        	          if(a1[i+1]!=null || a1[i+1]==null)
	        	                  {
	        	                  	j1.setForeground(Color.black);
	        	                  	j2.setForeground(Color.black);
	        	                  	j3.setForeground(Color.black);
	        	                  	j4.setForeground(Color.black);

	        	                  }
	        	                   if(a1[i]==null)
	        	       				  {
	        	         				if(c3[i].equals("A"))
	    	 							j1.setForeground(Color.green);
	    	 							if(c3[i].equals("B"))
	    	 							j2.setForeground(Color.green);
	    	 							if(c3[i].equals("C"))
	    	 							j3.setForeground(Color.green);
	    	 							if(c3[i].equals("D"))
	    	 							j4.setForeground(Color.green);
	        	        			 }
	        	        			 if(a1[i]!=null)
	        	        			 {
	        	         				if(a1[i].equals(c3[i]))
	        	         					{
	        	         				if(c3[i].equals("A"))
	    	 							j1.setForeground(Color.green);
	    	 							if(c3[i].equals("B"))
	    	 							j2.setForeground(Color.green);
	    	 							if(c3[i].equals("C"))
	    	 							j3.setForeground(Color.green);
	    	 							if(c3[i].equals("D"))
	    	 							j4.setForeground(Color.green);
	        	         				}
	        	         				else
	        	         				{
	        	         					if(!a1[i].equals(c3[i]))
	        	         					{
	        	      							if(a1[i].equals("A"))
	    	 									{
	    	 									j1.setForeground(Color.red);
	    	 									if(c3[i].equals("A"))
	    	 									j1.setForeground(Color.green);
	    	 									if(c3[i].equals("B"))
	    	 									j2.setForeground(Color.green);
	    	 									if(c3[i].equals("C"))
	    	 									j3.setForeground(Color.green);
	    	 									if(c3[i].equals("D"))
	    	 									j4.setForeground(Color.green);
	    	 									}
	    	 									if(a1[i].equals("B"))
	    	 									{
	    	 									j2.setForeground(Color.red);
	    	 									if(c3[i].equals("A"))
	    	 									j1.setForeground(Color.green);
	    	 									if(c3[i].equals("B"))
	    	 									j2.setForeground(Color.green);
	    	 									if(c3[i].equals("C"))
	    	 									j3.setForeground(Color.green);
	    	 									if(c3[i].equals("D"))
	    	 									j4.setForeground(Color.green);
	    	 									}
	    	 									if(a1[i].equals("C"))
	    	 									{
	    	 									j3.setForeground(Color.red);
	    	 									if(c3[i].equals("A"))
	    	 									j1.setForeground(Color.green);
	    	 									if(c3[i].equals("B"))
	    	 									j2.setForeground(Color.green);
	    	 									if(c3[i].equals("C"))
	    	 									j3.setForeground(Color.green);
	    	 									if(c3[i].equals("D"))
	    	 									j4.setForeground(Color.green);
	    	 									}

	    	 									if(a1[i].equals("D"))
	    	 									{
	    	 									j4.setForeground(Color.red);
	    	 									if(c3[i].equals("A"))
	    	 									j1.setForeground(Color.green);
	    	 									if(c3[i].equals("B"))
	    	 									j2.setForeground(Color.green);
	    	 									if(c3[i].equals("C"))
	    	 									j3.setForeground(Color.green);
	    	 									if(c3[i].equals("D"))
	    	 									j4.setForeground(Color.green);
	    	 									}
	        	         					}
	        	         				}

	        	        			 }
	        	          if(q1==1)
	        	          {
	        	          	b3.setEnabled(false);
	        	          }


				}
					catch(Exception e1)
		             {
			            e1.printStackTrace();
		             }

				 }
			cl.previous(p2);

		}


	if(str.equals("Exit"))
	{
		f1.setVisible(false);
	}


	if(str.equals("Finish"))
	{

		if(finish==0)
		{



				if(j1.isSelected())
	    			{
	    				a1[i]="A";
	    			}

	    			if(j2.isSelected())
	    			{
	    				a1[i]="B";
	    			}

	    			if(j3.isSelected())
	    			{
	    				a1[i]="C";

	    			}
	    			if(j4.isSelected())
	    			{
	 					a1[i]="D";

	    			}

		if(reviewClicked)
		{
			f2.setVisible(false);
			f3.setVisible(true);
		}
		else
		{
			int n2=JOptionPane.showConfirmDialog(f2,"Do you Want to Finish","Alert",JOptionPane.YES_NO_OPTION);
			System.out.println(n2);

			if(n2==1)
			{
				f3.setVisible(false);
				f2.setVisible(true);
			}
		}

			for(i=0;i<20;i++)
			{

	    	    	try
	    	    	{
	    	    		System.out.println(a1[i]);
	    	    		if(a1[i]==null)
	    	    		{

	    	    		}
	    	    		else
	    	    			if(c3[i].equals(a1[i]))
	    	        	{
	    				correct++;
//	    				t2.setText(correct+"");
//	    			  	m=20-(correct+wrong);
//	    			  	t3.setText(wrong+"");
//	    			    t4.setText(m+"");
	    			    }

//	    	        	else
//	    	        		 if(!j1.isSelected()&!j2.isSelected()&!j3.isSelected()&!j4.isSelected())
//	    					{
//	    						m=20-(correct+wrong);
//	    						t4.setText(m+"");
//	    					}

	    			else
	    			 {
	    				   wrong++;
//	    				   t3.setText(wrong+"");
//	    				   m=20-(correct+wrong);
//	    				   t4.setText(m+"");
//	    				   t2.setText(correct+"");
//
	    			 }
	    			 	m=20-(correct+wrong);
	    			 	t2.setText(correct+"");
	    				t3.setText(wrong+"");
	    				 t4.setText(m+"");

	    	    	}
	    	    	catch(NullPointerException e3)
	    	    	{
	    	    		e3.printStackTrace();
	    	    	}
	    	    	jbr.setValue(correct);

	   		  	}
	   		  	finish++;
		}

	    t1.setText("20");
		t1.setEditable(false);

	    f2.setVisible(false);
	    f3.setVisible(true);

	}
	if(str.equals("Exit"))
	{
		f3.setVisible(false);
	}


	if(str.equals("Review"))
	{
		b4.setEnabled(true);
		i=0;
		if(a1[i]==null)
		{
			System.out.println("Mohit Bansal");
			bg.clearSelection();
			j1.setSelected(false);
			j2.setSelected(false);
			j3.setSelected(false);
			j4.setSelected(false);
		}
		else
		{
			System.out.println("Mohit Bansalggggggggggggggggg");
  	   if(a1[i]=="A")
       {
       	j1.setSelected(true);
       }
       if(a1[i]=="B")
       {
       	j2.setSelected(true);
       }
       if(a1[i]=="C")
       {
       	j3.setSelected(true);
       }
       if(a1[i]=="D")
       {
       	j4.setSelected(true);
       }
		}

	f2.setVisible(true);
	f3.setVisible(false);
	reviewClicked=true;
   try
    {
	     rs=stmt.executeQuery("SELECT * FROM Questions");
       rs.absolute(arr1[i]);

			  String Qno=rs.getString("Question_No");
				  String question=rs.getString("Question");
  			String q="Q."+1+" "+question;

         a=rs.getString("A");   b=rs.getString("B");   c=rs.getString("C"); d=rs.getString("D");
         	 l1.setText(q);
         j1.setText(a);  j2.setText(b);  j3.setText(c);  j4.setText(d);
         if(a1[i]==null)
         {
         		if(c3[i].equals("A"))
						j1.setForeground(Color.green);
						if(c3[i].equals("B"))
						j2.setForeground(Color.green);
						if(c3[i].equals("C"))
						j3.setForeground(Color.green);
						if(c3[i].equals("D"))
						j4.setForeground(Color.green);
         }
         if(a1[i]!=null)
         {
         	if(a1[i].equals(c3[i]))
         	{
         				if(c3[i].equals("A"))
						j1.setForeground(Color.green);
						if(c3[i].equals("B"))
						j2.setForeground(Color.green);
						if(c3[i].equals("C"))
						j3.setForeground(Color.green);
						if(c3[i].equals("D"))
						j4.setForeground(Color.green);
         	}
         	else
         	{
         		if(!a1[i].equals(c3[i]))
         		{
      				if(a1[i].equals("A"))
					{
						j1.setForeground(Color.red);
						if(c3[i].equals("A"))
						j1.setForeground(Color.green);
						if(c3[i].equals("B"))
						j2.setForeground(Color.green);
						if(c3[i].equals("C"))
						j3.setForeground(Color.green);
						if(c3[i].equals("D"))
						j4.setForeground(Color.green);
					}
					if(a1[i].equals("B"))
					{
 					j2.setForeground(Color.red);
 					if(c3[i].equals("A"))
 					j1.setForeground(Color.green);
 					if(c3[i].equals("B"))
 					j2.setForeground(Color.green);
 					if(c3[i].equals("C"))
 					j3.setForeground(Color.green);
 					if(c3[i].equals("D"))
 					j4.setForeground(Color.green);
 						}
 				if(a1[i].equals("C"))
 				{
 					j3.setForeground(Color.red);
 					if(c3[i].equals("A"))
 					j1.setForeground(Color.green);
 					if(c3[i].equals("B"))
 					j2.setForeground(Color.green);
 					if(c3[i].equals("C"))
 					j3.setForeground(Color.green);
 					if(c3[i].equals("D"))
 					j4.setForeground(Color.green);
 				}

 			if(a1[i].equals("D"))
 			{
 				j4.setForeground(Color.red);
 				if(c3[i].equals("A"))
 				j1.setForeground(Color.green);
 				if(c3[i].equals("B"))
 				j2.setForeground(Color.green);
 				if(c3[i].equals("C"))
 				j3.setForeground(Color.green);
 				if(c3[i].equals("D"))
 				j4.setForeground(Color.green);
 			}

  	     }
  	   }
    }
  }
	catch(Exception e1)
        {
	        e1.printStackTrace();
        }
	}
}
public static void main(String args[])
	{
		FE fe=new FE();
	}
}







