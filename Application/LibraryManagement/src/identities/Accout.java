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
public class Accout {
    private int id;
    private String userName;
    private String password;
    private int decentralization;

    public Accout(int id, String userName, String password, int decentralization) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.decentralization = decentralization;
    }

    public Accout(String userName, String password, int decentralization) {
        this.userName = userName;
        this.password = password;
        this.decentralization = decentralization;
    }

    public Accout() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Accout{" + "id=" + id + ", userName=" + userName + ", password=" + password + ", decentralization=" + decentralization + '}';
    }
    
    
}
