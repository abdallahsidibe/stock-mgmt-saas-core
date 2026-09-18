package com.tech.saas.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyTrendEntry {

    private int year;
    private int month;
    private String monthLabel;
    private long inCount;
    private long outCount;
}
