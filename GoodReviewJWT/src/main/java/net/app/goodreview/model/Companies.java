package net.app.goodreview.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="companies")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Companies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="company_id")
    private Integer id;

    @Column(name="title")
    @Type(type="text")
    private String title;

    @Column(name="logo")
    @Type(type="text")
    private String logo;

    @Column(name="top_img")
    @Type(type="text")
    private String topImg;

    @Column(name="city")
    @Type(type="text")
    private String city;

    @Column(name="location")
    @Type(type="text")
    private String location;

    @Column(name="site")
    @Type(type="text")
    private String site;

    @Column(name="site_link")
    @Type(type="text")
    private String siteLink;

    @Column(name="schedule")
    @Type(type="text")
    private String schedule;

    @Column(name="phone")
    @Type(type="text")
    private String phone;

    @Column(name="route")
    @Type(type="text")
    private String route;

    @Column(name="about")
    @Type(type="text")
    private String about;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;

    @OneToMany(mappedBy = "company")
    private Set<Review> reviewList;

//    @OneToMany(mappedBy = "company")
//    private Set<Favorites> favoriteList;
}
