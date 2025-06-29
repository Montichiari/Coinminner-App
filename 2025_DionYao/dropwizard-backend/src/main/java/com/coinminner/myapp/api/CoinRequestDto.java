package com.coinminner.myapp.api;

import java.util.List;

public class CoinRequestDto {
	
	private double target;
	private List<Double> denoms;
	
	
	public CoinRequestDto() {
		
	}
	
	public CoinRequestDto(double target, List<Double> denoms) {
		super();
		this.target = target;
		this.denoms = denoms;
	}
	
	public double getTarget() {
		return target;
	}
	public void setTarget(double target) {
		this.target = target;
	}
	public List<Double> getDenoms() {
		return denoms;
	}
	public void setDenoms(List<Double> denoms) {
		this.denoms = denoms;
	}
	
	
}
