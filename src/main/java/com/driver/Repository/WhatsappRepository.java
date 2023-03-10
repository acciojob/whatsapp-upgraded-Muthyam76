package com.driver.Repository;

import com.driver.Group;
import com.driver.Message;
import com.driver.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Repository
public class WhatsappRepository {
    HashMap<String, User> userList;
    HashMap<String,List<User>>groupList;
    HashMap<String,List<User>>groupList1;
   //List<Group>groupList;
    HashMap<Integer, Message>messageList;
    HashMap<String,List<Message>>mg;
    HashMap<String,List<Message>>mu;

    public WhatsappRepository(){
        userList=new HashMap<>();
        groupList=new HashMap<>();
        groupList1=new HashMap<>();
        messageList=new HashMap<>();
        mg=new HashMap<>();
        mu=new HashMap<>();
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
            groupList1.put(group.getName(),users);

        }
        else if(size>2){
            int noOfGroups=groupList.size()+1;
             group=new Group("Group "+noOfGroups,size);
            groupList.put(group.getName(),users);

        }
        return group;
   }

    public int createMessage(String content) {
        Message m=new Message(messageList.size()+1,content,new Date());
        messageList.put(m.getId(),m);
        return m.getId();
    }

    public String changeAdmin(User approver, User user, Group group) throws Exception {
        if(!groupList.containsKey(group.getName()))
            throw new Exception("Group does not exist");
        List<User>user1=groupList.get(group.getName());
        if(user1.get(0).getName().equals(approver))
            throw new Exception("Approver does not have rights");
        if(!user1.contains(user))
            throw new Exception("User is not a participant");
        int n=user1.indexOf(user);
        user1.set(n,user1.get(0));
        user1.set(0,user);
        return "SUCCESS";

    }

    public int removeUser(User user) throws Exception {
        int count=0;
        boolean flag=false;
        for(String name : groupList.keySet()){
            List<Message>message2=mg.get(name);
            List<User>users=groupList.get(name);
            if(users.contains(user) && users.get(0).equals(user))
                throw new Exception("Cannot remove admin");
            if(users.contains(user)){
                flag=true;
                List<Message>message1=mu.get(user);

                for(Message m : message1){
                    if(message2.contains(m))
                        message2.remove(m);
                    message1.remove(m);
                }
                mu.put(user.getMobile(),message1);
                mg.put(name,message2);
                users.remove(user);
                groupList.put(name,users);
                count=count+mu.size();
            }
            count=count+mg.size();


        }
        if(flag==false)
            throw new Exception("User not found");

        return  count;
    }

    public int sendMessage(Message message, User sender, Group group) throws Exception {
       if(group==null ){
           throw new Exception("Group does not exist");
       }
        List<User>users=null;

       if(groupList1.containsKey(group.getName()) || groupList.containsKey(group.getName())){
           if(groupList1.containsKey(group.getName()))
               users=groupList1.get(group.getName());
           else
               users=groupList.get(group.getName());
       }
       else{
           throw new Exception("Group does not exist");
       }
       if(sender==null || !users.contains(sender))
           throw new Exception("You are not allowed to send message");
        List<Message>messages=mg.get(group.getName());
        if(messages==null){
           messages=new ArrayList<>();
       }
        if(message!=null) {
            messages.add(message);
            mg.put(group.getName(), messages);
        }


       return messages.size();



    }
}
