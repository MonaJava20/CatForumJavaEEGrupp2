package com.grupp2javaee.catforum.model;


import lombok.*;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Document(collection = "accounts")
public class Account {

    @Id
    private String id; //long istället för int för att öka säkerheten.
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)

    private String name;
    private String nickName;
    private String email;
    private String password;
    private String description; //Om tid finns ändra så att description är begränsad i antalet tecken.

    @DBRef
    private Set<Role> roles;

    public Account(String name, String nickName, String email, String password, String description) {
    }
    //Ev bild om tid finns.

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
