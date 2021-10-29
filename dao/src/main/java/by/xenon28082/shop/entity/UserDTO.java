package by.xenon28082.shop.entity;

public class UserDTO {

    public String login;
    public int role;
    public long id;

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

}
