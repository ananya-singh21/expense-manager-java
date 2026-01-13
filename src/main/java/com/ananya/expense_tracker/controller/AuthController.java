package com.ananya.expense_tracker.controller;

import com.ananya.expense_tracker.entity.User;
import com.ananya.expense_tracker.service.UserService;
import com.ananya.expense_tracker.service.ExpenseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;
    private final ExpenseService expenseService;

    // constructor injection
    public AuthController(UserService userService, ExpenseService expenseService) {
        this.userService = userService;
        this.expenseService = expenseService;
    }

    // when user goes to "/" → send to login page
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    // show register form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // handle register submit
    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        userService.register(user);
        model.addAttribute("success", "Registration successful! Please login.");
        return "login";
    }

    // show login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // handle login form submit
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        User user = userService.login(email, password);
        if (user == null) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }

        // store logged in user in session
        session.setAttribute("loggedInUser", user);
        return "redirect:/dashboard";
    }

    // dashboard
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("name", user.getName());
        model.addAttribute("totalThisMonth", expenseService.getTotalForCurrentMonth(user));
        model.addAttribute("totalCount", expenseService.getCountForUser(user));
        model.addAttribute("recentExpenses", expenseService.getRecent(user));

        return "dashboard";
    }

    // logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
