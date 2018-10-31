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
import java.util.stream.Collectors;
import javax.ejb.EJB;
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

        Client current = clientDAO.getClient(client);
        carInsurance(current);
    }

    private void carInsurance(Client client) {
        if (client.getHasCar()) {
            int quantityBrokenCars = clientDAO.getClientsDB().parallelStream()
                    .filter(temp -> {
                        if (temp.getHasCar()) {
                            if (temp.isCarBroke()) {
                                if (Math.abs(temp.getAddress() - client.getAddress()) < 100) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList())
                    .size();

            if (quantityBrokenCars > Common.NUMBER_OF_CONDITION_TO_CAR_INSURANCE) {
                try {
                    client.getServices().add(Services.CAR_INSURANCE);
                } catch (NullPointerException e) {
                    List<Services> services = new ArrayList<>();
                    services.add(Services.CAR_INSURANCE);
                    client.setServices(services);
                }
            }
        }
    }
}
