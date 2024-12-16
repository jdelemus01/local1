/*
 ****************************************************************
Apex Class Name : TestFactory
    Created Date : 12/11/2024
    @description :Factory de datos para clases de test
 ****************************************************************
@author : José García
Modification Log:
    Date                Author                  Modification
    12/11/2024          José García             Initial Version
     *****************************************************************
 */
public with sharing class TestFactory {
    
    /*
     *********************************************************
    @Method Name : createDeactiveTriggers
    @author : José García
    @description : devuelve y puede insertar el objeto Deactivate_Triggers__c para desactivar los triggers
    @param : accountTrigger = valor de AccountTriggerActive__c
    @param : ContactTrigger = valor de ContactTriggerActive__c
    @param : debtTrigger = valor de debtTriggerActive__c
    @param : entityTrigger = valor de EntityTriggerActive__c
    @param : OppTrigger = valor de OpportunityTriggerActive__c
    @param : ahorroTrigger = valor de AhorroTriggerActive__c
    @param : lawsuitTrigger = valor de LawsuitTriggerActive__c
    @return : deactivateTriggers = objeto de tipo Deactivate_Triggers__c conn los valores introducidos por parámetros y Name='Default'
     ********************************************************
     */
    public static Deactivate_Triggers__c createDeactiveTriggers(Boolean accountTrigger, Boolean ContactTrigger, Boolean debtTrigger, Boolean entityTrigger,
    Boolean OppTrigger, Boolean ahorroTrigger, Boolean lawsuitTrigger, Boolean toInsert){
        
        
        Deactivate_Triggers__c deactiveTriggers = new Deactivate_Triggers__c();
        
        deactiveTriggers.Name = 'Default';
        deactiveTriggers.AccountTriggerActive__c = accountTrigger;
        deactiveTriggers.ContactTriggerActive__c = ContactTrigger;
        deactiveTriggers.DebtTriggerActive__c = debtTrigger;
        deactiveTriggers.EntityTriggerActive__c = entityTrigger;
        deactiveTriggers.OpportunityTriggerActive__c = OppTrigger;
        deactiveTriggers.AhorroTriggerActive__c = ahorroTrigger;
        deactiveTriggers.LawsuitTriggerActive__c = lawsuitTrigger;
        
        if(toInsert){
            insert deactiveTriggers;
        }
        return deactiveTriggers;
        
    }
    
    /*
     *********************************************************
    @Method Name : createRoundRobinLastOpp
    @author : José García
    @description : devuelve y puede insertar el roundRobin Round_Robin_Last_Opportunity_Assignment__c
    @param : toInsert = true para insertar / false para solo devolver el roundRobin
    @return : roundRobinCS = objeto de tipo Round_Robin_Last_Opportunity_Assignment__c con Name='Último Asesor' y Nombre_Asesor__c='Asesor Rol 1'
     ********************************************************
     */
    public static Round_Robin_Last_Opportunity_Assignment__c createRoundRobinLastOpp(Boolean toInsert){
        Round_Robin_Last_Opportunity_Assignment__c roundRobinCS = new Round_Robin_Last_Opportunity_Assignment__c();
        
        roundRobinCS.Name = 'Último Asesor';
        roundRobinCS.Nombre_Asesor__c = 'Asesor Rol 1';
        
        if(toInsert){
            insert roundRobinCS;
        }
        
        return roundRobinCS;
    }
    
    /*
     *********************************************************
    @Method Name : createNewUser
    @author : José García
    @description : devuelve y puede insertar un usuario test en base a un lastName
    @param : lastName = valor que diferencia un usuario de otra
    @param : toInsert = true para insertar / false para solo devolver el contacto
    @return : usuario = usuario test con valores genéricos
     ********************************************************
     */
    public static User createNewUser(String lastName, Boolean toInsert){
        
        User usuario= new User(alias = 'test1', DefaultGroupNotificationFrequency = 'N', DigestFrequency = 'N', Email = 'test1@example.com', EmailEncodingKey = 'UTF-8', LastName = lastName,
        Username = 'testtt'+lastName+'@example.com', TimeZoneSidKey = 'Europe/Paris', LocaleSidKey = 'es_ES', ProfileId = '00eSo000001MBNdIAO', LanguageLocaleKey = 'es');
        if(toInsert)
            insert usuario;
        
        return usuario;
    }
    
    /*
     *********************************************************
    @Method Name : createNewAccount
    @author : José García
    @description : devuelve y puede insertar una cuenta test en base a un lastName
    @param : lastName = valor que diferencia una cuenta de otra
    @param : toInsert = true para insertar / false para solo devolver el contacto
    @return : acc = cuenta test con valores genéricos
     ********************************************************
     */
    public static Account createNewAccount(String lastName, Boolean toInsert){
        Account acc = new Account(LastName = lastName, LeadOrigin__c = 'Llamada', Creditors__pc = 'berni', PresaleStage__pc = 'Sin Tocar', RecordTypeId = '0127Q000000yrwqQAA',
        TotalDebtOutstanding__c = 20000, NumberCreditorsNames__pc = 2, SaleStage__pc = 'Propuesta', DNI__pc = '12345678A', Cliente_de__c = 'Programa',
        PersonMailingStreet = 'Direccion Test', PersonMailingPostalCode = 'CPTest', PersonMailingState = 'Provincia Test', PersonMailingCity = 'Localidad test');
        
        if(toInsert)
            insert acc;
        
        return acc;
    }
    
    /*
     *********************************************************
    @Method Name : createNewAccountList
    @author : José García
    @description : devuelve y puede insertar una lista de cuentas test en base a un número máximo de cuentas
    @param : nCuentas = valor que marca cuántas cuentas se crearán y diferencia una de otra
    @param : toInsert = true para insertar / false para solo devolver el contacto
    @return : accs = lista de cuentas test con valores genéricos
     ********************************************************
     */
    public static List<Account> createNewAccountList(Integer nCuentas, Boolean toInsert){
        List<Account> accs = new List<Account>();

        for(Integer i = 0; i<nCuentas; i++){
            Account acc = new Account(LastName = 'test'+i, LeadOrigin__c = 'Llamada', Creditors__pc = 'berni', PresaleStage__pc = 'Sin Tocar', RecordTypeId = '0127Q000000yrwqQAA',
            TotalDebtOutstanding__c = 20000, NumberCreditorsNames__pc = 2, SaleStage__pc = 'Propuesta', DNI__pc = i+'2345678A', Cliente_de__c = 'Programa',
            PersonMailingStreet = 'Direccion Test', PersonMailingPostalCode = 'CPTest', PersonMailingState = 'Provincia Test', PersonMailingCity = 'Localidad test');

            accs.add(acc);
        }
        
        if(toInsert)
            insert accs;
        
        return accs;
    }
    
    /*
     *********************************************************
    @Method Name : createContact
    @author : José García
    @description : devuelve y puede insertar un contacto test con valores genéricos
    @param : toInsert = true para insertar / false para solo devolver el contacto
    @return : contacto = contacto test con valores genéricos
     ********************************************************
     */
    public static Contact createContact(Boolean toInsert){
        Contact contacto = new Contact(FirstName = 'FirstPrueba', LastName = 'LastPrueba', Phone = '618166166', MobilePhone = '618166166');
        if(toInsert){
            insert contacto;
        }
        return contacto;
    }
    
    /*
     *********************************************************
    @Method Name : createContactList
    @author : José García
    @description : devuelve y puede insertar una lista de contactos test con valores genéricos
    @param : toInsert = true para insertar / false para solo devolver los contactos
    @return : contacts = lista de contactos test con valores genéricos
     ********************************************************
     */
    public static List<Contact> createContactList(Boolean toInsert, Integer nContacts){
        List<Contact> contacts = new List<Contact>();
        for(Integer i = 0; i<nCOntacts; i++){
            Contact contact = new Contact(FirstName = 'FirstPrueba', LastName = 'LastPrueba'+i, Phone = '618166166', MobilePhone = '618166166');
            contacts.add(contact);
        }
        if(toInsert){
            insert contacts;
        }
        
        return contacts;
    }

    public static MessagingChannel createMessagingChannel(Boolean toInsert){
        MessagingChannel channel = new MessagingChannel(DeveloperName = 'Chat_Web_SMD', MasterLabel = 'Test', MessagingPlatformKey = 'Test', MessagingChannelType = 'SMS');
        if(toInsert){
            insert channel;
        }
    }
    
    /*
     *********************************************************
    @Method Name : createNewMessagingEndUser
    @author : José García
    @description : devuelve y puede insertar un MessagingEndUser test con valores recibidos por parametros
    @param : account = cuenta asociada al MessagingEndUser
    @param : messagingChannel = canal de MessagingEndUser
    @param : messagingEndUser = MessagingEndUser del que coger valores para nombre, tipo de mensaje y plataforma del mensaje(????)
    @param : toInsert = true para insertar / false para solo devolver el contacto
    @return : newMessagingEndUser = messagingEndUser construido en el método
     ********************************************************
     */
    public static MessagingEndUser createNewMessagingEndUser(Account account, MessagingChannel messagingChannel, Boolean toInsert){
        MessagingEndUser newMessagingEndUser = new MessagingEndUser(AccountId = account.Id, messagingChannelId = messagingChannel.Id, Name = 'Test', MessageType = 'WhatsApp');
        newMessagingEndUser.Name = messagingEndUser.Name;
        newMessagingEndUser.MessageType = messagingEndUser.MessageType;
        newMessagingEndUser.MessagingPlatformKey = messagingEndUser.MessagingPlatformKey;
        
        if(toInsert){
            insert newMessagingEndUser;
        }
        return newMessagingEndUser;
    }
    
    /*
     *********************************************************
    @Method Name : createNewDebtByOppAndProd
    @author : José García
    @description : devuelve y puede insertar una deuda en base a una oportunidad y un producto
    @param : op = oportunidad de la deuda
    @param : prod = producto de la deuda
    @param : toInsert = true para insertar / false para solo devolver el contacto
    @return : newDebt = deuda construida
     ********************************************************
     */
    public static Debt__c createNewDebtByOppAndProd(Opportunity opp, Producto__c prod, Boolean toInsert){
        Debt__c newDebt = new Debt__c();
        newDebt.Opportunity__c = opp.Id;
        newDebt.Producto__c = prod.Id;
        newDebt.SettlementType__c = 'Negociacion Judicial';
        newDebt.ActiveDebt__c = 'Si';
        newDebt.Liquidado__c = true;
        if(toInsert){
            insert newDebt;
        }
        return newDebt;
    }
    
    /*
     *********************************************************
    @Method Name : createNewDebtByOpp
    @author : José García
    @description : devuelve y puede insertar una deuda en base a una oportunidad
    @param : op = oportunidad de la deuda
    @param : toInsert = true para insertar / false para solo devolver el contacto
    @return : newDebt = deuda construida
     ********************************************************
     */
    public static Debt__c createNewDebtByOpp(Opportunity opp, Boolean toInsert){
        Debt__c newDebt = new Debt__c();
        newDebt.Opportunity__c = opp.Id;
        newDebt.ActiveDebt__c = 'Si';
        if(toInsert){
            insert newDebt;
        }
        return newDebt;
    }
    
    /*
     *********************************************************
    @Method Name : createNewAccountByAcctRTId
    @author : José García
    @description : devuelve y puede insertar una cuenta en base a un RecordTypeId
    @param : accRTId = recordTypeId de la cuenta
    @param : toInsert = true para insertar / false para solo devolver el contacto
    @return : account = cuenta construida
     ********************************************************
     */
    public static Account createNewAccountByAcctRTId(Id accRTId, Boolean toInsert){
        Account account = new Account(LastName = 'Test', LeadOrigin__c = 'Llamada', Creditors__pc = 'berni', PresaleStage__pc = 'Sin Tocar', RecordTypeId = accRTId,
        TotalDebtOutstanding__c = 20000, NumberCreditorsNames__pc = 2, SaleStage__pc = 'Propuesta', Cliente_de__c = 'Programa');
        if(toInsert){
            insert account;
        }
        return account;
    }
    
    /*
     *********************************************************
    @Method Name : createNewOppByRTId
    @author : José García
    @description : devuelve y puede insertar una oportunidad en base a un RecordTypeId
    @param : accRTId = recordTypeId de la oportunidad
    @param : toInsert = true para insertar / false para solo devolver el contacto
    @return : opp = oprtunidad construida
     ********************************************************
     */
    public static Opportunity createNewOppByRTId(Id oppRTId, Id accId, Boolean toInsert){
        
        Opportunity opp = new Opportunity(Name = 'Test1', StageName = 'Sin tocar', CloseDate = Date.today(), NetValueOfPrimaryResidence__c = 200,
        NetValueOfOtherRealEstate__c = 200, NetValueOfVehicles__c = 200, NetMonthlySalary__c = 200, AdditionalHouseholdIncome__c = 500,
        FixedExpensesFamilyMortgageInstallme__c = 300, TotalDebtOutstanding__c = 20000, RecordTypeId = oppRTId, NumberCreditorsNames__c = 2,
        LeadOrigin__c = 'Llamada', AccountId = accId);
        if(toInsert){
            insert opp;
        }
        
        return opp;
    }
    
    /*
     *********************************************************
    @Method Name : createTotalActiveDebt
    @author : José García
    @description : devuelve y puede insertar un objeto TotalActiveDebt__c
    @param : toInsert = true para insertar / false para solo devolver el contacto
    @return : totalActiveDebt = TotalActiveDebt__c construido
     ********************************************************
     */
    public static TotalActiveDebt__c createTotalActiveDebt(Boolean toInsert){
        TotalActiveDebt__c totalActiveDebt = new TotalActiveDebt__c( MinDebt__c = 1, MaxDebt__c = 100000, Months__c = 1);
        
        if(toInsert){
            insert totalActiveDebt;
        }
        
        return totalActiveDebt;
    }
    
    /*
     *********************************************************
    @Method Name : createNewDebtSettlementByDebt
    @author : José García
    @description : devuelve y puede insertar un objeto DebtSettlement__c en base a una deuda
    @param : debt = deuda que recibe el constructor de DebtSettlement__c
    @param : toInsert = true para insertar / false para solo devolver el contacto
    @return : debtSettlement = DebtSettlement__c construido
     ********************************************************
     */
    public static DebtSettlement__c createNewDebtSettlementByDebt(Debt__c debt, Boolean toInsert){
        
        DebtSettlement__c debtSettlement = new DebtSettlement__c(Debt__c = debt.Id);
        
        if(toInsert){
            insert debtSettlement;
        }
        return debtSettlement;
    }
}