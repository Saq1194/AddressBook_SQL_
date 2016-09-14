package am.aca.addressbook.repository.implementation;

import am.aca.addressbook.comman.exception.InvalidArgumentException;
import am.aca.addressbook.comman.bean.User;
import am.aca.addressbook.comman.util.Utils;
import am.aca.addressbook.repository.interfaces.UserRepositoryInterface;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static am.aca.addressbook.comman.util.Utils.*;

public class UserRepositoryImplements implements UserRepositoryInterface {

    private static UserRepositoryInterface instance;

    public static UserRepositoryInterface getInstance() {
        if (instance == null) {
            loadProperties();
            instance = new UserRepositoryImplements();
        }
        return instance;
    }

    private UserRepositoryImplements() {

    }


    @Override
    public User addUser(User user) throws InvalidArgumentException, SQLException {
        String userName = user.getUserName();
        String userPassword = user.getPassword();
        String insert = "INSERT INTO phonebook.users(user_name,user_password) VALUES(?,?);";
        try (Connection connection = DriverManager.getConnection(url, Utils.userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);
            preparedStatement.execute();

        } catch (SQLException se) {

            se.getMessage();
            se.getSQLState();
            se.getErrorCode();
        }

        return user;
    }

    @Override
    public User getUser(Integer id) throws InvalidArgumentException, SQLException {
        User user = new User();
        String userName = null;
        String userPassword = null;
        Integer userID = null;
        String query = "SELECT * FROM phonebook.users WHERE user_id = ?";


        try (Connection connection = DriverManager.getConnection(url, Utils.userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userName = resultSet.getString("user_name");
                    userPassword = resultSet.getString("user_password");
                    userID = resultSet.getInt("user_id");
                }
            } catch (SQLException se) {
                se.getMessage();
            }


        } catch (SQLException se) {

            se.getMessage();
            se.getSQLState();
            se.getErrorCode();
        }
        user.setUserName(userName);
        user.setPassword(userPassword);
        user.setId(userID);
        return user;
    }


    @Override
    public User getUser(String name) throws InvalidArgumentException, SQLException {
        User user = new User();
        String userName = null;
        String userPassword = null;
        Integer userID = null;
        String query = "SELECT * FROM phonebook.users WHERE user_name =? ";

        try (Connection connection = DriverManager.getConnection(url, Utils.userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                    userName = resultSet.getString("user_name");
                    userPassword = resultSet.getString("user_password");
                    userID = resultSet.getInt("user_id");
                }

            } catch (SQLException se) {
                se.getMessage();
            }


        } catch (SQLException se) {

            se.getMessage();
            se.getSQLState();
            se.getErrorCode();
        }
        user.setUserName(userName);
        user.setPassword(userPassword);
        user.setId(userID);
        return user;
    }

    @Override
    public void deleteUser(String name, Integer id) throws InvalidArgumentException, SQLException {
        String delete = "DELETE FROM phonebook.users WHERE user_name=? AND user_id=?;";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            String delete1 = "DELETE FROM phonebook.tel_numbers WHERE user_id=?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(delete1);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement1.setInt(1, id);
            preparedStatement.execute();
            preparedStatement1.execute();

        } catch (SQLException se) {
            se.getMessage();
            se.getSQLState();
            se.getErrorCode();

        }
    }


    @Override
    public Map addFriendInList(Integer id1, Integer id2) throws InvalidArgumentException, SQLException {
        Map<Integer, Integer> friendsMap = new HashMap();
        friendsMap.put(id1, id2);
        String insert = "INSERT INTO phonebook.user_map(user1_id,user2_id) VALUES (?,?);";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setInt(1, id1);
            preparedStatement.setInt(2, id2);
            preparedStatement.execute();

        } catch (SQLException se) {
            se.getMessage();
            se.getSQLState();
            se.getErrorCode();
        }
        return friendsMap;
    }


    @Override
    public void deleteFriendFromList(Integer id1, Integer id2) throws InvalidArgumentException, SQLException {

        String delete = "DELETE FROM phonebook.user_map WHERE user1_id=? AND user2_id=?;";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setInt(1, id1);
            preparedStatement.setInt(2, id2);
            preparedStatement.execute();
        } catch (SQLException se) {
            se.getMessage();
            se.getSQLState();
            se.getErrorCode();

        }
    }


    @Override
    public Set<String> getFriendsList(Integer id) throws InvalidArgumentException, SQLException {
        Set<String> friendsList = new HashSet<>();
        String query = "SELECT * FROM phonebook.user_map";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                if (resultSet.getInt("user1_id") == id) {
                    friendsList.add(getUser(resultSet.getInt("user2_id")).getUserName());
                }
            }

        } catch (SQLException se) {
            se.getMessage();
        }
        return friendsList;
    }

    @Override
    public boolean searchInFriendList(Integer id1, Integer id2) throws InvalidArgumentException, SQLException {
        String query = "SELECT * FROM phonebook.user_map ";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                if (resultSet.getInt("user1_id") == id1 && resultSet.getInt("user2_id") == id2) {
                    return true;
                }
            }

        } catch (SQLException se) {
            se.getMessage();
            se.getSQLState();
            se.getErrorCode();
        }
        return false;
    }
}


