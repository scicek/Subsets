import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/********************************************************************
 * Written by: Simon Cicek                                          *
 * Last changed: 2012-04-26                                         *
 *                                                                  *
 * The main class, containing the code to calculate all the subsets *
 * of a given set and structuring the result.                       *                              
 ********************************************************************/

public class Main 
{
    public static void main(String[] args)
    {
        Set<String> set = new ArraySet();
        set.add("A");
        set.add("B");
        set.add("C");
        set.add("D");
        set.add("E");
        
        // The set of subsets
        Set<Set<String>> subsets = allSubsets(set);
        // The list containing the subsets in order
        List[] list = new ArrayList[subsets.size()];
        // Iterator used to iterate over the subsets
        Iterator<Set<String>> itr = subsets.iterator();
        
        // Initialize the list
        for(int i = 0; i < set.size()+1;i++)
            list[i] = new ArrayList();
        
        // The index where the subsets are to be added
        int index = 0;
        while(itr.hasNext())
        {
            // The subset to be added
            Set<String> subset = itr.next();
            // The subsets cardinality decides which index it is to be added to
            index = subset.size();
            list[index].add(subset);
        }

        System.out.println("Total number of subsets: " + subsets.size() + "\n");
        System.out.println("Nr. of elements: \t Nr. of subsets:\tSubsets:");
        // Print all the subsets
        for(int i = 0; i < set.size() + 1; i++)
        {
            System.out.println("\t" + i + "\t    |\t\t" + 
                               list[i].size() + "\t    |\t  " + 
                               list[i].toString().replace('[', '{').replace(']', '}'));
        }
    }
    
    // Given a set, calculates and returns a new set containing all subsets
    public static <E> Set<Set<E>> allSubsets(Set<E> s) 
    {
        // The new set that is to hold all subsets
        Set<Set<E>> allSubsets = new ArraySet();
        
        // If the given set is empty, return the empty set
        if(s.isEmpty())
        {
            allSubsets.add(new ArraySet());
            return allSubsets;
        }
        
        // A list containing all the elements in the given set
        ArrayList<E> elements = new ArrayList();
        for(E e : s)
            elements.add(e);
        
        // Calculate the total amount of subsets, formula: 2^n, n = |Set|
        int nrOfSubsets = (int) Math.pow(2, s.size());
        
        // Iterate over the amount of subsets, in order to add all subsets
        for (int i = 0; i < nrOfSubsets; i++)
        {
            // The subset to be added
            Set<E> subset = new ArraySet();
            // Iterate through all the elements in the given set
            for (int bitIndex = 0; bitIndex < s.size(); bitIndex++)
                // Use the "bit trick" to decide whether to add an element or not
                if (bitValue(i, bitIndex) == 1)
                    subset.add(elements.get(bitIndex));
            // Add the subset to the set containing all the subsets
            allSubsets.add(subset);
        }
        return allSubsets;
    }
    
    // Given a value and a position in the binary numeral system
    // returns whether the bit at the position is set or not
    public static int bitValue(int value, int binaryPosition)
    {
        // Convert the value to its binary representation
        String binary = Integer.toBinaryString(value);
        // Mask the binary representation
        int bit = Integer.parseInt(binary) & (int)Math.pow(2, binaryPosition);
        // Check if the bit is set
        return (bit > 0 ? 1 : 0);
    } 
}