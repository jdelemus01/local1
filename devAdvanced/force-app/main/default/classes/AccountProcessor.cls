public with sharing class AccountProcessor {
    @future
    public static void countContacts(List<ID> accIds){
        List<AggregateResult> numberOfContacts;
        Account thisAccount;
        for(Integer i = 0; i<accIds.size(); i++){
            thisAccount = [SELECT Id,Number_of_Contacts__c 
                FROM Account 
                WHERE Id = :accIds.get(i)];
            
            numberOfContacts = [SELECT COUNT(Id) totalContacts
                        FROM Contact
                        WHERE AccountId = :accIds.get(i)];
            
            thisAccount.Number_of_Contacts__c = (Integer) numberOfContacts[0].get('totalContacts');
            
            upsert thisAccount;
        }
    }
}