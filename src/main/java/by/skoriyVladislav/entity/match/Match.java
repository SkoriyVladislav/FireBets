package by.skoriyVladislav.entity.match;

import java.io.Serializable;

public class Match implements Serializable {
    private int id;
    private String team1;
    private String team2;
    private String data;
    private String time;
    private double coefTeam1;
    private double coefTeam2;
    private double coefDraw;
    private double coefExAcc;
    private Integer goalsTeam1 = null;
    private Integer goalsTeam2 = null;

    public Match(int id, String team1, String team2, String data, String time,
                 double coefTeam1, double coefTeam2, double coefDraw, double coefExAcc, Integer goalsTeam1, Integer goalsTeam2) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.data = data;
        this.time = time;
        this.coefTeam1 = coefTeam1;
        this.coefTeam2 = coefTeam2;
        this.coefDraw = coefDraw;
        this.coefExAcc = coefExAcc;
        this.goalsTeam1 = goalsTeam1;
        this.goalsTeam2 = goalsTeam2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getCoefTeam1() {
        return coefTeam1;
    }

    public void setCoefTeam1(double coefTeam1) {
        this.coefTeam1 = coefTeam1;
    }

    public double getCoefTeam2() {
        return coefTeam2;
    }

    public void setCoefTeam2(double coefTeam2) {
        this.coefTeam2 = coefTeam2;
    }

    public double getCoefDraw() {
        return coefDraw;
    }

    public void setCoefDraw(double coefDraw) {
        this.coefDraw = coefDraw;
    }

    public double getCoefExAcc() {
        return coefExAcc;
    }

    public void setCoefExAcc(double coefExAcc) {
        this.coefExAcc = coefExAcc;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (id != match.id) return false;
        if (Double.compare(match.coefTeam1, coefTeam1) != 0) return false;
        if (Double.compare(match.coefTeam2, coefTeam2) != 0) return false;
        if (Double.compare(match.coefDraw, coefDraw) != 0) return false;
        if (Double.compare(match.coefExAcc, coefExAcc) != 0) return false;
        if (team1 != null ? !team1.equals(match.team1) : match.team1 != null) return false;
        if (team2 != null ? !team2.equals(match.team2) : match.team2 != null) return false;
        if (data != null ? !data.equals(match.data) : match.data != null) return false;
        if (time != null ? !time.equals(match.time) : match.time != null) return false;
        if (goalsTeam1 != null ? !goalsTeam1.equals(match.goalsTeam1) : match.goalsTeam1 != null) return false;
        return goalsTeam2 != null ? goalsTeam2.equals(match.goalsTeam2) : match.goalsTeam2 == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (team1 != null ? team1.hashCode() : 0);
        result = 31 * result + (team2 != null ? team2.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        temp = Double.doubleToLongBits(coefTeam1);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(coefTeam2);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(coefDraw);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(coefExAcc);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (goalsTeam1 != null ? goalsTeam1.hashCode() : 0);
        result = 31 * result + (goalsTeam2 != null ? goalsTeam2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", data='" + data + '\'' +
                ", time='" + time + '\'' +
                ", coefTeam1=" + coefTeam1 +
                ", coefTeam2=" + coefTeam2 +
                ", coefDraw=" + coefDraw +
                ", coefExAcc=" + coefExAcc +
                ", goalsTeam1=" + goalsTeam1 +
                ", goalsTeam2=" + goalsTeam2 +
                '}';
    }
}
