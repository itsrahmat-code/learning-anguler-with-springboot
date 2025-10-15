package com.emranhss.merchandise.dto;

public class DueSummaryDTO {

    private Double last30Days;

    public DueSummaryDTO( Double last30Days) {
        this.last30Days = last30Days;
    }


    public Double getLast30Days() { return last30Days; }
    public void setLast30Days(Double last30Days) { this.last30Days = last30Days; }


}
