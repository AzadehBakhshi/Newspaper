/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Azadeh
 */
public class Editor extends Worker {

    private static final int roleEditor = 2;

    public int getRole() {
        return roleEditor;
    }
    
    @Override
    public String toString() {
        return getFirstName() + ' ' + getLastName();
    }
}
