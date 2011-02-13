package br.com.easyclinica.domain.types;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Quantity {

	private BigDecimal qty;

	public Quantity(){}
	public Quantity(double quantity) {
		this.qty = new BigDecimal(quantity);
	}	
	public Quantity(BigDecimal quantity) {
		this.qty = quantity;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((qty == null) ? 0 : qty.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quantity other = (Quantity) obj;
		if (qty == null) {
			if (other.qty != null)
				return false;
		} else if (!qty.equals(other.qty))
			return false;
		return true;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getQty() {
		return qty;
	}
}
