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
public class Category {
    private int catId;
    private String name;
    private byte status;

    public Category() {
    }

    public Category(int catId, String name, byte status) {
        this.catId = catId;
        this.name = name;
        this.status = status;
    }

    public Category(String name, byte status) {
        this.name = name;
        this.status = status;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int cat_id) {
        this.catId = cat_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return name;
    }
}
