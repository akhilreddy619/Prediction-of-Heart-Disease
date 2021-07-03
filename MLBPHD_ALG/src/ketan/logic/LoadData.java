 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ketan.logic;
import java.io.*;

import ketan.auth.DataOperations;
/**
 *
 * @author pc
 */
public class LoadData {
    private static String data="";
    public static int totalTrans=0;
    public static Object[] columns=null;
    
    public static String UploadData(String filename)
    {
        int cnt=1;
        try
           {
               FileInputStream fis=new FileInputStream(filename);
               BufferedReader br=new BufferedReader(new InputStreamReader(fis));
               String line=br.readLine();
               while(line!=null)
               {
                   data+=line +"\r\n";
                   cnt++;
                   line=br.readLine();
               }
               fis.close();
               GlobalStorage.rowscount=cnt;
               return(data);
            }
           catch(Exception ex)
           {
               return("Unable to Read");
           }
    }
    
    public String GetPatName(String pid)
    {
         try
         {
             return(new DataOperations().GetPatName(pid));
         }
        catch(Exception ex)
        {
             return("NA");
        }
    }
    public static boolean convertIntoDB(String data){
        DataOperations dopr=new DataOperations();
        String rows[]=data.split("\r\n");
        columns=rows[0].split(",");
        totalTrans=rows.length-1;
        if(dopr.createTable(columns))
        {
            int flag=0;
            for(int i=1;i<rows.length;i++)//since 1st row indicates headers...
            {
                
                if(!dopr.insertTransaction(rows[i]))
                {
                    flag=1;
                    break;
                }
            }
            if(flag==0) return true;
        }
        return false;
    }
    
}
