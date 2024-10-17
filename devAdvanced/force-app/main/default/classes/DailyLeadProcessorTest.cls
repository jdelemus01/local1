@IsTest
public with sharing class DailyLeadProcessorTest {
    public DailyLeadProcessorTest() {

    }
@IsTest
static void testLeadUpdate() {
    List<Lead> leadsList = new List<Lead>();
    for(Integer i = 0; i < 200; i++){
        leadsList.add(new Lead(LastName='Test'+i, Company='TestCompany'+i, Status='Open - Not Contacted', LeadSource='Web'));
    }
    insert leadsList;

    Test.startTest();
    DailyLeadProcessor dlp = new DailyLeadProcessor();
    String sch = '0 0 0 15 3 ? 2042';
    String jobId = System.schedule('DailyLeadProcessor', sch, dlp);
    Test.stopTest();

    leadsList = [SELECT Id, LeadSource 
                FROM Lead
                WHERE LeadSource = 'Dreamforce'];    
    System.assertEquals(200, leadsList.size());
    
    for(Lead lead : leadsList){
        System.assertEquals('Dreamforce', lead.LeadSource);
    }
}}