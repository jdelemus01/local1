public with sharing class ContactSearch {
    public static List<Contact> searchForContacts(String lastName, String mailingPostalCode){

        List<Contact> contacts = new List<Contact>();

        if(lastName != '' && mailingPostalCode != ''){
            contacts = [SELECT Id,Name,LastName,MailingPostalCode FROM Contact WHERE LastName = :lastName AND MailingPostalCode = :mailingPostalCode];
        }

        return contacts;
    }
}