package com.learncamel.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Country {

    String name;
    String alpha3Code;
    String population;
}
