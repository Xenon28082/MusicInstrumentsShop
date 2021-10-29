//package by.xenon28082.shop.controller;
//
//import java.sql.*;
//
//public class PostgresImpl {
//
//    private ResultSetMetaData data;
//
//    public static boolean validateUser(String login, String password) throws SQLException {
//        System.out.println(login + " | " + password);
//        String query = "SELECT count(*) FROM users WHERE login=? AND password=?";
//        Connection connection = DataBaseConfig.getConnection();
//
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setString(1, login);
//        preparedStatement.setString(2, password);
//
//        ResultSet set = preparedStatement.executeQuery();
//        set.next();
//
//        if(set.getInt(1) != 0){
//            System.out.println("There is user with login - " + login + " and password - " + password);
//            return true;
//        }
//        else{
//            System.out.println("There is no such user");
//            return false;
//        }
//    }
//
//    public static void getUsers() throws SQLException {
//        String query = "SELECT * FROM users";
//        Connection connection = DataBaseConfig.getConnection();
//        if(connection != null){
//            System.out.println("connection created");
//        }
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while(resultSet.next()){
//            System.out.println("id - " + resultSet.getLong(5));
//            System.out.println("\t Login - " + resultSet.getString(1));
//            System.out.println("\t Name - " + resultSet.getString(2));
//            System.out.println("\t Lastname - " + resultSet.getString(3));
//            System.out.println("\t Password - " + resultSet.getString(4));
//        }
//    }
//
//    public static void addUser(User user) throws SQLException {
//        String query = " insert into users (login, firstname, lastname, password)"  + " values (?, ?, ?, ?)";
//        Connection connection= DataBaseConfig.getConnection();
//        if(connection != null){
//            System.out.println("Connection created in addUser");
//        }
//
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setString(1, user.login);
//        preparedStatement.setString(2, user.name);
//        preparedStatement.setString(3, user.lastname);
//        preparedStatement.setString(4, user.password);
//        preparedStatement.executeUpdate();
//    }
//
//    public static void main(String[] args) {
//        try {
//            User user = new User("Mud", "2", "2", "primus");
//            PostgresImpl.getUsers();
////            PostgresImpl.addUser(user);
////            PostgresImpl.getUser();
//            PostgresImpl.validateUser(user.login, user.password);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//}
