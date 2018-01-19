package by.skoriyVladislav.entity.match;

public class Match {
    private int id;
    private String team1;
    private String team2;
    private String data;
    private String time;
    private double coefTeam1;
    private double coefTeam2;
    private double coefDraw;
    private double coefExAcc;
    private int goalsTeam1;
    private int goalsTeam2;

    public Match(int id, String team1, String team2, String data, String time,
                 double coefTeam1, double coefTeam2, double coefDraw, double coefExAcc, int goalsTeam1, int goalsTeam2) {
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

    public int getGoalsTeam1() {
        return goalsTeam1;
    }

    public void setGoalsTeam1(int goalsTeam1) {
        this.goalsTeam1 = goalsTeam1;
    }

    public int getGoalsTeam2() {
        return goalsTeam2;
    }

    public void setGoalsTeam2(int goalsTeam2) {
        this.goalsTeam2 = goalsTeam2;
    }
}
