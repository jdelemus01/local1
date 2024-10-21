public class RandomContactFactory {
    public static List<Contact> generateRandomContacts(Integer index, String lastNameTest){

        List<Contact> contactList = new List<Contact>();
        
        for(Integer i = 0; i<index; i++){
            Contact thisContact = new Contact(firstName='Test ' + i, lastName=lastNameTest);
            contactList.add(thisContact);
        }

        system.debug(contactList);

        return contactList;
    }
}