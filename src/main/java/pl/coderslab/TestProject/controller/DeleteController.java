package pl.coderslab.TestProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.TestProject.model.UserService;

@Controller
@RequestMapping("/delete")
public class DeleteController {

    private final UserService userService;

    public DeleteController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String deleteAll() {
        userService.deleteAll();
        return "redirect:/show";
    }
    @GetMapping("/{id}")
    public String delete(@PathVariable long id) {
        userService.deleteOne(id);
        return "redirect:/show";
    }
}
