trigger ClosedOpportunityTrigger on Opportunity (after insert, after update) {
    
    List<Task> tasksToInsert = new List<Task>();

    for (Opportunity opp : Trigger.new) {
        if (opp.StageName == 'Closed Won') {
            Task task = new Task();
            task.WhatId = opp.Id;
            task.Subject = 'Follow Up Test Task';
            tasksToInsert.add(task);
        }
    }

    if(!tasksToInsert.isEmpty()){
        insert tasksToInsert;
    }
}