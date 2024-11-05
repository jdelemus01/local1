public with sharing class PlanLiquidacionController{
    private static final Id AdendaRT = Schema.SObjectType.Opportunity.getRecordTypeInfosByDevelopername().get('Adenda').getRecordTypeId();

    @AuraEnabled
    public static string checkFilledField(Id recordId){
        try{
            List<String> strList = new List<String>();
            PlandeLiquidacion__c pl = [SELECT Id, File__r.Account.LastName, File__r.Account.PersonMailingCity, File__r.Account.PersonMailingState, File__r.Account.PersonMailingStreet, File__r.Account.DNI__pc, File__r.Account.PersonBirthdate, File__r.DateOfFirstContribution__c, File__r.RecordType.DeveloperName, File__r.DayDateContribution__c, File__r.TotalActiveDebt__c, CuotaMensual__c, File__r.TotalLsoFee__c, File__r.Account.SecondLastName__c, File__r.Account.Nationality__pc, File__r.Account.Gender__pc, File__r.Account.DependentPeople__pc, File__r.Account.MaritalStatus__pc, File__r.Account.HousingOwnership__c, File__r.Account.MortgageBank__pc, File__r.Account.MaritalRegime__pc, File__r.Account.AutonomoCuentaAjena__c, File__r.Account.MonthlyIncome__c, File__r.Account.Phone, File__r.Account.PersonEmail, Duration__c, File__r.Account.PersonMailingPostalCode, TotalPay__c, Adenda__c, File__r.Account.Fecha_Caducidad_DNI__c
                                       FROM PlandeLiquidacion__c
                                       WHERE Id = :recordId];

            if (pl.Adenda__c)
                return 'success';
            if (String.isBlank(pl.File__r.Account.LastName))
                strList.add('Apellido');
            if (String.isBlank(pl.File__r.Account.SecondLastName__c))
                strList.add('2º Apellido');
            if (String.isBlank(pl.File__r.Account.PersonMailingCity))
                strList.add('Ciudad');
            if (String.isBlank(pl.File__r.Account.PersonMailingState))
                strList.add('Provincia');
            if (String.isBlank(pl.File__r.Account.PersonMailingStreet))
                strList.add('Calle');
            if (String.isBlank(pl.File__r.Account.DNI__pc))
                strList.add('DNI');
            if (String.isBlank(pl.File__r.Account.Nationality__pc))
                strList.add('Nacionalidad');
            if (String.isBlank(pl.File__r.Account.Gender__pc))
                strList.add('Género');
            if (String.isBlank(pl.File__r.Account.DependentPeople__pc))
                strList.add('Personas a cargo');
            // if (String.isBlank(pl.File__r.Account.HousingOwnership__c))
            //     strList.add('Vivienda en Propiedad');
            if (String.isBlank(pl.File__r.Account.MaritalStatus__pc))
                strList.add('Estado Civil');
            // if (String.isBlank(pl.File__r.Account.MortgageBank__pc))
            //     strList.add('Banco hipoteca');
            if (String.isBlank(pl.File__r.Account.MaritalRegime__pc))
                strList.add('Régimen Matrimonial');
            if (String.isBlank(pl.File__r.Account.AutonomoCuentaAjena__c))
                strList.add('Situación profesional');
            if (pl.File__r.Account.Fecha_Caducidad_DNI__c == null)
                strList.add('Fecha Caducidad DNI');
            if (pl.File__r.Account.MonthlyIncome__c == null)
                strList.add('Ingresos Mensuales');
            if (String.isBlank(pl.File__r.Account.Phone))
                strList.add('Teléfono');
            if (String.isBlank(pl.File__r.Account.PersonEmail))
                strList.add('Email');
            if (pl.CuotaMensual__c == null)
                strList.add('Cuota mensual');
            if (pl.File__r.DateOfFirstContribution__c == null)
                strList.add('Fecha 1ª aportación');
            if (pl.Duration__c == null)
                strList.add('Duración');
            if (pl.File__r.Account.PersonBirthdate == null)
                strList.add('Fecha de cumpleaños');
            if (pl.File__r.DayDateContribution__c == null)
                strList.add('Día fecha aportación');
            // if(pl.File__r.RecordType.DeveloperName == 'LSO'){
            // }

            // if(pl.File__r.RecordType.DeveloperName == 'Programa'){
            if (String.isBlank(pl.File__r.Account.PersonMailingPostalCode))
                strList.add('Código Postal');
            // }

            String returnedMsg;

            if (strList.isEmpty()){
                returnedMsg = 'success';
            }

            if (!strList.isEmpty()){
                returnedMsg = 'Falta por rellenar los siguientes campos requeridos para poder envíar el plan de liquidación a zoho: ';
                for (String str : strList){
                    returnedMsg += str + ', ';
                }
                returnedMsg = returnedMsg.removeEnd(', ');
            }

            return returnedMsg;
        } catch (Exception e){
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled(cacheable = true)
    public static PlandeLiquidacion__c getActivePlan(String recordId){
        try{
            return [SELECT CreatedDate, Id, TotalActiveDebts__c, Duration__c, TotalPaymentToBanks__c, FixedFees__c, CuotaMensual__c, Fee__c, TotalMonthlyCommissions__c, LiquidationCommission__c, TotalComisiones__c, Discount__c, PercentagePaymentBanks__c, TotalPay__c, File__c, DebtNumber__c, NeedsPowerOfAttorney__c, InitialCommissionAddendum__c, TotalPayablePostAdendum__c, Adenda__c, TotalAdendaDebt__c, TotalInactiveDebts__c, TotalPago__c, InitialComssion__c,Plan_de_Liquidacion_antiguo__c
                    FROM PlandeLiquidacion__c
                    WHERE File__c = :recordId and Active__c = true];
        } catch (Exception e){
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled(cacheable = true)
    public static List<PlandeLiquidacion__c> getPlan(String recordId){
        try{
            return [SELECT CreatedDate, Id, Active__c, Name, Duration__c, CuotaMensual__c, Adenda__c, AdendaOpportunity__c, AdendaOpportunity__r.Name
                    FROM PlandeLiquidacion__c 
                    WHERE File__c = :recordId];
        } catch (Exception e){
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled(cacheable = true)
    public static List<PlandeLiquidacion__c> getPlanAdenda(String recordId){
        try{
            Opportunity opp = [SELECT Id, AdendaOpportunity__c FROM Opportunity WHERE Id = :recordId];
            return [SELECT CreatedDate, Id, Active__c, Name, Duration__c, CuotaMensual__c, Adenda__c, AdendaOpportunity__c, AdendaOpportunity__r.Name, File__c, File__r.Name
                    FROM PlandeLiquidacion__c 
                    WHERE AdendaOpportunity__c = :recordId OR (Active__c = true AND File__c = :opp.AdendaOpportunity__c)];
        } catch (Exception e){
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled(cacheable = true)
    public static List<Debt__c> getDeudasAdenda(String recordId){
        try{
            Opportunity opp = [SELECT AdendaOpportunity__c
                               FROM Opportunity
                               WHERE Id = :recordId];

            return [SELECT Id, RecordType.DeveloperName, Liquidado__c, ActiveDebt__c, Name, Financial__c, Financial__r.Name, 
                    PreviousFinancial__c, EGestora__c, Amount__c, ClaimStatus__c, TypeOfDebts__c, News__c, 
                    CreditNumber__c, Discount__c, SueType__c, TypeCreditorClaim__c, ClaimSubmissionDate__c, 
                    ReferenceNumberBurofax__c, Ahorro_vs_Deuda__c, ClaimDateMail__c, CreatedDate
                    FROM Debt__c
                    WHERE Opportunity__c = :opp.AdendaOpportunity__c];
        } catch (Exception e){
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static List<Debt__c> getDeudas(String recordId){
        try{
            return [SELECT Id, Discount__c, Amount__c, ModifiedDiscount__c, RecordType.DeveloperName, CreatedDate
                    FROM Debt__c
                    WHERE ActiveDebt__c = 'Si' AND Opportunity__c = :recordId];
        } catch (Exception e){
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static List<Account> getLastOpp(String recordId){
        System.debug('recordid apex = '+recordId);
        Opportunity getOpp = [SELECT Id, Name, AccountId FROM Opportunity WHERE Id = :recordId];
        //Account getAcc = [SELECT Id, Name, LastOpportunity__pc, TotalInitialCommission__c, TotalMonthlyCommission__c FROM Account WHERE Id = :getOpp.AccountId];
        System.debug('active = '+getOpp);
        System.debug('active2 = '+[SELECT Id, Name, LastOpportunity__pc, TotalInitialCommission__c, TotalMonthlyCommission__c FROM Account WHERE Id = :getOpp.AccountId]);
        try{
            return [SELECT Id, Name, LastOpportunity__pc, TotalInitialCommission__c, TotalMonthlyCommission__c 
                    FROM Account 
                    WHERE Id = :getOpp.AccountId];
        }catch (Exception e){
            throw new AuraHandledException(e.getMessage());
        }
    }


    @AuraEnabled
    public static List<Debt__c> getDeudasNo(String recordId, Boolean isAdenda){
        // try{
        if (isAdenda){
            Datetime twentyFourHoursAgo = Datetime.now().addHours(-24);
            List<Debt__c> debtList = [SELECT Id, (SELECT Id, IsDeleted, ParentId, CreatedById, CreatedDate, Field, DataType, OldValue, NewValue
                                                  FROM Histories
                                                  WHERE CreatedDate >= :twentyFourHoursAgo)
                                      FROM Debt__c 
                                      WHERE Opportunity__c = :recordId AND ActiveDebt__c = 'No'];
            Set<Id> debtSetId = new Set<Id>();
            for (Debt__c debt : debtList){
                for (Debt__History hist : debt.Histories){
                    if (hist.Field == 'ActiveDebt__c'){
                        debtSetId.add(debt.Id);
                    }
                }
            }

            return [SELECT Id, Discount__c, Amount__c, ModifiedDiscount__c, RecordType.DeveloperName, CreatedDate
                    FROM Debt__c
                    WHERE Id IN:debtSetId];
        } else{
            return [SELECT Id, Discount__c, Amount__c, ModifiedDiscount__c, RecordType.DeveloperName, CreatedDate
                    FROM Debt__c
                    WHERE ActiveDebt__c = 'No' AND Opportunity__c = :recordId];
        }
        // } catch (Exception e){
        //     throw new AuraHandledException(e.getMessage());
        // }
    }


    @AuraEnabled
    public static List<Debt__c> getDeudasAdendas(String recordId){
        Datetime last24Hours = Datetime.now().addHours(-24);

        return [SELECT Id, Discount__c, Amount__c, ModifiedDiscount__c, RecordType.DeveloperName, CreatedDate
                FROM Debt__c
                WHERE AdendaOpportunity__c = :recordId AND Recordtype.DeveloperName = 'Adenda' AND ActiveDebt__c = 'Si'];
    }

    @AuraEnabled(cacheable = true)
    public static Decimal getDuracion(String recordId){
        try{
            Opportunity opp = [SELECT Id, TotalActiveDebt__c
                               FROM Opportunity
                               WHERE Id = :recordId];
            return Utils.getMonthsTotalActiveDebt(1000);
            // return Utils.getMonthsTotalActiveDebt(opp.TotalActiveDebt__c != null ? opp.TotalActiveDebt__c : 0);
        } catch (Exception e){
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static Boolean sendEmail(String recordId){
        try{
            PlandeLiquidacion__c pl = [SELECT Id, File__r.Owner.Name, File__r.AccountId, File__r.Account.PersonContactId, File__r.Account.name, Name, DebtNumber__c, CreatedDate, CuotaMensual__c, Duration__c, Discount__c, TotalActiveDebts__c, TotalPay__c, Saving__c
                                       FROM PlandeLiquidacion__c
                                       WHERE Id = :recordId];
            return sendMailPlanLiquidacion(pl, null, 'Test_Plan_de_Liquidacion', pl.File__r.Account.PersonContactId, pl.Id);
        } catch (Exception e){
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static Opportunity createAdenda(Account acc){
        try{
            Opportunity opp = new Opportunity();
            opp.AccountId = acc.Id;
            opp.Name = 'Opportunity Adenda' + ' ' + acc.FirstName + ' ' + acc.LastName;
            opp.CloseDate = System.today().addDays(15);
            opp.AdendaOpportunity__c = acc.LastOpportunity__pc;
            opp.StageName = 'Estudio';
            opp.RecordTypeId = AdendaRT;
            insert opp;

            return opp;
        } catch (Exception e){
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static Opportunity createAdendaSolicitadaPor(Account acc, String solicitado){
        try{
            Opportunity opp = new Opportunity();
            opp.AccountId = acc.Id;
            opp.Name = 'Opportunity Adenda' + ' ' + acc.FirstName + ' ' + acc.LastName;
            opp.CloseDate = System.today().addDays(15);
            opp.AdendaOpportunity__c = acc.LastOpportunity__pc;
            opp.StageName = 'Estudio';
            opp.RecordTypeId = AdendaRT;
            opp.Adenda_solicitada_por__c = solicitado;
            insert opp;

            return opp;
        } catch (Exception e){
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static Boolean activeDesactivePlan(String recordId, Boolean desactivar){
        try{
            if (desactivar){
                PlandeLiquidacion__c pl = new PlandeLiquidacion__c(Id = recordId, Active__c = false);
                return Database.update (pl).isSuccess();
            } else{
                PlandeLiquidacion__c auxPl = [SELECT File__c
                                              FROM PlandeLiquidacion__c
                                              WHERE Id = :recordId];
                List<PlandeLiquidacion__c> plList = [SELECT Id
                                                     FROM PlandeLiquidacion__c
                                                     WHERE File__c = :auxPl.File__c];
                List<PlandeLiquidacion__c> plListToUpdate = new List<PlandeLiquidacion__c>();
                for (PlandeLiquidacion__c pl : plList){
                    if (pl.id != recordId){
                        pl.Active__c = false;
                        plListToUpdate.add(pl);
                    }
                }
                PlandeLiquidacion__c pl = new PlandeLiquidacion__c(Id = recordId, Active__c = true);
                Database.update (plListToUpdate);
                return Database.update (pl).isSuccess();
            }
        } catch (Exception e){
            throw new AuraHandledException(e.getMessage());
        }
    }

    private static Boolean sendMailPlanLiquidacion(PlandeLiquidacion__c pl, String email, String template, Id recordId, Id toWhoId){

        Messaging.SingleEmailMessage mail = new Messaging.SingleEmailMessage();

        mail.setUseSignature(false);
        mail.setBccSender(false);
        mail.setSaveAsActivity(true);

        EmailTemplate et = [SELECT Id, Subject, Description, HtmlValue, DeveloperName, Body
                            FROM EmailTemplate
                            WHERE DeveloperName = :template];

        et.HtmlValue = et.HtmlValue.replace('{!PlandeLiquidacion__c.File__r.Account.name}', pl.File__r.Account.name);
        et.HtmlValue = et.HtmlValue.replace('{!PlandeLiquidacion__c.Owner.name}', pl.File__r.Owner.Name);
        et.HtmlValue = et.HtmlValue.replace('{!PlandeLiquidacion__c.Name}', pl.Name);
        et.HtmlValue = et.HtmlValue.replace('{!PlandeLiquidacion__c.DebtNumber__c}', String.valueOf(pl.DebtNumber__c));
        et.HtmlValue = et.HtmlValue.replace('{!PlandeLiquidacion__c.CreatedDate}', String.valueOf(pl.CreatedDate));
        et.HtmlValue = et.HtmlValue.replace('{!PlandeLiquidacion__c.CuotaMensual__c}', String.valueOf(pl.CuotaMensual__c));
        et.HtmlValue = et.HtmlValue.replace('{!PlandeLiquidacion__c.Duration__c}', String.valueOf(pl.Duration__c));
        et.HtmlValue = et.HtmlValue.replace('{!PlandeLiquidacion__c.Discount__c}', String.valueOf(pl.Discount__c));
        et.HtmlValue = et.HtmlValue.replace('{!PlandeLiquidacion__c.TotalActiveDebts__c}', String.valueOf(pl.TotalActiveDebts__c));
        et.HtmlValue = et.HtmlValue.replace('{!PlandeLiquidacion__c.TotalPay__c}', String.valueOf(pl.TotalPay__c));
        et.HtmlValue = et.HtmlValue.replace('{!PlandeLiquidacion__c.Saving__c}', String.valueOf(pl.Saving__c));

        mail.setTemplateId(et.id);
        mail.setSubject(et.Subject);
        mail.setHtmlBody(et.HtmlValue);
        mail.setTargetObjectId(recordId);
        mail.setSenderDisplayName('Soluciona mi Deuda');

        Messaging.SendEmailResult ser = Messaging.sendEmail(new Messaging.SingleEmailMessage[]{ mail }, false)[0];
        System.debug('ser: ' + ser);
        return ser.isSuccess();
    }

}