package org.lforeman.controllers;

import org.lforeman.models.Grocery;
import org.lforeman.models.User;
import org.lforeman.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.lforeman.controllers.EmailController.sendEmails;


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
                // testing. delete later.
                try {
                    sendEmails(user.getEmail());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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
        public String checkLogin(Model model, @ModelAttribute User user, HttpServletRequest request,
                                 HttpServletResponse response){
            List<User> userList = userDao.findByUsername(user.getUsername());
            if(userList.get(0).getPassword().equals(user.getPassword())) {
                String username = userList.get(0).getUsername();
                Cookie myCookie =
                        new Cookie("name", username);
                response.addCookie(myCookie);
                List<Grocery> groceries = user.getGroceries();
                new ArrayList<Grocery>(groceries);
                model.addAttribute("groceries", groceries);
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

