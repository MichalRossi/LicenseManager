package pl.coderslab.season;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
@RequestMapping("/seasons")
public class SeasonController {

    private final SeasonRepository seasonRepository;

    public SeasonController(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }


    @GetMapping("/")
    public String seasonsList(Model model) {
        model.addAttribute("seasons", seasonRepository.findAll());
        return "seasons/seasonsList";
    }


    @Scheduled(cron = "0 0 0 1 8 ?") //happens at the beginning of August, when transfer window starts
    public void addSeason() {
        Season newSeason = new Season();
        newSeason.setStartYear(LocalDate.now().getYear());
        newSeason.setEndYear(LocalDate.now().getYear() + 1);

        seasonRepository.save(newSeason);
        }


}




