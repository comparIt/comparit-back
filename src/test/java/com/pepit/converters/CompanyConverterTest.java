package com.pepit.converters;

import com.pepit.CompareITBackApplicationTests;
import com.pepit.dto.CompanyDto;
import com.pepit.model.Company;
import com.pepit.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import java.util.ArrayList;

public class CompanyConverterTest extends CompareITBackApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyConverter companyConverter;


    private CompanyDto getCompanyDTO(){
        return CompanyDto.builder()
                .id(1L)
                .providerName(RandomStringUtils.randomAlphabetic(10))
                .providerEmail(RandomStringUtils.randomAlphabetic(10))
                .site(RandomStringUtils.randomAlphabetic(10))
                .adress(RandomStringUtils.randomAlphabetic(10))
                .telephone(RandomStringUtils.randomAlphabetic(10))
                .presentation(RandomStringUtils.randomAlphabetic(10))
                .members(new ArrayList<>())
                .createdAt(null)
                .updatedAt(null)
                .build();
    }
    private Company getCompany(){
        return Company.builder()
                .id(1L)
                .providerName(RandomStringUtils.randomAlphabetic(10))
                .providerEmail(RandomStringUtils.randomAlphabetic(10))
                .site(RandomStringUtils.randomAlphabetic(10))
                .adress(RandomStringUtils.randomAlphabetic(10))
                .telephone(RandomStringUtils.randomAlphabetic(10))
                .presentation(RandomStringUtils.randomAlphabetic(10))
                .members(new ArrayList<>())
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

    @Test
    public void convertOneCompanyToCompanyDTO(){
        Company company = this.getCompany();
        CompanyDto companyDto = companyConverter.entityToDto(company);
        Assert.assertEquals(companyDto.getId(),company.getId());
        Assert.assertEquals(companyDto.getProviderEmail(),company.getProviderEmail());
        Assert.assertEquals(companyDto.getProviderName(),company.getProviderEmail());
        Assert.assertEquals(companyDto.getSite(), company.getSite());
        Assert.assertEquals(companyDto.getAdress(),company.getAdress());
        Assert.assertEquals(companyDto.getTelephone(),company.getTelephone());
        Assert.assertEquals(companyDto.getPresentation(),company.getPresentation());
        Assert.assertEquals(companyDto.getCreatedAt(),company.getCreatedAt());
        Assert.assertEquals(companyDto.getUpdatedAt(), company.getUpdatedAt());

        Assert.assertNotNull(companyDto.getMembers());
        Assert.assertNotNull(company.getMembers());
    }

    @Test
    public void convertOneCompanyDTOToCompany(){
        CompanyDto companyDto = this.getCompanyDTO();
        Company company = companyConverter.dtoToEntity(companyDto);

        Assert.assertEquals(companyDto.getId(),company.getId());
        Assert.assertEquals(companyDto.getProviderEmail(),company.getProviderEmail());
        Assert.assertEquals(companyDto.getProviderName(),company.getProviderEmail());
        Assert.assertEquals(companyDto.getSite(), company.getSite());
        Assert.assertEquals(companyDto.getAdress(),company.getAdress());
        Assert.assertEquals(companyDto.getTelephone(),company.getTelephone());
        Assert.assertEquals(companyDto.getPresentation(),company.getPresentation());
        Assert.assertEquals(companyDto.getCreatedAt(),company.getCreatedAt());
        Assert.assertEquals(companyDto.getUpdatedAt(), company.getUpdatedAt());

        Assert.assertNotNull(companyDto.getMembers());
        Assert.assertNotNull(company.getMembers());
    }

}
