package am.aca.addressbook.repository.implementation;

import am.aca.addressbook.comman.exception.InvalidArgumentException;
import am.aca.addressbook.comman.bean.TelNumber;
import am.aca.addressbook.repository.interfaces.TelNumberRepositoryInterface;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static am.aca.addressbook.comman.util.Utils.password;
import static am.aca.addressbook.comman.util.Utils.url;
import static am.aca.addressbook.comman.util.Utils.userName;

public class TelNumberRepositoryImplements implements TelNumberRepositoryInterface {

    private static TelNumberRepositoryInterface instance;

    public static TelNumberRepositoryInterface getInstance() {
        if (instance == null) {
            instance = new TelNumberRepositoryImplements();
        }
        return instance;
    }

    private TelNumberRepositoryImplements() {

    }

    @Override
    public TelNumber addTelNumber(TelNumber telNumber, Integer userId) throws InvalidArgumentException, SQLException {

        String insert = "INSERT INTO phonebook.tel_numbers(user_tel_number,tel_number_type,user_id) VALUES (?,?,?);";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, telNumber.getTelNumber());
            preparedStatement.setString(2, String.valueOf(telNumber.getTelNumberType()));
            preparedStatement.setInt(3, userId);
            preparedStatement.execute();
        } catch (SQLException se) {
            se.getMessage();
            se.getSQLState();
            se.getErrorCode();
        }

        return telNumber;
    }

    @Override
    public void deleteTelNumber(String number, Integer userId) throws InvalidArgumentException, SQLException {
        String delete = "DELETE FROM phonebook.tel_numbers WHERE user_tel_number=? AND user_id=?;";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setString(1, number);
            preparedStatement.setInt(2, userId);
            preparedStatement.execute();
        } catch (SQLException se) {
            se.getMessage();
            se.getSQLState();
            se.getErrorCode();
        }
    }

    @Override
    public Set<String> getTelNumbers(Integer userId) throws SQLException {
        Set<String> telNumbers = new HashSet<>();
        String query = "SELECT * FROM phonebook.tel_numbers;";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                if (resultSet.getInt("user_id") == userId) {
                    telNumbers.add(resultSet.getString("tel_number_type") + " " + resultSet.getString("user_tel_number"));
                }
            }
        } catch (SQLException se) {
            se.getMessage();
            se.getSQLState();
            se.getErrorCode();
        }

        return telNumbers;
    }
}
