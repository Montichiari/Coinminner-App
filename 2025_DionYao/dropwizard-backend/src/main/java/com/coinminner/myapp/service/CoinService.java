package com.coinminner.myapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.coinminner.myapp.api.CoinRequestDto;
import com.coinminner.myapp.api.CoinResponseDto;

public class CoinService {
	
	public static CoinResponseDto findMinCoins(CoinRequestDto request) {
		
		// Unpack the Dto first
		double target = request.getTarget();
		List<Double> denoms = request.getDenoms();
		
		// Protecting against 0 or negative target edge case
		if (target <= 0) {
			throw new IllegalArgumentException("Target must be greater than 0.");
		} else if (denoms.isEmpty()) {
			throw new IllegalArgumentException("You have to pick at least 1 denomination.");
		}
		
		// Convert the target and denominations into integer to prevent precision issues
		int targetInCents = (int) Math.round(target * 100);
		
		List<Integer> denomsInCents = new ArrayList<>();
		for (Double denom : denoms) {
			denomsInCents.add((int) Math.round(denom * 100));
		}
		
		// Sort the denoms in desc order
		Collections.sort(denomsInCents, Collections.reverseOrder());
		
		// Initialising the final list to return
		List<Double> answer = new ArrayList<>();
		
		// Remainder starts as the whole target
		int remainder = targetInCents;
		
		// For each coin denom, if the remainder is smaller or equal to it,
		// divide the remainder by the denom and add the denom to the answer list that number of times
		// then set the remainder for the next loop to be the remainder after dividing by the current denom
		for (Integer denom : denomsInCents) {
			if (remainder >= denom) {
				int amtOfDenom = remainder / denom;
				while (amtOfDenom > 0) {
					answer.add((denom / 100.0));
					amtOfDenom -= 1;
				}
				remainder = remainder % denom;
			}

		}
		
		// Final sort of the list in asc order
		Collections.sort(answer);
		
		// Final remainder in double
		double finalRemainder = remainder / 100.0;
		
		// Add to and return the responseDto
		CoinResponseDto response = new CoinResponseDto(answer, finalRemainder);
		
		return response;
	}
}
