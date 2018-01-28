package by.skoriyVladislav.dal.match_dao;

import by.skoriyVladislav.entity.match.Match;

import java.util.List;

public interface MatchDAO {
    List<Match> createMatches();
    Match createMatch(int id);
    boolean registrMatch(String team1, String team2, String dataTime, double[] coef);
    boolean registrCoeff(int id, double[] coef);
}
