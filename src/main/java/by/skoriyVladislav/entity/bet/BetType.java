package by.skoriyVladislav.entity.bet;

public enum BetType {
    TEAM1("team1"),
    TEAM2("team2"),
    DRAW("draw"),
    EXACC("exAcc");

    private String type;

    BetType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
