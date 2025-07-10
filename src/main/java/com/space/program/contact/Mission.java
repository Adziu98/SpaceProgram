package com.space.program.contact;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Mission {
    private String destination;
    private MissionStatus missionStatus;
}
