/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ketan.auth;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;
/**
 *
 * @author saiketan
 */
public class DataOperations {
  Connection con;
  
  public DataOperations()
    {
    con=ConnectionObj.getConnection();
    }
  public boolean insertUser(User user)
  {
      try{
          Statement stmt=con.createStatement();
          String insertStr="insert into tblRegister values('"+user.Name+"','"+user.Uname+"','"+user.Pwd+"','"+user.PhoneNo+"')";
          System.out.print(insertStr);
          stmt.execute(insertStr);
          return true;
      }
      catch(Exception ex)
      {
          return false;
      }
  }
  public String getPwd(String Uname)
  {
      try{
          Statement stmt=con.createStatement();
          String cmdStr="select pwd from tblRegister where uname='"+Uname+"'";
          System.out.print(cmdStr);
          ResultSet rs=stmt.executeQuery(cmdStr);
          
          if(rs.next())
              return rs.getString(1);
          return null;
      }
      catch(Exception ex)
      {
          return null;
      }
  }
  public ResultSet getTranactions()
  {
        try
        {
             Statement stmt=con.createStatement();
             ResultSet rs=stmt.executeQuery("select * from tblUnnormalized");
             return(rs);
        }
        catch(Exception ex)
        {
            return(null);
        }
  }
  
  public String GetSymptoms(String pid) throws Exception
  {
       Statement stmt=con.createStatement();
       String query="select * from tblsymptoms where pat_id='" + pid + "'";
       System.out.println(query);
       ResultSet rs=stmt.executeQuery(query);
       ResultSetMetaData rsmd=rs.getMetaData();
       String line="";
       rs.next();
       for(int i=2;i<=rsmd.getColumnCount();i++)
           line+=rs.getString(i) +",";
       line=line.substring(0,line.length()-1);
       
       rs.close();stmt.close();
       return(line);
  }
  
  public List<String> GetTrained() throws Exception
  {
       List<String> trained=new ArrayList<String>();
       
       Statement stmt=con.createStatement();
       ResultSet rs=stmt.executeQuery("select * from normalized");
       ResultSetMetaData rsmd=rs.getMetaData();
       String line="";
       while(rs.next())
       {
           for(int i=1;i<=rsmd.getColumnCount();i++)
                line+=rs.getString(i) +",";
           line=line.substring(0,line.length()-1);
           
           trained.add(line);
           line="";
       }
       return(trained);
  }
  public boolean insertTransaction(String rowData)
  {
      try
      {
          String fields[]=rowData.split(",");
          String cmdStr="insert into tblUnnormalized values(";
          for(String str:fields)
              cmdStr+="'"+str+"',";
          cmdStr=cmdStr.substring(0,cmdStr.length()-1)+")";
          System.out.println("Record " + cmdStr);
          Statement stmt=con.createStatement();
          stmt.execute(cmdStr);
          stmt.close();
          return true;
      }
      catch(Exception ex)
      {
          System.out.println("Error " + ex);
          return false;
      }
}
  
  
  public java.util.ArrayList GetPids()
  {
        try
            
        {
            ArrayList temp=new ArrayList();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select pat_id from tblreports");
        while(rs.next())
        {
            temp.add(rs.getString(1));
        }
        rs.close();stmt.close();
        return(temp);
        }
        catch(Exception ex)
        {
            return(null);
        }
  }
  public String GetPatName(String pid)
  {
      
    try
    {
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select pat_name from tblpatients where pat_id like '" + pid + "'");
        rs.next();
        return(rs.getString(1));
    }
    catch(Exception ex)
    {
        return("NA");
    }
  }
  public boolean createTable(Object[] headers)
  {
       try{
          //Statement stmt=con.createStatement();
           
           Statement pstmt=con.createStatement();
           ResultSet rs=pstmt.executeQuery("select count(*) from tab where tname like 'TBLUNNORMALIZED'");
           rs.next();
           int cnt=Integer.parseInt(rs.getString(1));
           
           if(cnt>0)
           {
                 pstmt=con.createStatement();
                 pstmt.executeUpdate("drop table tblunnormalized");
           }
          String cmdStr="create table tblunnormalized(";
          for(int i=0;i<headers.length;i++)
          {
              if(i==0)
                  cmdStr+=headers[i]+" varchar2(15),";
              else
              cmdStr+=headers[i]+" varchar2(35),";
          }
           cmdStr=cmdStr.substring(0,cmdStr.length()-1)+")";
           System.out.print(cmdStr);
           Statement stmt=con.createStatement();
           stmt.execute(cmdStr);
           
           
          cmdStr="create table normalized(";
          for(int i=0;i<headers.length;i++)
          {
              if(i==0)
                  cmdStr+=headers[i]+" varchar2(15),";
              else
              cmdStr+=headers[i]+" varchar2(35),";
          }
           cmdStr=cmdStr.substring(0,cmdStr.length()-1)+")";
           System.out.print(cmdStr);
           stmt=con.createStatement();
           stmt.execute(cmdStr);
           
           
            return true;
            }
      catch(Exception ex)
      {
          ex.printStackTrace();
          return false;
      }
  }
}
