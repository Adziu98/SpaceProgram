package com.space.program.service;

import com.space.program.contact.Mission;
import com.space.program.contact.MissionStatus;
import com.space.program.contact.Rocket;
import com.space.program.contact.RocketStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

import static com.space.program.contact.MissionStatus.SCHEDULED;
import static com.space.program.contact.RocketStatus.ON_GROUND;

@Slf4j
public class SpaceProgramService {
    private Map<String, Mission> missions = new HashMap<>();
    private Map<String, Rocket> rockets = new HashMap<>();

    public Mission addMission(String destination) {
        if (destination != null) {
            if (!missions.containsKey(destination)) {
                Mission mission = Mission.builder()
                        .missionStatus(SCHEDULED)
                        .destination(destination)
                        .build();

                missions.put(destination, mission);
                log.info("Mission: " + destination + " has been added successfully!");
                return mission;
            }
            log.error("Mission: " + destination + " already exists!");
            throw new IllegalArgumentException("Mission: " + destination + " already exists!");
        }
        throw new IllegalArgumentException("Null values are not allowed");
    }

    public Rocket addRocket(String rocketName) {
        if (rocketName != null) {
            if (!rockets.containsKey(rocketName)) {
                Rocket rocket = Rocket.builder()
                        .rocketStatus(ON_GROUND)
                        .name(rocketName)
                        .build();

                rockets.put(rocketName, rocket);
                log.info("Rocket: " + rocketName + " has been added successfully!");
                return rocket;
            }
            throw new IllegalArgumentException("Rocket: " + rocketName + " already exists!");
        }
        throw new IllegalArgumentException("Null values are not allowed");
    }

    public void changeRocketStatus(String rocketName, String rocketStatusToChange) {
        Rocket rocket = rockets.get(rocketName);

        if (rocket == null) {
            throw new IllegalArgumentException("Rocket does not exist!");
        }
        if (!containsRocketStatus(rocketStatusToChange)) {
            throw new IllegalArgumentException("Rocket status does not exist!");
        }

        rocket.setRocketStatus(RocketStatus.valueOf(rocketStatusToChange));
        log.info("Rocket status has been changed to: " + rocketStatusToChange);
    }

    public void changeMissionStatus(String destination, String missionStatusToChange) {
        Mission mission = missions.get(destination);

        if (mission == null) {
            throw new IllegalArgumentException("Mission does not exist!");
        }
        if (!containsMissionStatus(missionStatusToChange)) {
            throw new IllegalArgumentException("Mission status does not exist!");
        }

        mission.setMissionStatus(MissionStatus.valueOf(missionStatusToChange));
        log.info("Mission status has been changed to: " + missionStatusToChange);
    }

    public void assignMissionToRocket(String rocketName, String destination) {
        Rocket rocket = rockets.get(rocketName);
        Mission mission = missions.get(destination);

        if (rocket == null || mission == null) {
            throw new IllegalArgumentException("Rocket or Mission does not exist!");
        }

        if (Optional.ofNullable(rocket.getMission()).isPresent()) {
            throw new IllegalArgumentException("Rocket has already assigned mission!");
        }

        rocket.setMission(mission);
        log.info("Mission: " + destination + " has been added to rocket: " + rocketName + " successfully!");
    }

    public void showSummaryOfSpaceProgram() {
        Map<MissionStatus, Map<String, List<Rocket>>> grouped = groupRocketsByStatusAndDestination();

        for (MissionStatus status : MissionStatus.values()) {
            Map<String, List<Rocket>> missions = grouped.get(status);
            if (missions == null || missions.isEmpty()) continue;

            List<Map.Entry<String, List<Rocket>>> sortedMissions = sortMissionsByRocketCountThenName(missions);

            for (Map.Entry<String, List<Rocket>> entry : sortedMissions) {
                printMissionSummary(entry.getKey(), status, entry.getValue());
            }
        }
    }

    private Map<MissionStatus, Map<String, List<Rocket>>> groupRocketsByStatusAndDestination() {
        Map<MissionStatus, Map<String, List<Rocket>>> grouped = new HashMap<>();

        for (Rocket rocket : rockets.values()) {
            Mission mission = rocket.getMission();
            if (mission == null) continue;

            grouped.computeIfAbsent(mission.getMissionStatus(), k -> new HashMap<>())
                    .computeIfAbsent(mission.getDestination(), k -> new ArrayList<>())
                    .add(rocket);
        }

        return grouped;
    }

    private List<Map.Entry<String, List<Rocket>>> sortMissionsByRocketCountThenName(Map<String, List<Rocket>> missionMap) {
        return missionMap.entrySet().stream()
                .sorted(Comparator
                        .<Map.Entry<String, List<Rocket>>>comparingInt(e -> e.getValue().size())
                        .reversed()
                        .thenComparing(Map.Entry::getKey, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    private void printMissionSummary(String missionName, MissionStatus status, List<Rocket> rockets) {
        String rocketSummary = rockets.stream()
                .map(rocket -> rocket.getName() + " - " + rocket.getRocketStatus())
                .collect(Collectors.joining("\n"));

        String summary = String.format(
                "%s - %s - %d rockets\n%s",
                missionName, status, rockets.size(), rocketSummary
        );

        log.info(summary);
    }


    private boolean containsRocketStatus(String value) {
        return Arrays.stream(RocketStatus.values()).anyMatch(status -> status.name().equalsIgnoreCase(value));
    }

    private boolean containsMissionStatus(String value) {
        return Arrays.stream(MissionStatus.values()).anyMatch(status -> status.name().equalsIgnoreCase(value));
    }
}
