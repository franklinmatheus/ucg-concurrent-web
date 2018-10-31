/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import data.ClientDAO;
import entity.Account;
import entity.Client;
import entity.Common;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author franklin
 */
@Named(value = "populateBusiness")
@RequestScoped
public class PopulateDatabaseBusiness {

    @EJB
    private ClientDAO clientDAO;

    public void generateNewClients(int quantity) {
        int slice = quantity / Common.THREAD_POPULATE_WORK;

        ExecutorService executorService = Executors.newFixedThreadPool(slice);
        for (int i = 0; i < slice; ++i) {
            executorService.execute(new Populater(i, Common.THREAD_POPULATE_WORK));
        }
        executorService.shutdown();
    }

    public class Populater extends Thread {

        private final int id;
        private final int quantity;
        private final List<Client> clients;

        public Populater(int id, int quantity) {
            this.id = id;
            this.quantity = quantity;
            this.clients = new ArrayList<>();
        }

        @Override
        public void run() {
            for (int i = 0; i < quantity; ++i) {
                Client client = new Client();
                client.setName(randomString(Common.NAME_LENGTH));
                client.setSalary(randomInteger(0, 10000));
                client.setAddress(randomInteger(0, Common.ADDRESS_RANGE));
                client.setStudent(randomBoolean());
                client.setHasJob(randomBoolean());
                client.setMarried(randomBoolean());
                client.setHasChildren(randomBoolean());
                client.setHasHouse(randomBoolean());
                client.setHasCar(randomBoolean());
                if (client.getHasCar()) {
                    client.setCarBroke(randomBoolean());
                }
                client.setIndependent(randomBoolean());

                Account checkingAccount = new Account();
                int checkingAccountBalance = 0;
                int checkingAccountNumTransactions = randomInteger(0, Common.MAX_OF_TRANSACTIONS);
                for (int j = 0; j < checkingAccountNumTransactions; ++j) {
                    int transaction = randomInteger(Common.MAX_OF_ESCAPE, Common.MAX_OF_ENTRANCE);
                    checkingAccountBalance += transaction;
                    checkingAccount.addTransaction(transaction);
                }
                checkingAccount.setBalance(checkingAccountBalance);
                client.setCheckingAccount(checkingAccount);

                boolean savingAccount = randomBoolean();
                if (savingAccount) {
                    Account temp = new Account();
                    int balance = 0;
                    int numTransactions = randomInteger(0, Common.MAX_OF_TRANSACTIONS);
                    for (int j = 0; j < numTransactions; ++j) {
                        int transaction = randomInteger(Common.MAX_OF_ESCAPE, Common.MAX_OF_ENTRANCE);
                        balance += transaction;
                        temp.addTransaction(transaction);
                    }
                    temp.setBalance(balance);
                    client.setSavingAccount(temp);
                }

//                int numRelationships = randomInteger(0, Common.MAX_OF_RELATIONSHIPS);
//                List<Client> relationships = new ArrayList<>();
//                for(int j = 0; j < numRelationships; ++j) {
//                    try {
//                        relationships.add(clientDAO.getClient(randomInteger(0, clientDAO.getClientsDB().size())));
//                    } catch (NoClientsInDatabaseException ex) {
//                        Logger.getLogger(PopulateDatabaseBusiness.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                client.setRelationships(relationships);
                clientDAO.add(client);
            }
        }
    }

    //* Utils
    private int randomInteger(int begin, int end) {
        Random random = new Random();
        int value = begin + random.nextInt(end - begin + 1);
        return value;
    }

    private String randomString(int size) {
        String name = "";
        for (int i = 0; i < size; ++i) {
            name += (char) randomInteger(65, 90);
        }
        return name;
    }

    private boolean randomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
