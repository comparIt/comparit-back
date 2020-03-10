package com.pepit.business;

import com.pepit.dto.FilterDto;
import com.pepit.dto.ProductDto;
import com.pepit.dto.ProductPagineDTO;
import com.pepit.mail.MailSender;
import com.pepit.model.Filter;
import com.pepit.service.FilterService;
import com.pepit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlertScheduledGenerator {

    @Autowired
    ProductService productService;

    @Autowired
    FilterService filterService;

    @Scheduled(fixedRate = 300000L)
    public void generateAlertForMinutesFilterAlert() {
        createAlertsAndSendMail(filterService.getAllFilterWithAlertActivatedAndMinutes());
    }

    @Scheduled(fixedRate = 86400000L)
    public void generateAlertForDailyFilterAlert() {
        createAlertsAndSendMail(filterService.getAllFilterWithAlertActivatedAndDaily());
    }

    @Scheduled(fixedRate = 604800000L)
    public void generateAlertForWeeklyFilterAlert() {
        createAlertsAndSendMail(filterService.getAllFilterWithAlertActivatedAndWeekly());
    }

    @Scheduled(fixedRate = 2592000000L)
    public void generateAlertForMonthlyFilterAlert() {
        createAlertsAndSendMail(filterService.getAllFilterWithAlertActivatedAndMonthly());
    }

    private void createAlertsAndSendMail(List<Filter> dailyAlertedFilter) {
        dailyAlertedFilter.forEach(filter -> {
            HashMap<String, String> mapForAlert = new HashMap<>();
            filter.getCriterias().forEach((key, value) -> mapForAlert.put(key.getTechnicalName(), value));
            ProductPagineDTO productPagineDTO = productService
                    .search(mapForAlert, null, null, null, filter.getCategory());
            List<ProductDto> productDtos = productPagineDTO.getProductsToDisplay()
                    .stream()
                    .limit(5)
                    .collect(Collectors.toList());
            if (!productDtos.isEmpty())
                MailSender.sendGridMail(productDtos, filter.getUsers().email);
        });
    }


}
