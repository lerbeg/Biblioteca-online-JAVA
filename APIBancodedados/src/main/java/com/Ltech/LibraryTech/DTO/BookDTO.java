package com.Ltech.LibraryTech.DTO;
import com.Ltech.LibraryTech.Entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Integer Id ;
    private Author author;
    private String Summary;
    private Genre Genre;
    private BigDecimal Price;

}
