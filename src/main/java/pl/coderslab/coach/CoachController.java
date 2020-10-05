package pl.coderslab.coach;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.commons.AgeCategory;
import pl.coderslab.commons.Gender;
import pl.coderslab.club.Club;
import pl.coderslab.club.ClubRepository;
import pl.coderslab.mail.EmailService;
import pl.coderslab.season.Season;
import pl.coderslab.season.SeasonRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/coaches")
public class CoachController {

    @Value("${transfer_month}")
    private int TRANSFER_MONTH;

    @Value("${admin_email}")
    public String ADMIN_EMAIL;


    private final CoachRepository coachRepository;
    private final ClubRepository clubRepository;
    private final SeasonRepository seasonRepository;
    private final EmailService emailService;

    public CoachController(CoachRepository coachRepository, ClubRepository clubRepository, SeasonRepository seasonRepository, EmailService emailService) {
        this.coachRepository = coachRepository;
        this.clubRepository = clubRepository;
        this.seasonRepository = seasonRepository;
        this.emailService = emailService;
    }

    @GetMapping("/")
    public String coachList(Model model) {
        model.addAttribute("coaches", coachRepository.findAll());
        return "coach/coachesList";
    }


    @GetMapping("/add")
    public String addCoach(Model model) {
        model.addAttribute("coach", new Coach());
        return "coach/addCoach";
    }

    @PostMapping("/add")
    public String addCoach(Model model, @ModelAttribute @Valid Coach coach, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "coach/addCoach";
        }

        if (LocalDate.now().getMonthValue()!=TRANSFER_MONTH) {
            model.addAttribute("errorMessage", "Changes can be made only during transfer window.");
            return "coach/addCoach";
        }

        if(!coach.isValid()){
            coach.setValid(true);
        }
        Season currentSeason = seasonRepository.findLast();

        List<Season> coachSeasons = new ArrayList<>();
        coachSeasons.add(currentSeason);
        coach.setSeasons(coachSeasons);

        List<Club> coachClubs = new ArrayList<>();
        coachClubs.add(coach.getLastClub());
        coach.setClubs(coachClubs);

        coachRepository.save(coach);

        emailService.sendMail(ADMIN_EMAIL,
                "Coach " + coach.getFirstName() + " " + coach.getLastName() + " has been added.",
                "Coach " +
                        coach.getFirstName() + " " + coach.getLastName() +
                        " has been added. Coach info: " + "\n" +
                         getCoachInformation(coach));

        return "redirect:../coaches/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Optional<Coach> coach = coachRepository.findById(id);
        model.addAttribute("coach", coach);
        return "coach/addCoach";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model, @ModelAttribute @Valid Coach coach , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "coach/addCoach";
        }

        if (LocalDate.now().getMonthValue()!=TRANSFER_MONTH) {
            model.addAttribute("errorMessage", "Changes can be made only once during transfer window.");
            return "coach/addCoach";
        }

        Coach oldCoach = coachRepository.findCoachById(id);
        Season currentSeason = seasonRepository.findLast();

       /* Any changes should be available only once during transfer window. This feature is switch off to allow user more easily test test the application.
            if (oldCoach.getLastSeason().getId().equals(currentSeason.getId())) {
            model.addAttribute("errorMessage", "Changes can be made only once during transfer window.");
            return "coach/addCoach";
        }*/

        if(coach.isValid()){
            List<Club> coachClubs = oldCoach.getClubs();
            coachClubs.add(coach.getLastClub());
            coach.setClubs(coachClubs);

            List<Season> coachSeasons = oldCoach.getSeasons();
            coachSeasons.add(currentSeason);
            coach.setSeasons(coachSeasons);
        }

        if(!coach.isValid()){
            List<Club> coachClubs = oldCoach.getClubs();
            coach.setClubs(coachClubs);

            List<Season> coachSeasons = oldCoach.getSeasons();
            coach.setSeasons(coachSeasons);
        }

        coachRepository.save(coach);

        emailService.sendMail(ADMIN_EMAIL,
                "Coach " + coach.getFirstName() + " " + coach.getLastName() + " has been changed.",
                "Coach " +
                        coach.getFirstName() + " " + coach.getLastName() +
                " has been changed. Coach info: " + "\n" +
                        getCoachInformation(coach));
        return "redirect:../";
    }

    @Scheduled(cron = "0 0 0 1 9 ?") //happens at the beginning of September, when transfer window ends
    public void updateCoaches() {
        List<Coach> coaches = coachRepository.findAll();
        Season currentSeason = seasonRepository.findLast();

        coaches.stream()
                .filter(c -> !c.getLastSeason().getId().equals(currentSeason.getId()))
                .forEach(c->{
                    List<Club> coachClubs = c.getClubs();
                    coachClubs.add(c.getLastClub());
                    c.setClubs(coachClubs);

                    List<Season> coachSeasons = c.getSeasons();
                    coachSeasons.add(currentSeason);
                    c.setSeasons(coachSeasons);

                    coachRepository.save(c);
                });
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        emailService.sendMail(ADMIN_EMAIL,
                "Coach " + coachRepository.findCoachById(id).getFirstName() + " " + coachRepository.findCoachById(id).getLastName() + " has been deleted.",
                "Coach " +
                        coachRepository.findCoachById(id).getFirstName() + " " + coachRepository.findCoachById(id).getLastName() +
                " has been deleted. Deleted coach info: " + "\n" +
                        getCoachInformation(coachRepository.findCoachById(id)));

        coachRepository.deleteById(id);
        return "redirect:../";
    }

    @ModelAttribute("ageCategories")
    public List<AgeCategory> ageCategories() {
        return Stream.of(AgeCategory.values())
                .collect(Collectors.toList());
    }

    @ModelAttribute("genders")
    public List<Gender> genders() {
        return Stream.of(Gender.values())
                .collect(Collectors.toList());
    }

    @ModelAttribute("clubs")
    public List<Club> clubs() {
        return clubRepository.findAll();
    }

    @GetMapping("/club/{id}")
    public String clubCoaches(@PathVariable Long id, Model model) {

        List<Coach> clubCoaches = coachRepository.findCoachByClub(id);
        List<Coach> currentClubCoaches = clubCoaches.stream()
                .filter(p -> (p.getLastClub().getName().equals(clubRepository.findClubById(id).getName())) && p.isValid())
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("coaches",currentClubCoaches);
        return "coach/coachesList";
    }

    @GetMapping("/season/{id}")
    public String seasonCoaches(@PathVariable Long id, Model model) {

        List<Coach> coaches = coachRepository.findAll();
        Season season = seasonRepository.findById(id).orElse(null);

        List<Coach> seasonCoaches   = coaches.stream()
                .filter(c -> (c.getSeasons().stream()
                        .anyMatch(s -> s.getId().equals(season.getId()))))
                .filter(Coach::isValid)
                .collect(Collectors.toList());

        model.addAttribute("coaches",seasonCoaches);
        return "coach/coachesList";
    }

    public String getCoachInformation(Coach coach){
        StringBuilder clubsAndSeasons = new StringBuilder();

        for(int i = 0; i < coach.getClubs().size(); i++){
            clubsAndSeasons.append(coach.getClubs().get(i).getName())
                    .append(" in season: ")
                    .append(coach.getSeasons().get(i).getName())
                    .append("\n");
        }
        return
                "Coach license no.: " + coach.getId() + "\n" +
                        "Name: " + coach.getFirstName() + " " + coach.getLastName() + "\n" +
                        "Age category: " + coach.getAgeCategory() + "\n" +
                        "Gender: " + coach.getAgeCategory() + "\n " +
                        "Valid: " + coach.isValid() + "\n "+
                        "Clubs: \n" +
                        clubsAndSeasons.toString()
                        ;
    }

}
