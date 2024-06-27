package com.example.football_compare.utils;

import com.example.football_compare.models.Team;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;


import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {

    public static List<Team> readTeamsFromCsv(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            return reader.readAll().stream()
                    .skip(1)
                    .map(CsvReader::mapToTeam)
                    .collect(Collectors.toList());
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read CSV file", e);
        }
    }
    private static Team mapToTeam(String[] data) {
        Team team = new Team();
        team.setId(Long.parseLong(data[0].trim()));
        team.setName(data[1].trim());
        team.setPlayed(Integer.parseInt(data[2].trim()));
        team.setWon(Integer.parseInt(data[3].trim()));
        team.setDrawn(Integer.parseInt(data[4].trim()));
        team.setLost(Integer.parseInt(data[5].trim()));
        team.setGoalsFor(Integer.parseInt(data[6].trim()));
        team.setGoalsAgainst(Integer.parseInt(data[7].trim()));
        team.setGoalDifference(Integer.parseInt(data[8].trim()));
        team.setPoints(Integer.parseInt(data[9].trim()));
        return team;
    }

}
