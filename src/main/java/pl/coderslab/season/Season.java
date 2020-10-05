package pl.coderslab.season;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.club.Club;
import pl.coderslab.coach.Coach;
import pl.coderslab.player.Player;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "seasons")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 4, min = 4)
    @NotNull
    private  Integer startYear;

    @Size(max = 4, min = 4)
    @NotNull
    private  Integer endYear;

    @ManyToMany (mappedBy = "seasons")
    private List<Player> players= new ArrayList<>();

    @ManyToMany (mappedBy = "seasons")
    private List<Coach> coaches= new ArrayList<>();


    public Season() {
    }

    public String getName(){
        return startYear + "/" + endYear;
    }



}
