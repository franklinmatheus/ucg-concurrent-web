/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.exception.NoClientsInDatabaseException;
import business.services.Services;
import data.ClientDAO;
import entity.Client;
import entity.Common;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;

/**
 *
 * @author franklin
 */
@Stateless
public class ReaderBusiness {
    
    @EJB
    private ClientDAO clientDAO;
    
    public void decideServices(int client) throws NoClientsInDatabaseException {
        
        int readersNumber = (int) Math.ceil(clientDAO.getClientsDB().size() * Common.TAX_INTERPRETER_READERS);
        List<ArrayReader> readers = new ArrayList<>();
        Counter counter = new Counter();
        Counter counterBronkenCars = new Counter();
//        for (int i = 0; i < readersNumber; ++i) {
//            ArrayReader reader = new ArrayReader(client, counter, counterBronkenCars);
//            readers.add(reader);
//        }
//        
//        for (int i = 0; i < readersNumber; ++i) {
//            readers.get(i).start();
//        }
//        
//        for (int i = 0; i < readersNumber; ++i) {
//            try {
//                readers.get(i).join();
//            } catch (InterruptedException ex) {
//                Logger.getLogger(OfferServicesBusiness.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//        if (counterBronkenCars.get() > Common.NUMBER_OF_CONDITION_TO_CAR_INSURANCE) {
//            try {
//                clientDAO.getClient(client).getServices().add(Services.CAR_INSURANCE);
//            } catch (NullPointerException e) {
//                List<Services> services = new ArrayList<>();
//                services.add(Services.CAR_INSURANCE);
//                clientDAO.getClient(client).setServices(services);
//            }
//        }

        ExecutorService executorService = Executors.newFixedThreadPool(readersNumber);
        for (int i = 0; i < readersNumber; ++i) {
            executorService.execute(new ArrayReader(client, counter, counterBronkenCars));
        }
        executorService.shutdown();
    }
    
    class ArrayReader extends Thread {
        
        private int client;
        private Counter counter, counterBrokenCars;
        
        public ArrayReader(int client, Counter counter, Counter counterBrokenCars) {
            this.client = client;
            this.counter = counter;
            this.counterBrokenCars = counterBrokenCars;
        }
        
        @Override
        public void run() {
            boolean hasClients = true;
            while (hasClients) {
                try {
                    int index = counter.getAndIncrement();
                    
                    if (index < clientDAO.getClientsDB().size()) {
                        Client temp = clientDAO.getClient(index);
                        if (temp.getHasCar()) {
                            if (temp.isCarBroke()) {
                                if (Math.abs(temp.getAddress() - clientDAO.getClient(client).getAddress()) < 100) {
                                    counterBrokenCars.getAndIncrement();
                                }
                            }
                        }
                    } else {
                        hasClients = false;
                    }
                } catch (NoClientsInDatabaseException | EJBException e) {
                    hasClients = false;
                }
            }
        }
    }
}
