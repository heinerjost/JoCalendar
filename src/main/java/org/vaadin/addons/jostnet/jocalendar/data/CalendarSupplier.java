package org.vaadin.addons.jostnet.jocalendar.data;

import java.time.LocalDate;
import java.util.List;

public interface CalendarSupplier
{
	public abstract List<CalendarEntry> get(LocalDate from, LocalDate to);
}
