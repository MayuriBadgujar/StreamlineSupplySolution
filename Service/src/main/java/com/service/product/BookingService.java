package com.service.product;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.dao.BookingRepository;
import com.service.dao.CategoryRepository;
import com.service.entity.Booking;
import com.service.entity.Category;
import com.service.exception.BookingNotFoundException;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    // Add a new Booking
    public Booking addBooking(Booking booking) {
        Category category = categoryRepo.findByName(booking.getCategory().getName())
            .orElseGet(() -> {
                Category newCategory = new Category();
                newCategory.setName(booking.getCategory().getName());
                newCategory.setCategoryType(booking.getCategory().getCategoryType()); // Set categoryType
                return categoryRepo.save(newCategory);
            });

        // Create a new Booking with category
        Booking book = new Booking();
        book.setName(booking.getName());
        book.setDescription(booking.getDescription());
        book.setPrice(booking.getPrice());
        book.setMakeCompany(booking.getMakeCompany());
        book.setImage(booking.getImage());
        book.setPdf(booking.getPdf());
        book.setCreatedDate(booking.getCreatedDate());
        book.setCreatedBy(booking.getCreatedBy());
        book.setCategory(category); // Set category

        return bookingRepo.save(book);
    }

    // Get all Bookings
    public Collection<Booking> getAllBooking() {
        return bookingRepo.findAll();
    }

    // Get Booking by ID
    public Booking getBookingById(Long id) {
        return bookingRepo.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found for ID: " + id));
    }

    // Delete Booking by ID
    public String deleteBookingById(Long id) {
        Booking foundBooking = bookingRepo.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found for ID: " + id));
        bookingRepo.delete(foundBooking);
        return "Booking with ID: " + id + " deleted successfully";
    }

    // Update Booking by ID
    public Booking updateBookingById(Long id, Booking updatedBooking) {
        Booking foundBooking = bookingRepo.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found for ID: " + id));

        foundBooking.setName(updatedBooking.getName());
        foundBooking.setDescription(updatedBooking.getDescription());
        foundBooking.setPrice(updatedBooking.getPrice());
        foundBooking.setMakeCompany(updatedBooking.getMakeCompany());
        foundBooking.setImage(updatedBooking.getImage());
        foundBooking.setPdf(updatedBooking.getPdf());
        foundBooking.setCreatedDate(updatedBooking.getCreatedDate());
        foundBooking.setCreatedBy(updatedBooking.getCreatedBy());

        // Ensure category is updated correctly
        if (updatedBooking.getCategory() != null && updatedBooking.getCategory().getName() != null) {
            Category category = categoryRepo.findByName(updatedBooking.getCategory().getName())
                    .orElseGet(() -> {
                        Category newCategory = new Category();
                        newCategory.setName(updatedBooking.getCategory().getName());
                        return categoryRepo.save(newCategory);
                    });

            foundBooking.setCategory(category);
        }

        return bookingRepo.save(foundBooking);
    }
}
