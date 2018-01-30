package by.skoriyVladislav.entity.bet;

public class Bet {
    private String loginUser;
    private int idMatches;
    private double size;
    private BetType type;
    private Integer goalsTeam1;
    private Integer goalsTeam2;
    private String status;

    public Bet(String loginUser, int idMatches, double size, BetType type, Integer goalsTeam1, Integer goalsTeam2, String status) {
        this.loginUser = loginUser;
        this.idMatches = idMatches;
        this.size = size;
        this.type = type;
        this.goalsTeam1 = goalsTeam1;
        this.goalsTeam2 = goalsTeam2;
        this.status = status;
    }

    public Bet() {
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public int getIdMatches() {
        return idMatches;
    }

    public void setIdMatches(int idMatches) {
        this.idMatches = idMatches;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public BetType getType() {
        return type;
    }

    public void setType(BetType type) {
        this.type = type;
    }

    public Integer getGoalsTeam1() {
        return goalsTeam1;
    }

    public void setGoalsTeam1(Integer goalsTeam1) {
        this.goalsTeam1 = goalsTeam1;
    }

    public Integer getGoalsTeam2() {
        return goalsTeam2;
    }

    public void setGoalsTeam2(Integer goalsTeam2) {
        this.goalsTeam2 = goalsTeam2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
