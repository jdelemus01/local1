@RestResource(urlMapping='/Accounts/*/contacts')
global with sharing class AccountManager {
    @HttpGet
    global static Account getAccount(){
        RestRequest request = RestContext.request;
        RestResponse response = RestContext.response;
        
        String acId = request.requestURI.substringBetween('Accounts/', '/contacts');
        System.debug(acId);
        
        Account acc = [SELECT Id, Name, (SELECT Id, Name FROM Contacts) 
                       FROM Account 
                       WHERE Id = :acId];
        
        System.debug(acc);
        return acc;
    }
}