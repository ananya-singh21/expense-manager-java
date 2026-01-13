package com.ananya.expense_tracker.controller;

import com.ananya.expense_tracker.entity.Expense;
import com.ananya.expense_tracker.entity.User;
import com.ananya.expense_tracker.repository.CategoryRepository;
import com.ananya.expense_tracker.service.ExpenseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final CategoryRepository categoryRepository;

    public ExpenseController(ExpenseService expenseService,
                             CategoryRepository categoryRepository) {
        this.expenseService = expenseService;
        this.categoryRepository = categoryRepository;
    }

    private User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute("loggedInUser");
    }

    // LIST ALL EXPENSES
    @GetMapping
    public String listExpenses(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        model.addAttribute("expenses", expenseService.getAllForUser(user));
        return "expenses";  // <- VERY IMPORTANT, must be "expenses"
    }


    // SHOW ADD FORM
    @GetMapping("/add")
    public String showAddForm(HttpSession session, Model model) {
        User user = getLoggedInUser(session);
        if (user == null) return "redirect:/login";

        model.addAttribute("expense", new Expense());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("formTitle", "Add Expense");
        return "expense-form";
    }

    // SHOW EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id,
                               HttpSession session,
                               Model model) {
        User user = getLoggedInUser(session);
        if (user == null) return "redirect:/login";

        Expense expense = expenseService.getById(id);
        if (expense == null) return "redirect:/expenses";

        model.addAttribute("expense", expense);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("formTitle", "Edit Expense");
        return "expense-form";
    }

    // SAVE (ADD OR EDIT)
    @PostMapping("/save")
    public String saveExpense(@ModelAttribute("expense") Expense expense,
                              @RequestParam("categoryId") Long categoryId,
                              HttpSession session) {

        User user = getLoggedInUser(session);
        if (user == null) return "redirect:/login";

        expenseService.saveExpenseForUser(expense, categoryId, user);
        return "redirect:/expenses";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id,
                                HttpSession session) {
        User user = getLoggedInUser(session);
        if (user == null) return "redirect:/login";

        expenseService.delete(id);
        return "redirect:/expenses";
    }
}
