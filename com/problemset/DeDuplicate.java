package com.problemset;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class to remove the duplicates from a given array. The array given in the problem set is instance level.
 * If this needs to be dynamic, then all the methods can be made parametric.
 * For example:  public int[] manualRemovalOfDuplicates(int[] randomIntegers);
 */
public class DeDuplicate {

    public int[] randomIntegers = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3,
            20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,16,3,6,19,
            13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11};

    /**
     * This method is the most time consuming method as it iterates each element against every other element in the array.
     * as the duplicates are found in the array the logic makes sure that the next search will search for 1 less element in the array.
     *
     * @return int[] the returned array is the final array that has unique integers in it.
     */
    public int[] manualRemovalOfDuplicates() {

        int[] deDups1 = randomIntegers;
        int length = deDups1.length;
        for(int i = 0; i < length; i++)
        {
            for(int j=i+1; j<length;j++) {
                if (deDups1[i] == deDups1[j]) {
                        for(int k = j; k<length-1;k++) {
                            deDups1[k] = deDups1[k+1];
                        }
                        --j;--length;
                }
            }
        }

        //Duplicates are removed but the last element remains in the array even if it is duplicate.
        //Just for this reason, we have to create another array that does not have the last element.
        int[] finalArray = new int[length];
        for(int i=0; i<length;i++) {
            finalArray[i]=deDups1[i];
        }

        return finalArray;
    }

    /**
     * This method uses the same logic as the above method but in addition whenever there is a non-match of the ith element with i+1th element
     * the iteration is stopped using the break statement. This is because the array is sorted and when there is a non-match it is for sure that
     * the element being searched will not be found in the rest of the array.
     * @return int[] the final array containing only unique elements.
     */
    public int[] sortAndRemove() {

        int[] deDups2 = randomIntegers;
        java.util.Arrays.sort(deDups2);
        int length = deDups2.length;
        for(int i = 0; i < length; i++)
        {
            // print initial array
            for(int j=i+1; j<length; j++) {
                if (deDups2[i] == deDups2[j]) {
                        for(int k = j; k<length-1;k++) {
                            deDups2[k] = deDups2[k+1];
                        }
                        --j;--length;
                } else
                    break;
            }
        }

        //Duplicates are removed but the last element remains in the array even if it is duplicate.
        //Just for this reason, we have to create another array that does not have the last element.
        int[] finalArray = new int[length];
        for(int i=0; i<length;i++) {
            finalArray[i]=deDups2[i];
        }
        return finalArray;
    }

    /**
     * This method uses simple java collections API. Since Set does not accept duplicate elements it automatically stores only the unique values.
     *
     * @return int[] The return type can be Set here if we don't need an integer array return type. In which case the method will return deDups3.
     * It will then be the responsibility of the caller to decide what to do with the Set.
     */

    public int[] usingOriginalOrderAndCollections() {

        Set<Integer> deDups3 = new TreeSet<Integer>();
        for(int i = 0; i<randomIntegers.length; i++) {
            deDups3.add(randomIntegers[i]);
        }
        int[] finalArray = new int[deDups3.size()];
        int j = 0;
        for(int numbers : deDups3)
        finalArray[j++] = numbers;
        return finalArray;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        DeDuplicate instance1 = new DeDuplicate();
        System.out.println("Actual Array Length" + instance1.randomIntegers.length);
        long startTime = new Date().getTime();

        //Manually traverse against each element in the array.
        int one[] = instance1.manualRemovalOfDuplicates();
        long endTime = new Date().getTime();
        System.out.println("\nManualRemovalofDuplicates() took " + (endTime-startTime) + " milliseconds ");
        System.out.println("com.problemset.DeDuplicate Array Length " + one.length + " Number of duplicates found: " + (instance1.randomIntegers.length - one.length));
        for(int i = 0 ; i < one.length; i++) {
            System.out.print(one[i] + ",");
        }

        //Sort And Remove
        startTime = new Date().getTime();
        int two[] = instance1.sortAndRemove();
        endTime = new Date().getTime();
        System.out.println("\nsortAndRemove() took " + (endTime-startTime) + " milliseconds ");
        System.out.println("com.problemset.DeDuplicate Array Length " + two.length + " Number of duplicates found: " + (instance1.randomIntegers.length - two.length));
        for(int i = 0 ; i < two.length; i++) {
            System.out.print(two[i] + ",");
        }

        //usingOriginalOrderAndCollections
        startTime = new Date().getTime();
        int three[] = instance1.usingOriginalOrderAndCollections();
        endTime = new Date().getTime();
        System.out.println("\nsortAndRemove() took " + (endTime-startTime) + " milliseconds ");
        System.out.println("com.problemset.DeDuplicate Array Length " + two.length + " Number of duplicates found: " + (instance1.randomIntegers.length - two.length));
        for(int i = 0 ; i < three.length; i++) {
            System.out.print(three[i] + ",");
        }
    }

}
