package com.lei.serialization;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SerializationInfo implements Serializable {
    private static final long serialVersionUID = -6339795630504736475L;
    private Long id;
    private String name;
    private BigDecimal amount;
}