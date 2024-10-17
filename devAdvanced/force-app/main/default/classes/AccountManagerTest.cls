@IsTest
public with sharing class AccountManagerTest {
@IsTest
    static void testGetAccount() {
        Account acc = [SELECT Id, Name FROM Account LIMIT 1];
        List<Contact> conresult = [SELECT Id,Name FROM Contact WHERE AccountId = :acc.Id];
        // Call the getAccount method
        RestRequest request = new RestRequest();
        RestResponse response = new RestResponse();
        request.requestURI = 'https://yourInstance.my.salesforce.com/services/apexrest/Cases/' + acc.Id;
        RestContext.request = request;
        RestContext.response = response;
        Test.startTest();
        AccountManager.getAccount();
        Test.stopTest();
        // Verify the response
        System.assertEquals(200, response.statusCode, 'Expected status code to be 200');
        Map<String, Object> result = (Map<String, Object>) JSON.deserializeUntyped(response.responseBody.toString());
        //System.assertEquals(acc.Id, result.get('account').Id, 'Account Id does not match');
        System.assertEquals(conresult.size(), ((List<Contact>) result.get('contacts')).size(), 'Contact size does not match');
    }}