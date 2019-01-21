/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author CyNEXX
 */
public class EmptyInfoException extends Exception {

    public EmptyInfoException() {
        super();
    }

    public EmptyInfoException(String message) {
        super(message);
    }

    public EmptyInfoException(String message, Throwable clause) {
        super(message, clause);
    }

    public EmptyInfoException(Throwable clause) {
        super(clause);
    }
}
