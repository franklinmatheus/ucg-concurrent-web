/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import business.OfferServicesBusiness;
import business.exception.NoClientsInDatabaseException;
import data.ClientDAO;
import entity.Client;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author franklin
 */
@Named(value = "offerServicesMB")
@RequestScoped
public class OfferServicesMB {

    @Inject
    private OfferServicesBusiness offerServices;
    
    @EJB
    private ClientDAO clientDAO;
    
    //* using id client in database to save memory.
    private int client;

    private int getRandomClient() throws NoClientsInDatabaseException {
        return this.clientDAO.getRandomClient();
    }

    public Client getClient() throws NoClientsInDatabaseException {
        this.setClient();
        this.getServices();
        return this.clientDAO.getClient(client);
    }

    public void setClient() throws NoClientsInDatabaseException {
        this.client = getRandomClient();
    }
    
    public void getServices() {
        try {
            offerServices.decideServices(client);
        } catch (NoClientsInDatabaseException e) {
            // TODO
        }
    }
}
