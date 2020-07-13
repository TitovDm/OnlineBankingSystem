package com.example.banking.controllers.adminController;


import com.example.banking.models.Appointment;
import com.example.banking.models.PrimaryTransaction;
import com.example.banking.models.SavingsTransaction;
import com.example.banking.models.User;
import com.example.banking.services.AppointmentService;
import com.example.banking.services.TransactionService;
import com.example.banking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping ("/mainPage")
    public String homeAdmin () {
        return "/admin";
    }

    @RequestMapping("/mainPage/")
    public String admin () {
        return "/admin";
    }

    @RequestMapping ("/usersAdmin")
    public String users (Model model) {
        List<User> users = userService.findUserList();
        model.addAttribute("users", users);
        return "usersAdmin";
    }

        @RequestMapping ("/appointmentAdmin")
    public String appointments (Model model) {
        List<Appointment> appointments = appointmentService.findAll();

        model.addAttribute("appointments", appointments);
        return "appointmentAdmin";
    }

    @RequestMapping (value = "/primaryTransactionsAdmin", method = RequestMethod.GET)
    public String getPrimaryTransactions (@RequestParam("username") String username, Model model){
        List<PrimaryTransaction> primaryTransactions = transactionService.findPrimaryTransactionList(username);
        model.addAttribute("primaryTransactions", primaryTransactions);
        model.addAttribute("username", username);

        return "primaryTransactionsAdmin";
    }

    @RequestMapping (value = "/savingsTransactionsAdmin", method = RequestMethod.GET)
    public String getSAvingsTransactions (@RequestParam("username") String username, Model model) {
        List<SavingsTransaction> savingsTransactions = transactionService.findSavingsTransactionList(username);
        model.addAttribute("savingsTransactions", savingsTransactions);
        model.addAttribute("username", username);

        return "savingsTransactionsAdmin";
    }



}
