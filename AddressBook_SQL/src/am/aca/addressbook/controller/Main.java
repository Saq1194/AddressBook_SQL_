package am.aca.addressbook.controller;

import am.aca.addressbook.comman.exception.InvalidArgumentException;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, InvalidArgumentException, ClassNotFoundException {

        Controller.getInstance().programStart();

    }
}