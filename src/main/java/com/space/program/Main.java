package com.space.program;


import com.space.program.service.SpaceProgramService;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        SpaceProgramService spaceProgramService = new SpaceProgramService();
        spaceProgramService.addRocket("Adam");
        spaceProgramService.addRocket("Bartek");
        spaceProgramService.addRocket("Bartek1");
        spaceProgramService.addRocket("Bartek2");
        spaceProgramService.addRocket("Bartek3");
        spaceProgramService.addRocket("Celina");
        spaceProgramService.addMission("anal");
        spaceProgramService.addMission("cipa");
        spaceProgramService.addMission("Dupa");
        spaceProgramService.assignMissionToRocket("Celina", "cipa");
        spaceProgramService.assignMissionToRocket("Adam", "cipa");
        spaceProgramService.assignMissionToRocket("Bartek", "cipa");
        spaceProgramService.assignMissionToRocket("Bartek1", "Dupa");
        spaceProgramService.assignMissionToRocket("Bartek2", "Dupa");
        spaceProgramService.assignMissionToRocket("Bartek3", "Dupa");
        spaceProgramService.assignMissionToRocket("Adam", "Dupa");

        spaceProgramService.changeRocketStatus("Adam", "IN_REPAIR");
        spaceProgramService.changeMissionStatus("Dupa", "IN_PROGRESS");

        spaceProgramService.showAllData();
    }
}