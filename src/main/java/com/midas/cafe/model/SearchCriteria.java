package com.midas.cafe.model;

import lombok.Data;

@Data
public class SearchCriteria extends Criteria {

    private String searchType;
    private String keyword;
    private String lineUp;
    private String upRegion;
    private String downRegion;
    private String upCategory;
    private String category;
    private String month;
    private String year;
}