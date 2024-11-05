public with sharing class DataFactory {
    private final static Id oppProgramaRTId =  Schema.SObjectType.Opportunity.getRecordTypeInfosByDeveloperName().get('Programa').getRecordTypeId();
    private final static Id oppPresaleRTId =  Schema.SObjectType.Opportunity.getRecordTypeInfosByDeveloperName().get('Presale').getRecordTypeId();
    private final static Id accClienteRTId =  Schema.SObjectType.Account.getRecordTypeInfosByDeveloperName().get('Cliente').getRecordTypeId();
    private final static Id revolvingLawsuit =  Schema.SObjectType.TM_obj_lawsuit__c.getRecordTypeInfosByDeveloperName().get('TM_rt_lawsuit').getRecordTypeId();
    private final static Id oppOnboardingId = Schema.SObjectType.Opportunity.getRecordTypeInfosByDeveloperName().get('Onboarding').getRecordTypeId();
    private final static Id recordTypeOppAdenda = Schema.SObjectType.Opportunity.getRecordTypeInfosByDeveloperName().get('Adenda').getRecordTypeId();
    private final static Id oppLSORTId =  Schema.SObjectType.Opportunity.getRecordTypeInfosByDeveloperName().get('LSO').getRecordTypeId();
    private static final Id LSOAhorroRT = Schema.SObjectType.Saving__c.getRecordTypeInfosByDevelopername().get('LSO').getRecordTypeId();
    private static final Id ProgramaAhorroRT = Schema.SObjectType.Saving__c.getRecordTypeInfosByDevelopername().get('Programa').getRecordTypeId();
    
    
    
    
    public static void DataFactory(Integer numAccount,Boolean Programa) {
        
        Deactivate_Triggers__c deactiveTriggers = [SELECT Id FROM Deactivate_Triggers__c WHERE Name = 'Default' LIMIT 1];
        deactiveTriggers.AccountTriggerActive__c = false;
        deactiveTriggers.ContactTriggerActive__c = false;
        deactiveTriggers.DebtTriggerActive__c = false;
        deactiveTriggers.EntityTriggerActive__c = false;
        deactiveTriggers.OpportunityTriggerActive__c = false;
        deactiveTriggers.AhorroTriggerActive__c = false;
        update deactiveTriggers;
        
        
        
        
        List<Account> accList = new List<Account>();
        List<Opportunity> lstOpps = new List<Opportunity>();
        List<Opportunity> lstOppsNoVenta = new List<Opportunity>();
        List<Entity__c> lstEnt = new List<Entity__c>();
        List<Debt__c> lstDebt = new List<Debt__c>();
        List<PlandeLiquidacion__c> lstPDL = new List<PlandeLiquidacion__c>();
        List<Saving__c> ListAhorro = new List<Saving__c>();
        List<Producto__c> lstProd = new List<Producto__c>();
        List<TM_obj_lawsuit__c> lstLawsuit = new List<TM_obj_lawsuit__c>();
        List<Cuota_Estructurada__c> ListCE =new List<Cuota_Estructurada__c>();
        List<DebtSettlement__c> lstDS = new List<DebtSettlement__c>();
        List<Pago_Estructurado__c> lstPEToInsert =new List<Pago_Estructurado__c>();
        List<Fees__c> lstHonorarios =new List<Fees__c>();
        
        
        
        if(Programa){
            for (Integer i = 0; i < numAccount; i++) {
                accList.add(new Account(LastName = 'Test' + i, LeadOrigin__c = 'Llamada', Creditors__pc = 'berni', PresaleStage__pc = 'Sin Tocar', RecordTypeId = accClienteRTId,
                TotalDebtOutstanding__c = 20000, NumberCreditorsNames__pc = 2, SaleStage__pc = 'Propuesta', DNI__pc = '12345678A', Cliente_de__c = 'Programa',
                PersonMailingStreet = 'Direccion Test', PersonMailingPostalCode = 'CPTest', PersonMailingState = 'Provincia Test', PersonMailingCity = 'Localidad test',
                PersonEmail = generarEmailAleatorio(), JuniorManagerAssigned__c = UserInfo.getUserId(),Phone =generarTelefonoAleatorio() , PersonMobilePhone = generarTelefonoAleatorio()));
                
            }
            
            insert accList;
            
            for (Integer i = 0; i < numAccount; i++) {
                lstOpps.add(new Opportunity(Name = 'Test1', StageName = PasarelaConstantes.stageContratoFirm, CloseDate = Date.today(), NetValueOfPrimaryResidence__c = 200,
                NetValueOfOtherRealEstate__c = 200, NetValueOfVehicles__c = 200, NetMonthlySalary__c = 200, AdditionalHouseholdIncome__c = 500, FechaCobroPasarela__c = 1,
                FixedExpensesFamilyMortgageInstallme__c = 300, TotalDebtOutstanding__c = 20000, RecordTypeId = oppProgramaRTId, NumberCreditorsNames__c = 2,
                LeadOrigin__c = 'Llamada', AccountId = accList[i].Id));
                
                lstOpps.add(new Opportunity(Name = 'Test2', StageName = PasarelaConstantes.stageContratoFirm, CloseDate = Date.today(), NetValueOfPrimaryResidence__c = 200,
                NetValueOfOtherRealEstate__c = 200, NetValueOfVehicles__c = 200, NetMonthlySalary__c = 200, AdditionalHouseholdIncome__c = 500, FechaCobroPasarela__c = 1,
                FixedExpensesFamilyMortgageInstallme__c = 300, TotalDebtOutstanding__c = 20000, RecordTypeId = oppLSORTId, NumberCreditorsNames__c = 2,
                LeadOrigin__c = 'Llamada', AccountId = accList[i].Id));
                
                lstOppsNoVenta.add(new Opportunity(Name = 'TestLSO', StageName = 'Cerrada ganada', CloseDate = Date.today(), Heritage__c = -2, GuarantorCoOwnerOtherThanPartner__c = 'NO', SocioeconomicCrimes__c = 'NO',
                SanctionedHaciendaOrSS__c = 'NO', Creditors__c = 'berni,berni2,berni3', TotalDebtOutstanding__c = 20000, DebtCategory__c = 'Reclamable', NetValueOfPrimaryResidence__c = 200,
                NetValueOfOtherRealEstate__c = 200, NetValueOfVehicles__c = 200, NetMonthlySalary__c = 200,
                AdditionalHouseholdIncome__c = 500, FixedExpensesFamilyMortgageInstallme__c = 300, RecordTypeId = oppPresaleRTId, AccountId = accList[i].Id,QuestionDone__c  = true));
                
                lstOppsNoVenta.add(new Opportunity(Name = 'Test2', StageName = 'Estudio', CloseDate = Date.today(), NetValueOfPrimaryResidence__c = 200,
                NetValueOfOtherRealEstate__c = 200, NetValueOfVehicles__c = 200, NetMonthlySalary__c = 200, AdditionalHouseholdIncome__c = 500,
                FixedExpensesFamilyMortgageInstallme__c = 300, TotalDebtOutstanding__c = 20000, RecordTypeId = oppOnboardingId,
                NumberCreditorsNames__c = 2, LeadOrigin__c = 'Llamada', AccountId = accList[i].Id, DateClosedAdvisor__c = Date.today()));
                
                lstOppsNoVenta.add( new Opportunity(Name = 'Opportunity Test',RecordTypeId = recordTypeOppAdenda,AccountId =  accList[i].Id,StageName = 'Cerrada ganada',CloseDate = Date.today()));
                
            }
            insert lstOppsNoVenta;
            insert lstOpps;
            
            lstEnt.add(new Entity__c( Name = 'Financiera 1' ,habilitada__c = true, AccountType__c = 'Fondo', CIF__c = 'B87547659'));
            lstEnt.add(new Entity__c( Name = 'Carrefour' ,habilitada__c = true, CIF__c = 'A-79456232', SocialAddress__c = 'Direccion Test' ));
            insert lstEnt;
            
            lstProd.add(new Producto__c(Tipo_de_Producto__c = 'Microcrédito', Entidad__c = lstEnt[0].Id, Correo_Reclamacion__c = 'testsmd@solucionamideuda.es' ));
            lstProd.add(new Producto__c(Tipo_de_Producto__c = 'Microcrédito', Entidad__c = lstEnt[1].Id, Correo_Reclamacion__c = 'testsmd@solucionamideuda.es' ));
            insert lstProd;
            
            
            
            
            
            for (Integer i = 0; i < numAccount; i++) {
                lstPDL.add(new PlandeLiquidacion__c(File__c = lstOpps[i].Id, Active__c = true, Duration__c = 24, CuotaMensual__c = 120, DebtNumber__c = 2, Discount__c = 12,
                TotalActiveDebts__c = 3, TotalPay__c = 15000,InitialComssion__c =100));
                lstDebt.add(new Debt__c(Name = '1', Opportunity__c = lstOpps[i].Id, ActiveDebt__c = 'Si', Financial__c = lstEnt[0].Id, ProductType__c = 'Microcrédito', IsAHolder__c = 'Si', MaximumGracePeriod__c = 15, ClaimSubmissionDate__c = Date.today().addDays(-100)));
                ListAhorro.add(new Saving__c(Opportunity__c =lstOpps[0].Id,MonthlyContribution__c = 100,ThisMonthSavingCheck__c = true,SavingsTypeBalance__c = 'Aportacion Mensual',Recovery__c = 'Si', RecordTypeId = ProgramaAhorroRT, Date__c = System.today()));
            }
            insert lstDebt;
            insert lstPDL;
            insert ListAhorro;
            
            
            
            for (Integer i = 0; i < lstDebt.size(); i++) {
                lstLawsuit.add(new TM_obj_lawsuit__c(TM_fld_debt__c = lstDebt[i].Id, Fecha_firmeza_sentencia_1_Instancia__c = Date.today().addDays(-30), Fecha_firmeza_sentencia_2_instancia__c = null, RecordTypeId = revolvingLawsuit));
                ListCE.add(new Cuota_Estructurada__c(Cantidad__c = 100, Deuda__c= lstDebt[i].Id,Name='1',Numero_de_cuotas__c=1,Numero_de_cuotas_restantes__c=1,orden__c = 1));
                
                
            }
            
            insert lstLawsuit;
            insert ListCE;
            
            for (Integer i = 0; i < lstDebt.size(); i++) {
                lstPEToInsert.add(new Pago_Estructurado__c(Name = 'Pago Cuota 1', Pago__c = ListCE[i].Cantidad__c, Deuda__c = lstDebt[i].Id, Fecha__c = Date.Today(), fecha_aux__c = Date.Today().addMonths(-2), orden__c = 1));
                lstDS.add(new DebtSettlement__c(Debt__c = lstDebt[i].Id, Account__c = accList[i].Id, LiquidationTeam__c = 'Reclamable Amistoso'));
                
            }
            
            insert lstPEToInsert;
            insert lstDS;
        }else{
            
            
            
            for (Integer i = 0; i < numAccount; i++) {
                accList.add(new Account(LastName = 'Test' + i, LeadOrigin__c = 'Llamada', Creditors__pc = 'berni', PresaleStage__pc = 'Sin Tocar', RecordTypeId = accClienteRTId,
                TotalDebtOutstanding__c = 20000, NumberCreditorsNames__pc = 2, SaleStage__pc = 'Propuesta', DNI__pc = '12345678A', Cliente_de__c = 'LSO',
                PersonMailingStreet = 'Direccion Test', PersonMailingPostalCode = 'CPTest', PersonMailingState = 'Provincia Test', PersonMailingCity = 'Localidad test',
                PersonEmail = generarEmailAleatorio(), JuniorManagerAssigned__c = UserInfo.getUserId(),Phone =generarTelefonoAleatorio() , PersonMobilePhone = generarTelefonoAleatorio(),Gender__c = 'Don' ,Gender__pc = 'Don'));
                
            }
            
            insert accList;
            
            for (Integer i = 0; i < numAccount; i++) {
                lstOpps.add(new Opportunity(Name = 'Test '+i, StageName = 'Cerrada ganada', CloseDate = Date.today(), NetValueOfPrimaryResidence__c = 200,
                NetValueOfOtherRealEstate__c = 200, NetValueOfVehicles__c = 200, NetMonthlySalary__c = 200, AdditionalHouseholdIncome__c = 500,
                FixedExpensesFamilyMortgageInstallme__c = 300, TotalDebtOutstanding__c = 20000, RecordTypeId = oppProgramaRTId, NumberCreditorsNames__c = 2,
                LeadOrigin__c = 'Llamada', AccountId = accList[i].Id));
                
                lstOppsNoVenta.add(new Opportunity(Name = 'TestLSO', StageName = 'Cerrada ganada', CloseDate = Date.today(), Heritage__c = -2, GuarantorCoOwnerOtherThanPartner__c = 'NO', SocioeconomicCrimes__c = 'NO',
                SanctionedHaciendaOrSS__c = 'NO', Creditors__c = 'berni,berni2,berni3', TotalDebtOutstanding__c = 20000, DebtCategory__c = 'Reclamable', NetValueOfPrimaryResidence__c = 200,
                NetValueOfOtherRealEstate__c = 200, NetValueOfVehicles__c = 200, NetMonthlySalary__c = 200,
                AdditionalHouseholdIncome__c = 500, FixedExpensesFamilyMortgageInstallme__c = 300, RecordTypeId = oppPresaleRTId, AccountId = accList[i].Id,QuestionDone__c  = true));
                
                lstOppsNoVenta.add(new Opportunity(Name = 'Test2', StageName = 'Estudio', CloseDate = Date.today(), NetValueOfPrimaryResidence__c = 200,
                NetValueOfOtherRealEstate__c = 200, NetValueOfVehicles__c = 200, NetMonthlySalary__c = 200, AdditionalHouseholdIncome__c = 500,
                FixedExpensesFamilyMortgageInstallme__c = 300, TotalDebtOutstanding__c = 20000, RecordTypeId = oppOnboardingId,
                NumberCreditorsNames__c = 2, LeadOrigin__c = 'Llamada', AccountId = accList[i].Id, DateClosedAdvisor__c = Date.today()));
                
                lstOppsNoVenta.add( new Opportunity(Name = 'Opportunity Test',RecordTypeId = recordTypeOppAdenda,AccountId =  accList[i].Id,StageName = 'Cerrada ganada',CloseDate = Date.today()));
                
            }
            insert lstOppsNoVenta;
            insert lstOpps;
            
            lstEnt.add(new Entity__c( Name = 'Financiera 1' ,habilitada__c = true, AccountType__c = 'Fondo', CIF__c = 'B87547659'));
            lstEnt.add(new Entity__c( Name = 'Carrefour' ,habilitada__c = true, CIF__c = 'A-79456232', SocialAddress__c = 'Direccion Test' ));
            insert lstEnt;
            
            lstProd.add(new Producto__c(Tipo_de_Producto__c = 'Microcrédito', Entidad__c = lstEnt[0].Id, Correo_Reclamacion__c = 'testsmd@solucionamideuda.es' ));
            lstProd.add(new Producto__c(Tipo_de_Producto__c = 'Microcrédito', Entidad__c = lstEnt[1].Id, Correo_Reclamacion__c = 'testsmd@solucionamideuda.es' ));
            insert lstProd;
            
            
            
            
            
            for (Integer i = 0; i < numAccount; i++) {
                lstPDL.add(new PlandeLiquidacion__c(File__c = lstOpps[i].Id, Active__c = true, Duration__c = 24, CuotaMensual__c = 120, DebtNumber__c = 2, Discount__c = 12,
                TotalActiveDebts__c = 3, TotalPay__c = 15000,InitialComssion__c =100));
                lstDebt.add(new Debt__c(Name = '1', Opportunity__c = lstOpps[i].Id, ActiveDebt__c = 'Si', Financial__c = lstEnt[0].Id, ProductType__c = 'Microcrédito', IsAHolder__c = 'Si', MaximumGracePeriod__c = 15, ClaimSubmissionDate__c = Date.today().addDays(-100)));
                lstHonorarios.add(new Fees__c(Name = 'Test', Opportunity__c = lstOpps[i].Id,ThisMonthFee__c= true));
            }
            insert lstDebt;
            insert lstPDL;
            insert lstHonorarios;
            
            
            
            for (Integer i = 0; i < lstDebt.size(); i++) {
                lstLawsuit.add(new TM_obj_lawsuit__c(TM_fld_debt__c = lstDebt[i].Id, Fecha_firmeza_sentencia_1_Instancia__c = Date.today().addDays(-30), Fecha_firmeza_sentencia_2_instancia__c = null, RecordTypeId = revolvingLawsuit));
                ListCE.add(new Cuota_Estructurada__c(Cantidad__c = 100, Deuda__c= lstDebt[i].Id,Name='1',Numero_de_cuotas__c=1,Numero_de_cuotas_restantes__c=1,orden__c = 1));
                
                
            }
            
            insert lstLawsuit;
            insert ListCE;
            
            for (Integer i = 0; i < lstDebt.size(); i++) {
                lstPEToInsert.add(new Pago_Estructurado__c(Name = 'Pago Cuota 1', Pago__c = ListCE[i].Cantidad__c, Deuda__c = lstDebt[i].Id, Fecha__c = Date.Today(), fecha_aux__c = Date.Today().addMonths(-2), orden__c = 1));
                //lstDS.add(new DebtSettlement__c(Debt__c = lstDebt[i].Id, Account__c = accList[i].Id, LiquidationTeam__c = 'Reclamable Amistoso'));
                
            }
            
            insert lstPEToInsert;
            //insert lstDS;
        }
        deactiveTriggers.AccountTriggerActive__c = true;
        deactiveTriggers.ContactTriggerActive__c = true;
        deactiveTriggers.DebtTriggerActive__c = true;
        deactiveTriggers.EntityTriggerActive__c = true;
        deactiveTriggers.OpportunityTriggerActive__c = true;
        deactiveTriggers.AhorroTriggerActive__c = true;
        update deactiveTriggers;
        
    }
    static String generarEmailAleatorio() {
        String caracteres = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
        String email = '';
        Integer longitud = 10; // Puedes ajustar la longitud del correo electrónico según tus necesidades
        for (Integer i = 0; i < longitud; i++) {
            Integer indice = Math.mod(Math.floor(Math.random() * caracteres.length()).intValue(), caracteres.length());
            email += caracteres.substring(indice, indice + 1);
        }
        email += '@smd.com'; // Cambia 'example.com' por el dominio que desees
        return email;
    }
    static String generarTelefonoAleatorio() {
        String randomPhone = String.valueOf(600000000 + (Integer)(Math.random() * (699999999 - 600000000 + 1)));
        System.debug(randomPhone);
        
        return randomPhone;
        
    }
}