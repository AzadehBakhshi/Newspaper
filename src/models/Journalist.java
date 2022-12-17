/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Azadeh
 */
public class Journalist extends Worker {

    private static final int roleJournalist = 3;

    public int getRole() {
        return roleJournalist;
    }

    @Override
    public String toString() {
        return getFirstName() + ' ' + getLastName();
    }
}
