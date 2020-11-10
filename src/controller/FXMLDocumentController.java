/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Usermodel;

/**
 *
 * @author Joe
 */
/**
 * Sample Skeleton for 'CRUD.fxml' Controller Class
 * Initially copied from SceneBuilder
 */




public class FXMLDocumentController implements Initializable {
    
    //the following code block was copied from the DerbyDB Setup Google Doc
    
    // Database manager
    EntityManager manager;

    public void initialize(URL url, ResourceBundle rb) {        
        // loading data from database
        //database reference: "JosephDowneyFXMLPU"
        //needed to change the reference to match the persistence.xml file
        manager = (EntityManager) Persistence.createEntityManagerFactory("JosephDowneyFXMLPU").createEntityManager();
    }


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="CreateButton"
    private Button CreateButton; // Value injected by FXMLLoader

    @FXML // fx:id="ReadButton"
    private Button ReadButton; // Value injected by FXMLLoader

    @FXML // fx:id="UpdateButton"
    private Button UpdateButton; // Value injected by FXMLLoader

    @FXML // fx:id="DeleteButton"
    private Button DeleteButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="EmailContainingButton"
    private Button EmailContainingButton; // Value injected by FXMLLoader

    @FXML // fx:id="UsernameAndPasswordButton"
    private Button UsernameAndPasswordButton; // Value injected by FXMLLoader


    //****************************************'
    // Most of the following code was copied and modified from the example project
    // Other than slight modifications, such as changing Student to user
    // The code was not written by me
    //
    
    @FXML
    void CreateAction(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        System.out.println("Enter Username:");
        String name = input.next();
        
        System.out.println("Enter Password:");
        String password = input.next();
        
        System.out.println("Enter Email Addresss:");
        String email = input.next();
        
        // create a user instance
        Usermodel user = new Usermodel();
        
        // set properties
        user.setId(id);
        user.setUsername(name);
        user.setPassword(password);
        user.setEmailaddress(email);
        
        // save this user to databse by calling Create operation        
        create(user);

    }

    @FXML
    void DeleteAction(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
         // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        Usermodel u = readById(id);
        System.out.println("we are deleting this user: "+ u.toString());
        delete(u);

    }
    

    @FXML
    void ReadAction(ActionEvent event) {
        readAll();

    }

    


    @FXML
    void UpdateAction(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        System.out.println("Enter Username:");
        String name = input.next();
        
        System.out.println("Enter Password:");
        String password = input.next();
        
        System.out.println("Enter Email Addresss:");
        String email = input.next();
        
        // create a student instance
        Usermodel user = new Usermodel();
        
        // set properties
        user.setId(id);
        user.setUsername(name);
        user.setPassword(password);
        user.setEmailaddress(email);
        
        // save this student to databse by calling Create operation        
        update(user);

    }
    
    //The next two methods were written by me
    //ReadByEmailContaining
    @FXML
    void EmailContainingAction(ActionEvent event) {
        //first, prompt the user for what they want to search for
        //code adapted from the delete and readByAll methods
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the term you want to search for:");
        String emailTerm = input.next();
        
        //then create a new query using the created email address containing namedQuery
        Query query = manager.createNamedQuery("Usermodel.findByEmailaddressContaining");
        
        // setting query parameter
        //the wildcards (%) are passed in so it searches for the term anywhere in
        //the email strings in the table -- from https://stackoverflow.com/questions/10912320/named-query-with-like-in-where-clause
        query.setParameter("emailaddress", "%" + emailTerm + "%");
        
        // execute query
        List<Usermodel> users = query.getResultList();

        //display query
        for (Usermodel u : users) {
            System.out.println(u.getId() + " " + u.getUsername() + " " + u.getPassword() + " " + u.getEmailaddress());
        }
    }
    
    //ReadByUsernameAndPassword
    @FXML
    void UsernameAndPasswordAction(ActionEvent event) {
        //first, prompt the user for the username and password
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the username:");
        String username = input.next();
        System.out.println("Enter the password");
        String password = input.next();
        
        //create a new query using the username and password reading namedQuery
        Query query = manager.createNamedQuery("Usermodel.findByUsernameandPassword");
        
        //set the query paramaters -- this time there are two
        query.setParameter("username", username);
        query.setParameter("password", password);
        
        //execute the query and show the result
        Usermodel user = (Usermodel) query.getSingleResult();
        if (user != null) {
            System.out.println(user.getId() + " " + user.getUsername() + " " + user.getPassword() + " " + user.getEmailaddress());
        }
        
    }
    
    
    //********************
    // Again, the following functions are copied and modified from the example project
    // Other than slight modifications, I did not write this code
    
    // Create operation
    public void create(Usermodel user) {
        try {
            // begin transaction
            manager.getTransaction().begin();
            
            // sanity check
            if (user.getId() != null) {
                
                // create student
                manager.persist(user);
                
                // end transaction
                manager.getTransaction().commit();
                
                System.out.println(user.toString() + " is created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // Read Operations
    public List<Usermodel> readAll(){
        Query query = manager.createNamedQuery("Usermodel.findAll");
        List<Usermodel> users = query.getResultList();

        for (Usermodel u : users) {
            System.out.println(u.getId() + " " + u.getUsername() + " " + u.getPassword() + " " + u.getEmailaddress());
        }
        
        return users;
    }
    
    public Usermodel readById(int id){
        Query query = manager.createNamedQuery("Usermodel.findById");
        
        // setting query parameter
        query.setParameter("id", id);
        
        // execute query
        Usermodel user = (Usermodel) query.getSingleResult();
        if (user != null) {
            System.out.println(user.getId() + " " + user.getUsername() + " " + user.getPassword() + " " + user.getEmailaddress());
        }
        
        return user;
    }
    
    
    // Update operation
    public void update(Usermodel model) {
        try {

            Usermodel existingUser = manager.find(Usermodel.class, model.getId());

            if (existingUser != null) {
                // begin transaction
                manager.getTransaction().begin();
                
                // update all atttributes
                existingUser.setUsername(model.getUsername());
                existingUser.setPassword(model.getPassword());
                existingUser.setEmailaddress(model.getEmailaddress());
                
                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Delete operation
    public void delete(Usermodel user) {
        try {
            Usermodel existingUser = manager.find(Usermodel.class, user.getId());

            // sanity check
            if (existingUser != null) {
                
                // begin transaction
                manager.getTransaction().begin();
                
                //remove student
                manager.remove(existingUser);
                
                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}