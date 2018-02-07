package by.skoriyVladislav.entity.bet;

import by.skoriyVladislav.constants.Constants;

public enum BetType {
    TEAM1(Constants.TEAM1),
    TEAM2(Constants.TEAM2),
    DRAW(Constants.DRAW),
    EXACTSCORE(Constants.EXACTSCORE);

    private String type;

    BetType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
