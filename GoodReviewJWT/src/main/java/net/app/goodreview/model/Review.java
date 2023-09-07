package net.app.goodreview.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "review")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer id;

    @Column(name = "review_text")
    private String text;

    @Column(name = "review_image")
    private String image;

    @Column(name = "review_rating")
    private Float rating;

    @Column(name = "review_date")
    private LocalDateTime reviewDate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Companies company;

    @JsonManagedReference
    @OneToMany(mappedBy = "review")
    private Set<Comments> commentsList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
