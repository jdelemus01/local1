@isTest
private class DataFactoryTest {

    @isTest
    static void DataFactory() {
        HTTPMockFactoryZohoController mock = new HTTPMockFactoryZohoController(200, 'OK', '453713000001121001', new Map<String,String>(), new Map<String,String>());
        Test.setMock(HttpCalloutMock.class, mock);
        Deactivate_Triggers__c deactiveTriggers = new Deactivate_Triggers__c();

        deactiveTriggers.Name = 'Default';
        deactiveTriggers.AccountTriggerActive__c = True;
        deactiveTriggers.ContactTriggerActive__c = True;
        deactiveTriggers.DebtTriggerActive__c = True;
        deactiveTriggers.EntityTriggerActive__c = True;
        deactiveTriggers.OpportunityTriggerActive__c = True;

        insert deactiveTriggers;

        DataFactory.DataFactory(1,true);
        DataFactory.DataFactory(1,false);
    }
}