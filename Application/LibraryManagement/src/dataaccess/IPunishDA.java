/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import identities.Punish;
import java.util.List;

/**
 *
 * @author A
 */
public interface IPunishDA {
    public List<Punish> getAll();
    public Punish get(int punishId);
    public boolean create(Punish p);
    public boolean update(Punish P);
    public boolean delete(int punishId);
}
