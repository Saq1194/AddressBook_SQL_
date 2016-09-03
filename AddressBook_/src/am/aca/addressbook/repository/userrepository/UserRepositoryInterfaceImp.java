package am.aca.addressbook.repository.userrepository;

import am.aca.addressbook.comman.exception.InvalidArgumentException;
import am.aca.addressbook.comman.model.User;

import java.util.Map;

public class UserRepositoryInterfaceImp implements UserRepositoryInterface {


    @Override
    public User addUser(User user) throws InvalidArgumentException {
        return null;
    }

    @Override
    public void deleteUser(Integer id) throws InvalidArgumentException {

    }

    @Override
    public User createUser(User user) throws InvalidArgumentException {
        return null;
    }

    @Override
    public User getUser(Integer id) throws InvalidArgumentException {
        return null;
    }

    @Override
    public User getUser(String name, String pass) throws InvalidArgumentException {
        return null;
    }

    @Override
    public Map addFriendInList(Integer userId1, Integer userId2) throws InvalidArgumentException {
        return null;
    }
}