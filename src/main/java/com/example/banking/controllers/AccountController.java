package com.example.banking.controllers;

import com.example.banking.models.*;
import com.example.banking.services.AccountService;
import com.example.banking.services.TransactionService;
import com.example.banking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "account")
public class AccountController {


        @Autowired
        private UserService userService;

        @Autowired
        private AccountService accountService;

        @Autowired
        private TransactionService transactionService;

        @RequestMapping("/primaryAccount")
        public String primaryAccount(Model model, Principal principal) {
            List<PrimaryTransaction> primaryTransactionList =
                    transactionService.findPrimaryTransactionList(principal.getName());

            User user = userService.findByUsername(principal.getName());
            PrimaryAccount primaryAccount = user.getPrimaryAccount();

            model.addAttribute("primaryAccount", primaryAccount);
            model.addAttribute("primaryTransactionList", primaryTransactionList);

            return "primaryAccount";
        }

        @RequestMapping("/savingsAccount")
        public String savingsAccount(Model model, Principal principal) {
            List<SavingsTransaction> savingsTransactionList =
                    transactionService.findSavingsTransactionList(principal.getName());
            User user = userService.findByUsername(principal.getName());
            SavingsAccount savingsAccount = user.getSavingsAccount();

            model.addAttribute("savingsAccount", savingsAccount);
            model.addAttribute("savingsTransactionList", savingsTransactionList);

            return "savingsAccount";
        }

        @RequestMapping(value = "/deposit", method = RequestMethod.GET)
        public String deposit(Model model) {
            model.addAttribute("accountType", "");
            model.addAttribute("amount", "");

            return "deposit";
        }

        @RequestMapping(value = "/deposit", method = RequestMethod.POST)
        public String depositPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal) {
            accountService.deposit(accountType, Double.parseDouble(amount), principal);

            return "redirect:/userFront";
        }

        @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
        public String withdraw(Model model) {
            model.addAttribute("accountType", "");
            model.addAttribute("amount", "");

            return "withdraw";
        }

        @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
        public String withdrawPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType")
                String accountType, Model model, Principal principal) {
            User user = userService.findByUsername(principal.getName());
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            SavingsAccount savingsAccount = user.getSavingsAccount();
            if (accountType.equalsIgnoreCase("Primary")) {
                if (primaryAccount.getAccountBalance().compareTo(new BigDecimal(amount)) < 0) {
                    model.addAttribute("wrongTransaction", true);
                    return "withdraw";
                } else { accountService.withdrawFromPrimary(accountType, Double.parseDouble(amount), principal);}
            } else if (accountType.equalsIgnoreCase("Savings")) {
                if (savingsAccount.getAccountBalance().compareTo(new BigDecimal(amount)) < 0) {
                    model.addAttribute("wrongTransaction", true);
                    return "withdraw";
                } else {accountService.withdrawFromSavings(accountType, Double.parseDouble(amount), principal);}
            } else {model.addAttribute("selectAccount", true);
                return "withdraw"; }

            return "redirect:/userFront";
        }
}
