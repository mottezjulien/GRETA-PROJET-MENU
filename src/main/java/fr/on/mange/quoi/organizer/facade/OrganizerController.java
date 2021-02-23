package fr.on.mange.quoi.organizer.facade;


import fr.on.mange.quoi.generic.exception.ApplicationCommunicationException;
import fr.on.mange.quoi.generic.exception.ApplicationServiceException;
import fr.on.mange.quoi.organizer.domain.model.choice.*;
import fr.on.mange.quoi.organizer.domain.service.OrganizerService;
import fr.on.mange.quoi.organizer.facade.dto.ChoiceOrganizerTemplateDTO;
import fr.on.mange.quoi.organizer.facade.dto.NewOrganizerLabelRequestDTO;
import fr.on.mange.quoi.organizer.facade.wrapper.OrganizerDTOWrapper;
import fr.on.mange.quoi.organizer.facade.wrapper.OrganizerListDTOWrapper;
import fr.on.mange.quoi.organizer.persistence.entity.DayOrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.entity.OrganizerEntity;
import fr.on.mange.quoi.organizer.persistence.repository.ChoiceOrganizerRepository;
import fr.on.mange.quoi.organizer.persistence.repository.DayOrganizerRepository;
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

import java.lang.management.OperatingSystemMXBean;
import java.util.List;

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
    private OrganizerRepository organizerRepository;


    @Autowired
    private UserService userService;

    @Autowired
    private DayOrganizerRepository dayOrganizerRepository;

    @Autowired
    private ChoiceOrganizerRepository choiceOrganizerRepository;

    @GetMapping("/organizer")
    public ModelAndView home() {
        try {
            ModelAndView modelAndView = new ModelAndView("organizer");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (isConnected(auth)) {
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

    @GetMapping("/organizer/supOrganizer")
    public ModelAndView supOrganizer(@RequestParam("id")String uuid) throws ApplicationCommunicationException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserIdDTO userIdDTO = userIdDTOWrapper.fromEntity(userRepository.findByLogin(auth.getName()));
        if (!userIdDTO.getOrganizerId().equalsIgnoreCase(uuid)) {
            OrganizerEntity o = organizerRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException(uuid+" n'existe pas"));
            organizerRepository.delete(o);
        }
        return new ModelAndView("redirect:/organizer");
    }

    @GetMapping("/organizer/new")
    public ModelAndView newOrganizerForUser() {
        ModelAndView modelAndView = new ModelAndView("addOrganizer");
        return modelAndView;
    }

    @PostMapping("/organizer/new")
    public ModelAndView saveNewOrganizerForUser(@ModelAttribute("request") NewOrganizerLabelRequestDTO label) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (isConnected(auth)) {
            try {
                UserIdDTO userIdDTO = userIdDTOWrapper.fromEntity(userRepository.findByLogin(auth.getName()));
                OrganizerEntity organizerEntity = organizerRepository.save(new OrganizerEntity(userIdDTO.getUuid(), label.getLabel()));
                organizerService.initDays(organizerEntity);
            } catch (ApplicationCommunicationException e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("redirect:/organizer");
    }

    @PostMapping("/organizer/new/template")
    public ModelAndView saveNewOrganizerForUserWithTemplate(@ModelAttribute("request") ChoiceOrganizerTemplateDTO choiceOrganizerTemplateDTO) {
        try {
            organizerService.createFromTemplate(choiceOrganizerTemplateDTO);
        } catch (ApplicationCommunicationException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/organizer");
    }

}
