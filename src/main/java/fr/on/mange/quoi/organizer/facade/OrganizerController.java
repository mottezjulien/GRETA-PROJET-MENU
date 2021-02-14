package fr.on.mange.quoi.organizer.facade;


import fr.on.mange.quoi.generic.exception.ApplicationServiceException;
import fr.on.mange.quoi.organizer.domain.service.OrganizerService;
import fr.on.mange.quoi.organizer.facade.dto.OrganizerDTO;
import fr.on.mange.quoi.organizer.facade.wrapper.OrganizerDTOWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class OrganizerController {

    @Autowired
    private OrganizerDTOWrapper wrapper;

    @Autowired
    private OrganizerService organizerService;

    @GetMapping("/")
    public ModelAndView home() {

        try {
            ModelAndView modelAndView = new ModelAndView("organizer");
            modelAndView.addObject("organizer", wrapper.fromModel(organizerService.findAny()));
            return modelAndView;
        } catch (ApplicationServiceException e) {
            e.printStackTrace();
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        }



    }

}
