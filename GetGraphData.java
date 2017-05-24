import java.awt.Color; 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.BasicStroke; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;


class Graph_XYLineChart extends ApplicationFrame {

	String [][][] allKeyStrokeStore;
	String [][] keyStore;
	int status = 0,passLength=0;
	JFreeChart xylineChart;
	Graph_XYLineChart(String[][][] allkeystrokestore, String [][] keystore, int status, int size){

		super("Keystroke Patterns");
		allKeyStrokeStore = new String[13][50][8];
		keyStore = new String[size+2][8];
		allKeyStrokeStore = allkeystrokestore;
		keyStore = keystore;
		passLength = size;
		this.status = status;	
		if(status==1){
				 xylineChart = ChartFactory.createXYLineChart(
         "Stored Keystroke Parameter Graph" ,
         "Character Index" ,
         "Mean Time In MilliSeconds" ,
         createDataset(status) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
		}
		if(status==2){
			 xylineChart = ChartFactory.createXYLineChart(
         "Current Keystroke Parameter Graph" ,
         "Character Index" ,
         "Mean Time" ,
         createDataset(status) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
		}
		if(status==3){
		xylineChart = ChartFactory.createXYLineChart(
         "Variation In Keystrokes Parameter Graph" ,
         "Character Index" ,
         "Mean Time" ,
         createDataset(status) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         

		}

		ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 800 , 600 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.GREEN );
      renderer.setSeriesPaint( 2 , Color.YELLOW );
	renderer.setSeriesPaint( 3 , Color.BLACK );
	renderer.setSeriesPaint( 4 , Color.BLUE );
      	renderer.setSeriesStroke( 0 , new BasicStroke( 2.0f ) );
      	renderer.setSeriesStroke( 1 , new BasicStroke( 2.0f ) );
      	renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
      	renderer.setSeriesStroke( 3 , new BasicStroke( 2.0f ) );
	renderer.setSeriesStroke( 4 , new BasicStroke( 2.0f ) );
	plot.setRenderer( renderer ); 
      	setContentPane( chartPanel ); 

			


	}

	
   private XYDataset createDataset(int flag ) {

      final XYSeries fts= new XYSeries( "Flight Time" );          
      final XYSeries dts = new XYSeries( "Dwell Time" );            
      final XYSeries pts = new XYSeries( "Press Time" );          	 
      final XYSeries ppts = new XYSeries( "Press-Press Time" );          
      final XYSeries rrts = new XYSeries( "Release-Release Time" );          
      final XYSeriesCollection dataset = new XYSeriesCollection( );          

	Double tempi;// = new Double(0.0);
	Double tempj ;//= new Double(0.0);		
		if(flag==1){ // code for adding stored keystrokes
		
			for(int i=0;i<(passLength);i++)
			{
				for(int j=1;j<6;j++)
				{
					tempi = 1.0 * i;
					tempj = 1.0 * j;
					switch (j){
						
					case 1:
							fts.add( tempi + 1 , Double.parseDouble(allKeyStrokeStore[10][i][j]));  
							break;
					case 2:
							dts.add( tempi + 1 ,  Double.parseDouble(allKeyStrokeStore[10][i][j]) );  
							break;	
					case 3:
							pts.add(  tempi + 1 ,  Double.parseDouble(allKeyStrokeStore[10][i][j]));  
							break;
					case 4:
							ppts.add(  tempi + 1 ,  Double.parseDouble(allKeyStrokeStore[10][i][j]));  
							break;

					case 5:
							rrts.add(  tempi + 1 ,  Double.parseDouble(allKeyStrokeStore[10][i][j]) );  
							break;

					}
					
				}
			}	
			

		}      

		if(flag==2){ // code for adding current keystrokes 
					
					for(int i=0;i<(passLength);i++)
			{
				for(int j=1;j<6;j++)
				{
					tempi = 1.0 * i;
					tempj = 1.0 * j;
					switch (j){
						
					case 1:
							fts.add( tempi + 1 , Double.parseDouble(keyStore[i][j]));  
							break;
					case 2:
							dts.add( tempi + 1 ,  Double.parseDouble(keyStore[i][j]) );  
							break;	
					case 3:
							pts.add(  tempi + 1 ,  Double.parseDouble(keyStore[i][j]));  
							break;
					case 4:
							ppts.add(  tempi + 1 ,  Double.parseDouble(keyStore[i][j]));  
							break;

					case 5:
							rrts.add(  tempi + 1 ,  Double.parseDouble(keyStore[i][j]) );  
							break;

					}
					
				}
			}	


		}
		if(flag==3){ // code for variation in keystrokes

							for(int i=0;i<(passLength);i++)
			{
				for(int j=1;j<6;j++)
				{
					tempi = 1.0 * i;
					tempj = 1.0 * j;
					switch (j){
						
					case 1:
							fts.add( tempi + 1 , Double.parseDouble(allKeyStrokeStore[10][i][j])-Double.parseDouble(keyStore[i][j]));  
							break;
					case 2:
							dts.add( tempi + 1 , Double.parseDouble(allKeyStrokeStore[10][i][j])- Double.parseDouble(keyStore[i][j]) );  
							break;	
					case 3:
							pts.add(  tempi + 1 , Double.parseDouble(allKeyStrokeStore[10][i][j])- Double.parseDouble(keyStore[i][j]));  
							break;
					case 4:
							ppts.add(  tempi + 1 ,Double.parseDouble(allKeyStrokeStore[10][i][j]) -  Double.parseDouble(keyStore[i][j]));  
							break;

					case 5:
							rrts.add(  tempi + 1 ,Double.parseDouble(allKeyStrokeStore[10][i][j]) -  Double.parseDouble(keyStore[i][j]) );  
							break;

					}
					
				}
			}

		}

	dataset.addSeries( fts );          
       dataset.addSeries( dts );          
       dataset.addSeries( pts );
       dataset.addSeries( ppts );
 	dataset.addSeries( rrts );
	return dataset;
   }

	

}



public class GetGraphData extends JFrame implements ActionListener{

JButton storedgraph,currentgraph, variationgraph;

	String [][][] allKeyStrokeStore;
	String [][] keyStore;
	int passLength=0;
	int status = 0;
	GetGraphData(String[][][] allkeystrokestore, String [][] keystore, int size){
	super();
	setLayout(null);
	allKeyStrokeStore = new String[13][50][8];
	allKeyStrokeStore = allkeystrokestore;
	keyStore = new String[size+2][8];
	keyStore = keystore;
	passLength = size;	
	storedgraph = new JButton("Stored Keystroke Graph");
	currentgraph = new JButton("Current Keystroke Graph");
	variationgraph = new JButton("Variation In Keystrokes Graph");
	storedgraph.setBounds(50,50,250,30);
	currentgraph.setBounds(50,100,250,30);
	variationgraph.setBounds(50,150,250,30);
	add(storedgraph);
	add(currentgraph);
	add(variationgraph);
	storedgraph.addActionListener(this);
	currentgraph.addActionListener(this);
	variationgraph.addActionListener(this);
	this.setSize(350,250);
	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	this.setTitle("Graph View");
	setVisible(true);	
	} 

	public void actionPerformed(ActionEvent ae){

		if(ae.getActionCommand()=="Stored Keystroke Graph")
		{
			status=1;
			Graph_XYLineChart chart = new Graph_XYLineChart(allKeyStrokeStore,keyStore,status,passLength);
			chart.pack( );          
      			RefineryUtilities.centerFrameOnScreen( chart );          
      			chart.setVisible( true ); 
		}
		if(ae.getActionCommand()=="Current Keystroke Graph")
		{
			status=2;
			Graph_XYLineChart chart = new Graph_XYLineChart(allKeyStrokeStore,keyStore,status,passLength);
			chart.pack( );          
      			RefineryUtilities.centerFrameOnScreen( chart );          
      			chart.setVisible( true ); 
		}
		if(ae.getActionCommand()=="Variation In Keystrokes Graph")
		{
			status=3;
			Graph_XYLineChart chart = new Graph_XYLineChart(allKeyStrokeStore,keyStore,status,passLength);
			chart.pack( );          
      			RefineryUtilities.centerFrameOnScreen( chart );          
      			chart.setVisible( true ); 
		}


	}

}
