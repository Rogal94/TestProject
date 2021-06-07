package pl.coderslab.TestProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.TestProject.model.User;
import pl.coderslab.TestProject.model.UserRepository;
import pl.coderslab.TestProject.model.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Controller
@RequestMapping("/show")
public class ShowController {

    private final UserService userService;

    public ShowController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("count", userService.count());
        model.addAttribute("theOldest", userService.findTheOldestWithPhoneNo());
        return "show";
    }

    @GetMapping("/list")
    public String showAll(Model model) {
        List<User> userList = userService.findAll();
        for(User user : userList) {
            Period diff= Period.between(user.getBirthDate(), LocalDate.now());
            user.setAge(diff.getYears());
        }
        model.addAttribute("userList", userList);
        return "list";
    }

    @GetMapping("/list/sorted")
    public String showSorted(Model model) {
        List<User> userList = userService.sortedByAge();

        for(User user : userList) {
            Period diff= Period.between(user.getBirthDate(), LocalDate.now());
            user.setAge(diff.getYears());
        }
        model.addAttribute("userList", userList);
        return "list";
    }

    @PostMapping("")
    public String findOne(@RequestParam String lastName, Model model) {
        List<User> userList = userService.findByLastName(lastName);
        for(User user : userList) {
            Period diff= Period.between(user.getBirthDate(), LocalDate.now());
            user.setAge(diff.getYears());
        }
        model.addAttribute("userList", userList);
        return "list";
    }
}
