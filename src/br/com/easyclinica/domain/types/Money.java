package br.com.easyclinica.domain.types;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Money {

	private BigDecimal amount;

	public Money(){}
	public Money(BigDecimal amount){
		this.amount = amount;
	}
	public Money(double amount) {
		this.amount = new BigDecimal(amount);
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	
	public static Money empty() {
		return new Money(0.0);
	}
	
	public void addValueToAmount(BigDecimal value) {
		this.amount = this.amount.add(value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
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
		Money other = (Money) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		return true;
	}
}
