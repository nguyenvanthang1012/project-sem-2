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
    private int id;
    private String name;
    private byte status;

    public Category() {
    }

    public Category(int id, String name, byte status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Category(String name, byte status) {
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int cat_id) {
        this.id = cat_id;
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
