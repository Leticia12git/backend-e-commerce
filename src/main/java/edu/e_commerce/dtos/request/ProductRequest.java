package edu.e_commerce.dtos.request;

public record ProductRequest(String name,
                             int quantity,
                             Double price) {
}
