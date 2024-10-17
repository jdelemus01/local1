public with sharing class AnimalLocator {
    public static String getAnimalNameById(Integer n){
        
        //Se crea la petición y se introducen los parámetros
        Http http = new Http();
        HttpRequest request = new HttpRequest();
        request.setEndpoint('https://th-apex-http-callout.herokuapp.com/animals/'+ n);
        request.setMethod('GET');

        //Se hace la petición
        HttpResponse response = http.send(request);

        String cad = '';

        if(response.getStatusCode() == 200){
            //Mapeo de los diferentes campos que devuelve la petición
            Map<String, Object> animalMap = (Map<String, Object>) JSON.deserializeUntyped(response.getBody());
			cad = (String) animalMap.get('name');
        }

        return cad;
    }
    
    
}