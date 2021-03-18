package code.modern.presentation;

import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import code.modern.optional.Car;
import code.modern.optional.Insurance;
import code.modern.optional.Person;

public class WhatIsNew3 {
    public Set<String> getCarInsuranceNames(final List<Person> persons) {
        return persons.stream()
                      .map(Person::getCar)
                      .map(optCar -> optCar.flatMap(Car::getInsurance))
                      .map(optIns -> optIns.map(Insurance::getName))
                      .flatMap(Optional::stream)
                      .collect(toSet());
    }
}
