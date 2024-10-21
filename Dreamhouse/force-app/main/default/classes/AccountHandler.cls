public with sharing class AccountHandler {
    public static Account insertNewAccount(String name) {
        try {
            Account acct = new Account(Name=name);
            insert acct;
            system.debug('Account is created');
            return acct;
        }
        catch(DmlException e) {
            return null;
        }
        
    }
}