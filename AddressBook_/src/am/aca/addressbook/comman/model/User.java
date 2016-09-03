package am.aca.addressbook.comman.model;

import java.util.*;

public class User {
    private String  userName;
    private String password;
    private Integer id;
    public List<TelNumber> telNumbersList =new ArrayList<>();

    public Set<FriendList> friendList = new HashSet();



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

    public List<TelNumber> getTelNumbersList() {
        return telNumbersList;
    }

    public void setTelNumbersList(List<TelNumber> telNumbersList) {
        this.telNumbersList = telNumbersList;
    }

    public String getFriendList() {
        return String.valueOf(friendList);
    }

    public void setFriendList(Set<FriendList> friendList) {
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
