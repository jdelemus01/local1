@RestResource(urlMapping='/Accounts/*')
global with sharing class AccountManager {
    @HttpGet
    global static void getAccount(){
        RestRequest request = RestContext.request;
        RestResponse response = RestContext.response;
        
        String acId = request.requestURI.substring(request.requestURI.lastIndexOf('/')+1);
        
        Account acc = [SELECT Id,Name 
                       FROM Account 
                       WHERE Id = :acId];
        
        List<Contact> contacts = [SELECT Id, Name
                                  FROM Contact
                                  WHERE AccountId = :acId];
        
        Map<String, Object> result = new Map<String, Object>();
        result.put('account', acc);
        result.put('contacts', contacts);
        
        
        response.statusCode = 200;
        response.responseBody = Blob.valueOf(JSON.serialize(result));
    }
}