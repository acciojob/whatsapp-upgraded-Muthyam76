package com.driver.Repository;

import com.driver.Group;
import com.driver.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
@Repository
public class WhatsappRepository {
    HashMap<String, User> userList;
    WhatsappRepository(){
        userList=new HashMap<>();
    }

    public String addUser(String name, String mobile) throws Exception {
        for(User user : userList.values()){
            if(user.getMobile().equals(mobile))
                throw new Exception("User already exists");
        }
        User user =new User(name,mobile);
        userList.put(mobile,user);
        return "SUCCESS";
    }

   // public Group createGroup(List<User> users) {
   // }
}
