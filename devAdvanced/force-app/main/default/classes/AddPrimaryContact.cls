public with sharing class AddPrimaryContact implements Queueable{
    
    Contact contact;
    String state;

    public AddPrimaryContact(Contact contact, String state) {
        this.contact = contact;
        this.state = state;
    }

    public void execute(QueueableContext context) {
        
        List<Contact> contactsToInsert = new List<Contact>();
        for (Account account : [SELECT Id FROM Account WHERE BillingState = :state LIMIT 200]) {
            Contact contactClone = contact.clone();
            contactClone.AccountId = account.Id;
        }

        update contactsToInsert;
    }
}