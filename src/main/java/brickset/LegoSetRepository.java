package brickset;

import repository.Repository;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet>
{
    /**
     * Returns whether a set exists with the given number.
     * @param number a potential number of a LEGO set
     * @return true or false, depending if the given number corresponds to an existing LEGO set
     */
    public boolean isThereASetWithThisNumber(String number)
    {
        return getAll().stream()
                .anyMatch(legoSet -> legoSet.getNumber().equals(number));
    }

    /**
     * Prints the number for each Duplo set.
     */
    public void printNumberOfDuploSets()
    {
        getAll().stream()
                .filter(legoSet -> legoSet.getTheme().equals("Duplo"))
                .flatMap(legoSet -> Stream.of(legoSet.getNumber()))
                .forEach(System.out::println);
    }

    /**
     * Returns the number and corresponding name of each set.
     * @return a map of the number and name for each set
     */
    public Map<String, String> getNumberAndNameOfSets()
    {
        return getAll().stream()
                .collect(Collectors.toMap(legoSet -> legoSet.getNumber(), legoSet -> legoSet.getName()));
    }

    public LegoSetRepository()
    {
        super(LegoSet.class, "brickset.json");
    }

    public static void main(String[] args)
    {
        var repository = new LegoSetRepository();

        System.out.println(repository.isThereASetWithThisNumber("3838") + "\n");
        repository.printNumberOfDuploSets();
        System.out.println("\n");
        System.out.println(repository.getNumberAndNameOfSets() + "\n");
    }
}