 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ketan.logic;
/**
 *
 * @author PRASAD
 */
public class Neurons
{
int input;
int h1;
int op;
public Neurons(int i,int h11,int o)
{
input=i;
h1=h11;
op=o;;
}
public int getinput()
{
return(input);
}
public int getop()
{
return(op);
}
public int geth1()
{
return(h1);
}    
}
