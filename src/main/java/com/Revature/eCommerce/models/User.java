package com.Revature.eCommerce.models;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class User
{
    private String user_id;    
    private String username;
    private String password;
    private String roleId;
    
    public User(String username , String password, String roleId){
        this.user_id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }


    public String getId()
    {
        return this.user_id;
    }
    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setId(String id)
    {
        this.user_id = user_id;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


    

}
