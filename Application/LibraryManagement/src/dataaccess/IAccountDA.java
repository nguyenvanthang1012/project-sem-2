/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;
import identities.Account;
import java.util.List;

/**
 *
 * @author A
 */
public interface IAccountDA {
    public List<Account> getAll();
    public List<Account> search(String name);
    public Account login(String username , String password);
    public String update(Account a);
    public String create(Account a);
    public String delete(int idAccount);
}
