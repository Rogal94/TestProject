package pl.coderslab.TestProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.TestProject.model.UserService;

import java.io.File;

@Controller
@RequestMapping("/add")
public class AddController {
    private final UserService userService;

    public AddController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String home() {
        return "add";
    }

    @PostMapping("")
    public String upload(@RequestParam("file") MultipartFile file, Model model) {

        model.addAttribute("info", userService.addUsers(file));
        return "addInfo";
    }
}
