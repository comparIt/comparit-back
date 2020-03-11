package com.pepit.business;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.constants.TypeAlertEnum;
import com.pepit.converters.FilterConverter;
import com.pepit.converters.UserConverter;
import com.pepit.dto.FilterCriteriaDto;
import com.pepit.dto.FilterDto;
import com.pepit.dto.UserDto;
import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.Filter;
import com.pepit.model.User;
import com.pepit.service.FilterService;
import com.pepit.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class FilterBusinessTest extends CompareITBackApplicationTests {

    @Autowired
    FilterConverter filterConverter;

    @Autowired
    UserService userService;

    @Autowired
    UserConverter userConverter;

    @Autowired
    FilterBusiness filterBusiness;

    @Autowired
    FilterService filterService;

    private FilterCriteriaDto filterCriteriaDto;

    @Before
    public void initTests() throws ReferentielRequestException {
        initDatas();
        initMocks();
    }

    private void initDatas() {
        filterCriteriaDto = new FilterCriteriaDto();
    }

    private void initMocks() {
        Mockito.when(filterConverter.dtoToEntity(Mockito.any(FilterDto.class))).thenReturn(getFilter());
        Mockito.when(filterService.create(Mockito.any(Filter.class))).thenReturn(getFilterDTO());
        Mockito.when(filterService.getAllFiltersByUser(Mockito.any())).thenReturn(new ArrayList<>());
        Mockito.when(userService.getUserByToken()).thenReturn(getUser());
    }

    private UserDto getUserDto() {
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

    private User getUser() {
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

    private FilterDto getFilterDTO() {
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

    private Filter getFilter() {
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
    public void createFromCriteriaDTOOk() {
        Assert.assertEquals(filterBusiness.createFromCriteriaDto(filterCriteriaDto),getFilterDTO());
    }

    @Test(expected = Exception.class)
    public void createFromCriteriaDTOKO(){
        Mockito.when(filterConverter.dtoToEntity(Mockito.any())).thenThrow(new Exception());
        filterBusiness.createFromCriteriaDto(filterCriteriaDto);
    }

    @Test(expected = Exception.class)
    public void createFromCriteriaDTOKO2(){
        Mockito.when(filterService.create(Mockito.any())).thenThrow(new Exception());
        filterBusiness.createFromCriteriaDto(filterCriteriaDto);
    }

    @Test
    public void getAllFiltersByUserOk(){
        Assert.assertEquals(new ArrayList<>(),filterService.getAllFiltersByUser(getUser()));
    }

    @Test(expected = Exception.class)
    public void getAllFiltersKO(){
        Mockito.when(filterService.getAllFiltersByUser(Mockito.any())).thenThrow(new Exception());
        filterBusiness.getAllFiltersByUser();
    }

    @Test(expected = Exception.class)
    public void removeFilterKO(){
        Mockito.when(userService.getUserByToken()).thenThrow(new Exception());
        filterBusiness.removeFilter(1);
    }

}
