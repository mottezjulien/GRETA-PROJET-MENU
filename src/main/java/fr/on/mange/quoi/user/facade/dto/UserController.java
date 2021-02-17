package fr.on.mange.quoi.user.facade.dto;

import fr.on.mange.quoi.user.facade.wrapper.UserRegistrationDTOWrapper;
import fr.on.mange.quoi.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController{
    @Autowired
    private UserRegistrationDTOWrapper wrapper;

    @Autowired
    private UserService service;

    @PostMapping(value = "/register")
    public String registerUser(@ModelAttribute("userregisterdto") UserRegistrationDTO userDTO){
            service.saveNewUser(wrapper.fromDTO(userDTO));
            return "redirect:/";
    }

    @GetMapping(value = "/register")
    public ModelAndView displayRegisterForm(){
        return new ModelAndView("register");
    }

    @GetMapping(value = "/login")
    public ModelAndView displayLoginPage(){
        return new ModelAndView("login");
    }
}
