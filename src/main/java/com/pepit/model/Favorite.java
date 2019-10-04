package com.pepit.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @ManyToOne
    private User user;

    //Voir pour integrer les productsIds?
    //un champ favoris = collection<objectid> mais ça dépend s'il faut des infos complémentaire par favoris (genre date)


    @CreatedDate
    @Column(name = "createdat")
    LocalDateTime createdat;

    @LastModifiedDate
    @Column(name = "updatedat")
    LocalDateTime updatedat;


}
