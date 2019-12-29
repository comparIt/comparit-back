package com.pepit.business;

import com.pepit.model.Filter;
import com.pepit.service.FilterService;
import com.pepit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlertScheduledGenerator {

    @Autowired
    ProductService productService;

    @Autowired
    FilterService filterService;

    @Scheduled(fixedRate = 86400000L)
    public void generateAlertForDailyFilterAlert(){
        List<Filter> dailyAlertedFilter = filterService.getAllFilterWithAlertActivatedAndDaily();
    }

    @Scheduled(fixedRate = 604800000L)
    public void generateAlertForWeeklyFilterAlert(){
        List<Filter> weeklyAlertedFilter = filterService.getAllFilterWithAlertActivatedAndWeekly();
    }

    @Scheduled(fixedRate = 2592000000L)
    public void generateAlertForMonthlyFilterAlert(){
        List<Filter> monthlyAlertedFilter = filterService.getAllFilterWithAlertActivatedAndMonthly();
    }



}
