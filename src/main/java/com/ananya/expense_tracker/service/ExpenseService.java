package com.ananya.expense_tracker.service;

import com.ananya.expense_tracker.entity.Expense;
import com.ananya.expense_tracker.entity.User;
import com.ananya.expense_tracker.repository.CategoryRepository;
import com.ananya.expense_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    // Constructor injection
    public ExpenseService(ExpenseRepository expenseRepository,
                          CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    public void saveExpenseForUser(Expense expense, Long categoryId, User user) {
        expense.setUser(user);
        expense.setCategory(categoryRepository.findById(categoryId).orElse(null));
        expenseRepository.save(expense);
    }

    public List<Expense> getAllForUser(User user) {
        return expenseRepository.findByUser(user);
    }

    public Expense getById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public void save(Expense expense) {
        expenseRepository.save(expense);
    }

    public void delete(Long id) {
        expenseRepository.deleteById(id);
    }

// Get total expense of current month
public BigDecimal getTotalForCurrentMonth(User user) {
    LocalDate now = LocalDate.now();
    LocalDate start = now.withDayOfMonth(1);
    LocalDate end = now.withDayOfMonth(now.lengthOfMonth());

    return expenseRepository
            .findByUserAndExpenseDateBetween(user, start, end)
            .stream()
            .map(Expense::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
}

    // total number of expenses user made
    public long getCountForUser(User user) {
        return expenseRepository.findByUser(user).size();
    }

    // get 5 recent expenses
    public List<Expense> getRecent(User user) {
        List<Expense> all = expenseRepository.findByUser(user);
        all.sort(Comparator.comparing(Expense::getExpenseDate).reversed());
        return all.stream().limit(5).toList();
    }
}