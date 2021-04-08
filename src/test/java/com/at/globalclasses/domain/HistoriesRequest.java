package com.at.globalclasses.domain;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class HistoriesRequest {
    private Integer type;
    private String user_id;
    private Integer totalHours, totalDays;
    private Integer status;
    private Double costDay, costHour, projectCost, taxIVA, taxISR_r, taxIVA_r, total, revenue;

}





