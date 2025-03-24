import java.io.*;
import java.util.*;
class Contact{
    private String name;
    private String phoneNumber;
    private String email;
    public Contact(String name,String phoneNumber,String email){
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.email=email;
    }
    public String getName(){
        return name;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getEmail(){
        return email;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public void setEmail(String email){
        this.email=email;
    }
    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }
}
public class phonebook{
    private List<Contact> contacts;
    private final String filePath= "contact.csv"; //csv file
    public phonebook(){
        contacts=new ArrayList<>();
        loadContacts();
    }
    public void addContact(String name,String phoneNumber,String email){
        if(isValidPhonenUmber(phoneNumber)){
            contacts.add(new Contact(name,phoneNumber,email));
            saveContacts();
        }else{
            System.out.println("Invalid Phone Number");
        }
    }
    public void viewContacts(){
        if(contacts.isEmpty()){
            System.out.println("No contact found");
            return;
        }
        contacts.sort(Comparator.comparing(Contact::getName));
        for(Contact contact:contacts){
            System.out.println(contact);
        }
    }
    public void searchContact(String query){
        for(Contact contact:contacts){
            if(contact.getName().equalsIgnoreCase(query) || contact.getPhoneNumber().equalsIgnoreCase(query) || contact.getEmail().equalsIgnoreCase(query)){
                System.out.println(contact);
                return;
            }
        }System.out.println("Contact not found");
    }
    public void editContact(String name,String newPhoneNumber, String newEmail){
        for(Contact contact:contacts){
            if(contact.getName().equalsIgnoreCase(name)){
                contact.setPhoneNumber(newPhoneNumber);
                contact.setEmail(newEmail);
                saveContacts();
                return;
            }
        }System.out.println("Contact not found");
    }
    public void deleteContact(String name){
        contacts.removeIf(contact ->contact.getName().equalsIgnoreCase(name));
        saveContacts();
    }
    private boolean isValidPhonenUmber(String phoneNumber){
        return phoneNumber.matches("\\d{10}");
    }
    private void loadContacts(){
        try{
            FileReader fr=new FileReader(filePath);
            Scanner sc=new Scanner(fr);
            while(sc.hasNextLine()){
                String line=sc.nextLine();
                String[] details=line.split(",");
                if(details.length==3){
                    contacts.add(new Contact(details[0],details[1],details[2]));
                }
            }
            sc.close();
            fr.close();
        }catch(IOException e){
            System.out.println("Error loading contacts: "+e.getMessage());
        }
    }
    private void saveContacts(){
        try{
            FileWriter fw=new FileWriter(filePath);
            for(Contact contact:contacts){
                fw.write(contact.getName()+","+contact.getPhoneNumber()+","+contact.getEmail()+"\n");
            }
            fw.close();
        }catch(IOException e){
            System.out.println("Error saving contacts: "+e.getMessage());
        }
    }
    public static void main(String[] args){
     phonebook phonebook=new phonebook();
        Scanner sc=new Scanner(System.in);
        String coms;
        do{
            System.out.println("Phone Book Management System");
            System.out.println("1. Add New Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Search for a Contact");
            System.out.println("4. Edit Contact Details");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            coms = sc.nextLine();
            switch(coms){
                case "1":
                    System.out.print("Enter name: ");
                    String name=sc.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber=sc.nextLine();
                    System.out.print("Enter Email address: ");
                    String email=sc.nextLine();
                    phonebook.addContact(name,phoneNumber,email);
                    break;
                case "2":
                    phonebook.viewContacts();
                    break;
                case "3":
                    System.out.print("Enter Name,PhoneNumber or email to be searched : ");
                    String search=sc.nextLine();
                    phonebook.searchContact(search);
                    break;
                case "4":
                    System.out.print("Enter name of the contact to be edited: ");
                    String editName=sc.nextLine();
                    System.out.print("Enter new Phone Number: ");
                    String newPhoneNumber=sc.nextLine();
                    System.out.print("Enter email: ");
                    String newemail=sc.nextLine();
                    phonebook.editContact(editName, newPhoneNumber, newemail);
                    break;
                case "5":
                    System.out.print("Enter name of the contact to be deleted: ");
                    String deleteName=sc.nextLine();
                    phonebook.deleteContact(deleteName);
                    break;
                case "6":
                    System.out.println(">>>Exiting>>>");
                    break;
                default:
                    System.out.println("Invalid choice. Try again");
            }
        }while(!coms.equals("6"));
        sc.close();
    }
}