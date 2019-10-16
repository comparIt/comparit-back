package com.pepit.service.impl;

import com.pepit.model.Product;
import com.pepit.repository.ProductRepository;
import com.pepit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> search(Map<String, String> params) {
        return productRepository.testRequest(queryBuilder(params));
    }

    private String queryBuilder(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();

        params.entrySet().stream()
                .map(e -> {
                    switch (e.getKey()){
                        case "a" : return intervalBuilder(e.getKey(), e.getValue());
                        case "os" : return enumBuilder(e.getKey(), e.getValue());
                        default: return "";
                    }
                })
                .reduce((x,y) -> x + " AND " + y)
                .map(sb::append);
        System.out.println(sb.toString());
        return sb.toString();
    }

    private String intervalBuilder(String k, String v) {
        StringBuilder sb = new StringBuilder();
        String[] interval = v.split("-");
        int min = Integer.parseInt(interval[0]);
        int max = Integer.parseInt(interval[1]);
        sb.append(k).append(" >= ").append(min).append(" AND ").append(k).append(" <= ").append(max);
        return sb.toString();
    }

    private String enumBuilder(String k, String v) {
        StringBuilder sb = new StringBuilder();
        String[] interval = v.split(",");
        sb.append(k).append(" in ");
        sb.append(" (");
        Arrays.stream(interval).map(e -> "'"+e+"'").reduce((x,y) -> x + ", " + y).map(sb::append);
        sb.append(")");
        return sb.toString();
    }
}
