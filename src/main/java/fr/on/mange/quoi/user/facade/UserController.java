package fr.on.mange.quoi.user.facade;

import fr.on.mange.quoi.organizer.domain.service.OrganizerService;
import fr.on.mange.quoi.organizer.facade.dto.OrganizerListDTO;
import fr.on.mange.quoi.organizer.persistence.entity.OrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.repository.DayOrganizerRepository;
import fr.on.mange.quoi.organizer.persistence.repository.OrganizerRepository;
import fr.on.mange.quoi.user.facade.dto.UserRegistrationDTO;
import fr.on.mange.quoi.user.facade.wrapper.UserRegistrationDTOWrapper;
import fr.on.mange.quoi.user.model.User;
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

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private OrganizerService organizerService;


    @PostMapping(value = "/register")
    public ModelAndView registerUser(@ModelAttribute("userregisterdto") UserRegistrationDTO userDTO){
            User user = service.saveNewUser(wrapper.fromDTO(userDTO));
            OrganizerEntity organizerEntity = organizerRepository.save(new OrganizerEntity(user.getOptId().get(),"Test 1"));
            organizerService.initDays(organizerEntity);
            return new ModelAndView("accueil");
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
