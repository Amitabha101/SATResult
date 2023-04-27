package com.precize.io.app.models;

import com.precize.io.app.enums.SATResultStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor()
@NoArgsConstructor()
@Table(name = "satresult")
public class SATResult {
    @Id
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_address_id", referencedColumnName = "addressId")
    private Address address;

    private double satScore;

    private SATResultStatus satResultStatus;

    // Constructors, getters and setters
}