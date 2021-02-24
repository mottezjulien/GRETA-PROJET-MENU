package fr.on.mange.quoi.organizer.facade.wrapper;


import fr.on.mange.quoi.organizer.domain.model.Organizer;
import fr.on.mange.quoi.organizer.facade.dto.OrganizerListDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrganizerListDTOWrapper {
    public List<OrganizerListDTO> fromModels(List<Organizer> models){
        List<OrganizerListDTO> dtos = new ArrayList<>();
        for(Organizer model: models){
            dtos.add(fromModel(model));
        }
        return dtos;
    }

    public OrganizerListDTO fromModel(Organizer model){
        OrganizerListDTO dto = new OrganizerListDTO();

        if(model.getOptId().isPresent()) {
            dto.setUuid(model.getOptId().get());
        }
        dto.setLabel(model.getLabel());

        return dto;
    }
}