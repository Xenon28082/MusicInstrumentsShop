package by.xenon28082.shop.entity;

public class User {

    private String login;
    private String name;
    private String lastname;
    private String password;
    private int role;
    private long id;

    public User(User user) {
        this.login = user.login;
        this.name = user.name;
        this.lastname = user.lastname;
        this.password = user.password;
        this.role = user.role;
    }

    public User(String login, String name, String lastname, String password, long id) {
        this.login = login;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.id = id;
    }

    public User(String login, String name, String lastname, String password) {
        this.login = login;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public User() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
