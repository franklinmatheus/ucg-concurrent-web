/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.exception;

/**
 *
 * @author franklin
 */
public class NoClientsInDatabaseException extends Exception {

    /**
     * Creates a new instance of <code>NoClientsInDatabaseException</code>
     * without detail message.
     */
    public NoClientsInDatabaseException() {
        super();
    }

    /**
     * Constructs an instance of <code>NoClientsInDatabaseException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoClientsInDatabaseException(String msg) {
        super(msg);
    }
}
