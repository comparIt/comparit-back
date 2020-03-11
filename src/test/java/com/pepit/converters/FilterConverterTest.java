package com.pepit.converters;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.constants.TypeAlertEnum;
import com.pepit.dto.FilterDto;
import com.pepit.dto.UserDto;
import com.pepit.model.Filter;
import com.pepit.model.User;
import com.pepit.service.UserService;
import javafx.scene.control.Alert;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class FilterConverterTest extends CompareITBackApplicationTests {
    @Autowired
    FilterConverter filterConverter;

    @Autowired
    UserService userService;

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

    private FilterDto getFilterDTO(){
        return FilterDto.builder()
                .id(1)
                .user(getUserDto())
                .isAlert(false)
                .category(RandomStringUtils.randomAlphabetic(10))
                .alertType(TypeAlertEnum.HEBDOMADAIRE)
                .orderBy(RandomStringUtils.randomAlphabetic(10))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .criterias(new HashMap<>())
                .build();
    }
    private Filter getFilter(){
        return Filter.builder()
                .id(1)
                .users(getUser())
                .alert(false)
                .category(RandomStringUtils.randomAlphabetic(10))
                .alertType(TypeAlertEnum.HEBDOMADAIRE)
                .createdAt(null)
                .updatedAt(null)
                .criterias(new HashMap<>())
                .orderBy(RandomStringUtils.randomAlphabetic(10))
                .build();
    }

    @Test
    public void convertOneFilterToFilterDTO(){
        Filter filter = this.getFilter();
        FilterDto filterDto = filterConverter.entityToDto(filter);
        Assert.assertEquals(filterDto.id,filter.getId());
        Assert.assertEquals(filterDto.orderBy,filter.getOrderBy());
        Assert.assertEquals(filterDto.category,filter.getCategory());
        Assert.assertEquals(filterDto.isAlert, filter.isAlert());
        Assert.assertEquals(filterDto.createdAt,filter.getCreatedAt());
        Assert.assertEquals(filterDto.updatedAt,filter.getUpdatedAt());

        Assert.assertNotNull(filterDto.criterias);
        Assert.assertNotNull(filterDto.alertType);
    }

    @Test
    public void convertOneFilterDTOtoFilter(){
        FilterDto filterDto = this.getFilterDTO();
        Filter filter = filterConverter.dtoToEntity(filterDto);

        Assert.assertEquals(filterDto.id,filter.getId());
        Assert.assertEquals(filterDto.orderBy,filter.getOrderBy());
        Assert.assertEquals(filterDto.category,filter.getCategory());
        Assert.assertEquals(filterDto.isAlert, filter.isAlert());
        Assert.assertEquals(filterDto.createdAt,filter.getCreatedAt());
        Assert.assertEquals(filterDto.updatedAt,filter.getUpdatedAt());

        Assert.assertNotNull(filterDto.criterias);
        Assert.assertNotNull(filterDto.alertType);
    }



}
