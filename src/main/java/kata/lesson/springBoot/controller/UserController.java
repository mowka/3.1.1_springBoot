package kata.lesson.springBoot.controller;

import kata.lesson.springBoot.model.User;
import kata.lesson.springBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Отображение пользователей
    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "main-page";
    }

    //Добавление пользователя
    @GetMapping("/add-user")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "user-info";
    }

    //Добавление пользователя
    @PostMapping()
    public String createUser(@ModelAttribute("newUser") User user) {
        userService.save(user);
        return "redirect:/";
    }

    //Удаление пользователя
    @RequestMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @RequestMapping("/update-info/{id}")
    public String updateUser(Model model, @PathVariable("id") long id) {
        User currentUser = userService.getUser(id);
        model.addAttribute("newUser", currentUser);
        return "user-info";
    }
}
