public class Main {

    public static void main(String[] args) {
       /*
       1. creem o lista
       2. afisarea listei create
       3. remove de la o anumita pozitie
       4. remove o anumita valoare
       5. sortarea
        */

        CustomList list = new CustomList();

        list.add(5);
        list.add(67);
        list.add(3);
        list.add(1);
        int a = 1;
        list.removePozitie(a); // 5 67 0 0 --> 5 0 0
        //   list.removePozitie(-5); // NU EXISTA


//        System.out.println(list.get(0));  // 5
//        System.out.println(list.get(1));  // 3
//
//        System.out.println(list.get(50)); // -1
//        System.out.println(list.get(3));  // -1


     //   for (int i = 0; i < list.size(); i++) {
      //  System.out.println(list.get(i));
        }
       // list.sort();





    }

