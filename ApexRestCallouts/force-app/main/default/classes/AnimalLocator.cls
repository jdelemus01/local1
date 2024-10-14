public with sharing class AnimalLocator {
    public String getAnimalNameById(Integer n){
        
        Http http = new Http();
        HttpRequest request = new HttpRequest();
        
        request.setEndpoint('https://th-apex-http-callout.herokuapp.com/animals/'+ n);
        request.setMethod('GET');

        HttpResponse response = http.send(request);

        String cad = '';

        if(response.getStatusCode() == 200){
            cad = response.getBody();
        }

        return cad;
    }
}