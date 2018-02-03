package by.skoriyVladislav.entity.bet;

public enum BetType {
    TEAM1("Team1"),
    TEAM2("Team2"),
    DRAW("Draw"),
    EXACTSCORE("ExactScore");

    private String type;

    BetType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
