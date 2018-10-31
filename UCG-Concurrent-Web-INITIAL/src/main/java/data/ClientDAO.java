/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.exception.NoClientsInDatabaseException;
import java.util.List;
import entity.Client;
import java.util.ArrayList;
import java.util.Random;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author franklin
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN   )
@Startup
public class ClientDAO {

    private final List<Client> clientsDB = new ArrayList<>();

    public List<Client> getClientsDB() {
        synchronized (clientsDB) {
            return clientsDB;
        }
    }

    public void add(Client client) {
        synchronized (clientsDB) {
            this.clientsDB.add(client);
        }
    }

    public int getRandomClient() throws NoClientsInDatabaseException {
        if (getClientsDB().isEmpty()) {
            throw new NoClientsInDatabaseException();
        }
        Random random = new Random();
        return random.nextInt(clientsDB.size());
    }

    public Client getClient(int index) throws NoClientsInDatabaseException {
        if (getClientsDB().isEmpty()) {
            throw new NoClientsInDatabaseException();
        }
        return this.clientsDB.get(index);
    }
}
