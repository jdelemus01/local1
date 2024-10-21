@IsTest
public with sharing class AccountManagerTest {
    @IsTest
    static void testGetAccount() {
        Id accountId = makeTestAccount();
        
        RestRequest request = new RestRequest();
        request.requestUri =
            'https://yourInstance.my.salesforce.com/services/apexrest/Accounts/'+
            + accountId + '/contacts';
        request.httpMethod = 'GET';

        System.debug(request.requestUri);
        
        RestContext.request = request;
        
        Account testAccountResult = AccountManager.getAccount();

        Assert.areEqual(testAccountResult.Id, accountId);
        Assert.areEqual(3, [SELECT COUNT() FROM Contact WHERE AccountId = :testAccountResult.Id]);
    }

    static ID makeTestAccount(){
        Account testAccount = new Account(Name='TestAccount');
        insert testAccount;
        
        Contact con = new Contact(FirstName='Test1',LastName='Test1',AccountId=testAccount.Id);
        Contact con2 = new Contact(FirstName='Test2',LastName='Test2',AccountId=testAccount.Id);
        Contact con3 = new Contact(FirstName='Test2',LastName='Test3',AccountId=testAccount.Id);
        
        List<Contact> contactsToInsert = new List<Contact>{con,con2,con3};
        
        insert contactsToInsert;

        return testAccount.Id;
    }
}