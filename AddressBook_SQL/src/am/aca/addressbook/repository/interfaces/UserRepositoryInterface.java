package am.aca.addressbook.repository.interfaces;

import am.aca.addressbook.comman.exception.InvalidArgumentException;
import am.aca.addressbook.comman.bean.User;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public interface UserRepositoryInterface {

    User addUser(User user) throws InvalidArgumentException, SQLException;

    User getUser(Integer id) throws InvalidArgumentException, SQLException;

    User getUser(String name) throws InvalidArgumentException, SQLException;

    void deleteUser(String name, Integer id) throws InvalidArgumentException, SQLException;

    Map addFriendInList(Integer id1, Integer id2) throws InvalidArgumentException, SQLException;

    void deleteFriendFromList(Integer id1, Integer id2) throws InvalidArgumentException, SQLException;

    Set<String> getFriendsList(Integer id) throws InvalidArgumentException, SQLException;

    boolean searchInFriendList(Integer id1, Integer id2) throws InvalidArgumentException, SQLException;

}