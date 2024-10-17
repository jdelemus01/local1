@isTest
private class LeadProcessorTest {
    
    //Esto es pa crear datos
    @TestSetup
    static void makeData(){
        List<Lead> leads = new List<Lead>();
        
        for (Integer i = 0; i < 200; i++) {
            leads.add(new Lead(
                LastName = 'lastname test',
            Company = 'company test',
            Status = 'Open - Not Contacted',
            LeadSource='Web'));
        }
        insert leads;
    }
    
    //Esto ya sí es el test
    @isTest
    static void test(){
        Test.startTest();
        LeadProcessor lp = new LeadProcessor();
        Id batchId = Database.executeBatch(lp, 200);
        Test.stopTest();
        
        Assert.areEqual(200, [SELECT count() FROM Lead WHERE LeadSource='Dreamforce']);
    }
}