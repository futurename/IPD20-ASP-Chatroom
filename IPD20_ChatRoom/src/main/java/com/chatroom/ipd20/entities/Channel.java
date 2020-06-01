package com.chatroom.ipd20.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Indexed @Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="channels")
public class Channel {

    public Channel(int id){
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min=1, max=100)
    @Column(length=100, nullable = false)
    @Field
    private String title;

    @NotNull
    @Size(min=1, max=200)
    @Column(length=200, nullable = false)
    @Field
    private String description;

    @ManyToOne
    @JoinColumn(name="ownerId", nullable = false)
    @IndexedEmbedded(depth = 1)
    @ToString.Exclude
    private User owner;

    @OneToMany(mappedBy = "channel")
    @ToString.Exclude
    private Set<Message> messages = new HashSet<Message>();

    @ManyToMany(mappedBy = "favoriteChannels")
    @ToString.Exclude
    private Set<User> users = new HashSet<User>();

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdTS = LocalDateTime.now();


}
