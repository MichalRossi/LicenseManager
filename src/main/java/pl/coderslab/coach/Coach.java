package pl.coderslab.coach;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.commons.AgeCategory;
import pl.coderslab.commons.Gender;
import pl.coderslab.season.Season;
import pl.coderslab.club.Club;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "coaches")
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private AgeCategory ageCategory;

    private boolean valid;

    @NotNull
    @ManyToMany
    private List<Club> clubs = new ArrayList<>();;

    @NotNull
    @ManyToMany //(mappedBy = "coaches")
    private List<Season> seasons = new ArrayList<>();


    public Coach() {
    }

    public Season getLastSeason(){
        return getSeasons().get(getSeasons().size()-1);
    }

    public Club getLastClub(){
        return getClubs().get(getClubs().size()-1);
    }


}
