package by.skoriyVladislav.entity.bets;

public enum BetsType {
    TEAM1("team1"),
    TEAM2("team2"),
    DRAW("draw"),
    EXACC("ExAcc");

    private String type;

    BetsType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
