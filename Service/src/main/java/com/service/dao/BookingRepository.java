package com.service.dao;



import java.util.Collection;


import org.springframework.data.jpa.repository.JpaRepository;
import com.service.entity.Booking; 


import com.service.entity.Category;



public interface BookingRepository extends JpaRepository<Booking, Long>{
	Collection<Booking> findByCategory(Category category);

}
