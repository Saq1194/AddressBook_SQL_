package am.aca.addressbook.repository.interfaces;


import am.aca.addressbook.comman.exception.InvalidArgumentException;
import am.aca.addressbook.comman.bean.TelNumber;

import java.sql.SQLException;
import java.util.Set;

public interface TelNumberRepositoryInterface {

    TelNumber addTelNumber(TelNumber telNumber, Integer userId) throws InvalidArgumentException, SQLException;

    void deleteTelNumber(String number, Integer userId) throws InvalidArgumentException, SQLException;

    Set<String> getTelNumbers(Integer userId) throws SQLException;

}
