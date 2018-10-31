/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.exception.NoClientsInDatabaseException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author franklin
 */
@Named(value = "offerServices")
@RequestScoped
public class OfferServicesBusiness {

    @EJB
    private ReaderBusiness readerBusiness;

    public void decideServices(int client) throws NoClientsInDatabaseException {
        readerBusiness.decideServices(client);
    }
}
