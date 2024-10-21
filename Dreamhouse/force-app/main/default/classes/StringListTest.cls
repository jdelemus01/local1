public with sharing class StringListTest {
    public static List<String> generateStringList(Integer index) {
        List<String> listString = new List<String>();

        for(Integer i = 0; i<index; i++){
            String str = 'Test ' + i;
            listString.add(str);
        }
        return listString;
    }
}