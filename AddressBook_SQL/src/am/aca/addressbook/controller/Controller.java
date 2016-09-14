package am.aca.addressbook.controller;

import am.aca.addressbook.comman.exception.InvalidArgumentException;
import am.aca.addressbook.comman.bean.TelNumber;
import am.aca.addressbook.comman.bean.User;
import am.aca.addressbook.repository.implementation.TelNumberRepositoryImplements;
import am.aca.addressbook.repository.implementation.UserRepositoryImplements;
import java.sql.SQLException;
import static am.aca.addressbook.comman.bean.TelNumberType.HOME;
import static am.aca.addressbook.comman.bean.TelNumberType.MOBILE;
import static am.aca.addressbook.comman.util.Utils.*;


public class Controller {

    private static User currentUser;
    private String userAns;
    private static Controller instance;

    static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    private Controller() {
    }


    public void programStart() throws SQLException, InvalidArgumentException {
        printMessage("write.sign.up.or.sign.in");
        userAns = scanner.nextLine();

        while (signInCount < 3) {
            switch (userAns) {
                case "Sign Up":
                    signUp();
                    break;
                case "Sign In":
                    signIn();
                    break;
                case "1":
                    addTelNumber();
                    break;
                case "2":
                    showTelNumbers();
                    break;
                case "3":
                    addFriend();
                    break;
                case "4":
                    showFriendList();
                    break;
                case "5":
                    deleteFriend();
                    break;
                case "6":
                    deleteTelNumber();
                    break;
                case "7":
                    deleteUser();
                    break;
                case "Sign Out":
                    signOut();
                    break;
                case "Exit":
                    exit();
                    break;
                case "Help":
                    printMessage("help.text");
                    printMessage("write.sign.up.or.sign.in");
                    break;
                default:
                    printMessage("invalid.command");
                    printMessage("write.sign.up.or.sign.in");
            }
            userAns = scanner.nextLine();
        }

    }


    private void signUp() throws SQLException, InvalidArgumentException {
        User newUser = new User();
        printMessage("provide.username");
        newUser.setUserName(scanner.nextLine());
        printMessage("provide.password");
        newUser.setPassword(scanner.nextLine());

        boolean isSignUpUser = false;
        if (UserRepositoryImplements.getInstance().getUser(newUser.getUserName()).getUserName() != null) {
            isSignUpUser = true;
        }
        if (!isSignUpUser) {
            UserRepositoryImplements.getInstance().addUser(newUser);
            printMessage("successfully.created");
        } else {
            printMessage("wrong.add");
            printMessage("write.sign.up.or.sign.in");
        }
    }

    private void signIn() throws SQLException, InvalidArgumentException {
        printMessage("provide.username");
        String logUserName = scanner.nextLine();
        printMessage("provide.password");
        String logUserPassword = scanner.nextLine();
        if (UserRepositoryImplements.getInstance().getUser(logUserName).getUserName() != null &&
                logUserName.equals(UserRepositoryImplements.getInstance().getUser(logUserName).getUserName()) &&
                logUserPassword.equals(UserRepositoryImplements.getInstance().getUser(logUserName).getPassword())) {

            currentUser = UserRepositoryImplements.getInstance().getUser(logUserName);
            printMessage("user.logged.successfully");
            printMessage("add.command");

        } else if (signInCount < 2) {
            signInCount++;
            printMessage("wrong.user.ans");
            signIn();
        } else {
            printMessage("lock.account");
            exit();
        }


    }


    private void addTelNumber() throws SQLException, InvalidArgumentException {
        TelNumber userTelNumber = new TelNumber();
        printMessage("enter.tel.number");
        String number = scanner.nextLine();
        userTelNumber.setTelNumber(number);
        if (number.startsWith("+374 10")) {
            userTelNumber.setTelNumberType(HOME);
        } else {
            userTelNumber.setTelNumberType(MOBILE);
        }
        userTelNumber.setTelNumberId(currentUser.getId());
        currentUser.setTelNumbersList(userTelNumber);
        TelNumberRepositoryImplements.getInstance().addTelNumber(userTelNumber, currentUser.getId());
        printMessage("command.after.input.number");
        printMessage("add.command");


    }

    private void deleteTelNumber() throws SQLException, InvalidArgumentException {
        printMessage("enter.tel.num");
        String number = scanner.nextLine();
        TelNumberRepositoryImplements.getInstance().deleteTelNumber(number, currentUser.getId());
        printMessage("deleted.number");
        printMessage("add.command");

    }

    private void addFriend() throws SQLException, InvalidArgumentException {
        printMessage("add.friend");
        String friendUserName = scanner.nextLine();
        User friendUser = UserRepositoryImplements.getInstance().getUser(friendUserName);

        boolean isFriendList = false;
        boolean isFriendUser = false;

        if (friendUser.getUserName() != null) {
            isFriendList = UserRepositoryImplements.getInstance().searchInFriendList(currentUser.getId(), friendUser.getId());
            isFriendUser = true;
        }
        if (isFriendUser && !currentUser.getUserName().equals(friendUser.getUserName()) && !isFriendList) {
            UserRepositoryImplements.getInstance().addFriendInList(currentUser.getId(), friendUser.getId());
            printMessage("added.friend");
            printMessage("add.command");
        } else if (currentUser.getUserName().equals(friendUser.getUserName())) {
            printMessage("wrong.himself.add");
            printMessage("add.command");
        } else if (isFriendList) {
            printMessage("wrong.friend");
            printMessage("add.command");
        } else {
            printMessage("user.does.not.exist");
            printMessage("add.command");
        }
    }

    public void showTelNumbers() throws SQLException, InvalidArgumentException {

        System.out.print(currentUser.getUserName() + "'s Tel Numbers List");

        System.out.println(TelNumberRepositoryImplements.getInstance().getTelNumbers(currentUser.getId()));

        printMessage("add.command");

    }

    private void showFriendList() throws SQLException, InvalidArgumentException {
        System.out.println(currentUser.getUserName() + "'s Friend List");
        System.out.println(UserRepositoryImplements.getInstance().getFriendsList(currentUser.getId()));
        printMessage("add.command");

    }

    private void deleteFriend() throws SQLException, InvalidArgumentException {
        printMessage("name.friend.for.delete");

        String deleteUserName = scanner.nextLine();
        User deletedFriend = UserRepositoryImplements.getInstance().getUser(deleteUserName);
        boolean isFriendList = false;
        boolean isFriendUser = false;

        if (deletedFriend.getUserName() != null) {
            isFriendList = UserRepositoryImplements.getInstance().searchInFriendList(currentUser.getId(), deletedFriend.getId());
            isFriendUser = true;
        }
        if (isFriendUser && !currentUser.getUserName().equals(deletedFriend.getUserName()) && isFriendList) {
            UserRepositoryImplements.getInstance().deleteFriendFromList(currentUser.getId(), deletedFriend.getId());
            printMessage("user.deleted");
            printMessage("add.command");
        } else if (currentUser.getUserName().equals(deletedFriend.getUserName())) {
            printMessage("wrong.himself.delete");
            printMessage("add.command");
        } else {
            printMessage("no.friend");
            printMessage("add.command");
        }

    }

    private void deleteUser() throws SQLException, InvalidArgumentException {
        printMessage("delete.user");
        String deleteUser = scanner.nextLine();
        UserRepositoryImplements.getInstance().deleteUser(deleteUser, currentUser.getId());
        printMessage("user.deleted");
        printMessage("add.command");

    }

    private void signOut() throws SQLException, InvalidArgumentException {
        printMessage("sign.out.account");
        programStart();
    }

    private void exit() {
        printMessage("exit.mes");
        System.exit(0);
    }
}