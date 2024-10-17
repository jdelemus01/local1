public class DailyLeadProcessor implements Schedulable{
    
    public void execute(SchedulableContext ctx){
        List<Lead> leadsToBeUpdated = [SELECT Id, LeadSource
                                        FROM Lead 
                                        WHERE LeadSource != 'Dreamforce'
                                        LIMIT 200];
        for(Lead lead : leadsToBeUpdated){
            lead.LeadSource = 'Dreamforce';
        }

        update leadsToBeUpdated;
    }
}