package am.aca.addressbook.repository.userrepository;

import am.aca.addressbook.comman.exception.InvalidArgumentException;
import am.aca.addressbook.comman.model.User;

import java.util.Map;

public interface UserRepositoryInterface {

    User addUser(User user) throws InvalidArgumentException;

    void deleteUser(Integer id)throws InvalidArgumentException;

    User createUser(User user)throws InvalidArgumentException;

    User getUser(Integer id)throws InvalidArgumentException;

    User getUser(String name, String pass)throws InvalidArgumentException;

    Map addFriendInList(Integer userId1,Integer userId2)throws InvalidArgumentException;

}