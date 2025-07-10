package com.space.program.service;


import com.space.program.contact.Mission;
import com.space.program.contact.MissionStatus;
import com.space.program.contact.Rocket;
import com.space.program.contact.RocketStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.mockito.Mockito;

import static com.space.program.contact.MissionStatus.ENDED;
import static com.space.program.contact.MissionStatus.SCHEDULED;
import static com.space.program.contact.RocketStatus.IN_REPAIR;
import static com.space.program.contact.RocketStatus.ON_GROUND;
import static org.junit.jupiter.api.Assertions.*;

class SpaceProgramServiceTest {
    private SpaceProgramService spaceProgramService;

    @BeforeEach
    void setUp() {
        spaceProgramService = new SpaceProgramService();
    }

    @Test
    void addMissionIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            spaceProgramService.addMission(null);
        });

        assertEquals("Null values are not allowed", exception.getMessage());
    }

    @Test
    void addMission() {
        // when
        Mission mission = spaceProgramService.addMission("EARTH");

        // then
        assertEquals("EARTH", mission.getDestination());
        assertEquals(SCHEDULED, mission.getMissionStatus());
    }

    @Test
    void addRocketIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            spaceProgramService.addRocket(null);
        });

        assertEquals("Null values are not allowed", exception.getMessage());
    }

    @Test
    void addRocket() {
        // when
        Rocket rocket = spaceProgramService.addRocket("Falcon4");

        // then
        assertEquals("Falcon4", rocket.getName());
        assertEquals(ON_GROUND, rocket.getRocketStatus());
    }

    @Test
    void changeRocketStatus() {
        // when
        Rocket rocket = spaceProgramService.addRocket("Falcon4");

        //then
        spaceProgramService.changeRocketStatus("Falcon4", "IN_REPAIR");
        assertEquals(IN_REPAIR, rocket.getRocketStatus());
    }
    @Test
    void changeRocketStatusAreNullIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            spaceProgramService.changeRocketStatus(null, null);
        });

        assertEquals("Rocket does not exist!", exception.getMessage());
    }
    @Test
    void changeMissionStatusAreNullIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            spaceProgramService.changeMissionStatus(null, null);
        });

        assertEquals("Mission does not exist!", exception.getMessage());
    }

    @Test
    void changeRocketStatusDoesNotIllegalArgumentException() {
        spaceProgramService.addRocket("Falcon4");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            spaceProgramService.changeRocketStatus("Falcon4", "test");
        });

        assertEquals("Rocket status does not exist!", exception.getMessage());
    }
    @Test
    void changeMissionStatusDoesNotExistIllegalArgumentException() {
        spaceProgramService.addMission("MARS");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            spaceProgramService.changeMissionStatus("MARS", "test");
        });

        assertEquals("Mission status does not exist!", exception.getMessage());
    }

    @Test
    void changeMissionStatus() {
        // when
        Mission mission = spaceProgramService.addMission("EARTH");

        //then
        spaceProgramService.changeMissionStatus("EARTH", "ENDED");
        assertEquals(ENDED, mission.getMissionStatus());
    }

    @Test
    void assignMissionToRocketButRocketIsNullIllegalArgumentException() {
        spaceProgramService.addMission("EARTH");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            spaceProgramService.assignMissionToRocket("MARS", "test");
        });

        assertEquals("Rocket or Mission does not exist!", exception.getMessage());
    }

    @Test
    void assignMissionToRocketButMissionIsNullIllegalArgumentException() {
        spaceProgramService.addRocket("FALCON4");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            spaceProgramService.assignMissionToRocket("MARS", "test");
        });

        assertEquals("Rocket or Mission does not exist!", exception.getMessage());
    }

    @Test
    void assignMissionToRocketButRocketHasAssignedMissionIllegalArgumentException() {
        spaceProgramService.addRocket("FALCON4");
        spaceProgramService.addMission("MARS");
        spaceProgramService.addMission("PLUTON");
        spaceProgramService.assignMissionToRocket("FALCON4", "MARS");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            spaceProgramService.assignMissionToRocket("FALCON4", "PLUTON");
        });

        assertEquals("Rocket has already assigned mission!", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void showAllData() {
    }
}