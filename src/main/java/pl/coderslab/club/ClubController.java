package pl.coderslab.club;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.commons.AgeCategory;

import pl.coderslab.mail.EmailService;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/clubs")
public class ClubController {

    @Value("${admin_email}")
    public String ADMIN_EMAIL;

    @Value("${transfer_month}")
    private int TRANSFER_MONTH;


    private final ClubRepository clubRepository;
    private final EmailService emailService;


    public ClubController(ClubRepository clubRepository, EmailService emailService) {
        this.clubRepository = clubRepository;
        this.emailService = emailService;
    }


    @GetMapping("/")
    public String clubsList(Model model) {
        model.addAttribute("clubs", clubRepository.findAll());
        return "club/clubsList";
    }


    @GetMapping("/add")
    public String addClub(Model model) {
        if (LocalDate.now().getMonthValue()!= TRANSFER_MONTH) {
            model.addAttribute("errorMessage", "Changes can be made only during transfer window.");
            return "club/addClub";
        }

        model.addAttribute("club", new Club());
        return "club/addClub";
    }


    @PostMapping("/add")
    public String addClub(Model model, @ModelAttribute @Valid Club club, BindingResult bindingResult) {
        if (bindingResult.hasErrors() ) {
            return "club/addClub";
        }

        else if (LocalDate.now().getMonthValue()!=8) {
            model.addAttribute("errorMessage", "Changes can be made only during transfer window.");
            return "club/addClub";
        }

        clubRepository.save(club);

        emailService.sendMail(ADMIN_EMAIL,
                "Club " + club.getName() + " has been added.",
                "Club " +
                club.getName() +
                " has been added. Club info: " + "\n" +
                getClubInformation(club));

        return "redirect:../clubs/";
    }


    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Optional<Club> club = clubRepository.findById(id);
        model.addAttribute("club", club);
        return "club/addClub";
    }


    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model, @ModelAttribute @Valid Club club, BindingResult bindingResult) {
        if (bindingResult.hasErrors() ) {
            return "club/addClub";
        }

        else if (LocalDate.now().getMonthValue()!=TRANSFER_MONTH) {
            model.addAttribute("errorMessage", "Changes can be made only during transfer window.");
            return "club/addClub";
        }
        Club oldClub = clubRepository.findClubById(id);
        clubRepository.save(club);

        emailService.sendMail(ADMIN_EMAIL,
                "Club " + club.getName() + " has been changed.",
                "Club " +
                        club.getName() +
                        " has been changed. Club info: " + "\n" +
                        getClubInformation(club));

        return "redirect:../";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        if (LocalDate.now().getMonthValue()!=TRANSFER_MONTH) {
            model.addAttribute("errorMessage", "Changes can be made only during transfer window.");
            return "club/addClub";
        }

        emailService.sendMail(ADMIN_EMAIL,
                "Club " + clubRepository.findClubById(id).getName() + " has been deleted.",
                "Club " +
                        clubRepository.findClubById(id).getName() +
                        " has been deleted. Deleted club info: " + "\n" +
                        getClubInformation(clubRepository.findClubById(id)));
            try {
                clubRepository.deleteById(id);
            } catch (Exception e){
                model.addAttribute("errorMessage", "Club can't be deleted - have active coaches and players");
                model.addAttribute("clubs", clubRepository.findAll());
                return "club/clubsList";
            }

        return "redirect:../";
    }


    @ModelAttribute("ageCategories")
    public List<AgeCategory> ageCategories() {
        return Stream.of(AgeCategory.values())
                .collect(Collectors.toList());
    }

    public String getClubInformation(Club club){
        return
       "Club license no.: " + club.getId() + "\n" +
        "Name: " + club.getName() + "\n" +
        "Age category: " + club.getAgeCategory() + "\n" +
        "Address: " + club.getAddress().getStreetName() + " " +
               club.getAddress().getStreetNumber() + ", " +
               club.getAddress().getZipCode() + " " +
               club.getAddress().getCity() +  "\n" +
        "E-mail: " + club.getEmail() + "\n" +
        "Phone number: " + club.getPhoneNumber() + "\n" +
        "Web address: " + club.getAddress() + "\n" +
        "NIP: " + club.getNip() ;
    }


}
