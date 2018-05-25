package com.rko.myspring.controller.web;


import com.rko.myspring.model.Message;
import com.rko.myspring.model.User;
import com.rko.myspring.repository.MessageRepository;
import com.rko.myspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MessageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;



    @GetMapping(path = "/message/{id}")
    public String getAllMessage(@PathVariable Long id , Model model) {
        User user = null;
        List<Message> messages = new ArrayList<Message>();
        Optional<User> u = userRepository.findById(id);

        if(u.isPresent()){
            user = u.get();
        }
        for (Message message : messageRepository.findAll()) {
            if(message.getDestinataire().getId() == id ){
                messages.add(message);
            }

        }
        model.addAttribute("messages" , messages);
        return "messages";
    }

    @GetMapping(path = "/message/new/{user_id}")
    public String sendMessageForm(@PathVariable Long user_id, Model model) {

        List<User> listUser = new ArrayList<User>();
        User u = new User();
        Optional<User> userTemp = userRepository.findById(user_id);

        if(userTemp.isPresent()){
            u = userTemp.get();
        }

        for (User user : userRepository.findAll()) {

            if (user.getId() != user_id) {
                listUser.add(user);
            }

        }

        model.addAttribute("users", listUser);
        model.addAttribute("msg", new Message());
        model.addAttribute("user_id" ,user_id);
        model.addAttribute("userRequest", u);

        return "message/message";
    }


    @PostMapping(path = "/message/new")
    public String sendMessageSubmit(@Valid @ModelAttribute Message msg, BindingResult bindingResul) {

            if (bindingResul.hasErrors()){
                return "message/message";
            }
         messageRepository.save(msg);
        return "redirect:/user";
    }


    @DeleteMapping(path = "/message/{id}")
    public String deleteMsg(@PathVariable Long id) {

        messageRepository.deleteById(id);

        return "redirect:/user";

    }

}
