@isTest
private class AhorroHelperTest {
    // Clase mock para simular la respuesta de la llamada web
    private class MockHttpResponseGenerator implements HttpCalloutMock {
        public HTTPResponse respond(HTTPRequest req) {
            String endpoint = req.getEndpoint();
            String method = req.getMethod();
            System.debug('endpoint:'+endpoint);
    
            HttpResponse res = new HttpResponse();
            res.setHeader('Content-Type', 'application/json');
            if(endpoint.contains('zoho.eu') && endpoint.contains('/token')){
                res.setBody('{'+
                            '"access_token": "fsadfsdafsdfasdf",'+
                            '"expires_in": 3600,'+
                            '"api_domain": "https://www.zohoapis.com",'+
                            '"token_type": "Bearer"'+
                            '}');
                res.setStatusCode(200);
            }else{
                res.setBody('');
                res.setStatusCode(200);
            }
            return res;
        }
    
    } 
    
    //¿¿??
    @isTest
    static void testSendAhorroToZoho() {
        // Crea datos de prueba necesarios
        Test.setMock(HttpCalloutMock.class, new MockHttpResponseGenerator());

        Omitir_Reglas_Validacion__c omitReglasValidacion = new Omitir_Reglas_Validacion__c(
            Name = 'Test Omitir Reglas',
            Omitir_Reglas_Validacion__c = true 
        );
        
        insert omitReglasValidacion;

        Deactivate_Triggers__c deactiveTriggers = new Deactivate_Triggers__c();
 
        deactiveTriggers.Name = 'Default';
        deactiveTriggers.AccountTriggerActive__c = False;
        deactiveTriggers.ContactTriggerActive__c = False;
        deactiveTriggers.DebtTriggerActive__c = False;
        deactiveTriggers.EntityTriggerActive__c = False;
        deactiveTriggers.OpportunityTriggerActive__c = False;
 
        insert deactiveTriggers;

        
        Id accRTId = Schema.sObjectType.Account.getRecordTypeInfosByDeveloperName().get('Cliente').getRecordTypeId();
        Account a =new Account(LastName = 'Test', LeadOrigin__c = 'Llamada', Creditors__pc = 'berni', PresaleStage__pc = 'Sin Tocar', RecordTypeId = accRTId,
                                TotalDebtOutstanding__c = 20000, NumberCreditorsNames__pc = 2, SaleStage__pc = 'Propuesta', PersonMobilePhone = '+34661662663', PersonEmail = 'test@test.com',ClientStatus__c = 'Graduado');
        Account b =new Account(LastName = 'Testb', LeadOrigin__c = 'Llamada', Creditors__pc = 'berni', PresaleStage__pc = 'Sin Tocar', RecordTypeId = accRTId,
                                TotalDebtOutstanding__c = 20000, NumberCreditorsNames__pc = 2, SaleStage__pc = 'Propuesta', PersonMobilePhone = '+34661548663', PersonEmail = 'testb@test.com',ClientStatus__c = 'Activo');
        insert a;
        List<TotalActiveDebt__c> lstTotalDebt = new List<TotalActiveDebt__c>();
        lstTotalDebt.add(new TotalActiveDebt__c( MinDebt__c = 1, MaxDebt__c = 100000, Months__c = 1));
        insert lstTotalDebt;
        Profile profileId = [SELECT Id FROM Profile WHERE Name = 'Standard User' LIMIT 1];
        User usuario= new User(alias = 'test1', DefaultGroupNotificationFrequency = 'N', DigestFrequency = 'N', Email = 'test1@example.com', EmailEncodingKey = 'UTF-8', LastName = 'test1',
                            Username = 'testtt1@example.com', TimeZoneSidKey = 'Europe/Paris', LocaleSidKey = 'es_ES', ProfileId = profileId.Id, LanguageLocaleKey = 'es');
       insert usuario;                     
        Id oppProgramaRTId =  Schema.SObjectType.Opportunity.getRecordTypeInfosByDeveloperName().get('Programa').getRecordTypeId();
        Id oppLSORTId =  Schema.SObjectType.Opportunity.getRecordTypeInfosByDeveloperName().get('LSO').getRecordTypeId();
        Opportunity oportunidad = new Opportunity(Name = 'Test2', CloseDate = Date.today(),stageName = 'Estudio', NetValueOfPrimaryResidence__c = 200, FechaCobroPasarela__c = 1, 
                                    NetValueOfOtherRealEstate__c = 200, NetValueOfVehicles__c = 200, NetMonthlySalary__c = 200, AdditionalHouseholdIncome__c = 500, 
                                    FixedExpensesFamilyMortgageInstallme__c = 300, TotalDebtOutstanding__c = 20000, RecordTypeId = oppProgramaRTId, NumberCreditorsNames__c = 2, 
                                    LeadOrigin__c = 'Llamada', AccountId = a.Id, OwnerId = usuario.Id,CompletedFixedFeesDate__c = System.today());
        insert oportunidad;
        Opportunity oportunidad2 = new Opportunity(Name = 'Test2', CloseDate = Date.today(),stageName = 'Estudio', NetValueOfPrimaryResidence__c = 200, FechaCobroPasarela__c = 1, 
                                    NetValueOfOtherRealEstate__c = 200, NetValueOfVehicles__c = 200, NetMonthlySalary__c = 200, AdditionalHouseholdIncome__c = 500, 
                                    FixedExpensesFamilyMortgageInstallme__c = 300, TotalDebtOutstanding__c = 20000, RecordTypeId = oppProgramaRTId, NumberCreditorsNames__c = 2, 
                                    LeadOrigin__c = 'Llamada', AccountId = a.Id, OwnerId = usuario.Id);
        insert oportunidad2;
        Opportunity oportunidad3 = new Opportunity(Name = 'Test2', CloseDate = Date.today(),stageName = 'Estudio', NetValueOfPrimaryResidence__c = 200, FechaCobroPasarela__c = 1, 
        NetValueOfOtherRealEstate__c = 200, NetValueOfVehicles__c = 200, NetMonthlySalary__c = 200, AdditionalHouseholdIncome__c = 500, 
        FixedExpensesFamilyMortgageInstallme__c = 300, TotalDebtOutstanding__c = 20000, RecordTypeId = oppProgramaRTId, NumberCreditorsNames__c = 2, 
        LeadOrigin__c = 'Llamada', AccountId = a.Id, OwnerId = usuario.Id,DateOfFirstContribution__c = System.today());
insert oportunidad3;
        PlandeLiquidacion__c planLiquidacion = new PlandeLiquidacion__c(File__c = oportunidad.Id, Active__c = true, Duration__c = 24, CuotaMensual__c = 120, DebtNumber__c = 2, Discount__c = 12,
                                                                        TotalActiveDebts__c = 3, TotalPay__c = 15000,InitialComssion__c =100);
        insert planLiquidacion;
        List<Debt__c> lstDebt = new List<Debt__c>();
        lstDebt.add(new Debt__c(Opportunity__c = oportunidad.Id, ActiveDebt__c = 'Si', TotalActiveDebts__c = 4, Amount__c = 753));
        
        insert lstDebt;

        DebtSettlement__c newDebtSettlement = new DebtSettlement__c();
        newDebtSettlement.Debt__c = lstDebt[0].Id;
        newDebtSettlement.BankPayment__c =-1000;
        newDebtSettlement.ToWhomIsTheRecoveryPaid__c ='SMD';
        newDebtSettlement.OwnerId = usuario.Id;


        insert newDebtSettlement;
        
        Saving__c newSaving = new Saving__c();
        newSaving.Deuda__c = lstDebt[0].id;
        newSaving.Opportunity__c = oportunidad.Id;
        newSaving.MonthlyContribution__c = 100;
        newSaving.ThisMonthSavingCheck__c = true;
        newSaving.SavingsTypeBalance__c = 'Aportacion Mensual';
        newSaving.Recovery__c = 'Si';
        newSaving.Date__c = System.today();
        insert newSaving;
        Saving__c newSaving3 = new Saving__c();
        newSaving3.Deuda__c = lstDebt[0].id;
        newSaving3.Opportunity__c = oportunidad2.Id;
        newSaving3.MonthlyContribution__c = 100;
        newSaving3.ThisMonthSavingCheck__c = true;
        newSaving3.SavingsTypeBalance__c = 'Aportacion Mensual';
        newSaving3.Recovery__c = 'Si';
        newSaving3.Date__c = System.today();
        insert newSaving3;
        Saving__c newSaving4 = new Saving__c();
        newSaving4.Deuda__c = lstDebt[0].id;
        newSaving4.Opportunity__c = oportunidad3.Id;
        newSaving4.MonthlyContribution__c = 100;
        newSaving4.ThisMonthSavingCheck__c = true;
        newSaving4.SavingsTypeBalance__c = 'Aportacion Mensual';
        newSaving4.Recovery__c = 'Si';
        newSaving4.Date__c = System.today();
        insert newSaving4;

        Saving__c newSaving2 = new Saving__c();
        newSaving2.id =newSaving.id;
        newSaving2.MonthlyContribution__c = 120;
        newSaving2.Date__c = System.today();
        update newSaving2;

        delete newSaving2;

    }

    //Cubre todo menos un if y una excepción
    @isTest
    static void testoppInactivosPagadoNoConciliado(){
        // Crea datos de prueba necesarios
        Test.setMock(HttpCalloutMock.class, new MockHttpResponseGenerator());

        Deactivate_Triggers__c deactiveTriggers = new Deactivate_Triggers__c();
 
        deactiveTriggers.Name = 'Default';
        deactiveTriggers.AccountTriggerActive__c = False;
        deactiveTriggers.ContactTriggerActive__c = False;
        deactiveTriggers.DebtTriggerActive__c = False;
        deactiveTriggers.EntityTriggerActive__c = False;
        deactiveTriggers.OpportunityTriggerActive__c = False;
 
        insert deactiveTriggers;
        
        Id accRTId = Schema.sObjectType.Account.getRecordTypeInfosByDeveloperName().get('Cliente').getRecordTypeId();
        Account a =new Account(LastName = 'Test', LeadOrigin__c = 'Llamada', Creditors__pc = 'berni', PresaleStage__pc = 'Sin Tocar', RecordTypeId = accRTId,
                                TotalDebtOutstanding__c = 20000, NumberCreditorsNames__pc = 2, SaleStage__pc = 'Propuesta', PersonMobilePhone = '+34661662663', PersonEmail = 'test@test.com',ClientStatus__c = 'Graduado');

        Id oppProgramaRTId =  Schema.SObjectType.Opportunity.getRecordTypeInfosByDeveloperName().get('Programa').getRecordTypeId();
        Opportunity oportunidad = new Opportunity(Name = 'Test2', CloseDate = Date.today(),stageName = 'Estudio', NetValueOfPrimaryResidence__c = 200, FechaCobroPasarela__c = 1, 
                                    NetValueOfOtherRealEstate__c = 200, NetValueOfVehicles__c = 200, NetMonthlySalary__c = 200, AdditionalHouseholdIncome__c = 500, 
                                    FixedExpensesFamilyMortgageInstallme__c = 300, TotalDebtOutstanding__c = 20000, RecordTypeId = oppProgramaRTId, NumberCreditorsNames__c = 2, 
                                    LeadOrigin__c = 'Llamada', AccountId = a.Id);
        insert oportunidad;

        Id recordTypeInactivo = Schema.SObjectType.Opportunity.getRecordTypeInfosByDeveloperName().get('Inactivo').getRecordTypeId();
        Opportunity existingOpp = new Opportunity(
            AccountId = a.Id,
            Name = 'Inactivo Test',
            RecordTypeId = recordTypeInactivo,
            StageName = 'Llamada Inicial Inactivos',
            CloseDate = Date.today().addMonths(1)
        );
        insert existingOpp;

        Saving__c newSaving = new Saving__c();
        newSaving.Opportunity__c = oportunidad.Id;
        newSaving.MonthlyContribution__c = 100;
        newSaving.ThisMonthSavingCheck__c = true;
        newSaving.SavingsTypeBalance__c = 'Recuperacion';
        newSaving.Recovery__c = 'Si';
        newSaving.Date__c = System.today();
        insert newSaving;
    }
}