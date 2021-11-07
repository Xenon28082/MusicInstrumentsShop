package by.xenon28082.shop.entity;

public class UserDTO {

    private String login;
    private int role;
    private long id;

    public UserDTO(User user){
        this.login = user.getLogin();
        this.role = user.getRole();
        this.id = user.getId();
    }

    public UserDTO(String login, int role, long id){
        this.login = login;
        this.role = role;
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
