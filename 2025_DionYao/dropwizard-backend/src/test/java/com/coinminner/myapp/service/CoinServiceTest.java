package com.coinminner.myapp.service;

import org.junit.jupiter.api.Test;

import com.coinminner.myapp.api.CoinRequestDto;
import com.coinminner.myapp.api.CoinResponseDto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CoinServiceTest {
	
	// Test simple case remainder 0
	@Test
	void testSimpleSubmissionRemainderZero() {
		CoinRequestDto request = new CoinRequestDto(12.31, Arrays.asList(0.01, 0.5, 0.1, 0.5, 1.0, 5.0, 100.0, 1000.0));
		
		CoinResponseDto response = CoinService.findMinCoins(request);
		
		List<Double> serviceCoins = response.getCoins();
		List<Double> expectedCoins = Arrays.asList(0.01, 0.1, 0.1, 0.1, 1.0, 1.0, 5.0, 5.0);
		
		double serviceRemainder = response.getRemainder();
		double expectedRemainder = 0;
		
		assertEquals(serviceCoins, expectedCoins);
		assertEquals(serviceRemainder, expectedRemainder);
	}
	
	// Test simple case remainder not 0
	@Test
	void testSimpleSubmissionRemainderNotZero() {
		CoinRequestDto request = new CoinRequestDto(12.31, Arrays.asList(0.5, 0.1, 0.5, 1.0, 5.0, 100.0, 1000.0));
		
		CoinResponseDto response = CoinService.findMinCoins(request);
		
		List<Double> serviceCoins = response.getCoins();
		List<Double> expectedCoins = Arrays.asList(0.1, 0.1, 0.1, 1.0, 1.0, 5.0, 5.0);
		
		double serviceRemainder = response.getRemainder();
		double expectedRemainder = 0.01;
		
		assertEquals(serviceCoins, expectedCoins);
		assertEquals(serviceRemainder, expectedRemainder);
	}
	
	// Test a case with a lot of iterating
	@Test
	void testHeavySubmission() {
		int target = 15000;
		
		CoinRequestDto request = new CoinRequestDto(target, Arrays.asList(0.01));
		
		CoinResponseDto response = CoinService.findMinCoins(request);
		
		List<Double> serviceCoins = response.getCoins();
		List<Double> expectedCoins = new ArrayList<>();
		
		for (int i = 0; i < (target/0.01); i++) {
			expectedCoins.add(0.01);
		}
		
		assertEquals(serviceCoins, expectedCoins);
	}
	
	// Test a case where the denoms are all greater than target
	@Test
	void testAllDenomsGreaterThanTarget() {
		CoinRequestDto request = new CoinRequestDto(12.31, Arrays.asList(100.0, 1000.0));
		CoinResponseDto response = CoinService.findMinCoins(request);

		List<Double> serviceCoins = response.getCoins();
		List<Double> expectedCoins = new ArrayList<>();
		
		double serviceRemainder = response.getRemainder();
		double expectedRemainder = 12.31;
		
		assertEquals(serviceCoins, expectedCoins);
		assertEquals(serviceRemainder, expectedRemainder);

	}
	
	// Test that calling the Service method with target 0 throws error
	@Test
	void testZeroTarget() {
		CoinRequestDto request = new CoinRequestDto(0, Arrays.asList(0.01, 0.5, 0.1, 0.5, 1.0, 5.0, 100.0, 1000.0));
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			CoinService.findMinCoins(request);
		});
				
		assertEquals("Target must be greater than 0.", exception.getMessage());

	}
	
	// Test that calling the Service method with negative target throws error with right message
	@Test
	void testNegativeTarget() {
		CoinRequestDto request = new CoinRequestDto(-100, Arrays.asList(0.01, 0.5, 0.1, 0.5, 1.0, 5.0, 100.0, 1000.0));
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			CoinService.findMinCoins(request);
		});
				
		assertEquals("Target must be greater than 0.", exception.getMessage());

	}
	
	// Test that calling the Service method with an empty list of denoms throws error with right message
	@Test
	void testNoDenoms() {
		CoinRequestDto request = new CoinRequestDto(12.31, new ArrayList<>());
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			CoinService.findMinCoins(request);
		});
				
		assertEquals("You have to pick at least 1 denomination.", exception.getMessage());

	}
	

	
}
