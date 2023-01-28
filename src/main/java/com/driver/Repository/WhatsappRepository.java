package com.driver.Repository;

import com.driver.Group;
import com.driver.Message;
import com.driver.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Repository
public class WhatsappRepository {
    HashMap<String, User> userList;
    HashMap<String,Group>groupList;
    HashMap<Integer, Message>messageList;
    public WhatsappRepository(){
        userList=new HashMap<>();
        groupList=new HashMap<>();
        messageList=new HashMap<>();
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

    public Group createGroup(List<User> users) {
        int size=users.size();
        Group group=null;
        if(size==2){
            User user=users.get(1);
           group=new Group(user.getName(),2);

        }
        else if(size>2){
            int size1=groupList.size()+1;
             group=new Group("Group "+size1,size);
            groupList.put(group.getName(),group);

        }
        return group;
   }

    public int createMessage(String content) {
        Message m=new Message(messageList.size(),content,new Date());
        return m.getId();
    }
}
