package com.pepit.util;

import com.mysql.cj.xdevapi.Collection;
import com.mysql.cj.xdevapi.FindStatement;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;

@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@Getter
public class Query {

    private List<String> criterias = new ArrayList<>();
    private Map<String, Object> boundParams = new HashMap<>();

    private Integer offset;
    private Integer limit;

    private String sort;

    public Query addAllCriterias(Map<String, String> params) {
        params.forEach(this::addCriteria);
        return this;
    }

    private void addCriteria(String fieldName, String unparsedValue) {
        switch (fieldName) {
            case "a" : addInterval(fieldName,unparsedValue); break;
            case "os" : addEnumeration(fieldName,unparsedValue); break;
        }
    }

    public Query addType(String unparsedType) {
        if(unparsedType != null){
            this.boundParams.put("type", unparsedType);
            this.criterias.add("typeProduct = :type");
        }
        return this;
    }

    public Query addSupplier(String unparsedType) {
        if(unparsedType != null){
            this.boundParams.put("supplier", unparsedType);
            this.criterias.add("Fournisseur = :supplier");
        }
        return this;
    }

    public Query addSorting(String sortingPredicate){
        if(sortingPredicate != null) {
            String field = sortingPredicate.startsWith("-") ? sortingPredicate.substring(1) : sortingPredicate;
            String direction = sortingPredicate.startsWith("-") ? "DESC" : "ASC";
            this.sort = field + " " + direction;
        }
        return this;
    }

    public Query page(Integer page){
        this.offset = page != null ? (page-1)*10 : 0;
        this.limit = page != null ? ((page-1)*10)+10 : 10;
        return this;
    }

    private Query addInterval(String field, String v) throws NumberFormatException {
        String[] interval = v.split("-");
        Integer min;
        Integer max;

        try {
            min = Integer.parseInt(interval[0]);
        } catch (NumberFormatException e){
            min = null;
        }

        try {
            max = Integer.parseInt(interval[1]);
        } catch (NumberFormatException e){
            max = null;
        }

        return this.addInterval(field, min, max);
    }

    private Query addInterval(String field, Integer min, Integer max){
        if(min != null){
            String paramMin = field+"min";
            String criteriaMin = "properties."+field + " >= :" + paramMin;
            this.boundParams.put(paramMin, min);
            this.criterias.add(criteriaMin);
        }

        if(max != null){
            String paramMax = field +"max";
            String criteriaMax = field + " <= :" + paramMax;
            this.boundParams.put(paramMax, max);
            this.criterias.add(criteriaMax);
        }

        return this;
    }

    private Query addEnumeration(String field, String v) {
        String[] interval = v.split(",");
        List<String> values = Arrays.asList(interval);

        return this.addEnumeration(field, values);
    }

    private Query addEnumeration(String field, List<String> values){
        List<String> bindParams = new ArrayList<>();
        for(int i = 0; i < values.size(); i++){
            String bindParam = field+i;
            bindParams.add(bindParam);
            boundParams.put(bindParam, values.get(i));
        }

        StringBuilder sb = new StringBuilder();
        sb.append("properties.").append(field).append(" in ").append("(");
        bindParams.stream().map(p -> ":"+p).reduce((x, y) -> x + ", " + y).map(sb::append);
        sb.append(")");

        this.criterias.add(sb.toString());
        return this;
    }

    private String criteriasAsStatement(){
        return this.criterias.stream().reduce((x,y) -> x + " AND " + y).orElse("");
    }

    public FindStatement toStatement(Collection collection) {
        FindStatement statement = collection.find(this.criteriasAsStatement());
        statement.offset(this.offset);
        statement.limit(this.limit);
        statement.sort(this.sort);
        statement.bind(this.boundParams);
        return statement;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        criterias.forEach(c -> sb.append("criteria : ").append(c).append("\n"));

        sb.append("statement : ").append(criteriasAsStatement()).append("\n");

        for(Map.Entry<String, Object> e : boundParams.entrySet()){
            sb.append("param : ").append(e.getKey()).append(" : ").append(e.getValue()).append("\n");
        }

        sb.append("Sorted : ").append(this.sort).append("\n");
        sb.append("Pagination : from ").append(this.offset).append(" to ").append(this.limit);

        return sb.toString();
    }

}
