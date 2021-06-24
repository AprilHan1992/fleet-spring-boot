package com.fleet.ldap.repository;

import com.fleet.ldap.enity.Person;
import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;

public interface PersonRepository extends CrudRepository<Person, Name> {
}
