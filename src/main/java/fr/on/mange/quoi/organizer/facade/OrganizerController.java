package fr.on.mange.quoi.organizer.facade;


import fr.on.mange.quoi.generic.exception.ApplicationCommunicationException;
import fr.on.mange.quoi.generic.exception.ApplicationServiceException;
import fr.on.mange.quoi.organizer.domain.service.OrganizerService;
import fr.on.mange.quoi.organizer.facade.dto.NewOrganizerLabelRequestDTO;
import fr.on.mange.quoi.organizer.facade.wrapper.OrganizerDTOWrapper;
import fr.on.mange.quoi.organizer.facade.wrapper.OrganizerListDTOWrapper;
import fr.on.mange.quoi.organizer.persistence.entity.OrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.repository.OrganizerRepository;
import fr.on.mange.quoi.user.facade.dto.UserIdDTO;
import fr.on.mange.quoi.user.facade.dto.UserIdDTOWrapper;
import fr.on.mange.quoi.user.model.service.UserService;
import fr.on.mange.quoi.user.persistance.UserEntity;
import fr.on.mange.quoi.user.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class OrganizerController {

    private static final String USER_ROLE = "USER";
    private static final String ORGA_EXAMPLE = "Organisateur d'exemple";

    @Autowired
    private UserIdDTOWrapper userIdDTOWrapper;

    @Autowired
    private OrganizerListDTOWrapper listDTOWrapper;

    @Autowired
    private OrganizerDTOWrapper wrapper;

    @Autowired
    private OrganizerService organizerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizerRepository organizaterRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/organizer")
    public ModelAndView home() {
        try {
            ModelAndView modelAndView = new ModelAndView("organizer");

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(isConnected(auth)) {
                UserIdDTO userIdDTO = userIdDTOWrapper.fromEntity(userRepository.findByLogin(auth.getName()));
                modelAndView.addObject("organizers", listDTOWrapper.fromModels(organizerService.findAllByUserId(userIdDTO.getUuid())));
                modelAndView.addObject("organizer", wrapper.fromModel(organizerService.findById(userIdDTO.getOrganizerId())));
            } else {
                modelAndView.addObject("organizer", wrapper.fromModel(organizerService.findByLabel(ORGA_EXAMPLE)));
            }

            return modelAndView;
        } catch (ApplicationServiceException | ApplicationCommunicationException e) {
            e.printStackTrace();
            return new ModelAndView("error");
        }
    }

    @GetMapping("/organizer/{organizerId}")
    public ModelAndView setDefaultOrganizer(@PathVariable String organizerId) throws ApplicationCommunicationException {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(isConnected(auth)){
            try {
                userService.saveNewDefaultOrganizer(auth.getName(), organizerId);
                return new ModelAndView("redirect:/organizer");
            } catch (ApplicationServiceException e) {
            e.printStackTrace();
        }
            throw new ApplicationCommunicationException("User not found");
        }
        throw new ApplicationCommunicationException("User not connected");
    }



    private boolean isConnected(Authentication auth) {
        return auth.getAuthorities().contains(new SimpleGrantedAuthority(USER_ROLE));
    }

    @GetMapping("/organizer/new")
    public ModelAndView newOrganizerForUser() {
        ModelAndView modelAndView = new ModelAndView("addOrganizer");
        return modelAndView;
    }

    @PostMapping("/organizer/new")
    public ModelAndView saveNewOrganizerForUser(@ModelAttribute("request") NewOrganizerLabelRequestDTO label) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(isConnected(auth)) {
            try {
                UserIdDTO userIdDTO = userIdDTOWrapper.fromEntity(userRepository.findByLogin(auth.getName()));
                OrganizerEntity organizerEntity = organizaterRepository.save(new OrganizerEntity(userIdDTO.getUuid(), label.getLabel()));
                organizerService.initDays(organizerEntity);
            } catch (ApplicationCommunicationException e) {
                e.printStackTrace();
            }
        }
    return new ModelAndView("redirect:/organizer");
    }
}
