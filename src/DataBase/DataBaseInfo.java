package DataBase;

public class DataBaseInfo {
    private String host;
    private String username;
    private String password;
    public DataBaseInfo(){}
    public DataBaseInfo(String username, String password, String host) {
        this.username = username;
        this.password = password;
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
