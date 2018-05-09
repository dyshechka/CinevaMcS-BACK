package cinema.cloud.service.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "film")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Film extends AbstractDomain {

    private static final long serialVersionUID = 5712452106362492423L;

    @Column
    private Integer duration;
    @Column
    private BigDecimal imdb;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "age_restriction_film",
            joinColumns = {@JoinColumn(name = "id_film")},
            inverseJoinColumns = {@JoinColumn(name = "id_age_restriction")}
    )
    private List<AgeRestriction> ageRestrictions = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "country_film",
            joinColumns = {@JoinColumn(name = "id_film")},
            inverseJoinColumns = {@JoinColumn(name = "id_country")}
    )
    private List<Country> countries = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "genre_film",
            joinColumns = {@JoinColumn(name = "id_film")},
            inverseJoinColumns = {@JoinColumn(name = "id_genre")}
    )
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "rental_period_film",
            joinColumns = {@JoinColumn(name = "id_film")},
            inverseJoinColumns = {@JoinColumn(name = "id_rental_period")}
    )
    private List<RentalPeriod> rentalPeriods = new ArrayList<>();
}
