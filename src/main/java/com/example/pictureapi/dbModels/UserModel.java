package com.example.pictureapi.dbModels;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashMap;
import java.util.Map;

@Document
public class UserModel {

    @Id
    public String id;

    public String username;

    @Indexed(unique = true)
    public String email;

    public String password;

    private Map<String, String> userSettings = new HashMap<>();

    public UserModel() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, String> getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(Map<String, String> userSettings) {
        this.userSettings = userSettings;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, Username='%s', Email='%s']",
                id, username, email);
    }

}
