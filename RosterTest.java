import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Comparator;
import java.util.function.Predicate;
import java.lang.Iterable;
import java.time.chrono.IsoChronology;

public class RosterTest{
    interface CheckPerson{ boolean test(Person p); }

    public static void printPersonsOlderThan(List<Person> roster, int age){
        for(Person p : roster){
            if(p.getAge() >= age) p.printPerson();
        }
    }

    public static void printPersonsWithinAgeRange(
        List<Person> roster, int low, int high
    ){
        for(Person p : roster){
            if(low <= p.getAge() && p.getAge() < high) p.printPerson();
        }
    }

    public static void printPersons(
        List<Person> roster, CheckPerson tester
    ){
        for(Person p : roster){
            if(tester.test(p)) p.printPerson();
        }
    }

    public static void printPersonsWithPredicate(
        List<Person> roster, Predicate<Person> tester
    ){
        for(Person p : roster){
            if(tester.test(p)) p.printPerson();
        }
    }

    public static void processPersons(
        List<Person> roster, 
        Predicate<Person> tester, 
        Consumer<Person> block
    ){
        for(Person p : roster){
            if(tester.test(p)) block.accept(p);
        }
    }

    public static void processPersonsWithFunction(
        List<Person> roster, 
        Predicate<Person> tester, 
        Function<Person, String> mapper, 
        Consumer<String> block
    ){
        for(Person p : roster){
            if(tester.test(p)){
                String data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    public static <X, Y> void processElements(
        Iterable<X> source, 
        Predicate<X> tester, 
        Function<X, Y> mapper, 
        Consumer<Y> block
    ){
        for(X p : source){
            if(tester.test(p)){
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    public static void main(String... args){
        List<Person> roster = Person.createRoster();

        for(Person p : roster) p.printPerson();

        System.out.println("Person older than 20:");
        printPersonsOlderThan(roster, 20);
        System.out.println();

        System.out.println("Persons between the ages of 14 and 30:");
        printPersonsWithinAgeRange(roster, 14, 30);
        System.out.println();

        System.out.println("Persons who are eligible for Selective Service:");

        class CheckPersonEligibleForSelectiveService implements CheckPerson{
            public boolean test(Person p){
                return p.getGender() == Person.Sex.MALE && 
                p.getAge() >= 18 && 
                p.getAge() <= 25;
            }
        }

        printPersons(roster, new CheckPersonEligibleForSelectiveService());
        System.out.println();

        System.out.println("Persons who are eligible for Selective Service " + 
        "(anonymous class):");

        printPersons(
            roster, 
            new CheckPerson(){
                public boolean test(Person p){
                    return p.getGender() == Person.Sex.MALE && 
                    p.getAge() >= 18 &&
                    p.getAge() <= 25;
                }
            }
        );

        System.out.println();

        System.out.println("Persons who are eligible for Selective Service " + 
        "(lambda expression):");

        printPersons(
            roster, 
            (Person p) -> p.getGender() == Person.Sex.MALE && 
            p.getAge() >= 18 &&
            p.getAge() <= 25
        );

        System.out.println();

        System.out.println("Persons who are eligible for Selective Service " + 
        "(with Predicate parameter):");

        printPersonsWithPredicate(
            roster, 
            p -> p.getGender() == Person.Sex.MALE && 
            p.getAge() >= 18 &&
            p.getAge() <= 25
        );

        System.out.println();

        System.out.println("Persons who are eligible for Selective Service " + 
        "(with Predicate and Consumer parameters):");

        processPersons(
            roster, 
            p -> p.getGender() == Person.Sex.MALE && 
            p.getAge() >= 18 &&
            p.getAge() <= 25, 
            p -> p.printPerson()
        );

        System.out.println();

        System.out.println("Persons who are eligible for Selective Service " + 
        "(with Predicate, Function, and Consumer parameters):");

        processPersonsWithFunction(
            roster, 
            p -> p.getGender() == Person.Sex.MALE &&
            p.getAge() >= 18 && p.getAge() <= 25, 
            p -> p.getEmailAddress(), 
            email -> System.out.println(email)
        );

        System.out.println();

        System.out.println("Persons who are eligible for Selective Service " + 
        "(generic version):");

        processElements(
            roster, 
            p -> p.getGender() == Person.Sex.MALE && 
            p.getAge() >= 18 && p.getAge() <= 25, 
            p -> p.getEmailAddress(), 
            email -> System.out.println(email)
        );

        System.out.println();

        System.out.println("Persons who are eligible for Selective Service " + 
        "(with bulk data operations):");

        roster
            .stream()
            .filter(
                p -> p.getGender() == Person.Sex.MALE &&
                p.getAge() >= 18 && p.getAge() <= 25
            )
            .map(p -> p.getEmailAddress())
            .forEach(email -> System.out.println(email));
    }
}