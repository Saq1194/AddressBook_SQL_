package am.aca.addressbook.controller;

import am.aca.addressbook.comman.model.FriendList;
import am.aca.addressbook.comman.model.TelNumber;
import am.aca.addressbook.comman.model.User;
import am.aca.addressbook.repository.telnumberrepository.TelNumberRepositoryInterface;
import am.aca.addressbook.repository.telnumberrepository.TelNumberRepositoryInterfaceImp;
import am.aca.addressbook.repository.userrepository.UserRepositoryInterface;
import am.aca.addressbook.repository.userrepository.UserRepositoryInterfaceImp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static am.aca.addressbook.comman.util.UtilClass.*;
import static java.lang.System.in;
import static sun.java2d.cmm.ColorTransform.In;

public class Controller {

    UserRepositoryInterface userRepo = new UserRepositoryInterfaceImp();
    TelNumberRepositoryInterface telNumberRepo = new TelNumberRepositoryInterfaceImp();
    public static List<User> friends = new ArrayList();
    public static List<User> users = new ArrayList();

    static User currentUser;
    static User friendUser;
    String userAns;
    static int count = 0;


    public void begin() {

        try {
            properties.load(new FileInputStream(FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        printMessage("write.sign.up.or.sign.in");
        userAns = scanner.nextLine();

        while (true) {
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
                case "Delete Friend":
                    deleteFriend();
                    break;
                case "Delete Tel. Number":
                    deleteTelNumber();
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


    public void signUp() {
        User newUser = new User();
        printMessage("provide.username");
        newUser.setUserName(scanner.nextLine());
        printMessage("provide.password");
        newUser.setPassword(scanner.nextLine());
        users.add(newUser);
        printMessage("successfully.created");

        //  friendUser = newUser;
    }

    public void signIn() {
        User us = new User();
        printMessage("provide.username");
        us.setUserName(scanner.nextLine());
        printMessage("provide.password");
        us.setPassword(scanner.nextLine());
        us.setId(generateId());


        boolean isUser = false;

        for (User u : users) {

            if (us.equals(u)) {
                isUser = true;
                currentUser = us;
                break;
            }
        }
        if (isUser) {
            printMessage("user.logged.successfully");
            printMessage("adds.comand");
        } else if (count < 3) {
            printMessage("wrong.user.ans");
            count++;
            signIn();
        } else {
            printMessage("lock.account");
            signOut();
        }


    }


    public void addTelNumber() {
        printMessage("enter.tel.number");
        Scanner scanners = new Scanner(in);
        String answer = scanners.nextLine();
        currentUser.telNumbersList.add(new TelNumber(answer));
        printMessage("command.after.input.number");
        printMessage("adds.comand");

    }

    private void deleteTelNumber() {

    }


    public void showTelNumbers() {
        System.out.print(currentUser.getUserName());
        printMessage("show.user.tel.list");

        for (TelNumber t : currentUser.telNumbersList) {
            System.out.println(t.getTelNumber());

        }
        printMessage("adds.comand");
    }

    public void addFriend() {
        printMessage("add.friend");
        Scanner scanner1 = new Scanner(in);
        String ans = scanner1.nextLine();

        currentUser.friendList.add(new FriendList(ans));
        boolean isFrend = false;
        for (User u : users) {
            if (ans.equals(u.getUserName())) {
                isFrend = true;
                break;

            }
            if (ans.equals(currentUser.getUserName())) {
                System.out.println("You can't add your accaunt:");
                break;
            }
        }
        if (isFrend) {
            System.out.print(ans + " ");
            printMessage("friend");
        } else {
            printMessage("invalid.command");
        }
        printMessage("added.friend");
        printMessage("adds.comand");

    }


    private void showFriendList() {
        System.out.println(currentUser.getUserName() + "'s Friend List");
        for (FriendList f : currentUser.friendList) {
            System.out.println(f.getFriendList());

        }

    }

    private void deleteFriend() {

    }

    public void signOut() {
        printMessage("sign.out.account");
        begin();
    }

    public void exit() {
        printMessage("exit.mes");
        System.exit(0);
    }

}