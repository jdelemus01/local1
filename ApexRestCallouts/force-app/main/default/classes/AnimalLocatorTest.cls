@isTest
private class AnimalLocatorTest {

    @isTest
    static void getAnimalNameByIdTest() {
         Test.setMock(HttpCalloutMock.class, new AnimalLocatorMock());
        
        String responseName = AnimalLocator.getAnimalNameById(1);
        
        System.assertEquals('chicken', responseName);	
    }
    
    @isTest
    static void getAnimalByIdNoIdTest(){
        String responseName = AnimalLocator.getAnimalNameById(1);
        System.assertEquals('', responseName);
    }
}