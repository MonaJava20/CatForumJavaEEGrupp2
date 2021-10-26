package com.grupp2javaee.catforum.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@ToString
@Document(collection = "users")
public class User {

    @Id
    private String id; //long istället för int för att öka säkerheten.
    private String name;
    private String nickName;
    private String email;
    private String description; //Om tid finns ändra så att description är begränsad i antalet tecken.

    public User(String name, String nickName, String email, String description) {
    }
    //Ev bild om tid finns.

}
