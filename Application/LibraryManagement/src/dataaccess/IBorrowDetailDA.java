/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import identities.BorrowDetail;
import java.util.List;

/**
 *
 * @author A
 */
public interface IBorrowDetailDA {
    public List<BorrowDetail> getAll();
    public BorrowDetail get(int borrowDetailId);
    public boolean create(BorrowDetail b);
    public boolean update(BorrowDetail b);
    public boolean delete(int borrowDetailId);
}
