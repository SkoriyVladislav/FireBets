package by.skoriyVladislav.constants;

public final class Constants {
    /**
     * User's roles
     */
    public static final String BANNED = "banned";
    public static final String PLAYER = "player";
    public static final String ADMIN = "admin";
    public static final String BOOKMAKER = "bookmaker";

    /**
     * Bet's types
     */
    public static final String TEAM1 = "Team1";
    public static final String TEAM2 = "Team2";
    public static final String DRAW = "Draw";
    public static final String EXACTSCORE = "ExactScore";

    public static final Double INITIALBALANCE = 10.0;
    public static final String INITIALROLE = "player";

    public static final String URLTODATABASE = "jdbc:mysql://localhost:3306/firebets"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";
    public static final Integer INITCAPASITYPOOL = 10;
    public static final String USERLOGINDB = "root";
    public static final String USERPASSDB = "root";
}
