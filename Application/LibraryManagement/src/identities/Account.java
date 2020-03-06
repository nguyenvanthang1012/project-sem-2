/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identities;

/**
 *
 * @author A
 */
public class Account {
    private int account_id;
    private String userName;
    private String password;
    private int decentralization;

    public Account(int account_id, String userName, String password, int decentralization) {
        this.account_id = account_id;
        this.userName = userName;
        this.password = password;
        this.decentralization = decentralization;
    }

    public Account(String userName, String password, int decentralization) {
        this.userName = userName;
        this.password = password;
        this.decentralization = decentralization;
    }

    public Account() {
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

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

    public int getDecentralization() {
        return decentralization;
    }

    public void setDecentralization(int decentralization) {
        this.decentralization = decentralization;
    }

    @Override
    public String toString() {
        return "Accout{" + "id=" + account_id + ", userName=" + userName + ", password=" + password + ", decentralization=" + decentralization + '}';
    }
    
    
}
