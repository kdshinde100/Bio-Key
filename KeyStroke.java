import java.lang.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

class KeyStroke extends JFrame implements ActionListener
{
	public String[][][] allkeystrokestore; // = new String[12][20][8]; 
	int passLength=0;
	String uname,password;
	String str="",text="keystrokedynamics"; // modification for using text from textarea 2
	JTextField t1,t2;
	JButton recordstatus;
	JButton b1,b2,b3,reset,count,l2;
	long milli1=0,milli2=0,milli3=0,pp=0,rr=0,pt=0,w22,r22,w,fc,lc;
	double typingrate;
	long chcnt=0,flag=0;	
	int totalkeycount=0,bscount=0,errors=0,overallk=0,keypre=-1,keyreal=0,x=0;// for counting total number of keys pressed  and total number of backspaces
	Highlighter highlighter;
	HighlightPainter painterc,painterw;
	String[][] keystore;// = new String[19][8];
	public static int keytrackcnt=-1;
	public static int iterationcount=0;
	public String wordanalyzer="";
	KeyStroke(String un, String pass)
	{
		
		super();
		uname=un;
		password = pass;
		text = password;
		passLength = password.length();
		allkeystrokestore = new String[13][passLength + 3][8];
		keystore = new String[passLength + 2][8];	 
		keyreal = passLength - 1;
		setLayout(null);
		t1=new JTextField();
		t2=new JTextField();
		l2=new JButton("0");
		b1=new JButton("Start");
		b2=new JButton("Stop");
		b3=new JButton("Submit");
		count=new JButton("Count");
		recordstatus=new JButton("Record Stored");
		reset=new JButton("Reset");	
		b1.setBounds(260,140,80,30);
		b3.setBounds(150,265,80,30);
		reset.setBounds(370,265,80,30);
		t1.setBounds(120,80,360,35);
		t1.setHorizontalAlignment(JTextField.CENTER);
		t2.setBounds(120,200,360,35);
		t1.setText(password);
		count.setBounds(440,40,150,30);
		recordstatus.setBounds(120,310,360,35);
		l2.setBounds(260,30,80,30);
		count.setOpaque(true);
		l2.setOpaque(true);
		t1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		count.setFont(new Font("Times New Roman", Font.BOLD, 18));
		count.setBackground(Color.YELLOW);
		count.setEnabled(false);
		recordstatus.setFont(new Font("Times New Roman", Font.BOLD, 18));
		recordstatus.setBackground(Color.GREEN);
		recordstatus.setForeground(Color.RED);
		//recordstatus.setEnabled(false);
		recordstatus.setVisible(false);
		l2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		l2.setBackground(Color.PINK);
		l2.setEnabled(false);
		b2.setBounds(500,203,80,30);
      		highlighter = t1.getHighlighter();
      		painterc = new DefaultHighlighter.DefaultHighlightPainter(Color.lightGray);
		painterw = new DefaultHighlighter.DefaultHighlightPainter(Color.magenta);
		add(t1);
		add(b1);
		//add(count);
		add(l2);
		add(b2);
		add(b3);
		add(t2);
                add(recordstatus);
		add(reset);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		reset.addActionListener(this);
		b2.setVisible(false);
		//b3.setVisible(false);
		//reset.setVisible(false);
		b3.setEnabled(false);
		reset.setEnabled(false);
		t1.setEditable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,400);
		t2.setEditable(false);
		setResizable(false);
		this.setTitle("Keystroke Pattern Recongnition");
		str = password;
		//Random no = new Random();
		//this.para(no.nextInt(5));
	}

public void actionPerformed(ActionEvent ae) 
{
	if(ae.getActionCommand()=="Start")
	{
		t2.setEditable(true);
		t2.requestFocus();
		
	 	t2.addKeyListener(new KeyAdapter() {
				int storecnt;
      				public void keyTyped(KeyEvent e) {
					milli1=System.currentTimeMillis();
					//System.out.println("Inside "+e.getKeyChar()+" Keytyped");
   				}

				public void keyPressed(KeyEvent e) {
						//keypre++;
						System.out.println("Inside "+e.getKeyChar()+" KeyPressed");
					System.out.println(e.getKeyCode());	
					//System.out.println(e.getAscii());
					int ascii_value = (int) e.getKeyChar();
					System.out.println("Ascii value - "+ascii_value);
					wordanalyzer= wordanalyzer+Character.toString(e.getKeyChar());
					//keytrackcnt++;
					//storecnt=keytrackcnt;
					storecnt=keypre;
					overallk++;
					milli2 =System.currentTimeMillis();chcnt++;
					if(milli3==0)
					{
			
						w=0;
						w22=milli2;
						pp=milli2-w22;
						fc=milli2;
                                           					}
					else
					{
						w=milli2-milli3;
						pp=milli2-w22;
					}
		System.out.println();	
		
		if(KeyEvent.getKeyText(e.getKeyCode()).equals("Backspace"))
		{
				System.out.println("KP="+KeyEvent.getKeyText(e.getKeyCode())); // modification 
				//System.out.println("F FT->"+w);
			bscount++;
			if(totalkeycount==0)
			totalkeycount=0;
			else
			totalkeycount=totalkeycount-1;

		}
		else if(ascii_value>=32 && ascii_value<=126)
		{
					System.out.println("KP="+e.getKeyChar()); // modification 
			keytrackcnt++;
			keypre++;

			char temp= e.getKeyChar();
			int askiir = (int) str.charAt(totalkeycount);
			int askiiw = (int) temp;
			//System.out.println("Read - "+askiir+"\t written - "+askiiw);
			try{
			totalkeycount=totalkeycount+1;
			if(askiir==askiiw)
			{
				
				highlighter.addHighlight(totalkeycount-1, totalkeycount, painterc );
			}else{

				errors++;
				reset.setEnabled(true);
				highlighter.addHighlight(totalkeycount-1, totalkeycount, painterw );					
			    }	
					}catch(Exception excer){System.out.println("Highlight Painter error - "+excer);}			
			
			

		}
		else
		{
			/*
				System.out.println("KP="+e.getKeyChar()); // modification 
			
			char temp= e.getKeyChar();
			int askiir = (int) str.charAt(totalkeycount);
			int askiiw = (int) temp;
			//System.out.println("Read - "+askiir+"\t written - "+askiiw);
			try{
			totalkeycount=totalkeycount+1;
			if(askiir==askiiw)
			{
				
				highlighter.addHighlight(totalkeycount-1, totalkeycount, painterc );
			}else{

				errors++;
				reset.setEnabled(true);
				highlighter.addHighlight(totalkeycount-1, totalkeycount, painterw );					
			    }	
					}catch(Exception excer){System.out.println("Highlight Painter error - "+excer);}			
				*/			
					
		}		


		} // used for calculating typing speed 

		
		public void keyReleased(KeyEvent e) {
			//keyreal--;
			System.out.println("Inside "+e.getKeyChar()+" KeyReleased");

			milli3 =System.currentTimeMillis();
			long b=milli3-milli2;
			pt=milli3-milli1;
			if(w==0)
			{
				r22=milli3;
				rr=0;
			}
			else{rr=milli3-r22;}
			System.out.println("FT->"+w);
			System.out.println("DT->"+b);
			System.out.println("PT->"+pt);
			System.out.println("PP->"+pp);
			System.out.println("RR->"+rr);
		//				if(wordanalyzer.length()==((t1.getText()).toString()).length())
				//System.out.println("Flag = "+flag);
				System.out.println("Keytrackcnt = "+keytrackcnt);
				
			/*	if((keytrackcnt==(strlen-1))&&(keypre==(strlen-1))&&(keyreal==-1))
				{
					flag=1;
				}*/
							
//				System.out.println("Flag = "+flag+"Keytrackcount "+keytrackcnt+"keypress"+keypre+" keyrelease "+keyreal);
			/*----------------------Keystroke Dynamics Storing Code-------------- */
			

					/**********Finding Index of character***********/
					int ascii_value = (int) e.getKeyChar();
					//text=password;
					if(ascii_value>=32 && ascii_value<=126)
					{
					keyreal--;
					System.out.println("Flag = "+flag+"Keytrackcount "+keytrackcnt+"keypress"+keypre+" keyrelease "+keyreal);
					int chacount;	
					for(int i=0;i<(passLength);i++)
						{	
							//System.out.println(""+text.charAt(i)+"||"+e.getKeyChar());
							if(text.charAt(i)==e.getKeyChar())
							{
								//System.out.println("Match Found - "+i);
								if(keystore[i][0]==null){
						
						keystore[i][0]=Character.toString(e.getKeyChar()); // Key pressed
						//System.out.println("KEY "+keystore[i][0]);
						keystore[i][1]=Long.toString(w); // flight time (FT)
						//System.out.println("FLIGHT TIME "+keystore[i][1]);			
			keystore[i][2]=Long.toString(b); // dwell time (DT)
			keystore[i][3]=Long.toString(pt); // Press time (PT)
			keystore[i][4]=Long.toString(pp); // press to press  time (PP)
			keystore[i][5]=Long.toString(rr); // release to release time (RR)
			break;						}
								else{//System.out.println("Check else");
									}
							}
							
						}  
					}
			w22=milli2;
			r22=milli3;
			
				if((keytrackcnt==(passLength-1))&&(keypre==(passLength-1))&&(keyreal==-1))
				{
					flag=1;
				}
			if(flag==1){
			//System.out.println("Inside Key Released");
		t2.removeKeyListener( t2.getKeyListeners()[ 0 ] );
 		double tyra=(chcnt*1.0/(milli3-fc))*1000;
		int a = (int) (tyra + 0.5);
		System.out.println();
		System.out.println("Typing Rate->"+a+" CPS");
		double accuracyrate= (((overallk-errors)*1.0)/overallk)*100;
		int acr=(int)(accuracyrate + 0.50);
		System.out.println("Accuracy Rate -> "+acr);


/*		
		/***************Calculating Mean & SD***************/
/*		// For mean purpose separate for every parameter
		double sum1=0.0;
		double mean1=0.0;
		keystore[(passLength)][0]="Mean";
		keystore[(passLength + 1)][0]="Standard Deviation";
			for(int i=1;i<6;i++)
			{sum1=0;
				for(int j=0;j<(passLength);j++)
				{
					sum1=sum1 + Long.parseLong(keystore[j][i]);
				}
			mean1 = (sum1*1.0)/(passLength);
			keystore[passLength][i]=Double.toString(mean1);				

			}
		double sumvar1=0.0;
		double variance1, stdev1,mean;
		// for standard variance purpose vertical  separate for every parameter
			for(int i=1;i<6;i++)
			{
				sumvar1=0.0;
				for(int j=0;j<(passLength);j++)
				{
				sumvar1=sumvar1 + (((Long.parseLong(keystore[j][i])*1.0)-mean1)*((Long.parseLong(keystore[j][i])*1.0)-mean1));	
				}
				variance1=sumvar1/(passLength -1);
				stdev1=Math.sqrt(variance1);
				keystore[(passLength+1)][i]=Double.toString(stdev1);	
			}
			
		// mean for combination of parameters	
			
				double sum2=0.0;
		double mean2=0.0;
				double sumvar2=0.0;
		double variance2, stdev2;
				for(int i=0;i<(passLength);i++)
			{sum2=0;
				for(int j=1;j<6;j++)
				{
					sum2=sum2 + Long.parseLong(keystore[i][j]);
				}
			mean2 = (sum2*1.0)/5;
			keystore[i][6]=Double.toString(mean2);				

			}	
		// for standard variance purpose horizontal
			for(int i=0;i<(passLength);i++)
			{
				sumvar2=0.0;
				for(int j=1;j<6;j++)
				{
				double x=((Long.parseLong(keystore[i][j]))-mean2);
				double fakepro = ((Double.parseDouble(keystore[i][j]))-mean2)*((Double.parseDouble(keystore[i][j]))-mean2);
				sumvar2=sumvar2+fakepro;
				//System.out.println("OV "+Long.parseLong(keystore[i][j])+"|Diff"+x+"|diffsq "+fakepro);
				//System.out.println("Sqaue"+fakepro);	
				//sumvar=sumvar + ((((Long.parseLong(keystore[i][j]))-mean)*((Long.parseLong(keystore[i][j]))-mean)));	
				}
				//System.out.println("Sum of variable"+sumvar);
				variance2=sumvar2/4;
				//System.out.println("Variance - "+variance2);
				stdev2=Math.sqrt(variance2);
				//System.out.println("SqSum - "+sumvar2+"|var "+variance2+" std "+stdev2);
				keystore[i][7]=Double.toString(stdev2);	
			}			*/
				/***********************************************/


		double sum=0,mean,vari,std;

			for(int i=1;i<6;i++) // vertical individual
			{
				sum=0;
				for(int j=0;j<(passLength);j++)
				{
					sum=sum+(Long.parseLong(keystore[j][i])*1.0);
				}
				mean=sum/(passLength);
				keystore[(passLength)][0]="Mean";
				keystore[(passLength)][i]=Double.toString(mean);
				System.out.println("Mean"+mean);
			}

			sum=0;
			for(int i=1;i<6;i++) // vertical individual
			{
				sum=0;
				for(int j=0;j<(passLength);j++)
				{
					sum=sum+Math.pow((((Long.parseLong(keystore[j][i]))*1.0)-(Double.parseDouble(keystore[passLength][i]))),2);
				}
				keystore[(passLength+1)][0]="STD";
				vari=(sum*1.0)/(passLength -1);
				std=Math.sqrt(vari);
				keystore[(passLength +1)][i]=Double.toString(std);
				System.out.println("STD"+std);
			}			

			// for horizontal purpose			
			sum=0;mean=0;

				for(int i=0;i<(passLength);i++) // vertical individual
			{
				sum=0;
				for(int j=1;j<6;j++)
				{
					sum=sum+(Long.parseLong(keystore[i][j])*1.0);
				}
				mean=sum/5;
				//keystore[17][0]="Mean";
				keystore[i][6]=Double.toString(mean);
				System.out.println("Mean"+mean);
			}
			
			sum=0;
			for(int i=0;i<(passLength);i++) // vertical individual
			{
				sum=0;
				for(int j=1;j<6;j++)
				{
					sum=sum+Math.pow((((Long.parseLong(keystore[i][j]))*1.0)-(Double.parseDouble(keystore[i][6]))),2);
				}
				//keystore[18][0]="STD";
				vari=(sum*1.0)/4;
				std=Math.sqrt(vari);
				keystore[i][7]=Double.toString(std);
				System.out.println("STD"+std);
			}	










		
		/******************Displaying Data**************/
		System.out.println("\nKey  \tFT  \tDT  \tPT  \tPP  \tRR\tMean \t\tSTD");

			

				for(int i=0;i<(passLength+2);i++)
				{
					System.out.println();
					for(int j=0;j<8;j++)
					{
					 System.out.print(""+keystore[i][j]+"\t");			
					}
				}
		/***********************************************/
		//System.out.println("Errors = "+errors+" \nTotal Key = "+totalkeycount+"\nBackspace= "+bscount+"\nOverall K ="+overallk);


		//t2.removeKeyListener(t2.getKeyListeners());
		t2.setEditable(false);										
					
					//MAKE BUTTON VISIBLE ACCORDING TO CHARACTER
					if(acr==100)
					{
						//b3.setVisible(true);
						b3.setEnabled(true);
					}
					else{//reset.setVisible(true);
						reset.setEnabled(true);
						
					}
						
					}
			

			}
	
		    });
			System.out.println("Start ");
		}

	if(ae.getActionCommand()=="Stop")
	{
		flag=1;
		t2.removeKeyListener( t2.getKeyListeners()[ 0 ] );
 		double tyra=(chcnt*1.0/(milli3-fc))*1000;
		int a = (int) (tyra + 0.5);
		System.out.println();
		System.out.println("Typing Rate->"+a+" CPS");
		double accuracyrate= (((overallk-errors)*1.0)/overallk)*100;
		int acr=(int)(accuracyrate + 0.50);
		System.out.println("Accuracy Rate -> "+acr);
		
		/******************Displaying Data**************/
			System.out.println("\nKey  \tFT  \tDT  \tPT  \tPP  \tRR");
				for(int i=0;i<17;i++)
				{
					System.out.println();
					for(int j=0;j<6;j++)
					{
					 System.out.print(""+keystore[i][j]+"\t");			
					}
				}
		/***********************************************/
		//System.out.println("Errors = "+errors+" \nTotal Key = "+totalkeycount+"\nBackspace= "+bscount+"\nOverall K ="+overallk);


		//t2.removeKeyListener(t2.getKeyListeners());
		t2.setEditable(false);
	}

	if(ae.getActionCommand()=="Reset")
	{
		x++;
		
		if(x==1)
		{
			reset.setEnabled(false);
			x=0;
		}
		//t2.setEditable(true);
		t2.setText("");
		t2.removeKeyListener( t2.getKeyListeners()[ 0 ] );
		// code for removing all the highlights and modification to make it as starting
		t2.setEditable(false);
		milli1=0;milli2=0;milli3=0;pp=0;rr=0;pt=0;
			chcnt=0;flag=0;	
			totalkeycount=0;bscount=0;errors=0;overallk=0;keypre=-1;keyreal=(passLength -1);				
			for(int i=0;i<(passLength +2);i++){
			for(int j=0;j<8;j++){	
			keystore[i][j]=null;}}			
			keytrackcnt=-1;
			t2.setEditable(false);
			str=password;
			t1.setText(str);
			//Random no = new Random();
			//this.para(no.nextInt(5));
		
			/****************************************/

			
	}
	
	if(ae.getActionCommand()=="Submit")
	{x++;
		if(x==1)
		{
			b3.setEnabled(false);
			x=0;
		}
		
		//iterationcount++;		
		l2.setText(Integer.toString(iterationcount+1));
		t2.setText("");
		
		
		// code for storing data alongwith iteration		
		/******************************************/
		//allkeystrokestore
			
			for(int i=0;i<(passLength +1);i++)
			{
				for(int j=0;j<8;j++)
				{
					allkeystrokestore[iterationcount][i][j]=keystore[i][j];
				}
			}
		
			iterationcount++;
			
			double allsum=0,allmean=0;
			if(iterationcount==10)
			{
				// mean
				for(int i=1;i<6;i++)	//	parameters
				{	
					for(int j=0;j<(passLength);j++)	// characters
					{allsum=0;
						for(int k=0;k<10;k++)	// iterations
						{
						allsum=allsum+(Long.parseLong(allkeystrokestore[k][j][i])*1.0);
						}
						allmean=(allsum*1.0)/10;
						allkeystrokestore[10][j][i]=Double.toString(allmean);	
					}
										
				}	
			
				// standard deviation
				double allstd;
				for(int i=1;i<6;i++)
				{	
					for(int j=0;j<(passLength);j++)
					{allsum=0;
						for(int k=0;k<10;k++)
						{
						allsum=allsum+Math.pow((((Long.parseLong(allkeystrokestore[k][j][i]))*1.0)-(Double.parseDouble(allkeystrokestore[10][j][i]))),2);
						}
						allmean=(allsum*1.0)/9;
						allstd=Math.sqrt(allmean);
						allkeystrokestore[11][j][i]=Double.toString(allstd);	
						
					}
										
				}	

								
				for(int i=10;i<12;i++)
				{
					for(int j=0;j<(passLength +1);j++){
						System.out.println();
						for(int k=1;k<8;k++){
								System.out.print("\t "+allkeystrokestore[i][j][k]);
							}
										
					}
	
				}


				recordstatus.setVisible(true);
				b1.setEnabled(false);

			allkeystrokestore[12][0][0] = password;			
				
			/******************************************************/ // Storing data using serialization
				
			try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Kaustubh\\Desktop\\MyKData\\"+uname+".txt"));
			out.writeObject(allkeystrokestore);
			out.flush();
			out.close();
			}catch(Exception err){System.out.println(""+err);}
			/******************************************************/




			}
			/*****************************************/
		
		// code for removing all the highlights and modification to make it as starting	
		/*****************************************/
			milli1=0;milli2=0;milli3=0;pp=0;rr=0;pt=0;
			chcnt=0;flag=0;	
			totalkeycount=0;bscount=0;errors=0;overallk=0;keypre=-1;keyreal=(passLength -1);
		//Highlighter highlighter;
		//HighlightPainter painterc,painterw;
			
			//keystore=null;	
			
			for(int i=0;i<(passLength +2);i++){
			for(int j=0;j<8;j++){	
			keystore[i][j]=null;}}			

		/*	for(int i=0;i<17;i++){System.out.println();
			for(int j=0;j<6;j++){	
			System.out.print("\t"+keystore[i][j]);}}
		*/	keytrackcnt=-1;
			str=password;
			t1.setText(str);
			//Random no = new Random();
			//this.para(no.nextInt(5));
		/****************************************/
			

	}
}

/*

void para(int i)
{
	//String str="";
 	FileReader reader,r1,r2,r3,r4 ;
 	BufferedReader bufferedReader ;
 
	String line;
	switch(i)
	{
		case 0:
				/*
  			try {
				
             			reader = new FileReader("f0.txt");
            			bufferedReader = new BufferedReader(reader);
 					while ((line = bufferedReader.readLine()) != null) {
                			//System.out.println(line);
					str+=line+"\n";
            				}
				t1.setText(password);
            			reader.close();
	
			    			//str="";
 				
				t1.setText(password);
  			      } catch (IOException e) {
      				      e.printStackTrace();
     				
			   }
				

				t1.setText(password);
					break;
		case 1:
			try
			{
				/*
	     			r1 = new FileReader("f0.txt");
           			BufferedReader br1 = new BufferedReader(r1);
 				while ((line = br1.readLine()) != null) {
                			//System.out.println(line);
					str+=line+"\n";
					}
	     			t1.setText(password);
            			r1.close();
				  //  str="";
				

				t1.setText(password); 
  		      } catch (IOException e1) {
            			e1.printStackTrace();
      				  }
			break;
		case 2:
	try
	{
		
		 r2 = new FileReader("f0.txt");
           BufferedReader br2 = new BufferedReader(r2);
 
             
 
            while ((line = br2.readLine()) != null) {
                //System.out.println(line);
		str+=line+"\n";
            }
	     t1.setText(password);
            r2.close();
	 //   str="";
		
		t1.setText(password);
 
        } catch (IOException e2) {
            e2.printStackTrace();
        }
break;
case 3:
	try
	{
	/* r3 = new FileReader("f0.txt");
           BufferedReader br3 = new BufferedReader(r3);
 
             
 
            while ((line = br3.readLine()) != null) {
                //System.out.println(line);
		str+=line+"\n";
            }
	
	    t1.setText(password);
            r3.close();
	  //  str="";
	
		 t1.setText(password);
 
        } catch (IOException e3) {
            e3.printStackTrace();
        }
break;
case 4:
	try
	{
	/*
	 r4 = new FileReader("f0.txt");
           BufferedReader br4 = new BufferedReader(r4);
 
             
 
            while ((line = br4.readLine()) != null) {
                //System.out.println(line);
		str+=line+"\n";
            }
	    t1.setText(password);
            r4.close();
	 //   str="";
		

		t1.setText(password); 
        } catch (IOException e4) {
            e4.printStackTrace();
        }
break;
}
}
*/
	public static void main(String args[])
	{
		//KeyStroke k=new KeyStroke("12");
		//Random no = new Random();
		//k.para(no.nextInt(5));
	}
}