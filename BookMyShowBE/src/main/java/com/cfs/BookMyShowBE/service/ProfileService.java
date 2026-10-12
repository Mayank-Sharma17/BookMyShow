package com.cfs.BookMyShowBE.service;

import com.cfs.BookMyShowBE.dto.CreateProfileRequest;
import com.cfs.BookMyShowBE.dto.ProfileResponse;
import com.cfs.BookMyShowBE.entity.Customer;
import com.cfs.BookMyShowBE.repository.CustomerRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProfileService {
    private final CustomerRepository customerRepository;

    public ProfileService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ProfileResponse create(CreateProfileRequest request) { // taking the request from controller, return res as id, name email, phone
        String email = request.email().trim().toLowerCase(Locale.ROOT);
        String phone = normalizePhone(request.phone());

        if (customerRepository.existsByEmail(email)) {
            throw new ProfileConflictException("An account already exists with this email");
        }

        if(customerRepository.existsByPhone(phone)) {
            throw new ProfileConflictException("An account already exists with this phone number");
        }

        return ProfileResponse.from(customerRepository.save(new Customer(request.name().trim(), email, phone))); // save to db
    }

    public ProfileResponse login(String identifier) {
        String value = identifier == null ? "" : identifier.trim();
        String phone = value.replaceAll("\\D", "");

        Customer customer = value.contains("@")
                ? customerRepository.findByEmail(value.toLowerCase(Locale.ROOT)).orElse(null)
                : customerRepository.findByPhone(phone).orElse(null);

        if(customer == null) {
            throw new ResourceNotFoundException("No profile found this phone number or email");
        }

        return ProfileResponse.from(customer);
    }

    public String normalizePhone(String phone) {
        // regex -> \D is a shorthand character class that matches any character that is not a decimal digit
        String normalize = phone == null ? "" : phone.replaceAll("\\D", "");
        if(normalize.length() < 10 || normalize.length() > 15) {
            throw new IllegalArgumentException("Enter valid phone number");
        }
        return normalize;
    }
}

// Locale kya hota hai?
//Locale basically batata hai ki kis language/region ke rules ke according text ko process karna hai.

// Email mein Locale.ROOT kyun?
// .ENGLISH --> eng ke rules ke according karega conversion
// ROOT --> neutral/default
//Email ko normalize karte waqt tum nahi chahte ki user's system language ke according lowercase behavior change ho.
//For example, kuch languages mein I/i jaise characters ke case-conversion rules English se different ho sakte hain.