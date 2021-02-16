package fr.on.mange.quoi.user.model.service;

import fr.on.mange.quoi.user.facade.dto.UserController;
import fr.on.mange.quoi.user.facade.dto.UserRegistrationDTO;
import fr.on.mange.quoi.user.facade.wrapper.UserRegistrationDTOWrapper;
import fr.on.mange.quoi.user.persistance.UserEntity;
import fr.on.mange.quoi.user.persistance.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class registerUserTest {

    @Autowired
    UserController userControllerTest;
    @Autowired
    private UserRegistrationDTOWrapper wrapper;
    @Autowired
    UserRepository userRepository;

    @Test
    public void registerUserTest() {
        UserRegistrationDTO newUser = new UserRegistrationDTO();

        newUser.setFirstname("firstNametest");
        newUser.setLastname("LastNametest");
        newUser.setEmail("mailTest@gmail.com");
        newUser.setLogin("testUser");
        newUser.setPassword("maga2020!");
        userControllerTest.registerUser(newUser);

        UserEntity testUser = userRepository.findByLogin("testUser");

        assertNotNull(testUser);
        assertEquals(newUser.getLogin(), testUser.getLogin());
    }

}