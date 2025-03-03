package com.example.greetingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.greetingApp.Model.Greeting;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {
}
