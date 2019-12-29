package com.pepit.util;

import com.mysql.cj.xdevapi.*;
import com.mysql.cj.xdevapi.Collection;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;

@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
public class Query {

    private List<String> criterias = new ArrayList<>();
    private Map<String, Object> boundParams = new HashMap<>();

    private static final String PROPERTIES = "properties.";

    private Integer offset;
    private Integer limit;

    private String sort;

    public Query addAllCriterias(Map<String, String> params) {
        params.remove("type");
        params.remove("supplier");
        params.remove("order");
        params.remove("page");
        params.forEach(this::addCriteria);
        return this;
    }

    private void addCriteria(String fieldName, String unparsedValue) {
        if (unparsedValue.contains("-")) {
            addInterval(fieldName, unparsedValue);
        } else {
            addEnumeration(fieldName, unparsedValue);
        }
    }

    public Query addType(String unparsedType) {
        if (unparsedType != null) {
            this.boundParams.put("type", unparsedType);
            this.criterias.add("type = :type");
        }
        return this;
    }

    public Query addSupplier(String unparsedType) {
        if (unparsedType != null) {
            this.boundParams.put("supplier", unparsedType);
            this.criterias.add("supplierId = :supplier");
        }
        return this;
    }

    public Query addSorting(String sortingPredicate) {
        if (sortingPredicate != null) {
            String field = sortingPredicate.startsWith("-") ? sortingPredicate.substring(1) : sortingPredicate;
            String direction = sortingPredicate.startsWith("-") ? "DESC" : "ASC";
            this.sort = PROPERTIES + field + " " + direction;
        }
        return this;
    }

    public Query page(Integer page) {
        this.offset = page != null ? (page-1)*10 : 0;
        this.limit = 10;
        return this;
    }

    private void addInterval(String field, String v) throws NumberFormatException {
        String[] interval = v.split("-");
        Integer min;
        Integer max;

        try {
            min = Integer.parseInt(interval[0]);
        } catch (NumberFormatException e) {
            min = null;
        }

        try {
            max = Integer.parseInt(interval[1]);
        } catch (NumberFormatException e) {
            max = null;
        }

        this.addInterval(field, min, max);
    }

    private void addInterval(String field, Integer min, Integer max) {
        if (min != null) {
            String paramMin = field + "min";
            String criteriaMin = PROPERTIES + field + " >= :" + paramMin;
            this.boundParams.put(paramMin, min);
            this.criterias.add(criteriaMin);
        }

        if (max != null) {
            String paramMax = field + "max";
            String criteriaMax = PROPERTIES + field + " <= :" + paramMax;
            this.boundParams.put(paramMax, max);
            this.criterias.add(criteriaMax);
        }
    }

    private void addEnumeration(String field, String v) {
        String[] interval = v.split(",");
        List<String> values = Arrays.asList(interval);

        this.addEnumeration(field, values);
    }

    private void addEnumeration(String field, List<String> values) {
        List<String> bindParams = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            String bindParam = field + i;
            bindParams.add(bindParam);
            boundParams.put(bindParam, values.get(i));
        }

        StringBuilder sb = new StringBuilder();
        sb.append(PROPERTIES).append(field).append(" in ").append("(");
        bindParams.stream().map(p -> ":" + p).reduce((x, y) -> x + ", " + y).ifPresent(sb::append);
        sb.append(")");

        this.criterias.add(sb.toString());
    }

    private String criteriasAsStatement() {
        return this.criterias.stream().reduce((x, y) -> x + " AND " + y).orElse("");
    }

    public Statement<FindStatement, DocResult> find(Collection collection) {
        FindStatement statement = collection.find(this.criteriasAsStatement()).sort(this.sort);
        statement.offset(this.offset);
        statement.bind(this.boundParams);
        return statement;
    }

    public Statement<RemoveStatement, Result> delete(Collection collection) {
        return collection.remove(this.criteriasAsStatement());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        criterias.forEach(c -> sb.append("criteria : ").append(c).append("\n"));

        sb.append("statement : ").append(criteriasAsStatement()).append("\n");

        for (Map.Entry<String, Object> e : boundParams.entrySet()) {
            sb.append("param : ").append(e.getKey()).append(" : ").append(e.getValue()).append("\n");
        }

        sb.append("Sorted : ").append(this.sort).append("\n");
        sb.append("Pagination : from ").append(this.offset).append(" to ").append(this.offset + this.limit);

        return sb.toString();
    }

}
