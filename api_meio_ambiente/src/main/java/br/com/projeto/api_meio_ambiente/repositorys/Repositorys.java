package br.com.projeto.api_meio_ambiente.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api_meio_ambiente.models.Person;

@Repository
public interface Repositorys extends CrudRepository<Person, Integer>{

    List<Person> findAll();

    Person findById(int codigo);

    List<Person> findByOrderByName();

    List<Person> findByOrderByYearOldDesc();

    List<Person> findByNameContaining(String term);

    List<Person> findByNameStartsWith(String term);

    List<Person> findByNameEndsWith(String term);

    @Query(value = "SELECT SUM(year_old) FROM persons", nativeQuery = true)
    int sumYearsOld();

    @Query(value = "SELECT * FROM persons WHERE year_old >= :yearOld", nativeQuery = true)
    List<Person> yearOldBggerThan(int yearOld);

    int countById(int id);
 
}
