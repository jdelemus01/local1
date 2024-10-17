@IsTest
public class AddPrimaryContactTest {
    
    @IsTest
    public static void testQueueable() {
        List<Account> accs = new List<Account>();
        for(Integer i = 0; i < 100; i++){
            if(i<50){
                Account acc = new Account(
                    Name = 'Test '+i,
                BillingState='NY'
                    );
                accs.add(acc);
                System.debug('Acc '+i+''+acc);
            } else {
                Account acc = new Account(
                    Name = 'Test '+i,
                BillingState='CA'
                    );
                accs.add(acc);
                System.debug('Acc '+i+''+acc);
            }
        }
        insert accs;
        System.debug(accs);

        Contact contactToInsert = new Contact(lastName='Test');

        AddPrimaryContact addPrimaryContact = new AddPrimaryContact(contactToInsert, 'CA');

        Test.startTest();
            System.enqueueJob(addPrimaryContact);
        Test.stopTest();

        System.Assert.areEqual(50, [SELECT count() FROM Account WHERE BillingState='CA']);
    }}