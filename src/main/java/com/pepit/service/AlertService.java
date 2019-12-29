package com.pepit.service;

import com.pepit.dto.AlertDto;

import java.util.List;

public interface AlertService {
    List<AlertDto> getUnconsultedAlertsByUser();
    List<AlertDto> getAllAlertsByUser();
    Integer getNumberOfUnseenNotifications();

}
