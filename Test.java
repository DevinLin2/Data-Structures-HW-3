import java.util.*;

class Test {
  public static void main(String[] args) {
    DoubleTreePhBook phB = new DoubleTreePhBook();
    System.out.println("----------------Preliminary tests for functionality----------------");
    System.out.println("Format: name, number");
    System.out.println("Able to add devin, 1324321?: " + phB.PhBInsert("devin", 1324321));
    System.out.println("Able to add steve, 6454567?: " + phB.PhBInsert("steve", 6454567));
    System.out.println("Able to add bob, 3456?: " + phB.PhBInsert("bob", 3456));
    System.out.println("Able to add george, 1234?: " + phB.PhBInsert("george", 1234));
    System.out.println("Able to add alex, 7681?: " + phB.PhBInsert("alex", 7681));
    System.out.println("Able to add billy, 14564?: " + phB.PhBInsert("billy", 14564));
    System.out.println("Able to add jean, 1009891?: " + phB.PhBInsert("jean", 1009891));
    System.out.println("Able to add howard, 1324?: " + phB.PhBInsert("howard", 1324));
    System.out.println("Able to add tracy, 1375467?: " + phB.PhBInsert("tracy", 1375467));
    System.out.println("Able to add tracy, 1567?: " + phB.PhBInsert("tracy", 1567));
    System.out.println("Able to add fakeJean 1009891?: " + phB.PhBInsert("fakeJean", 1009891));
    System.out.println("Able to add tracy, 13456?: " + phB.PhBInsert("tracy", 13456));
    System.out.println("Able to add tracy, 1365787?: " + phB.PhBInsert("tracy", 1365787));
    System.out.println("\nPreorder print of phonebook after inserts: ");
    phB.printPhoneBookTrees();
    System.out.println();
    System.out.println("\nSearching for Jean's number: ");
    System.out.println(phB.PhBNameSearch("jean"));
    System.out.println("\nSearching for the numbers of all the Tracys");
    System.out.println(phB.PhBNameSearch("tracy"));
    System.out.print("\nTesting phone number search (expected tracy): ");
    System.out.println(phB.PhBPhoneSearch(1365787));
    phB.PhBDelete("bob", 3456);
    System.out.println();
    System.out.println("Phonebook tree after deleting bob");
    phB.printPhoneBookTrees();
    phB.PhBDelete("devin", 1324321);
    System.out.println("\n\nPhonebook tree after deleting root node");
    phB.printPhoneBookTrees();
    System.out.println();
    System.out.println("Testing if tree is still functional after deleting: ");
    System.out.println("\nSearching for Jean's number: ");
    System.out.println(phB.PhBNameSearch("jean"));
    System.out.println("\nSearching for the numbers of all the Tracys");
    System.out.println(phB.PhBNameSearch("tracy"));
    System.out.print("\nTesting phone number search (expected tracy): ");
    System.out.println(phB.PhBPhoneSearch(1365787));
  }
}
