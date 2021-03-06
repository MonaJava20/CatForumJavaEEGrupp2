package com.grupp2javaee.catforum.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
//@ToString
@Document(collection = "accounts")
public class Account {

    @Id
    private String id; //Denna behöver tydligen vara en String
    private String name;
    private String username;
    private String email;
    private String password;
    private String description; //Om tid finns ändra så att description är begränsad i antalet tecken.

    public Account() {}

    public Account(String name, String username, String email, String password, String description) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.description = description;
    }
    //Ev bild om tid finns.


    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", användarnam='" + username + '\'' +
                ", email='" + email + '\'' +
                ", lösenord='" + password + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
