package com.pepit.converters;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.dto.UserDto;
import com.pepit.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserConverterTest extends CompareITBackApplicationTests {

    @Autowired
    UserConverter userConverter;

    private UserDto getUserDto(){
        return UserDto
                .builder()
                .id(1)
                .password(RandomStringUtils.randomAlphabetic(10))
                .email(RandomStringUtils.randomAlphabetic(10))
                .firstName(RandomStringUtils.randomAlphabetic(10))
                .lastName(RandomStringUtils.randomAlphabetic(10))
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();
    }
    private User getUser(){
        return User
                .builder()
                .id(1)
                .password(RandomStringUtils.randomAlphabetic(10))
                .email(RandomStringUtils.randomAlphabetic(10))
                .firstName(RandomStringUtils.randomAlphabetic(10))
                .lastName(RandomStringUtils.randomAlphabetic(10))
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    public void convertOneUserToUserDto(){
        User user = this.getUser();
        UserDto userDto = userConverter.entityToDto(user);
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getUpdatedAt(), user.getUpdatedAt());
        assertEquals(userDto.getCreatedAt(), user.getCreatedAt());
        assertEquals(userDto.getId(), user.getId());

        assertNull(userDto.getPassword());
    }

    @Test
    public void convertOneUserDtoToUser(){
        User user = this.getUser();
        UserDto userDto = userConverter.entityToDto(user);
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getUpdatedAt(), user.getUpdatedAt());
        assertEquals(userDto.getCreatedAt(), user.getCreatedAt());
        assertEquals(userDto.getId(), user.getId());

        assertNull(userDto.getPassword());
    }

    @Test
    public void convertListUserDtoToListUser(){
        List<User> userList = new ArrayList<>();
        userList.add(this.getUser());
        userList.add(this.getUser());
        userList.add(this.getUser());
        userList.add(this.getUser());

        List<UserDto> userDtoList = userConverter.entityListToDtoList(userList);

        AtomicInteger atomicInteger = new AtomicInteger(0);
        userDtoList.forEach(userDto -> {
            User user = userList.get(atomicInteger.get());
            assertEquals(userDto.getEmail(), user.getEmail());
            assertEquals(userDto.getLastName(), user.getLastName());
            assertEquals(userDto.getFirstName(), user.getFirstName());
            assertEquals(userDto.getUpdatedAt(), user.getUpdatedAt());
            assertEquals(userDto.getCreatedAt(), user.getCreatedAt());
            assertEquals(userDto.getId(), user.getId());
            assertNull(userDto.getPassword());
            atomicInteger.incrementAndGet();
        });
    }

    @Test
    public void convertListUserToListUserDto(){
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(this.getUserDto());
        userDtoList.add(this.getUserDto());
        userDtoList.add(this.getUserDto());
        userDtoList.add(this.getUserDto());

        List<User> userList = userConverter.dtoListToEntityList(userDtoList);

        AtomicInteger atomicInteger = new AtomicInteger(0);
        userList.forEach(user -> {
            UserDto userDto = userDtoList.get(atomicInteger.get());
            assertEquals(userDto.getEmail(), user.getEmail());
            assertEquals(userDto.getLastName(), user.getLastName());
            assertEquals(userDto.getFirstName(), user.getFirstName());
            assertEquals(userDto.getUpdatedAt(), user.getUpdatedAt());
            assertEquals(userDto.getCreatedAt(), user.getCreatedAt());
            assertEquals(userDto.getId(), user.getId());
            assertEquals(userDto.getPassword(), user.getPassword());
            atomicInteger.incrementAndGet();
        });
    }
}
