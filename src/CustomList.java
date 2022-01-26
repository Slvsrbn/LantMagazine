public class CustomList {

    private int[] content;
    private int numberOfAddedElements;

    public CustomList() {
        this.content = new int[5];
        this.numberOfAddedElements = 0;
    }
   /*
       afisare doar elementele care sunt adaugate in vector

       [3 5 7 0 0]  --> 3 5 7
    */
//    public int[] getContent() {
//        return this.content;
//    }

    public int get(int pozitie) {
       /*
       returneaza elementul de la acea pozitie
        */ // -5 50
        if (pozitie >= 0 && pozitie < numberOfAddedElements) {
            return content[pozitie];
        }
        //throw new IndexOutOfBoundsException();
        return -1;
    }

    public void add(int el) {
       /*   [0 0 0 0 0]
            el = 5 -->  [5 0 0 0 0]
            el = 6 -->  [5 6 0 0 0]
            el = 36 -->  [5 6 36 0 0]
        */
        if (numberOfAddedElements < content.length) {
            content[numberOfAddedElements++] = el;
        }


    }

    public void removePozitie(int pozitie) {
       /*
       [ 5 6 0 0 0 ]

       pozitie = 4 --> "niciun element" -- nu face nimic
       pozitie = 1 --> [5 0 0 0 0]
        */

        if (pozitie >= 0 && pozitie <= numberOfAddedElements) {
            if (pozitie == numberOfAddedElements) {
                numberOfAddedElements -= 1; // --
            } else {
                int i = pozitie;
                while (i < numberOfAddedElements - 1) {
                    content[i] = content[++i];
                }
                numberOfAddedElements--;
//                while (pozitie >= 0 && pozitie < numberOfAddedElements) {
//                    int p2 = pozitie + 1;
//                    content[pozitie] = content[p2];
//                    pozitie++;
//                }
//
            }

        } else {
            System.out.println("pozitia nu exista");
        }

    }

    public void removeElement(int element) {
       /*
       cauta elementul "element" in vector si il scoate
        */

    }

    public int[] sort() {
       /*
       returneaza vectorul sortat
        */
        int[] sortedVector = content;

        int i = 0;
        int j = 1;
        int k;
        while (i < numberOfAddedElements) {
            j = i + 1;
            while (j < numberOfAddedElements) {
                if (sortedVector[i] > sortedVector[j]) {
                    k = content[i];
                    content[i] = content[j];
                    content[j] = k;
                }
                j++;
            }
            i++;
        }
        return null;
    }

}

















