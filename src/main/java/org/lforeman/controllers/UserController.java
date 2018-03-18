package org.lforeman.controllers;

import org.lforeman.models.User;
import org.lforeman.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;


@Controller
    @RequestMapping("user")
    public class UserController {
        @Autowired
        private UserDao userDao;

        @RequestMapping(value = "add", method = RequestMethod.GET)
        public String add(Model model, @ModelAttribute User user) {
            model.addAttribute(new User());
            model.addAttribute("title", "New User");

            return "user/add";
        }

        @RequestMapping(value = "add", method = RequestMethod.POST)
        public String add(Model model, @ModelAttribute @Valid User user,
                          Errors errors, String verify){

            if(errors.hasErrors()){
                model.addAttribute(user);
                model.addAttribute("title", "New User");
                return "user/add";
            }
            if(user.getPassword().equals(verify)){
                userDao.save(user);
                return "grocery/index";
            }
            else{
                model.addAttribute(user);
                model.addAttribute("title", "New User");
                model.addAttribute("problem", "Password and Verify Password did not match.");
                return "user/add";
            }
        }
    @RequestMapping(value = "login", method = RequestMethod.GET)
        public String login(Model model, @ModelAttribute User user){
        model.addAttribute("title", "User Login");
        return "user/login";
    }
        @RequestMapping(value = "login", method = RequestMethod.POST)
        public String checkLogin(Model model, @ModelAttribute User user){
            List<User> userList = userDao.findByUsername(user.getUsername());
            if(userList.get(0).getPassword().equals(user.getPassword())) {
                model.addAttribute("title", "My Groceries");
                return "grocery/index";
            }
            else {
                model.addAttribute(user);
                model.addAttribute("title", "User Login");
                model.addAttribute("problem", "Username and password do not match");
                return "user/login";
            }
            }
    }

