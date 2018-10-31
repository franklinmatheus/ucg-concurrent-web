/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import business.services.Services;
import java.util.List;

/**
 *
 * @author franklin
 */
public class Client {
    
    private String name;
    private List<Client> relationships;
    private List<Services> services;
    
    // representation with a random value [0,100000]
    private int address;
    private double salary;
    
    private boolean student;
    private boolean hasJob;
    private boolean married;
    private boolean hasChildren;
    private boolean hasHouse;
    private boolean hasCar;
    private boolean carBroke;
    private boolean independent;
    
    private Account checkingAccount;
    private Account savingAccount;

    public Client() { }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Client> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Client> relationships) {
        this.relationships = relationships;
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }
    
    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public boolean getHasJob() {
        return hasJob;
    }

    public void setHasJob(boolean hasJob) {
        this.hasJob = hasJob;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean getHasHouse() {
        return hasHouse;
    }

    public void setHasHouse(boolean hasHouse) {
        this.hasHouse = hasHouse;
    }

    public boolean getHasCar() {
        return hasCar;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }

    public boolean isCarBroke() {
        return carBroke;
    }

    public void setCarBroke(boolean carBroke) {
        this.carBroke = carBroke;
    }

    public boolean isIndependent() {
        return independent;
    }

    public void setIndependent(boolean independent) {
        this.independent = independent;
    }

    public Account getCheckingAccount() {
        return checkingAccount;
    }

    public void setCheckingAccount(Account checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public Account getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(Account savingAccount) {
        this.savingAccount = savingAccount;
    }

    @Override
    public String toString() {
        return "Client{" + "name=" + name + ", relationships=" + relationships + ", address=" + address + ", salary=" + salary + ", student=" + student + ", hasJob=" + hasJob + ", married=" + married + ", hasChildren=" + hasChildren + ", hasHouse=" + hasHouse + ", hasCar=" + hasCar + ", independent=" + independent + ", checkingAccount=" + checkingAccount + ", savingAccount=" + savingAccount + '}';
    }
}
