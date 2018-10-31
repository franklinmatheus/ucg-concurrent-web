/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;
import data.ClientDAO;
import entity.Client;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author franklin
 */
@Named(value = "listClientsMB")
@RequestScoped
public class ListClientsMB {
    @EJB
    private ClientDAO clientDAO;
    
    public List<Client> getClients() {
        return this.clientDAO.getClientsDB();
    }
}