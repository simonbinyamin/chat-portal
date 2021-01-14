/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repository;

import com.bean.ChatRoom;
import com.bean.User;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Simon
 */
public class repository {
    
    java.sql.Connection conn = null;
    public repository(){
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (Exception ex) {
                System.out.println("Failed to register MySQL Connector/J");
                return;
            }

            try {

                conn = DriverManager.getConnection(
    "");
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                return;
            }
    }   
        
    public void insertUser(String pwd, String name, String email) {
        PreparedStatement stmt = null;
	ResultSet rs = null;

	try {
            stmt = conn.prepareStatement("INSERT INTO user(id, password, nickname, email) VALUES (0, ?, ?, ?)");
            stmt.setString(1, pwd);
            stmt.setString(2, name);
            stmt.setString(3, email);

            stmt.executeUpdate();
            
	} catch (SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage());
		return;
	} finally {
		if (rs != null) {
		  try {
		    rs.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  rs = null;
		}

		if (stmt != null) {
		  try {
		    stmt.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  stmt = null;
		}
	}
    }
    
    
    public Boolean checklogin(String name,String pwd) {
	java.sql.Statement stmt = null;
	ResultSet rs = null;
	try {
	    stmt = conn.createStatement();
            if( stmt.execute("SELECT * FROM user WHERE password='"+pwd+"' AND nickname='"+name+"'")) {
		rs = stmt.getResultSet();

                    
                if(rs.next()==false){
                    System.out.println("wrong pass");
                    return false;
                } else {
                    return true;
                }
	    }

	} catch (SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage());
	} finally {
		if (rs != null) {
		  try {
		    rs.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  rs = null;
		}

		if (stmt != null) {
		  try {
		    stmt.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  stmt = null;
		}
	}
        return false;
    }
    

    public ArrayList<User> getusersinroom() {
        ArrayList<User> users = new ArrayList<User>();
	java.sql.Statement stmt = null;
	ResultSet rs = null;
	try {
	    stmt = conn.createStatement();
            if( stmt.execute("SELECT * FROM user Where channel = 'channel1'")) {
		rs = stmt.getResultSet();
	    }
	    while (rs.next ())
	    {
                String uname = rs.getString("nickname");
		String email = rs.getString("email");
                String ch = rs.getString("channel");
                User u = new User();
                u.setNickname(uname);
                u.setEmail(email);
                u.setChannel(ch);
                users.add(u);

	    }
	} catch (SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage());

	} finally {
		if (rs != null) {
		  try {
		    rs.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  rs = null;
		}

		if (stmt != null) {
		  try {
		    stmt.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  stmt = null;
		}
	}
        return users;
    }
    
    
    public ArrayList<ChatRoom> getchatrooms(Integer usercounter) {
        ArrayList<ChatRoom> rooms = new ArrayList<ChatRoom>();
	java.sql.Statement stmt = null;

	ResultSet rs = null;

	try {
	    stmt = conn.createStatement();
            if( stmt.execute("SELECT * FROM chatroom")) {
		rs = stmt.getResultSet();
	    }
	    while (rs.next ())
	    {
 
                
                ChatRoom cr = new ChatRoom();
                
		String nameVal = (String)rs.getString ("name");

                
                cr.setChatName(nameVal);
    
                
                if("channel1".equals(nameVal)) {
                      for (int i = 0; i < usercounter; i++) {
                    User u = new User();
                     u.setEmail("dsa");
                     cr.addUser(u);
                
                }
                }
              
 
                rooms.add(cr);
                System.out.println(nameVal);
	    }
	} catch (SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage());

	} finally {
		if (rs != null) {
		  try {
		    rs.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  rs = null;
		}

		if (stmt != null) {
		  try {
		    stmt.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  stmt = null;
		}
	}
        return rooms;
    }
    
    
    public void deleteChatroom(String chname) {
        PreparedStatement stmt = null;
	ResultSet rs = null;

	try {
	        
            stmt = conn.prepareStatement("DELETE FROM chatroom WHERE name='" + chname + "'");
            stmt.executeUpdate();
            
	} catch (SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage());
		return;
	} finally {
		if (rs != null) {
		  try {
		    rs.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  rs = null;
		}

		if (stmt != null) {
		  try {
		    stmt.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  stmt = null;
		}
	}
    }
    
    
    public void changeChatroom(String oldname, String newname) {
        PreparedStatement stmt = null;
	ResultSet rs = null;

	try {
            stmt = conn.prepareStatement("UPDATE chatroom SET name='"+newname+"' WHERE name='" + oldname + "'");
            stmt.executeUpdate();
            
	} catch (SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage());
		return;
	} finally {
		if (rs != null) {
		  try {
		    rs.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  rs = null;
		}

		if (stmt != null) {
		  try {
		    stmt.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  stmt = null;
		}
	}
    }
    
    
    
    public void joinRoom(String id, String name) {
        PreparedStatement stmt = null;
	ResultSet rs = null;

	try {
            stmt = conn.prepareStatement("UPDATE user SET channel='"+id+"' WHERE nickname='"+ name+"'");
            stmt.executeUpdate();
            
	} catch (SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage());
		return;
	} finally {
		if (rs != null) {
		  try {
		    rs.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  rs = null;
		}

		if (stmt != null) {
		  try {
		    stmt.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  stmt = null;
		}
	}
    }
    
        public void unJoinRoom(String name) {
        PreparedStatement stmt = null;
	ResultSet rs = null;

	try {
            stmt = conn.prepareStatement("UPDATE user SET channel='NULL' WHERE nickname='"+ name+"'");
            stmt.executeUpdate();
            
	} catch (SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage());
		return;
	} finally {
		if (rs != null) {
		  try {
		    rs.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  rs = null;
		}

		if (stmt != null) {
		  try {
		    stmt.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  stmt = null;
		}
	}
    }
    
    public void resetChannel(String name) {
        PreparedStatement stmt = null;
	ResultSet rs = null;
	
	try {
            stmt = conn.prepareStatement("UPDATE user SET channel='NULL' WHERE nickname='" + name + "'");
            stmt.executeUpdate();
            
	} catch (SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage());
		return;
	} finally {
		if (rs != null) {
		  try {
		    rs.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  rs = null;
		}

		if (stmt != null) {
		  try {
		    stmt.close();
		  } catch (SQLException sqlEx) { /* ignore */ }
		  stmt = null;
		}
	}
    }
}
