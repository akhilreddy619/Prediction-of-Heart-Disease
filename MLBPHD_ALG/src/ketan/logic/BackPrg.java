 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ketan.logic;

/**
 *
 * @author PRASAD
 */
public class BackPrg 
{
public static String _error;
public static String _top;
int ipN,opN,h1N,h2N;
double oph1[][],oph2[][],finalop[][],input[][];
double deltaweightintoh1[][];
//double deltaweighth1toh2[][];
double deltaweighth1toop[][];
 double tweightintoh1[][];
 //double tweighth1toh2[][];
 double tweighth1toop[][];

double dop[][],dh2[][],errorh2[][],errorh1[][],dh1[][];
InitalNetwork network;
double  RMSE;

public static String getError()
{
  return(_error);    
}
//constructor

public BackPrg (int ip,int hidden,int op)
{
    ipN=ip;
    h1N=hidden;
    opN=op;
    GlobalStorage.counter=1;
Neurons n=new Neurons(ip,hidden,op);
ipN=n.getinput();
h1N=n.geth1();
//h2N=n.geth2();
opN=n.getop();
System.out.println("In Initial Network");
network=new InitalNetwork(ipN, h1N, opN);
network.initializeNet();
System.out.println("Initalized Network With Random Bits");

tweightintoh1=new double[h1N][ipN];
tweighth1toop=new double[h1N][opN];
//delta chnaes
deltaweightintoh1=new double[ipN][h1N];
//deltaweighth1toh2=new double[h1N][h2N];
deltaweighth1toop=new double[h1N][opN];
//deltas
dop=new double[opN][1];
dh1=new double[h1N][1];
errorh1=new double[h1N][1];
}
private void RepeatProcess(int ip,int hidden,int op)
{
Neurons n=new Neurons(ip,hidden,op);
ipN=n.getinput();
h1N=n.geth1();
//h2N=n.geth2();
opN=n.getop();
System.out.println("In Initial Network For NextTime onwards");
network=new InitalNetwork(ipN, h1N, opN);
tweightintoh1=new double[h1N][ipN];
tweighth1toop=new double[h1N][opN];

//delta chnaes
deltaweightintoh1=new double[ipN][h1N];
deltaweighth1toop=new double[h1N][opN];
//deltas
dop=new double[opN][1];
dh1=new double[h1N][1];
errorh1=new double[h1N][1];
System.out.println("End of Inital Network");
}

public void train(double input[][],double output[][])
{

oph1=activation(matmul(transpose(InitalNetwork.getweightintoh1(),ipN,h1N) ,input,h1N,ipN,1),h1N);
//final op
finalop=activation(matmul(transpose(InitalNetwork.getweighth1toop(),h1N,opN) ,oph1,opN,h1N,1),opN);
System.out.println("final op:");
if(finalop!=null)
for(int i=0;i<opN;i++);
RMSE=0;
//System.out.println("hai0");
for(int count=0;count<opN;count++)
{
RMSE+=(finalop[count][0]-output[count][0])*(finalop[count][0]-output[count][0]);
}//System.out.println("RMSE:");
RMSE=Math.sqrt(RMSE);
//calculating backpropagations
//delta at h2 to op
System.out.println("dop:");
for(int i=0;i<opN;i++)
{
dop[i][0]=(output[i][0]-finalop[i][0])*finalop[i][0]*(1-finalop[i][0]);
}
System.out.println("");
System.out.println("Op  is " + dop[0][0]);
//tweight..
tweighth1toop=matmul(oph1,transpose(dop,opN,1),h1N,1,opN);
//System.out.println("hai2");
deltaweighth1toop=deltachangeItoJ(deltaweighth1toop,tweighth1toop,h1N,opN);
System.out.println("delata weight at h2 to op");
errorh1=matmul(InitalNetwork.weighth1toop,dop,h1N,opN,1);
_top=output[0][0]+"";
System.out.println("Iteration " + GlobalStorage.counter);
System.out.println("Error { " + errorh1[0][0]+ " } --- Tolerance { " + output[0][0] +  " }");

for(int i=0;i<h1N;i++)
dh1[i][0]=errorh1[i][0]*oph1[i][0]*(1-oph1[i][0]);
tweightintoh1=matmul(input,transpose(dh1,h1N,1),ipN,1,h1N);
deltaweightintoh1=deltachangeItoJ(deltaweightintoh1,tweightintoh1,ipN,h1N);
//set weights or update weight
InitalNetwork.setweightintoh1(updateWeights(InitalNetwork.getweightintoh1(),deltaweightintoh1,ipN,h1N));
InitalNetwork.setweighth1toop(updateWeights(InitalNetwork.getweighth1toop(),deltaweighth1toop,h1N,opN));
//check for error_rate with output and then again call the logic
   GlobalStorage.counter++;
if(Math.round(errorh1[0][0])>Math.round(output[0][0]) && GlobalStorage.counter<50)
{
    System.out.println("Next Iteration Going On");
    double [][]newip=InitalNetwork.getweightintoh1();
    double [][]newop=InitalNetwork.getweighth1toop(); //doubt
    RepeatProcess(ipN,h1N, opN);
}
else
{
  _error=errorh1[0][0]+"";
  return;
}
} //end of training

//cal output-matrix multiplication
public  double[][] matmul(double weight[][], double input[][],int r1,int c1,int c2)
{
double output[][]=new double[r1][c2];
for(int i=0;i<r1;i++)
{
for(int j=0;j<c2;j++)
for( int k=0;k<c1;k++)
output[i][j]+=weight[i][k]*input[k][j];
}
return(output);
}
//end of calOutput
//Activation function
public double[][] activation(double op[][],int size)
{int s=size;
double activeop[][]=new double[s][1];
for(int i=0;i<s;i++)
   activeop[i][0]=(1)/(1+Math.exp(-op[i][0]));
return(activeop);
}

//transpose matrix
public double[][] transpose(double mat[][],int r,int c)
{
double trans[][]=new double[c][r];
for(int i=0;i<c;i++)
for(int j=0;j<r;j++)
trans[i][j]=mat[j][i];
return(trans);
}

public double[][] deltachangeItoJ(double deltaweightitoj[][],double ym[][],int x,int y)
{
for(int i=0;i<x;i++)
for(int j=0;j<y;j++)
deltaweightitoj[i][j]=0.1*deltaweightitoj[i][j]+0.05*ym[i][j];
return(deltaweightitoj);
}

//update weights 
public double[][] updateWeights(double wt[][],double dwt[][],int r,int c)
{
for(int i=0;i<r;i++)
for(int j=0;j<c;j++)
{
             wt[i][j]+=dwt[i][j];
}
return(wt);
}
    }
