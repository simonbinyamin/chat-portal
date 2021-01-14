package com.bean;

public class User{
    private String nickname;
    private String email;
    private String password;
   private String channel;
    
    public User(){}
    
 
    public void setPassword(String s){
        this.password = s;
    }
        public void setChannel(String s){
        this.channel = s;
    }
 
    public void setNickname(String s){
        this.nickname = s;
    }
    
    public void setEmail(String s){
        this.email = s;
    }
    
        
    public String getPassword(){
        return password;
    }
    
       public String getChannel(){
        return channel;
    }
       
     
    public String getNickname(){
        return nickname;
    }
    
    public String getEmail(){
        return email;
    }
}