/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import identities.Borrow;
import java.util.List;

/**
 *
 * @author A
 */
public interface IBorrowDA{
    public List<Borrow> getAll();
    public Borrow get(int borrowId);
    public <T>T create(Borrow b);
    public String update(Borrow b);
    public <T>T delete(int borrowId);
}
