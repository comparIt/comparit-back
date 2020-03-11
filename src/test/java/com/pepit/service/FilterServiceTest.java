package com.pepit.service;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.constants.TypeAlertEnum;
import com.pepit.dto.FilterDto;
import com.pepit.dto.UserDto;
import com.pepit.exception.DataProvidedException;
import com.pepit.model.Filter;
import com.pepit.model.User;
import com.pepit.repository.FilterRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterServiceTest extends CompareITBackApplicationTests {

    @Autowired
    FilterService filterService;

    @MockBean
    FilterRepository filterRepository;

    public List<Filter> filtersMinutes;
    public List<Filter> filtersDaily;
    public List<Filter> filtersWeekly;
    public List<Filter> filtersMonthly;

    Filter filterDaily;
    Filter filterMinutes;
    Filter filterWeekly;
    Filter filterMonthly;

    public List<FilterDto> listDTO;

    @Before
    public void initTests() {
        initDatas();
        initMocks();
    }

    private void initMocks() {
        Mockito.when(filterRepository.findAllByUsers(Mockito.any())).thenReturn(filtersDaily);
        Mockito.when(filterRepository.findAllByAlertIsTrueAndAlertTypeEquals(TypeAlertEnum.QUOTIDIENNE)).thenReturn(filtersDaily);
        Mockito.when(filterRepository.findAllByAlertIsTrueAndAlertTypeEquals(TypeAlertEnum.MINUTES)).thenReturn(filtersMinutes);
        Mockito.when(filterRepository.findAllByAlertIsTrueAndAlertTypeEquals(TypeAlertEnum.HEBDOMADAIRE)).thenReturn(filtersWeekly);
        Mockito.when(filterRepository.findAllByAlertIsTrueAndAlertTypeEquals(TypeAlertEnum.MENSUELLE)).thenReturn(filtersMonthly);
    }

    private void initDatas() {
        filtersMinutes = new ArrayList<>();
        filtersDaily = new ArrayList<>();
        filtersWeekly = new ArrayList<>();
        filtersMonthly = new ArrayList<>();

        Filter filterDaily = getFilter();
        Filter filterMinutes = getFilter();
        Filter filterWeekly = getFilter();
        Filter filterMonthly = getFilter();

        filterDaily.setAlertType(TypeAlertEnum.QUOTIDIENNE);
        filterMinutes.setAlertType(TypeAlertEnum.MINUTES);
        filterMonthly.setAlertType(TypeAlertEnum.MENSUELLE);

        filtersMinutes.add(filterMinutes);
        filtersDaily.add(filterDaily);
        filtersWeekly.add(filterWeekly);
        filtersMonthly.add(filterMonthly);
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
    public void createOk(){
        Assert.assertEquals(getFilterDTO(),filterService.create(filterDaily));
    }

    @Test
    public void getAllFiltersByUser(){
        Assert.assertEquals(listDTO,filterService.getAllFiltersByUser(getUser()));
    }

    @Test(expected = Exception.class)
    public void getAllFiltersByUserKO(){
        Mockito.when(filterRepository.findAllByUsers(getUser())).thenThrow(new Exception());
        filterService.getAllFiltersByUser(getUser());
    }

    @Test(expected = DataProvidedException.class)
    public void removeFilterOK(){
        Mockito.when(filterRepository.findById(Mockito.anyInt())).thenThrow(new DataProvidedException());
        filterService.removeFilter(1,getUser());
    }

    @Test
    public void getAllFilterWithAlertMinutesOK(){
        Assert.assertEquals(filterMinutes,filterService.getAllFilterWithAlertActivatedAndMinutes().get(0));
    }

    @Test(expected = Exception.class)
    public void getAllFilterWithAlertMinutesKO(){
        Mockito.when(filterRepository.findAllByAlertIsTrueAndAlertTypeEquals(Mockito.any())).thenThrow(new Exception());
        filterService.getAllFilterWithAlertActivatedAndMinutes();
    }

    @Test
    public void getAllFilterWithAlertDailyOK(){
        Assert.assertEquals(filterDaily, filterService.getAllFilterWithAlertActivatedAndDaily().get(0));
    }

    @Test(expected = Exception.class)
    public void getAllFilterWithAlertDailyKO(){
        Mockito.when(filterRepository.findAllByAlertIsTrueAndAlertTypeEquals(Mockito.any())).thenThrow(new Exception());
        filterService.getAllFilterWithAlertActivatedAndDaily();
    }

    @Test
    public void getAllFilterWithAlertWeeklyOK(){
        Assert.assertEquals(filterWeekly,filterService.getAllFilterWithAlertActivatedAndWeekly().get(0));
    }

    @Test(expected = Exception.class)
    public void getAllFilterWithAlertWeeklyKO(){
        Mockito.when(filterRepository.findAllByAlertIsTrueAndAlertTypeEquals(Mockito.any())).thenThrow(new Exception());
        filterService.getAllFilterWithAlertActivatedAndWeekly();
    }

    @Test
    public void getAllFilterWithAlertMonthlyOK(){
        Assert.assertEquals(filterMonthly, filterService.getAllFilterWithAlertActivatedAndMonthly().get(0));
    }

    @Test(expected = Exception.class)
    public void getAllFilterWithAlertMonthlyKO(){
        Mockito.when(filterRepository.findAllByAlertIsTrueAndAlertTypeEquals(Mockito.any())).thenThrow(new Exception());
        filterService.getAllFilterWithAlertActivatedAndMonthly();
    }






}
