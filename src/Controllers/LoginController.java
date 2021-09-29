/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 *
 * @author DTTM
 */
public class LoginController {

    public Boolean checkInput(String username, String password) {
        if (username == "") {
            return false;
        }
        if (password == "") {
            return false;
        }

        return true;
    }
}
