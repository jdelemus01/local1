@isTest
private class AccountProcessorTest {
    @isTest
    private static void testCountContacts(){
        Account acc1 = new Account(Name = 'Test Account 1');
        Account acc2 = new Account(Name = 'Test Account 2');
        insert new List<Account>{acc1, acc2};
        
        List<Contact> contacts = new List<Contact>{
            new Contact(FirstName = 'John', LastName = 'Doe', AccountId = acc1.Id),
            new Contact(FirstName = 'Jane', LastName = 'Doe', AccountId = acc1.Id),
            new Contact(FirstName = 'Mike', LastName = 'Smith', AccountId = acc2.Id)
        };
        insert contacts;
        
        List<ID> accountIds = new List<ID>{acc1.Id, acc2.Id};
        
        Test.startTest();
        AccountProcessor.countContacts(accountIds);
        Test.stopTest();
        
        List<Account> updatedAccounts = [SELECT Id, Number_of_Contacts__c FROM Account WHERE Id IN :accountIds];
        
        System.assertEquals(2, updatedAccounts[0].Number_of_Contacts__c, 'Number of contacts should be 2 for account: ' + updatedAccounts[0].Id);
        System.assertEquals(1, updatedAccounts[1].Number_of_Contacts__c, 'Number of contacts should be 1 for account: ' + updatedAccounts[1].Id);
    }
}