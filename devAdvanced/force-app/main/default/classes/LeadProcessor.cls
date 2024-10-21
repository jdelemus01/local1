public class LeadProcessor implements Database.Batchable<sObject> {

    public Database.QueryLocator start(Database.BatchableContext bc) {
        return Database.getQueryLocator('SELECT Id, LeadSource FROM Lead');
    }

    public void execute(Database.BatchableContext bc, List<Lead> scope) {
        for (Lead a : scope) {
            a.LeadSource = 'Dreamforce';
        }
        update scope;
    }

    public void finish(Database.BatchableContext bc) {
        System.debug('Aparcao');
    }
}