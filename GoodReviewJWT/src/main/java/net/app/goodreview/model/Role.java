package net.app.goodreview.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="roles")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    @EqualsAndHashCode.Include
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "roleList", fetch = FetchType.LAZY)
    private Set<User> userList;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userList=" + userList +
                '}';
    }
}
