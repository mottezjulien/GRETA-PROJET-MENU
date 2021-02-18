package fr.on.mange.quoi.organizer.facade;


import fr.on.mange.quoi.generic.exception.ApplicationCommunicationException;
import fr.on.mange.quoi.generic.exception.ApplicationServiceException;
import fr.on.mange.quoi.organizer.domain.service.OrganizerService;
import fr.on.mange.quoi.organizer.facade.dto.OrganizerDTO;
import fr.on.mange.quoi.organizer.facade.wrapper.OrganizerDTOWrapper;

import fr.on.mange.quoi.organizer.facade.wrapper.OrganizerListDTOWrapper;

import fr.on.mange.quoi.user.facade.dto.UserIdDTO;
import fr.on.mange.quoi.user.facade.dto.UserIdDTOWrapper;
import fr.on.mange.quoi.user.model.service.UserService;
import fr.on.mange.quoi.user.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public ModelAndView home() {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            ModelAndView modelAndView = new ModelAndView("organizer");
            OrganizerDTO organizerDTO;
            if(auth.getAuthorities().contains(new SimpleGrantedAuthority(USER_ROLE))) {
                String login = auth.getName();

                UserIdDTO userIdDTO = userIdDTOWrapper.fromEntity(userRepository.findByLogin(login));

                modelAndView.addObject("organizers", listDTOWrapper.fromModels(organizerService.findAllByUserId(userIdDTO.getUuid())));

                organizerDTO = wrapper.fromModel(organizerService.findByUserId(userIdDTO.getUuid()));
            }else{
                organizerDTO = wrapper.fromModel(organizerService.findByLabel(ORGA_EXAMPLE));
            }
            modelAndView.addObject("organizer", organizerDTO);
            return modelAndView;
        } catch (ApplicationServiceException | ApplicationCommunicationException e) {
            e.printStackTrace();
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        }



    }

}
