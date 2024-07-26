/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionSQL;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Avindu Aloka
 */
public class Login {

    private Statement state = null;
    private PreparedStatement prepair = null;
    private User logUser = null;
    ConnectionSQL cons = new ConnectionSQL();

    public int setlogUser(User logUser) {
        this.logUser = logUser;
        return logUserCheck();
    }

    public User setUserDetails(User logUser) {
        this.logUser = logUser;
        return logingData();
    }

    private int logUserCheck() {
        User user1 = new User();
        userLoad(user1, logUser.getUserName(),3);

        if (logUser.getUserName().equals(user1.getUserName())) {
            if (logUser.getPassWord().equals(user1.getPassWord())) {
                return 4;
            } else {
                return 2;
            }
        } else {
            return 5;
        }
    }

    private User logingData() {
        User user2 = new User();
        userLoad(user2, logUser.getUserName(),1);
        return user2;
    }

    private void userLoad(User userr, String Username, int mode) {
        try {
            cons.ConnectionSuccess();
            state = cons.Con.createStatement();
            ResultSet res = state.executeQuery("SELECT * FROM users WHERE Username = '" + Username + "'");

            while (res.next()) {
                userr.setUserName(res.getString(2));
                userr.setPassWord(res.getString(3));
                if (mode == 1) {
                    userr.setRole(res.getString(4));
                    userr.setUserId(res.getInt(1));
                    userr.setRegDate(res.getTimestamp(5).toLocalDateTime());
                }
            }

            cons.ConnectionClose();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
