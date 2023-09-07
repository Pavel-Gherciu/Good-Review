package net.app.goodreview.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nume")
    private String firstName;

    @Column(name = "prenume")
    private String lastName;

    @Column(name = "login")
    private String username;

    @Column(name = "icon")
    private String icon;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_roles", joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roleList;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Set<Review> reviewList;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Set<Comments> commentsList;

//    @OneToMany(mappedBy = "user")
//    private Set<Favorites> favoriteList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
