package tech.sosa.ingweb.domain.movie;

import java.time.LocalDate;

public class Year {

	private LocalDate date;
	
	public Year(int year) {
		date = LocalDate.of(year, 1, 1);
	}
	
	public int value() {
		return date.getYear();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		Year other = (Year) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(value());
	}
	
}
