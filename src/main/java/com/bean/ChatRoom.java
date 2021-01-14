/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import java.util.ArrayList;

/**
 *
 * @author Simon
 */
public class ChatRoom {
    private String chatName;
    private ArrayList<User> users;
    public ChatRoom(){
              users = new ArrayList<User>();
    }
    
    public void addUser(User p){
        users.add(p);
    }
    
    public ArrayList getUsers(){
        return users;
    }
    
        
    
    public void setChatName(String s){
        this.chatName = s;
    }
    
    public String getChatName(){
        return chatName;
    }
    
    

    
    
}
