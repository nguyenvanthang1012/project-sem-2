/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.implement;

import identities.Punish;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author A
 */
public class PunishDA implements dataaccess.IPunishDA{
    
    Connection con;

    public PunishDA(){
        con = ConnectionDB.getConnection();
    }
    @Override
    public List<Punish> getAll() {
        List<Punish> list = new ArrayList<>();
    }

    @Override
    public Punish get(int punishId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String create(Punish p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String update(Punish P) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete(int punishId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
