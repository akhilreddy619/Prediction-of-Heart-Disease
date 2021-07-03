/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ketan.auth;

/**
 *
 * @author saiketan
 */
public class RegisterUser {
   
    DataOperations dtOpr;
    public RegisterUser()
    {
        dtOpr=new DataOperations();
    }
    public boolean register(User user)
    {
        return dtOpr.insertUser(user);
    }
    public boolean validate(String Uname,String Pwd)
    {
        String OrgPwd=dtOpr.getPwd(Uname);
        if(OrgPwd!=null && OrgPwd.equals(Pwd))
            return true;
        else
            return false;
    }
    
}
