package com.springassignment.bookservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private long id;
    private String coverSource;
    private String name;
    private String author;
    private String description;
    private BigDecimal price;
}
