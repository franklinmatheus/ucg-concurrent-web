/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import business.PopulateDatabaseBusiness;
import business.exception.NoClientsInDatabaseException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author franklin
 */
@Named(value = "populateMB")
@RequestScoped
public class PopulateDataBaseMB {

    @Inject
    private PopulateDatabaseBusiness populateBusiness;
    
    public void addOneClient() {
        populateBusiness.generateNewClients(1);
    }
    
    public void addThousandClients() {
        populateBusiness.generateNewClients(1000);
    }
    
    public void addMillionClients() {
        populateBusiness.generateNewClients(1000000);
    }
    
    public void addTenMillionClients() {
        populateBusiness.generateNewClients(10000000);
    }
}
