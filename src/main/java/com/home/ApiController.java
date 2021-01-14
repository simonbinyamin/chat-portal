/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home;

import com.bean.ChatRoom;
import com.bean.User;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.repository.repository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Simon
 */
@RestController
public class ApiController {
    

    public String localhost="http://localhost:29565";
    public String chatServer="http://localhost:8080";
    
    @RequestMapping(value = "/getrooms", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody String rooms() throws Exception {

          repository rep = new repository();
          ArrayList<ChatRoom> rooms = rep.getchatrooms(rep.getusersinroom().size());
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(rooms);

           return json;
    }

    

    @RequestMapping(value = "/getusersinroom", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody String getusersinroom() throws Exception {

          repository rep = new repository();
          ArrayList<User> users = rep.getusersinroom();
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(users);

           return json;
    }

    
    
    
    @RequestMapping(value = "/deleteChatRoom/{id}", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody void deleteChatRoom(@PathVariable String id, HttpServletResponse response) throws Exception {

        repository rep = new repository();
        rep.deleteChatroom(id);
        System.out.print(id);
    

    response.sendRedirect(localhost+"/mychatwww/admin");

    }
    
    
    @RequestMapping(value = "/kickUserOut/{id}", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody void kickUserOut(@PathVariable String id, HttpServletResponse response) throws Exception {

    System.out.print(id);

      repository rep = new repository();
      rep.resetChannel(id);

    response.sendRedirect(localhost+"/mychatwww/admin");
    }
    

      @RequestMapping(value = "/updateChatRoom/{id}/{name}", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody void updateChatRoom(@PathVariable String id, @PathVariable String name, HttpServletResponse response) throws Exception {

        repository rep = new repository();
        rep.changeChatroom(id, name);
        System.out.print(id);
        System.out.print(name);
        
         response.sendRedirect(localhost+"/mychatwww/admin");

    }
    
    
      @RequestMapping(value = "/joinChatRoom/{id}/{name}", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody void joinChatRoom(@PathVariable String id, @PathVariable String name, HttpServletResponse response) throws Exception {

        repository rep = new repository();
        rep.joinRoom(id, name);
        System.out.print(id);
        System.out.print(name);
        
         response.sendRedirect(chatServer+"/?name="+name+"&ch="+id);

    }
    
    @RequestMapping(value = "/unJoinChatRoom/{name}", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody void unJoinChatRoom(@PathVariable String name, HttpServletResponse response) throws Exception {

        repository rep = new repository();
        rep.unJoinRoom(name);
 
        System.out.print(name);
        
         response.sendRedirect(localhost+"/mychatwww/login2?name="+ name);

    }
    
}
