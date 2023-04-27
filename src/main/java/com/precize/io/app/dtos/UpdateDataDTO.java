package com.precize.io.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDataDTO {
    @NonNull
    private String name;
    @NonNull
    private double satScore;

}
