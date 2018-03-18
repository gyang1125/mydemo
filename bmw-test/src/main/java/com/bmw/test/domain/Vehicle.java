package com.bmw.test.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Vehicle data model
 * 
 * @author gyang
 *
 */
@Entity
public class Vehicle extends AbstractPersistable {

	@NotNull
	@Size(min = 1)
	@Column(nullable = false, unique = true)
	private String vin;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_vehicle")
	private List<Position> positions = new ArrayList<Position>();

	public Vehicle() {

	}

	public Vehicle(String vin) {
		super();
		this.vin = vin;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public void addPosition(Position position) {
		this.positions.add(position);
	}

}
