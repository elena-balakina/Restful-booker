package com.restfulbooker.api;

import com.restfulbooker.api.api.BookingApi;
import com.restfulbooker.api.payloads.Booking;
import com.restfulbooker.api.payloads.BookingDates;
import com.restfulbooker.api.payloads.BookingResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiCreateBookingTest {

    @Test
    public void postBookingAndGetBooking(){
        BookingDates dates = new BookingDates.Builder()
                .setCheckin(new Date())
                .setCheckout(new Date())
                .build();

        Booking payload = new Booking.Builder()
                .setFirstname("Mary")
                .setLastname("White")
                .setTotalprice(200)
                .setDepositpaid(true)
                .setBookingdates(dates)
                .setAdditionalneeds("None")
                .build();

        BookingResponse createdBookingResponse = BookingApi.postBooking(payload).as(BookingResponse.class);
        System.out.println(createdBookingResponse.getBooking().toString());
        int bookingId = createdBookingResponse.getBookingid();

        Booking responseGetBookingBuId = BookingApi.getBooking(bookingId, "application/json").as(Booking.class);
        assertEquals(payload, responseGetBookingBuId, "Received booking is not as expected");
    }
}
