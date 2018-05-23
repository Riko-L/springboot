package com.rko.myspring.controller;


import com.rko.myspring.model.User;
import com.rko.myspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping(path = "/user")
    public String listAllUser(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "list";
    }

    @GetMapping(path = "/user/{id}")
    public String userProfil(@PathVariable long id, Model model) {

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {

            model.addAttribute("user", user.get());
            return "profil";
        }

        return "404";
    }


    @GetMapping(path = "/register")
    public String registerForm(Model model) {

        model.addAttribute("user", new User());
        return "register/register";
    }


    @PostMapping(path = "/register")
    public String registerSubmit(@ModelAttribute User user) {

        userRepository.save(user);

        return "register/registerResult";
    }


    @GetMapping(path = "/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {

            model.addAttribute("user", user.get());
            return "update/update";
        }

        return "404";

    }

    @PutMapping(path = "/update")
    public String updateSubmit(@ModelAttribute User userForm) {


        Optional<User> userBase = userRepository.findById(userForm.getId());


        if (userBase.isPresent()) {

            User n = userBase.get();
            n.setId(userForm.getId());
            n.setFirstName(userForm.getFirstName());
            n.setLastName(userForm.getLastName());
            n.setEmail(userForm.getEmail());
            n.setBirthdayDate(userForm.getBirthdayDate());
            userRepository.save(n);
        }


        return "update/updateResult";

    }

    @DeleteMapping(path = "/user/{id}")
    public String userDelete(@PathVariable long id) {
        userRepository.deleteById(id);

        return "deleteResult";
    }

    @PostMapping(path = "/ami/{id_user}/add/{id}")
    public String addFriend(@PathVariable(name = "id_user") Long id_user ,@PathVariable(name = "id") Long id) {


        Optional<User> cuser = userRepository.findById(id_user);
        Optional<User> fuser = userRepository.findById(id);

        if (cuser.isPresent() && fuser.isPresent()) {
            User u = cuser.get();
            User f = fuser.get();

            u.addAmis(f);
            f.addAmis(u);

            userRepository.save(u);
            userRepository.save(f);

        }

    return "redirect:/user/{id_user}";

    }

    @GetMapping(path = "/addlist/{id}")
    public String listForFriend(@PathVariable Long id, Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user_id" , id);

        return "addFriends";
    }
}
