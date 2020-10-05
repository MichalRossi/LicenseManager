package pl.coderslab.player;

import com.sun.xml.bind.v2.TODO;
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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
@RequestMapping("/players")
public class PlayerController {

    @Value("${transfer_month}")
    private int TRANSFER_MONTH;

    @Value("${admin_email}")
    public String ADMIN_EMAIL;

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;
    private final SeasonRepository seasonRepository;
    private final EmailService emailService;



    public PlayerController(PlayerRepository playerRepository, ClubRepository clubRepository, SeasonRepository seasonRepository,EmailService emailService) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
        this.seasonRepository = seasonRepository;
        this.emailService = emailService;
    }


    @GetMapping("/")
    public String playerList(Model model) {
        model.addAttribute("players", playerRepository.findAll());
        return "player/playersList";
    }


    @GetMapping("/add")
    public String addPlayer(Model model) {
        model.addAttribute("player", new Player());
        return "player/addPlayer";
    }


    @PostMapping("/add")
    public String addPlayer(Model model, @ModelAttribute @Valid Player player, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "player/addPlayer";
        }

        if (LocalDate.now().getMonthValue()!=TRANSFER_MONTH) {
            model.addAttribute("errorMessage", "Changes can be made only during transfer window.");
            return "player/addPlayer";
        }

        if(!player.isValid()){
            player.setValid(true);
        }

        Season currentSeason = seasonRepository.findLast();

        List<Season> playerSeasons = new ArrayList<>();
        playerSeasons.add(currentSeason);
        player.setSeasons(playerSeasons);

        player.setAgeCategory();
        playerRepository.save(player);

        emailService.sendMail(ADMIN_EMAIL,
                "Player " + player.getFirstName() + " " + player.getLastName() + " has been added.",
                "Player " +
                        player.getFirstName() + " " + player.getLastName() +
                        "has been added. Player info: " + "\n" +
                        getPlayerInformation(player));
        return "redirect:../players/";
    }


    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Optional<Player> player = playerRepository.findById(id);
        model.addAttribute("player", player);
        return "player/addPlayer";
    }


    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model, @ModelAttribute @Valid Player player, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "player/addPlayer";
        }

        if (LocalDate.now().getMonthValue()!=TRANSFER_MONTH) {
            model.addAttribute("errorMessage", "Changes can be made only during transfer window.");
            return "player/addPlayer";
        }

        Player oldPlayer = playerRepository.findPlayerById(id);
        Season currentSeason = seasonRepository.findLast();

       /* Any changes should be available only once during transfer window. This feature is switch off to allow user more easily test test the application.
        if (oldPlayer.getLastSeason().getId().equals(currentSeason.getId())) {
            model.addAttribute("errorMessage", "Changes can be made only once during transfer window.");
            return "player/addPlayer";
        }*/

        if(player.isValid()){
            List<Club> playerClubs = oldPlayer.getClubs();
            playerClubs.add(player.getLastClub());
            player.setClubs(playerClubs);


            List<Season> playerSeasons = oldPlayer.getSeasons();
            playerSeasons.add(currentSeason);
            player.setSeasons(playerSeasons);
        }

        if(!player.isValid()){
            List<Club> playerClubs = oldPlayer.getClubs();
            player.setClubs(playerClubs);

            List<Season> playerSeasons = oldPlayer.getSeasons();
            player.setSeasons(playerSeasons);
        }


        player.setAgeCategory();
        emailService.sendMail(ADMIN_EMAIL,
                "Player  " + player.getFirstName() + " " + player.getLastName() + " has been changed.",
                "Player " +
                        player.getFirstName() + " " + player.getLastName() +
                        "has been changed. Player info: " + "\n" +
                        getPlayerInformation(player));

        playerRepository.save(player);
        return "redirect:../";
    }


    @Scheduled(cron = "0 0 0 1 9 ?") //happens at the beginning of September, when transfer window ends
    public void updatePlayers() {
        List<Player> players = playerRepository.findAll();
        Season currentSeason = seasonRepository.findLast();

        players.stream()
                .filter(p -> !p.getLastSeason().getId().equals(currentSeason.getId()) && p.isValid())
                .forEach(p->{
                    List<Club> playerClubs = p.getClubs();
                    playerClubs.add(p.getLastClub());
                    p.setClubs(playerClubs);

                    List<Season> playerSeasons = p.getSeasons();
                    playerSeasons.add(currentSeason);
                    p.setSeasons(playerSeasons);

                    playerRepository.save(p);
                });
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        if (LocalDate.now().getMonthValue()!=TRANSFER_MONTH) {
            model.addAttribute("errorMessage", "Changes can be made only during transfer window.");
            return "player/addPlayer";
        }

        emailService.sendMail(ADMIN_EMAIL,
                "Player " + playerRepository.findPlayerById(id).getFirstName() + " " + playerRepository.findPlayerById(id).getLastName() + " has been deleted.",
                "Player " +
                        playerRepository.findPlayerById(id).getFirstName() + " " + playerRepository.findPlayerById(id).getLastName() +
                        "has been deleted. Deleted coach info: " + "\n" +
                        getPlayerInformation(playerRepository.findPlayerById(id)));

        playerRepository.deleteById(id);
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
    public String clubPlayers(@PathVariable Long id, Model model) {

        List<Player> clubPlayers = playerRepository.findPlayerByClub(id);
        List<Player> currentClubPlayers = clubPlayers.stream()
                .filter(p -> (p.getLastClub().getName().equals(clubRepository.findClubById(id).getName())) && p.isValid())
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("players",currentClubPlayers);
        return "player/playersList";
    }


    @GetMapping("/season/{id}")
    public String seasonPlayers(@PathVariable Long id, Model model) {

        List<Player> players = playerRepository.findAll();
        Season season = seasonRepository.findById(id).orElse(null);

        List<Player> seasonPlayers = players.stream()
                .filter(p -> (p.getSeasons().stream()
                        .anyMatch(s -> s.getId().equals(season.getId()))))
                .filter(Player::isValid)
                .collect(Collectors.toList());


        model.addAttribute("players",seasonPlayers);
        return "player/playersList";
    }


    public String getPlayerInformation(Player player){
        StringBuilder playersAndSeasons = new StringBuilder();

        for(int i = 0; i < player.getClubs().size(); i++){
            playersAndSeasons.append(player.getClubs().get(i).getName())
                    .append(" in season: ")
                    .append(player.getSeasons().get(i).getName())
                    .append("\n");
        }

        return
                "Player license no.: " + player.getId() + "\n" +
                        "Name: " + player.getFirstName() + " " + player.getLastName() + "\n" +
                        "Date of birth: " + player.getDateOfBirth() + "\n" +
                        "Age category: " + player.getAgeCategory() + "\n" +
                        "Gender: " + player.getGender() + "\n" +
                        "Valid: " + player.isValid() + "\n" +
                        "Clubs: \n" +
                        playersAndSeasons.toString()
                ;
    }

}
