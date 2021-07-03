/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ketan.bean;

/**
 *
 * @author saiketan
 */
public class RegistrationBean {
private String fullName;
private String userName;
private String password;
private String phoneNo;
private String address;
private String emailID;

public void setFullName(String name)
{
    this.fullName=name;
}
public String getFullName()
{
    return this.fullName;
}
public void setUserName(String uName)
{
    this.userName=uName;
}
public String getUserName()
{
    return this.userName;
}
public void setPassword(String pwd)
{
    this.password=pwd;
}
public String getPassword()
{
    return this.password;
}
public void setPhoneNo(String phoneNo)
{
    this.phoneNo=phoneNo;
}
public String getPhoneNo()
{
    return this.phoneNo;
}
public void setAddress(String address)
{
    this.address=address;
}
public String getAddress()
{
    return this.address;
}
public void setEmailId(String emailID)
{
    this.emailID=emailID;
}
public String getEmailID()
{
    return this.emailID;
}
}
