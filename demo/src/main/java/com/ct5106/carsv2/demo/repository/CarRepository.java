package com.ct5106.carsv2.demo.repository;


import java.util.List;

import com.ct5106.carsv2.demo.domain.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="cars")
public interface CarRepository extends CrudRepository<Car, Long>
{

    List<Car> findByMake (String make);

    List<Car> findByYearRegistered (int year);

    List<Car> findByMakeAndModel(String make, String model);

    List<Car> findByMakeOrModel(String make, String model);

    List<Car> findByMakeOrderByPriceDesc(String make);

    // Fetch cars by price range using SQL
    @Query("select c from Car c where c.price between ?1 and ?2")
    List<Car> findByPriceBetween(@Param("low") double low, @Param("high") double high);

    // Fetch cars with reg containing county string, e.g. "KY"
    List<Car> findByRegistrationContaining(@Param("county") String county);

}