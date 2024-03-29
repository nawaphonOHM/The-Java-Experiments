import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.Set;
import java.util.HashSet;
import java.time.chrono.IsoChronology;

public class MethodReferencesTest{
    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>>
    DEST transferElements(
        SOURCE sourceCollection, 
        Supplier<DEST> collectionFactory
        ){
            DEST result = collectionFactory.get();
            for(T t : sourceCollection) result.add(t);
            return result;
        }
    public static void main(String... args){
        List<Person> roster = Person.createRoster();

        Person[] rosterAsArray = roster.toArray(new Person[roster.size()]);

        class PersonAgeComparator implements Comparator<Person>{
            public int compare(Person a, Person b){
                return a.getBirthday().compareTo(b.getBirthday()); 
            }
        }

        Arrays.sort(rosterAsArray, new PersonAgeComparator());

        Arrays.sort(
            rosterAsArray, 
            (Person a, Person b) -> {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        );

        Arrays.sort(
            rosterAsArray, 
            (a, b) -> Person.compareByAge(a, b)
        );

        Arrays.sort(
            rosterAsArray, 
            Person::compareByAge
        );

        class ComparisonProvider{
            public int compareByName(Person a, Person b){
                return a.getName().compareTo(b.getName());
            }

            public int compareByAge(Person a, Person b){
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }

        ComparisonProvider myComparisonProvider = new ComparisonProvider();
        
        Arrays.sort(rosterAsArray, myComparisonProvider::compareByName);

        String[] stringArray = {
            "Barbara", "James", "Mary", "John", 
            "Patricia", "Robert", "Michael", "Linda"
        };

        Arrays.sort(stringArray, String::compareToIgnoreCase);
        
        Set<Person> rosterSetLambda = transferElements(
            roster, 
            () -> {
                return new HashSet<>();
            }
        );

        Set<Person> rosterSet = transferElements(
            roster, 
            HashSet::new
        );

        System.out.println("Printing rosterSet:");
        rosterSet.stream().forEach(p -> p.printPerson());
    }
}