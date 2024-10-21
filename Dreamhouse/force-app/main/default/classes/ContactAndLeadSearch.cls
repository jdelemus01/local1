public with sharing class ContactAndLeadSearch {
    
    public static List<List<sObject>> searchContactsAndLeads(String searchKey) {

        List<List<sObject>> searchList = [FIND :searchKey IN ALL FIELDS 
            RETURNING Lead(Name), Contact(FirstName, LastName, Department, Id)];

        return searchList;
    }
    
}