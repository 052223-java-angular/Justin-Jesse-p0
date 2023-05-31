package com.Revature.eCommerce.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.Revature.eCommerce.models.User;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Session {
    private String id;
    private String username;
    private String historyId;

    public void setSession(User user) {
        this.id = user.getUserId();
        this.username = user.getUsername();

    }
    public String getId() {return this.id;}
    public String getUsername()
    {
        return this.username;
    }
}