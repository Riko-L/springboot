package com.rko.myspring.controller.web;

import com.rko.myspring.model.Message;
import com.rko.myspring.model.User;
import com.rko.myspring.repository.MessageRepository;
import com.rko.myspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(path = "/user")
    public String listAllUser(Model model) {

        model.addAttribute("users", userRepository.findAll());

        return "list";
    }

    @GetMapping(path = "/user/{id}")
    public String userProfil(@PathVariable long id, Model model) {

        List<Message> messages = new ArrayList<Message>();
        Optional<User> user = userRepository.findById(id);

        for (Message msg : messageRepository.findAll()) {
            if(msg.getDestinataire().getId() == id){
                messages.add(msg);
            }
        }

        if (user.isPresent()) {

            model.addAttribute("user", user.get());
            model.addAttribute("msg", messages);
            return "profil";
        }

        return "404";
    }

//    @GetMapping(path = "/registration")
//    public String registerForm(Model model) {
//
//        model.addAttribute("user", new User());
//        return "register/register";
//    }
//
//    @PostMapping(path = "/registration")
//    public String registerSubmit(@Valid @ModelAttribute User user, BindingResult bindingResult) {
//
//
//        if (bindingResult.hasErrors()) {
//            return "register/register";
//        }
//
//         try {
//
//             userRepository.save(user);
//
//         }catch(Exception e)  {
//            ObjectError error = new FieldError("user","email","An account already exists for this email.");
//
//            bindingResult.addError(error);
//
//            bindingResult.getModel();
//
//
//            return "register/register";
//
//         }
//
//
//        return "redirect:/user";
//    }

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


        return "redirect:/user/" + userForm.getId();

    }

    @DeleteMapping(path = "/user/{id}")
    public String userDelete(@PathVariable long id) {
        userRepository.deleteById(id);

        return "redirect:/user";
    }

    @PostMapping(path = "/ami/{id_user}/add/{id}")
    public String addFriend(@PathVariable(name = "id_user") Long id_user, @PathVariable(name = "id") Long id) {


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
        List<User> listUser = new ArrayList<User>();
        User utemp = null;

        Optional<User> u = userRepository.findById(id);

        if (u.isPresent()) {

            utemp = u.get();

        }
        for (User user : userRepository.findAll()) {

            if (user.getId() != id && !utemp.getAmis().contains(user)) {
                listUser.add(user);
            }

        }
        model.addAttribute("users", listUser);
        model.addAttribute("user_id", id);

        return "addFriends";
    }

}
