package com.coinminner.myapp.api;

import java.util.List;

public class CoinResponseDto {
	
	private List<Double> coins;
	
	private double remainder;

	public CoinResponseDto() {
		
	}
	
	public CoinResponseDto(List<Double> coins, double remainder) {
		super();
		this.coins = coins;
		this.remainder = remainder;
	}

	public List<Double> getCoins() {
		return coins;
	}

	public void setCoins(List<Double> coins) {
		this.coins = coins;
	}

	public double getRemainder() {
		return remainder;
	}

	public void setRemainder(double remainder) {
		this.remainder = remainder;
	}
	
	
	
}
