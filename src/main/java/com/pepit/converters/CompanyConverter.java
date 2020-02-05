package com.pepit.converters;

import com.pepit.dto.CompanyDto;
import com.pepit.model.Company;
import com.pepit.model.User;
import com.pepit.repository.UserRepository;
import com.pepit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyConverter extends GenericsConverter<Company, CompanyDto> {

    @Autowired
    UserRepository userRepository;

    @Override
    public CompanyDto entityToDto(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .providerName(company.getProviderName())
                .providerEmail(company.getProviderEmail())
                .site(company.getSite())
                .adress(company.getAdress())
                .telephone(company.getTelephone())
                .presentation(company.getPresentation())
                .members(company.getMembers().stream().map(User::getId).collect(Collectors.toList()))
                .build();
    }

    @Override
    public Company dtoToEntity(CompanyDto companyDto) {

        List<User> userList = new ArrayList<>();

        //TODO faire une jolie lambda
        userRepository.findAll().forEach(user -> {
            if(companyDto.getMembers().contains(user.getId())) userList.add(user);
        });

        return Company.builder()
                .id(companyDto.getId())
                .providerName(companyDto.getProviderName())
                .providerEmail(companyDto.getProviderEmail())
                .site(companyDto.getSite())
                .adress(companyDto.getAdress())
                .telephone(companyDto.getTelephone())
                .presentation(companyDto.getPresentation())
                .members(userList)
                .build();
    }
}
