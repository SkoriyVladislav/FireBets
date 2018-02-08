package by.skoriyVladislav.entity.bet;

import java.io.Serializable;
import java.math.BigDecimal;

public class Bet implements Serializable {
    private String loginUser;
    private int idMatches;
    private BigDecimal size;
    private BetType type;
    private Integer goalsTeam1;
    private Integer goalsTeam2;
    private String status;

    public Bet(String loginUser, int idMatches, BigDecimal size, BetType type, Integer goalsTeam1, Integer goalsTeam2, String status) {
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

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bet bet = (Bet) o;

        if (idMatches != bet.idMatches) return false;
        if (loginUser != null ? !loginUser.equals(bet.loginUser) : bet.loginUser != null) return false;
        if (size != null ? !size.equals(bet.size) : bet.size != null) return false;
        if (type != bet.type) return false;
        if (goalsTeam1 != null ? !goalsTeam1.equals(bet.goalsTeam1) : bet.goalsTeam1 != null) return false;
        if (goalsTeam2 != null ? !goalsTeam2.equals(bet.goalsTeam2) : bet.goalsTeam2 != null) return false;
        return status != null ? status.equals(bet.status) : bet.status == null;
    }

    @Override
    public int hashCode() {
        int result = loginUser != null ? loginUser.hashCode() : 0;
        result = 31 * result + idMatches;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (goalsTeam1 != null ? goalsTeam1.hashCode() : 0);
        result = 31 * result + (goalsTeam2 != null ? goalsTeam2.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "loginUser='" + loginUser + '\'' +
                ", idMatches=" + idMatches +
                ", size=" + size +
                ", type=" + type +
                ", goalsTeam1=" + goalsTeam1 +
                ", goalsTeam2=" + goalsTeam2 +
                ", status='" + status + '\'' +
                '}';
    }
}
