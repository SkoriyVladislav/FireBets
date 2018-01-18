package by.skoriyVladislav.dal.match_dao;

import by.skoriyVladislav.entity.match.Match;

public interface MatchDAO {
    Match createMatch(String name, String surname);
}
