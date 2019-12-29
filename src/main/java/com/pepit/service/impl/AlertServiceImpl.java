package com.pepit.service.impl;

import com.pepit.converters.AlertConverter;
import com.pepit.dto.AlertDto;
import com.pepit.exception.NoResultException;
import com.pepit.repository.AlertRepository;
import com.pepit.service.AlertService;
import com.pepit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    public AlertRepository alertRepository;

    @Autowired
    public UserService userService;

    @Autowired
    public AlertConverter alertConverter;

    @Override
    public List<AlertDto> getUnconsultedAlertsByUser() {
        return alertConverter.entityListToDtoList(alertRepository.findAllByUserAndIsConsultedIsFalse(userService.getUserByToken())
                .orElseThrow(NoResultException::new));
    }

    @Override
    public List<AlertDto> getAllAlertsByUser() {
        return alertConverter.entityListToDtoList(alertRepository.findAllByUser(userService.getUserByToken())
                .orElseThrow(NoResultException::new));
    }

    @Override
    public Integer getNumberOfUnseenNotifications() {
        return this.getUnconsultedAlertsByUser().size();
    }

}
