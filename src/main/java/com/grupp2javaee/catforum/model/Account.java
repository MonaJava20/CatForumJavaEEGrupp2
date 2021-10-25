package com.grupp2javaee.catforum.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Document(collection = "accounts")
public class Account {
    @Id
    private Long id; //long istället för int för att öka säkerheten.
    private String name;
    private String nickName;
    private String email;
    private String description; //Om tid finns ändra så att description är begränsad i antalet tecken.
    //Ev bild om tid finns. ..

}
