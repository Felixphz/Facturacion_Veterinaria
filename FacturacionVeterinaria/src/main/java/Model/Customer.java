/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author David Gomez
 */
public class Customer {
    private int idPerson;
    private int identificationCard;
    private String name;
    private String lastName;
    private int phone;
    private String mail;
    private String address;
    
    
    public Customer(int cedula, String nombre, String apellidos, int telefono, String correo, String direccion) {
        this.identificationCard = cedula;
        this.name = nombre;
        this.lastName = apellidos;
        this.phone = telefono;
        this.mail = correo;
        this.address = direccion;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public int getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(int identificationCard) {
        this.identificationCard = identificationCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
