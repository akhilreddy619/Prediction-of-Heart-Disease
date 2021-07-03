 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ketan.logic;

/**
 *
 * @author PRASAD
 */
public class InitalNetwork
{
//one input,2 hidden,one output layers
int IPNeurons;
int HL1Neurons;
int HL2Neurons;
int OPNeurons;
double input[];
 
 //InitNetwork newnet;
 
//variables for weightmatrix
static double weightintoh1[][];
static double weighth1toh2[][];
static double weighth1toop[][];

//change of weight
double deltaweightintoh1[][];
double deltaweighth1toh2[][];
double deltaweighth1toop[][];

//variables for oouputs
double oph1[];
double oph2[];
double finalop[];

public InitalNetwork(int ip,int h1,int op)
{
IPNeurons=ip;
HL1Neurons=h1;
OPNeurons=op;
weightintoh1=new double[IPNeurons][HL1Neurons];
weighth1toop=new double[HL1Neurons][OPNeurons];

//delta change
deltaweightintoh1=new double[IPNeurons][HL1Neurons];


deltaweighth1toop=new double[HL1Neurons][OPNeurons];

//outputs in each layer
oph1=new double[HL1Neurons];

//oph2=new double[HL2Neurons];
finalop=new double[OPNeurons];
input=new double[IPNeurons];
}
public  void  initializeNet()
{
//initialize the network weights
weightintoh1=randomInit(weightintoh1,IPNeurons,HL1Neurons);
weighth1toop=randomInit(weighth1toop,HL1Neurons,OPNeurons);
System.out.println("Weights initalized first time");
}
//initialize the weights
public double[][] randomInit(double wt[][],int r,int c)
{
double weights[][];
weights=wt;
for(int i=0;i<r;i++)
for(int j=0;j<c;j++)
weights[i][j]=(Math.random()*(2-1));
return(weights);
}

//setters
public static void setweightintoh1(double wintoh1[][])
{
weightintoh1=wintoh1;
}

public static  void setweighth1toop(double wh2toop[][])
{
weighth1toop=wh2toop;
}
//getters
public static double[][] getweightintoh1()
{
return(weightintoh1);
}

public static double[][] getweighth1toop()
{
return(weighth1toop);
}
}    

