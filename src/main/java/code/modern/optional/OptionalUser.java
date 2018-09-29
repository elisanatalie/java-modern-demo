package code.modern.optional;

import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class OptionalUser {

    public String getCarInsuranceName(final Optional<Person> person) {
        return person.flatMap(Person::getCar)
                     .flatMap(Car::getInsurance)
                     .map(Insurance::getName)
                     .orElse("Unknown");
    }

    public Set<String> getCarInsuranceNames(final List<Person> persons) {
        return persons.stream()
                      .map(Person::getCar)
                      .map(optCar -> optCar.flatMap(Car::getInsurance))
                      .map(optInsurance -> optInsurance.map(Insurance::getName))
                      .flatMap(Optional::stream)
                      .collect(toSet());
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(final Optional<Person> person, final Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Insurance> nullSafeFindCheapestInsuranceQuiz(final Optional<Person> person, final Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    private Insurance findCheapestInsurance(final Person person, final Car car) {
        // queries services provided by the different insurance companies
        // compare all those data
        final Insurance cheapestCompany = new Insurance();
        return cheapestCompany;
    }

    public String getCarInsuranceName(final Optional<Person> person, final int minAge) {
        return person.filter(p -> p.getAge() >= minAge)
                     .flatMap(Person::getCar)
                     .flatMap(Car::getInsurance)
                     .map(Insurance::getName)
                     .orElse("Unknown");
    }
}
