package pl.coderslab.player;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.coderslab.club.Club;
import pl.coderslab.club.ClubRepository;
import pl.coderslab.commons.AgeCategory;
import pl.coderslab.season.Season;
import pl.coderslab.season.SeasonRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class PlayerTest {

    @Test
    void shouldSetCorrectAgeCategory() {
        //given
        AgeCategory ageCategory = AgeCategory.senior;

        Player player = new Player();;
        player.setDateOfBirth(LocalDate.of(1990, Month.AUGUST,13));

        //when
        player.setAgeCategory();

        //then
        Assert.assertEquals(ageCategory,player.getAgeCategory());

    }
}