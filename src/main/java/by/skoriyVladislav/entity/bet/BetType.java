package by.skoriyVladislav.entity.bet;

public enum BetType {
    TEAM1("team 1"),
    TEAM2("team 2"),
    DRAW("draw"),
    EXACC("exact score");

    private String type;

    BetType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
