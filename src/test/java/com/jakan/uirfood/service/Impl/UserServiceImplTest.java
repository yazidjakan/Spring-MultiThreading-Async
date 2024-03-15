package com.jakan.uirfood.service.Impl;

import com.jakan.uirfood.bean.User;
import com.jakan.uirfood.dao.UserDao;
import com.jakan.uirfood.dto.UserDto;
import com.jakan.uirfood.enums.Fonction;
import com.jakan.uirfood.transformer.UserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class UserServiceImplTest {

    @Mock private UserDao userDao;
    @Mock private UserTransformer userTransformer;
    @InjectMocks private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        log.info("Test STARTED");
    }

    @AfterEach
    void tearDown() {
        log.info("Test FINISHED");
    }

    @Test
    void shouldFindAllUsers() {
        //given
        List<User> users=new ArrayList<>();
        int cpt=0;
        User entity1=new User("",
                "jakan",
                "elyazid",
                "yazidjakan@gmail.com",
                "1234",
                Fonction.Etudiant
        );
        User entity2=new User("",
                "jakan",
                "hamza",
                "jakanhamza@gmail.com",
                "1234",
                Fonction.Personnel
        );
        users.add(entity1); cpt++;
        users.add(entity2); cpt++;

        List<UserDto> dtos=new ArrayList<>();
        UserDto dto1=new UserDto("1",
                "jakan",
                "elyazid",
                "yazidjakan@gmail.com",
                "1234",
                Fonction.Etudiant
        );
        UserDto dto2=new UserDto("2",
                "jakan",
                "hamza",
                "jakanhamza@gmail.com",
                "1234",
                Fonction.Personnel
        );
        dtos.add(dto1);
        dtos.add(dto2);

        //when
        Mockito.when(userDao.findAll()).thenReturn(users);
        Mockito.when(userTransformer.toDto(users)).thenReturn(dtos);

        //then
        List<UserDto> result=userService.findAll();
        assertEquals(cpt,result.size());
    }

    @Test
    void shouldFindUserById() {
        //given
        User entity1=new User("1",
                "jakan",
                "elyazid",
                "yazidjakan@gmail.com",
                "1234",
                Fonction.Etudiant
        );
        UserDto dto1=new UserDto("1",
                "jakan",
                "elyazid",
                "yazidjakan@gmail.com",
                "1234",
                Fonction.Etudiant
        );
        UserDto savedDTO=new UserDto("1",
                "jakan",
                "elyazid",
                "yazidjakan@gmail.com",
                "1234",
                Fonction.Etudiant
        );
        //when
        Mockito.when(userTransformer.toDto(entity1)).thenReturn(dto1);
        Mockito.when(userDao.save(entity1)).thenReturn(entity1);
        Mockito.when(userService.findById(savedDTO.id())).thenReturn(dto1);
        //then
        assertEquals(savedDTO.id(), dto1.id());
    }

    @Test
    void save() {
    }

    @Test
    void testSave() {
    }

    @Test
    void updateById() {
    }

    @Test
    void deleteById() {
    }
}