package pl.coderslab.player;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.commons.Gender;
import pl.coderslab.club.Club;
import pl.coderslab.commons.AgeCategory;
import pl.coderslab.season.Season;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

//@Data
@Getter
@Setter
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=3, message = "At least 3 letters")
    private String firstName;

    @NotNull
    @Size(min=3, message = "At least 3 letters")
    private String lastName;

    @Past
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private AgeCategory ageCategory;

    private boolean valid;

    @NotNull
    @ManyToMany
    private List<Club> clubs= new ArrayList<>();

    @NotNull
    @ManyToMany// (mappedBy = "players")
    private List<Season> seasons = new ArrayList<>();

    public Player() {
    }

    public Season getLastSeason(){
        return getSeasons().get(getSeasons().size()-1);
    }

    public Club getLastClub(){
        return getClubs().get(getClubs().size()-1);
    }

    public void setAgeCategory() {
        LocalDate now = LocalDate.now();

        Period period = Period.between(this.dateOfBirth, now);

        if(period.getYears() < 12){
            setAgeCategory(AgeCategory.U12);
        }
        else if(period.getYears() < 14){
            setAgeCategory(AgeCategory.U14);
        }
        else if(period.getYears() < 16){
            setAgeCategory(AgeCategory.U16);
        }
        else if(period.getYears() < 18){
            setAgeCategory(AgeCategory.U18);
        }
        else if(period.getYears() < 20){
            setAgeCategory(AgeCategory.U20);
        }
        else{
            setAgeCategory(AgeCategory.senior);
        }

    }



}
