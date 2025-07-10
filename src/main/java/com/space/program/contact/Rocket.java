package com.space.program.contact;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Rocket {
    private String name;
    private Mission mission;
    private RocketStatus rocketStatus;
}
