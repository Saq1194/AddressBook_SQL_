package am.aca.addressbook.comman.bean;

import java.util.*;

public class User {
    private String  userName;
    private String password;
    private Integer id;
    public Set<TelNumber> telNumbersList =new HashSet();

    public Set<User> friendList = new HashSet();



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<TelNumber> getTelNumbersList() {
        return telNumbersList;
    }

    public void setTelNumbersList(TelNumber telNumber) {
        telNumbersList.add(telNumber);
    }

    public Set<User> getFriendList() {
        return  friendList;
    }

    public void setFriendList(Set<User> friendList) {
        this.friendList = friendList;
    }



    
   
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;

    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", telNumbersList=" + telNumbersList +
                ", friendList=" + friendList +
                '}';
    }



}
