/*
*********************************************************
Apex Class Name : AhorroHelper
Created Date : 21/08/2023
@description :Es el trigger de Ahorros
@author : Andres Redondo
Modification Log:
Date                Author                  Modification
21/08/2023          Andres Redondo          Initial Version
23/05/2024          Pablo Rodriguez         SMD-826: Se añade la lógica para la creacion de aportacion mensual para la eliminacion de zoho
28/05/2024          Jesus Ruiz              SO-656: Se añade el trigger que cierra las task de inactividad con y sin fecha en caso de que se cree el ahorro de aportación mensual del mes
03/07/2024          Pablo Rodriguez         SO-853:   Se borra el fecha 1er ahorro e importe 1er ahorro cuando pasa a baja 1er mes
07/08/2024          Daniel Santaella        SO-1019: Función para el automatismo de la sección PAGADO NO CONCILIADO, cuando entre un ahorro de tipo Recuperación o aportación mensual y tiene una opp de inactivo abierta. 
16/09/2024          Miguel León             SO-1307: Se calcula el porcentaje del total ahorro disponible entre el total de la deuda de cada entidad cada vez que se modifica un ahorro
07/08/2024          Daniel Santaella        SO-1019: Función para el automatismo de la sección PAGADO NO CONCILIADO, cuando entre un ahorro de tipo Recuperación o aportación mensual y tiene una opp de inactivo abierta. 
25/09/2024          Pablo Rodriguez     	SO-1372 : Hacer que los ahorros esten separados por RT y que las comisiones mensuales no se ponga a 0 la comision inicial y mensual
25/09/2024          Jesus Ruiz              SO-1351: Llamada al batch que actualiza los honorarios pendientes LSO en Kmaleon
03/10/2024          Pablo Rodriguez         SO-1432: Da error de 101 query y se refactorizo un poco
08/10/2024          Pablo Rodriguez         Hotfix: Poner que el campo si esta a null no entre
30/10/2024          Pablo Rodriguez         Hotfix:No estaban creandose las comisiones mensuales ni iniciales
*********************************************************
*/
public with sharing class AhorroHelper {  
    private static final Id LSOAhorroRT = Schema.SObjectType.Saving__c.getRecordTypeInfosByDevelopername().get('LSO').getRecordTypeId();
    private static final Id ProgramaAhorroRT = Schema.SObjectType.Saving__c.getRecordTypeInfosByDevelopername().get('Programa').getRecordTypeId();
    private static final Id ProgramaRT = Schema.SObjectType.Opportunity.getRecordTypeInfosByDevelopername().get('Programa').getRecordTypeId();
    private static final Id LSORT = Schema.SObjectType.Opportunity.getRecordTypeInfosByDevelopername().get('LSO').getRecordTypeId();

    public static void sendAhorroToZoho(List<Saving__c> newSavings) {
        Set<String> setUserId = new Set<String>();
        List<SkipCallWS__c> scList = SkipCallWS__c.getall().values();
        for (SkipCallWS__c sc : scList) {
            setUserId.add(sc.UserId__c);
        }
        if(!newSavings.isEmpty() && !setUserId.contains(System.UserInfo.getUserId()) && !AhorroTriggerHandler.skipWS){
            System.enqueueJob(new ZohoAhorroQueueable(newSavings, true));
        }
    }
    public static void create(List<Saving__c> ahorros) {

        //Pablo Rodriguez, saqué el Select fuera del for.
        List<DebtSettlement__c> liquidaciones = [SELECT id, BankPayment__c, Debt__r.Opportunity__r.Account.PersonContactId, Recupero_parcial__c,OwnerId FROM DebtSettlement__c WHERE Debt__c IN :getDeudas(ahorros) AND LiquidationStatus__c NOT IN ('Liquidación completada', 'Liquidación perdida') LIMIT 1];
        List<Task> listaTareas = new List<Task>();
        
        for (Saving__c ahorro : ahorros) {
            if (ahorro.Deuda__c != null) {
                for (DebtSettlement__c liquidacion : liquidaciones) {
                    if ((ahorro.Recovery__c == 'Si' && liquidacion.BankPayment__c * -1 > ahorro.MonthlyContribution__c && liquidacion.Recupero_parcial__c == null) || (liquidacion.Recupero_parcial__c != null && liquidacion.Recupero_parcial__c * -1 > ahorro.MonthlyContribution__c)) {
                        listaTareas.add(createTask(liquidacion, 'Ha entrado un recupero con una cantidad menor', 'Normal', 'Open', liquidacion.id, System.today().addDays(3), 'Liquidación'));
                    }
                }
            }
        }
        
        if (!listaTareas.isEmpty()) {
            insert listaTareas;
        }
    }
    
    public static Set<Id> getDeudas(List<Saving__c> ahorros) {
        Set<Id> idsDeudas = new Set<Id>();
        
        for (Saving__c ahorro : ahorros) {
            if (ahorro.Deuda__c != null) {
                idsDeudas.add(ahorro.Deuda__c);
            }
        }
        
        return idsDeudas;
    }
    

    public static void createTaskAhorroGraduado(List<Saving__c> newSavings) {
                        //Pablo Rodriguez, saqué el Select fuera del for.

        List<Task> tasksToInsert = new List<Task>();
        
        Set<Id> clientIds = new Set<Id>();
        for (Saving__c save : newSavings) {
            clientIds.add(save.Client__c);
        }

        Map<Id, Account> accountMap = new Map<Id, Account>([SELECT Id, ClientStatus__c, PersonContactId, OwnerId FROM Account WHERE Id IN :clientIds]);
    
        for (Saving__c save : newSavings) {
            Account account = accountMap.get(save.Client__c);
    
            if (account != null && (account.ClientStatus__c == 'Baja' || account.ClientStatus__c == 'Propuesta de Baja' || account.ClientStatus__c == 'Graduado' || account.ClientStatus__c == 'Graduado pdt. honorarios') && save.SavingsTypeBalance__c == 'Aportacion Mensual') {
                Task task = createTask2(account, 'Aportación a revisar', 'Normal', 'Open', account.Id, System.today().addDays(2), 'Pago');
                tasksToInsert.add(task);
            }
        }
    
        if (!tasksToInsert.isEmpty()) {
            insert tasksToInsert;
        }
    }
    

    /*public static void setLastMensualContributionDate(Map<Id, Saving__c> oldSavings, List<Saving__c> newSavings){
        Set<Id> oppIdSet = new Set<Id>();
        for (Saving__c sav : newSavings) {
            if 
            (
                (sav.SavingsTypeBalance__c == 'Aportacion Mensual' && sav.Date__c != null ) &&
                (Trigger.isInsert || Trigger.isUpdate &&  oldSavings.get(sav.Id).Date__c != sav.Date__c)
                
            ) 
            {
                oppIdSet.add(sav.Opportunity__c);                
            }
        }

        if (!oppIdSet.isEmpty()) {
            Map<Id,List<Saving__c>> savingsPerOpp = new Map<Id,List<Saving__c>>();
            for (Saving__c sav : [SELECT Id, Opportunity__c, Date__c FROM Saving__c WHERE Opportunity__c IN :oppIdSet AND SavingsTypeBalance__c = 'Aportacion Mensual']) {
                System.debug(sav);
                if (savingsPerOpp.containsKey(sav.Opportunity__c)) {
                    savingsPerOpp.get(sav.Opportunity__c).add(sav);
                } else {
                    savingsPerOpp.put(sav.Opportunity__c, new List<Saving__c> { sav });
                }
            }

            List<Opportunity> oppToUpdate = new List<Opportunity>();
            for (Id oppId : savingsPerOpp.keySet()) {
                System.debug(getMostRecentDate(savingsPerOpp.get(oppId)));
                Opportunity opp = new Opportunity(
                    Id = oppId,
                    LastMensualContributionDate__c = getMostRecentDate(savingsPerOpp.get(oppId))
                );
                oppToUpdate.add(opp);
            }
            update oppToUpdate;
        }
    }*/

    public static void RTSegunOpp(List<Saving__c> newSavings) {
        // Recolectar los IDs de las oportunidades relacionadas con los nuevos ahorros
        Set<Id> opportunityIds = new Set<Id>();
        Map<Id, Opportunity> relatedOpportunities = new Map<Id, Opportunity>();
        for (Saving__c sav : newSavings) {
            if (sav.Opportunity__c != null) {
                opportunityIds.add(sav.Opportunity__c);
            }
        }
    

    
        try {
        // Consultar las oportunidades relacionadas para obtener sus RecordType
         relatedOpportunities = new Map<Id, Opportunity>(
            [SELECT Id, RecordType.Name 
             FROM Opportunity 
             WHERE Id IN :opportunityIds]
        );        
    } catch (DmlException e) {
            // Manejo de errores, por ejemplo, registrando el error
            ErrorManagement.createInsertErrorLog(AhorroHelper.class.getName(), 200, 'Error en la query del RTSegunOpp',  e);

        }
        // Asignar el RecordType adecuado a cada ahorro según el RecordType de la oportunidad relacionada
        for (Saving__c sav : newSavings) {
            Opportunity relatedOpp = relatedOpportunities.get(sav.Opportunity__c);
            if (relatedOpp != null && sav.RecordTypeId == null) {
                if (relatedOpp.RecordTypeId == ProgramaRT) {
                    sav.RecordTypeId = ProgramaAhorroRT;
                } else if (relatedOpp.RecordTypeId == LSORT) {
                    sav.RecordTypeId = LSOAhorroRT;
                }
            }
        }
    }
    
    public static void setThisMonthContribution(Map<Id, Saving__c> oldSavings, List<Saving__c> newSavings){
        for (Saving__c sav : newSavings) {
            if (
                (Trigger.isInsert && sav.Date__c != null) ||
                (Trigger.isUpdate && sav.Date__c != null && sav.Date__c != oldSavings.get(sav.Id).Date__c)
                ) 
            {
                if (sav.Date__c.year() == System.today().year() && sav.Date__c.month() == System.today().month()) {
                    sav.ThisMonthSavingCheck__c = true;
                }  else {
                    sav.ThisMonthSavingCheck__c = false;
                }
            }
        }
    }

    //Devuelve la fecha del ahorro mas reciente de la lista de ahorros que recibe
    private static Date getMostRecentDate(List<Saving__c> savList) {
        if (savList == null || savList.isEmpty()) {
            return null;
        }

        Date mostRecentDate = savList[0].Date__c;

        for (Saving__c sav : savList) {
            if (sav.Date__c > mostRecentDate) {
                mostRecentDate = sav.Date__c;
            }
        }

        return mostRecentDate;
    }    

    public static Task createTask(DebtSettlement__c acc, String name, String priority, String status, String accId, Date duedate, String tipo) {
        Task tsk = new Task(
            Subject         =   name,
            Priority        =   priority,
            Status          =   status,
            WhoId           =   acc.Debt__r.Opportunity__r.Account.PersonContactId,
            WhatId          =   accId,
            ActivityDate    =   duedate,
            Type            =   tipo
        );

            tsk.OwnerId = acc.OwnerId;
        

        return tsk;
    }

    public static Task createTask2(Account acc, String name, String priority, String status, Id accId, Date duedate, String tipo) {
        Task tsk = new Task(
            Subject         = name,
            Priority        = priority,
            Status          = status,
            WhoId           = acc.PersonContactId,
            WhatId          = accId,
            ActivityDate    = duedate,
            Type            =   tipo
        );
        tsk.OwnerId = acc.OwnerId;
    
        return tsk;
    }

    public static void createAsientoCerrado(List<Saving__c> oldSavings){
        List<Asiento_eliminado__c> asientosToCreate = new List<Asiento_eliminado__c>();
        
        for(Saving__c ahorro : oldSavings){

            Asiento_eliminado__c nuevoAsiento = new Asiento_eliminado__c();

            if (ahorro.Name.length() > 60) {
                // Si el campo Name tiene más de 60 caracteres, toma los primeros 60
                String nombreCorto = ahorro.Name.substring(0, 60);
                nuevoAsiento.Name = 'Ahorro eliminado ' + nombreCorto;
            } else {
                nuevoAsiento.Name = 'Ahorro eliminado ' + ahorro.Name;
            }
            
            nuevoAsiento.Actualizacion_Masiva__c = ahorro.Actualizacion_Masiva__c;
            nuevoAsiento.Ahorro_Automatico__c = ahorro.AutomaticSavings__c;
            nuevoAsiento.Ahorro_Automatico_No_Usar__c = ahorro.AutomaticSavindNotUse__c;
            nuevoAsiento.Ahorro_creado__c = ahorro.SavingCreated__c;
            nuevoAsiento.Ahorro_en_este_mes__c = ahorro.ThisMonthSavingCheck__c;
            nuevoAsiento.Ahorro_Name__c = ahorro.Name;
            nuevoAsiento.Aportaci_n_Mensual__c = ahorro.MonthlyContribution__c;
            nuevoAsiento.Banco_Del_Ingreso__c = ahorro.Banco_Del_Ingreso__c;
            nuevoAsiento.Client__c = ahorro.Client__c;
            nuevoAsiento.ClientZohoId__c = ahorro.ClientZohoId__c;
            nuevoAsiento.Comision_Cobrada__c = ahorro.CommissionCollected__c;            
            nuevoAsiento.Comisi_n_Inicial__c = ahorro.InitialCommission__c;
            nuevoAsiento.Comision_Liquidacion__c = ahorro.SettlementCommission__c;
            nuevoAsiento.Comision_Mensual__c = ahorro.MonthlyCommission__c;
            nuevoAsiento.creado_por_en_zoho__c = ahorro.creado_por_en_zoho__c;
            nuevoAsiento.Deuda__c = ahorro.Deuda__c;
            nuevoAsiento.Email__c = ahorro.Email__c;
            nuevoAsiento.Exchange_Rate__c = ahorro.ExchangeRate__c;
            nuevoAsiento.Facturado__c = ahorro.Facturado__c;
            nuevoAsiento.Fecha__c = ahorro.Date__c;
            nuevoAsiento.Fecha_asiento_rectificado__c = ahorro.DateOfRectifiedEntry__c;
            nuevoAsiento.Fecha_Creacion_Zoho__c = ahorro.ZohoCreationDate__c;
            nuevoAsiento.Fecha_Ingreso__c = ahorro.EntryDate__c;
            nuevoAsiento.Financiera__c = ahorro.Financial__c;
            nuevoAsiento.Id_Holded__c = ahorro.HoldedId__c;
            nuevoAsiento.AttorneyImports__c = ahorro.AttorneyImports__c;
            nuevoAsiento.RevenuesSuccess__c = ahorro.RevenuesSuccess__c;
            nuevoAsiento.Liquidacion__c = ahorro.Liquidation__c;
            nuevoAsiento.DebtSettlement__c = ahorro.DebtSettlement__c;
            nuevoAsiento.NoMonthlyCommission__c = ahorro.NoMonthlyCommission__c;
            nuevoAsiento.Opportunity__c = ahorro.Opportunity__c;
            nuevoAsiento.Pago_especial__c = ahorro.SpecialPayment__c;
            nuevoAsiento.Para_actualizar__c = ahorro.ToUpdate__c;
            nuevoAsiento.Provision__c = ahorro.Provision__c;
            nuevoAsiento.Recuperacion__c = ahorro.Recovery__c;
            nuevoAsiento.Saldo__c = ahorro.Balance__c;
            nuevoAsiento.Saldo_Provisionado__c = ahorro.ProvisionedBalance__c;
            nuevoAsiento.Tipo_Ahorro__c = ahorro.SavingsTypeBalance__c;
            nuevoAsiento.Total_Ahorro_Real__c = ahorro.RealTotalSaving__c;
            nuevoAsiento.ZohoID__c = ahorro.Zoho_Id__c;
            nuevoAsiento.IsItAMonthlyCommission__c = ahorro.IsItAMonthlyCommission__c;
            nuevoAsiento.PaymentGateway__c = ahorro.PaymentGateway__c;
            nuevoAsiento.DoYouHaveEnoughSavings__c = ahorro.DoYouHaveEnoughSavings__c;

            asientosToCreate.add(nuevoAsiento);
        }

        if(!asientosToCreate.isEmpty()){
            insert asientosToCreate;
        }            
    }

    /*
    *********************************************************
    @Method Name : closeInactiveTasks
    @author : Jesus Ruiz
    @description : Al crearse el ahorro del tipo "aportación mensual"
    de un cliente que tenía una task de alerta de inactividad se cierra
    automáticamente dicha task ya que se ha hecho la aportación y no caerá
    en inactivo. 
    @param : List<Account> oldAccs, Lista de los nuevos ahorros que se han creado
    @return : void
    ********************************************************
    */
    public static void closeInactiveTasks(List<Saving__c> Ahorros) {
        List<Task> tskToClose = new List<Task>();
    
        // Obtener la lista de IDs de cuentas de los ahorros
        Set<Id> accountIds = new Set<Id>();
        for (Saving__c ahorro : Ahorros) {
            if (ahorro.SavingsTypeBalance__c == 'Aportacion Mensual') {
                accountIds.add(ahorro.Client__c);
            }
        }
    
        // Consultar las cuentas necesarias en una sola consulta
        Map<Id, Account> accountMap = new Map<Id, Account>(
            [SELECT Id, ClientStatus__c, S40Date__c FROM Account WHERE Id IN :accountIds]
        );
    
        // Consultar las tareas necesarias en una sola consulta
        List<Task> allTasks = [SELECT Id, Status, Subject FROM Task 
        WHERE Subject LIKE 'S35%'
        AND (Status = 'Open' OR Status = 'Not Started')
            AND WhatId IN :accountIds];
    
        for (Saving__c ahorro : Ahorros) {
            if (ahorro.SavingsTypeBalance__c == 'Aportacion Mensual') {
                Account cuenta = accountMap.get(ahorro.Client__c);
    
                if (cuenta != null) {
                    System.debug('Datos extraidos: ' + cuenta.Id + ', ' + cuenta.ClientStatus__c + ', ' + cuenta.S40Date__c);
    
                    if (cuenta.S40Date__c != null && Date.today() > cuenta.S40Date__c && (cuenta.ClientStatus__c != 'Inactivo' || cuenta.ClientStatus__c != 'Propuesta de Baja')) {
                        System.debug('Se cumplen todas las condiciones');
    
                        for (Task tsk : allTasks) {
                            System.debug('Nombre de la task' + tsk);
                            Task taskInstance = new Task(Id = tsk.Id, Status = 'Completada ganada');
                            tskToClose.add(taskInstance);            
                        }
                    }
                }
            }
        }
    
        if (!tskToClose.isEmpty()) {
            update tskToClose;
        }
    }


/*
 *********************************************************
 @Method Name : createAportacionMensual
 @author : Pablo Rodriguez
 @description : SMD-826 Creacion de los ahorros de aportacion mensual segun la logica 
 que estaba en Zoho
 @param : List<Saving__c> newSavings , Son los ahorros que se insertan en el trigger
 @return : void
 ********************************************************
 */
public static void createAportacionMensual(List<Saving__c> newSavings) {
    Map<Id, Opportunity> oppMap = new Map<Id, Opportunity>();
    List<Id> IDoppList = new List<Id>();
    List<Saving__c> AhorroToUpdate = new List<Saving__c>();
    Date todaydate = System.today();
    Opportunity oppActual;
    PlandeLiquidacion__c planActual;
    Double comisionmensual = 0;
    Double comisionInicial = 0;
    Double honoraiosRestantes = 0;
    Double comisionmensualMenosInicial = 0;


    for (Saving__c ahorro : newSavings) {
        IDoppList.add(ahorro.Opportunity__c);
    }

    List<Opportunity> opportunities = [
        SELECT Id, FixedFees__c, CompletedFixedFeesDate__c, DateOfFirstContribution__c, 
               Total_Comision_Inicial__c,
               Total_Comision_Mensual__c,account.TypeOfContract__c, RecordTypeId,
               (SELECT Id, InitialComssion__c FROM LiquidationPlan__r WHERE Active__c = true) 
        FROM Opportunity 
        WHERE Id IN :IDoppList
    ];

    for (Opportunity opp : opportunities) {
        oppMap.put(opp.Id, opp);
    }

    for (Saving__c ahorro : newSavings) {
       

        oppActual = oppMap.get(ahorro.Opportunity__c);
        planActual = (oppActual.LiquidationPlan__r != null && !oppActual.LiquidationPlan__r.isEmpty()) 
                      ? oppActual.LiquidationPlan__r[0] : null;

        Double ahorroMensualContribution = (ahorro.MonthlyContribution__c != null) ? ahorro.MonthlyContribution__c : 0;
        Double oppFixedFees = (oppActual.FixedFees__c != null) ? oppActual.FixedFees__c : 0;
        Double oppTotalInitialCommission = (oppActual.Total_Comision_Inicial__c != null) ? oppActual.Total_Comision_Inicial__c : 0;
        Double oppTotalMonthlyCommission = (oppActual.Total_Comision_Mensual__c != null) ? oppActual.Total_Comision_Mensual__c : 0;
        Double planInitialCommission = (planActual != null && planActual.InitialComssion__c != null) ? planActual.InitialComssion__c : 0;
        String nombreAhorro = (ahorro.Zoho_Id__c != null && ahorro.SavingsTypeBalance__c == 'Aportacion Mensual' && ahorro.RecordTypeid == ProgramaAhorroRT) ?  
        'Aportación (' + String.valueOf(ahorro.Date__c.month()) + ')' : 
        (ahorro.RecordTypeid == LSOAhorroRT && ahorro.SavingsTypeBalance__c == 'Aportacion Mensual') ? 
        'Honorario (' + String.valueOf(ahorro.Date__c.month()) + ')' : 
        ahorro.name;
        String tipoahorro = ahorro.SavingsTypeBalance__c;
        Date fecha = ahorro.Date__c;



        comisionmensual = ahorroMensualContribution * SMD_cls_Constants.COMISION * SMD_cls_Constants.IVA;
        comisionInicial = (planInitialCommission - oppTotalInitialCommission) >= 0 ? planInitialCommission - oppTotalInitialCommission : 0 ;
        comisionmensualMenosInicial = (ahorroMensualContribution - (comisionInicial >= 0 ? comisionInicial : 0)) * SMD_cls_Constants.COMISION * SMD_cls_Constants.IVA;
        honoraiosRestantes = (oppFixedFees - (oppTotalInitialCommission + oppTotalMonthlyCommission)) >=0 ? oppFixedFees - (oppTotalInitialCommission + oppTotalMonthlyCommission) : 0;

        Saving__c newSaving = new Saving__c(
            Id = ahorro.Id,
            SavingsTypeBalance__c = tipoahorro,
            Name = nombreAhorro,
            Date__c = fecha
        );
        if (oppActual.CompletedFixedFeesDate__c != null) {

            newSaving.InitialCommission__c = 0;
            newSaving.MonthlyCommission__c = 0;
            
            
        } else if (oppActual.DateOfFirstContribution__c != null) {
            if (ahorroMensualContribution > comisionInicial) {

                if (honoraiosRestantes - comisionInicial <= comisionmensualMenosInicial) {
                    comisionmensualMenosInicial = honoraiosRestantes - comisionInicial;
                }

              
                newSaving.InitialCommission__c = comisionInicial;
                newSaving.MonthlyCommission__c = comisionmensualMenosInicial;
                
            } else {

                newSaving.InitialCommission__c = ahorroMensualContribution;
                newSaving.MonthlyCommission__c = 0;
                
            }
        } else if (ahorroMensualContribution > comisionInicial) {

            if (honoraiosRestantes - comisionInicial <= comisionmensualMenosInicial) {
                comisionmensualMenosInicial = honoraiosRestantes - comisionInicial;
            }

            
            newSaving.InitialCommission__c = comisionInicial;
            newSaving.MonthlyCommission__c = comisionmensualMenosInicial;
            
        } else {
            
            newSaving.InitialCommission__c = ahorroMensualContribution;
            newSaving.MonthlyCommission__c = 0;
            
        }
        //SO-1372 START
        if((tipoahorro == 'Aportacion Mensual' || tipoahorro == 'Recuperacion') && oppActual.account.TypeOfContract__c != 'C. Viejo'){
            newSaving.InitialCommission__c = (newSaving.InitialCommission__c < 0) ? 0 : newSaving.InitialCommission__c.setScale(2, RoundingMode.HALF_UP);
            newSaving.MonthlyCommission__c = (newSaving.MonthlyCommission__c < 0) ? 0 : newSaving.MonthlyCommission__c.setScale(2, RoundingMode.HALF_UP);
            if(ahorro.RecordTypeId != ProgramaAhorroRT){
                newSaving.InitialCommission__c = 0;
                newSaving.MonthlyCommission__c = 0;
            }
            AhorroToUpdate.add(newSaving);
        }else if(tipoahorro != 'Comision' && tipoahorro !='Rectificación factura'){
            newSaving.InitialCommission__c = 0;
            newSaving.MonthlyCommission__c = 0;
            AhorroToUpdate.add(newSaving);
        }

        //SO-1372 END
    }
    //SO-1432 START
    if (!AhorroToUpdate.isEmpty()) {
        try {
            update AhorroToUpdate;
        } catch (Exception e) {
            ErrorManagement.createInsertErrorLog('AhorroHelper', 1010, 'Update de ahorros '+AhorroToUpdate, e);
        }  
    }
    //SO-1432 END

}

/*
 *********************************************************
 @Method Name : AhorroAntiguo
 @author : Pablo Rodriguez
 @description : SMD-826 meter el ahorro anterior al insertado y meterlo en el campo ahorro anterior
 @param : List<Saving__c> newSavings , Son los ahorros que se insertan en el trigger
 @return : void
 ********************************************************
 */
public static void actualizarAhorroAntiguo(List<Saving__c> nuevosAhorros) {

    if (nuevosAhorros == null || nuevosAhorros.isEmpty()) {
        return;
    }
    
    Map<Id, Opportunity> mapaOportunidades = new Map<Id, Opportunity>();
    Set<Id> idsOportunidades = new Set<Id>();
    List<Saving__c> ahorrosActualizar = new List<Saving__c>();
    List<Opportunity> oppActualizar = new List<Opportunity>();

    // Recopilar los IDs de las oportunidades
    for (Saving__c ahorro : nuevosAhorros) {
        if (ahorro.Opportunity__c != null) {
            idsOportunidades.add(ahorro.Opportunity__c);
        }
    }
    
    // Consultar las oportunidades y sus ahorros asociados
    List<Opportunity> oportunidades = [
        SELECT Id,AmountFirstSaving__c, (SELECT Id, Date__c, MonthlyContribution__c, Saldo__c, Provision__c, 
                                  Ahorro_Antiguo__r.Provision__c, Ahorro_Antiguo__r.Total_Ahorro_Disponible2__c, 
                                  Ahorro_Antiguo__c, RecordTypeId 
                                  FROM Savings__r 
                                  ORDER BY Date__c, CreatedDate ASC)
        FROM Opportunity 
        WHERE Id IN :idsOportunidades
    ];
    
    for (Opportunity oportunidad : oportunidades) {
        if (oportunidad.Savings__r != null && !oportunidad.Savings__r.isEmpty()) {
            mapaOportunidades.put(oportunidad.Id, oportunidad);
        }
    }
    //SO-1372 START
    // Procesar los ahorros y actualizar los campos correspondientes
    for (Opportunity oportunidad : mapaOportunidades.values()) {
        // Variables para los cálculos de cada tipo de ahorro
        Double ahorroRealProgramaAhorro = 0;
        Double ahorroDisponibleProgramaAhorro = 0;
        Double honorariosAbonadosProgramaAhorro = 0;

        Double ahorroRealLSOAhorro = 0;
        Double ahorroDisponibleLSOAhorro = 0;
        Double honorariosAbonadosLSOAhorro = 0;

        Saving__c ahorroAnterior = null;
        boolean isFirstAhorroProcessed = false;
        Double ahorroreal=0;
        Double ahorrodisponible=0;
        Double HonorariosAbonados=0;
        Integer orden=0;

        for (Saving__c ahorro : oportunidad.Savings__r) {
            if (ahorroAnterior == null) {
                // Es el primer ahorro
                ahorro.Ahorro_Antiguo__c = null;
                if (!isFirstAhorroProcessed) {
                    if(oportunidad.AmountFirstSaving__c !=ahorro.MonthlyContribution__c){
                        oportunidad.AmountFirstSaving__c = ahorro.MonthlyContribution__c;
                        oppActualizar.add(oportunidad);
                    }
                    isFirstAhorroProcessed = true;
                }
            } else {
                // No es el primer ahorro, asigna el ahorro anterior
                ahorro.Ahorro_Antiguo__c = ahorroAnterior.Id;
            }

            // Usar ternario para manejar null en ahorroAnterior
            ahorro.Total_Ahorro_Disponible_AUX__c = (ahorro.Saldo__c != null ? ahorro.Saldo__c : 0) - 
                                                    (ahorro.Provision__c != null ? ahorro.Provision__c : 0);
            //SO-1432 START
            if(ahorro.RecordTypeId != ProgramaAhorroRT){
                HonorariosAbonados += (ahorro.MonthlyContribution__c != null ? ahorro.MonthlyContribution__c : 0); 
                ahorro.Total_honorarios_abonados__c = HonorariosAbonados;
            }
            //SO-1432 END
            
            // Cálculos según el RecordType del ahorro
            if (ahorro.RecordTypeId == ProgramaAhorroRT) {
                // Cálculos para ProgramaAhorroRT
                honorariosAbonadosProgramaAhorro += (ahorro.MonthlyContribution__c != null ? ahorro.MonthlyContribution__c : 0); 
                ahorro.Total_honorarios_abonados__c = honorariosAbonadosProgramaAhorro;

                ahorro.Total_Ahorro_Real_AUX__c = (ahorro.Saldo__c != null ? ahorro.Saldo__c : 0);
                ahorroRealProgramaAhorro += ahorro.Total_Ahorro_Real_AUX__c;
                ahorroDisponibleProgramaAhorro += ahorro.Total_Ahorro_Disponible_AUX__c;
                ahorro.RealTotalSaving__c = ahorroRealProgramaAhorro;
                ahorro.Total_Ahorro_Disponible2__c = ahorroDisponibleProgramaAhorro;

            } else if (ahorro.RecordTypeId == LSOAhorroRT) {
                // Cálculos para LSOAhorroRT
                honorariosAbonadosLSOAhorro += (ahorro.MonthlyContribution__c != null ? ahorro.MonthlyContribution__c : 0); 
                ahorro.Total_honorarios_abonados__c = honorariosAbonadosLSOAhorro;

                ahorro.Total_Ahorro_Real_AUX__c = (ahorro.Saldo__c != null ? ahorro.Saldo__c : 0);
                ahorroRealLSOAhorro += ahorro.Total_Ahorro_Real_AUX__c;
                ahorroDisponibleLSOAhorro += ahorro.Total_Ahorro_Disponible_AUX__c;
                ahorro.RealTotalSaving__c = ahorroRealLSOAhorro;
                ahorro.Total_Ahorro_Disponible2__c = ahorroDisponibleLSOAhorro;
            }

            // Actualizar el campo orden
            ahorro.orden__c = orden;
            orden++;

            ahorrosActualizar.add(ahorro);
            ahorroAnterior = ahorro;
        }
    }
        //SO-1372 END

    // Actualizar los registros de ahorros
    if (!ahorrosActualizar.isEmpty()) {
        try {
            update ahorrosActualizar;
        } catch (Exception e) {
            ErrorManagement.createInsertErrorLog('AhorroHelper', 1010, 'Update de ahorros '+ahorrosActualizar, e);
        }  
    }

    // Actualizar los registros de oportunidades
    if (!oppActualizar.isEmpty()) {
        try {
            update oppActualizar;
        } catch (DmlException e) {
            // Manejo de errores, por ejemplo, registrando el error
            //ErrorManagement.createInsertErrorLog(AhorroHelper.class.getName(), 900, 'Error al obtener metadata de Opp con Identificardor ' + oppActualizar,  e);

            System.debug('Error al actualizar las oportunidades: ' + e.getMessage());
        }
    }
}
/*
 *********************************************************
 @Method Name : oppInactivosPagadoNoConciliado
 @author : Daniel Santaella
 @description : SO-1019 Automatismo de la Sección: PAGADO NO CONCILIADO de la Oportunidad de Inactivos.
 @param : List<Saving__c> newSavings
 @return : void
 ********************************************************
 */
public static void oppInactivosPagadoNoConciliado(List<Saving__c> nuevosAhorros){ //SO-1019 Inicio
    try {
        //Obtener los Ids de cuentas asociadas a los ahorros
        Set<Id> oppIds = new Set<Id>();
        //Comprobar que el ahorro es de tipo Recuperacion o Aportacion Mensual
        for (Saving__c ahorro : nuevosAhorros) {
            if(ahorro.SavingsTypeBalance__c == 'Recuperacion' || ahorro.SavingsTypeBalance__c == 'Aportacion Mensual') {
                //Comprobamos si el ahorro tiene una oportunidad de inactivo asociada
                if(ahorro.Opportunity__c != null) {
                    oppIds.add(ahorro.Opportunity__c);
                }
            }
        }
        //  Obtener las cuentas asociadas a esas oportunidades
        Map<Id, Id> oppToAccountMap = new Map<Id, Id>();
        List<Opportunity> opps = [SELECT Id, AccountId FROM Opportunity WHERE Id IN :oppIds];
        for (Opportunity opp : opps) {
            oppToAccountMap.put(opp.Id, opp.AccountId);
        }
        // Obtener oportunidades de tipo "Inactivo" asociadas a las cuentas
        Set<Id> accountIds = new Set<Id>(oppToAccountMap.values());
        Map<Id, Opportunity> accountToInactiveOppMap = new Map<Id, Opportunity>();
        List<Opportunity> inactiveOpps = [SELECT Id, AccountId, InactiveManagerCloseDate__c, PaidAmountInactives__c FROM Opportunity
                                            WHERE AccountId  IN :accountIds
                                            AND RecordType.DeveloperName = 'Inactivo'
                                            AND StageName != 'Cerrada ganada'
                                            AND StageName != 'Cerrada perdida'];
        for (Opportunity opp : inactiveOpps) {
            accountToInactiveOppMap.put(opp.AccountId, opp);
        }

        // Porcesar cada ahorro y actualizar las oportunidades
        List<Opportunity> oppsToUpdate = new List<Opportunity>();
        Date today = Date.today(); 
        Integer currentMonth = today.month();
        Integer currentYear = today.year();

        for (Saving__c ahorro : nuevosAhorros) {
            if(ahorro.SavingsTypeBalance__c == 'Recuperacion' || ahorro.SavingsTypeBalance__c == 'Aportacion Mensual'){
                Id accountId = oppToAccountMap.get(ahorro.Opportunity__c);
                Opportunity inactiveOpp = accountToInactiveOppMap.get(accountId);
                if(inactiveOpp != null) {
                    // Actualizar el campo InactiveManagerCloseDate__c
                    if(inactiveOpp.InactiveManagerCloseDate__c == null) {
                        inactiveOpp.InactiveManagerCloseDate__c = today;
                    }
                    //Actualizar el campo PaidAmountInactives__c
                    Decimal aportacionMensual = ahorro.MonthlyContribution__c != null ? ahorro.MonthlyContribution__c : 0;
                    if(inactiveOpp.PaidAmountInactives__c == null) {
                        inactiveOpp.PaidAmountInactives__c = aportacionMensual;
                    }else {
                        // Si el ahorro es del mes y año actuales, sumamos la aportacion mensual
                        if(ahorro.Date__c != null && ahorro.Date__c.month() == currentMonth && ahorro.Date__c.year() == currentYear) {
                            inactiveOpp.PaidAmountInactives__c += aportacionMensual;
                        }
                    }
                    oppsToUpdate.add(inactiveOpp);
                }
            }
        }
        // Realizar la actulizacion
        if(!oppsToUpdate.isEmpty()){
            update oppsToUpdate;
        }
    } catch (Exception e) {
        ErrorManagement.createInsertErrorLog('AhorrotHelper', 1015, 'Error al actualizar la Oportunidad de Inactivos', e);
    }
}//SO-1019 Fin

    /*
     *********************************************************
     @Method Name : calculateSavingPercentByEntityDebts
     @author : Miguel León
     @description : Calcula el porcentaje de ahorro por cada entidad basado en las deudas de la cuenta.
     @param : Map<Id, Saving__c> oldSavings - Mapa de los ahorros antiguos antes de la actualización.
     @param : Map<Id, Saving__c> newSavings - Mapa de los ahorros nuevos después de la actualización.
     @return : void
     ********************************************************
     */
    public static void calculateSavingPercentByEntityDebts(Map<Id, Saving__c> oldSavings, Map<Id, Saving__c> newSavings, Boolean isInsert) {
        try {
            Set<Id> accIdSet = new Set<Id>();
            
            // Recopilamos los Account Ids a partir de los nuevos ahorros
            for (Saving__c mySaving : newSavings.values()) {
                if (mySaving.Client__c != null &&
                    (isInsert || 
                    (oldSavings.containsKey(mySaving.Id) && mySaving.MonthlyContribution__c != oldSavings.get(mySaving.Id).MonthlyContribution__c))) {
                    accIdSet.add(mySaving.Client__c);
                }
            }

            // Obtenemos todas las deudas de los clientes después de la actualización
            List<Debt__c> accountDebts = [SELECT Id, Name, Amount__c, EntityName__c, AccountId__c, Opportunity__r.Account.TotalAmountSaving__c
                                        FROM Debt__c 
                                        WHERE AccountId__c IN :accIdSet];

            // Mapa que almacena la deuda total por cada combinación cliente-entidad
            Map<String, Decimal> accEntityMap = new Map<String, Decimal>();

            // Recorremos las deudas para llenar el mapa
            for (Debt__c debt : accountDebts) {
                //Hotfix Pablo Rodriguez
                if (debt.AccountId__c != null && debt.EntityName__c != null && debt.Amount__c != null) {
                    String key = debt.AccountId__c + '-' + debt.EntityName__c;

                    // Si ya existe la clave, sumamos la deuda, de lo contrario inicializamos
                    if (accEntityMap.containsKey(key)) {
                        accEntityMap.put(key, accEntityMap.get(key) + debt.Amount__c);
                    } else {
                        accEntityMap.put(key, debt.Amount__c);
                    }
                }
            }

            // Lista de deudas a actualizar
            List<Debt__c> debtsToUpdate = new List<Debt__c>();

            System.debug('Deudas antes de actualizar: ' + debtsToUpdate);

            // Recorremos las deudas nuevamente para calcular y asignar el porcentaje de ahorro
            for (Debt__c debt : accountDebts) {
                if (debt.AccountId__c != null && debt.EntityName__c != null) {
                    String key = debt.AccountId__c + '-' + debt.EntityName__c;
                    Decimal totalDebtForEntity = accEntityMap.get(key);
                    System.debug('Deuda total por entidad: ' + totalDebtForEntity);

                    // Obtenemos el total del ahorro del cliente
                    Decimal totalSaving = debt.Opportunity__r.Account.TotalAmountSaving__c;
                    System.debug('Total ahorro disponible: ' + totalSaving);

                    // Si hay ahorro total y deuda total, calculamos el porcentaje
                    if (totalSaving != null && totalDebtForEntity != null && totalDebtForEntity > 0) {
                        Decimal savingPercentage = (totalSaving / totalDebtForEntity) * 100;
                        debt.Ahorro_real_VS_Total_de_cada_Entidad__c = savingPercentage;
                        debtsToUpdate.add(debt);
                        System.debug('Valor del campo después de actualizar: ' + savingPercentage);
                    }
                }
            }

            // Actualizamos las deudas con los valores calculados
            if (!debtsToUpdate.isEmpty()) {
                update debtsToUpdate;
                System.debug('Deudas después de actualizar: ' + debtsToUpdate);
            }

        } catch (Exception e) {
            ErrorManagement.createInsertErrorLog('AhorroHelper', 1001, 'Error al obtener el valor del campo Ahorro_real_VS_Total_de_cada_Entidad__c', e);
        }
    }    

    /*
    *********************************************************
    @Method Name : actualizaHonorariosKmaleon
    @author : Jesus Ruiz
    @description : SO-1351 Llamada al batch que actualiza los honorarios pendientes LSO en Kmaleon
    @param : List<Saving__c> newSavings
    @return : void
    ********************************************************
    */
    public static void actualizaHonorariosKmaleon(List<Saving__c> newSavings){
        try {
            // Obtener la lista de IDs de cuentas de los ahorros
            Set<Id> accountIds = new Set<Id>();

            for(Saving__c ahorro : newSavings){
                System.debug('Se entra en el ahorro: ' + ahorro.Id);
                if(ahorro.SavingsTypeBalance__c == 'Aportacion Mensual' && ahorro.RecordTypeId == LSOAhorroRT){
                    System.debug('Se cumplen las condiciones y se añade al Set de nuevo ahorro el id de la cuenta');
                    accountIds.add(ahorro.Client__c);
                } 
            }

            List<TM_obj_lawsuit__c> lawsuitList = [SELECT Id, RecordTypeId, TM_fld_contact__r.LsoFeesPendingOpp__c, TM_fld_kmaleonCode__c, Name, 
                                                        TM_fld_contact__r.TotalAmountSaving__c,
                                                        TM_fld_contact__r.TotalLsoFee__c,
                                                        TM_fld_contact__r.ClientStatus__c,
                                                        TM_fld_contact__r.Cliente_de__c
                                                        FROM TM_obj_lawsuit__c 
                                                        WHERE TM_fld_contact__c IN :accountIds];
                                                        
            if (!accountIds.isEmpty()) {
                BatchUpdateClientStatusKmaleon clientStatusBatch = new BatchUpdateClientStatusKmaleon(lawsuitList);
                Database.executeBatch(clientStatusBatch, 10);
            }
        } catch (Exception e) {
            ErrorManagement.createInsertErrorLog('AhorroHelper', 1010, 'Error al los honorarios LSO en Kmaleon', e);
        }        
    }

    
}