package com.example.module6_assesment_v2_microservicio.dto;

public record CoverageRequest(
        String document,
        String affiliationType,
        String serviceCode,
        Double cost
) {}
