/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Azadeh
 */
public class Admin extends Worker {

    private static final int roleAdmin = 1;

    public int getRole() {
        return roleAdmin;
    }

    @Override
    public String toString() {
        return getFirstName() + ' ' + getLastName();
    }
}
