/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home;


import com.bean.ChatRoom;
import com.bean.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.repository.repository;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


/**
 *
 * @author Simon
 */
@Controller
public class HomeController {
    
    @RequestMapping("/home")
    public String home() {      
        
        return "home.jsp";
    }
    
    @RequestMapping("/register")
    public String register() {      
        
        return "register.jsp";
    }
    
    
    @RequestMapping("/chat")
    public String chat() {      
        
        return "chat.jsp";
    }
    
    
    @RequestMapping("/reg")
    public ModelAndView reg(HttpServletRequest request, HttpServletResponse response) {      

        repository rep = new repository();
        String nick = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        

        rep.insertUser(password, nick, email);
        System.out.println("Username= " + nick);
        
        ModelAndView mv = new ModelAndView();
        mv.setViewName("displayReg.jsp");
        mv.addObject("result", nick);
        
        return mv;
    }
    

    
    @RequestMapping("/login1")
    public ModelAndView login1(HttpServletRequest request, HttpServletResponse response) {      
   
        repository rep = new repository();
        String nick = request.getParameter("name");
        String password = request.getParameter("password");
        
   
        //SQL check if nick exist in db with password
        if(rep.checklogin(nick, password)==true){
            Integer ucount= rep.getusersinroom().size();
            ArrayList<ChatRoom> rooms = rep.getchatrooms(ucount);

             ModelAndView mv = new ModelAndView();
            mv.setViewName("loggedIn.jsp");
            mv.addObject("result", nick);
            mv.addObject("list", rooms);
            return mv;

        } else {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("wrongpass.jsp");
                    return mv;

        }      

  
    }
 
 
        @RequestMapping("/login2")
    public ModelAndView login2(HttpServletRequest request, HttpServletResponse response) {      
   
        repository rep = new repository();
        String nick = request.getParameter("name");
    
        Integer ucount= rep.getusersinroom().size();
        ArrayList<ChatRoom> rooms = rep.getchatrooms(ucount);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("loggedIn.jsp");
        mv.addObject("result", nick);
        mv.addObject("list", rooms);
        return mv;
    }
    
    
    @RequestMapping("/admin")
    public String admin() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {      
       
        
        return "admin.jsp";
    }
    
    
  
      
}