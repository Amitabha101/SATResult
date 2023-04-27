package com.precize.io.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertDataDTO {

    @NonNull
    private String name;

    @NonNull
    private String city;

    @NonNull
    private String country;

    @NonNull
    private String pincode;

    @NonNull
    private double satScore;

}
