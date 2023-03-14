package org.vaadin.addons.jostnet.jocalendar.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DayItem
{
	private LocalDate day;

	private List<CalendarEntry> entries = new ArrayList<>();

	public DayItem(LocalDate day)
	{
		super();
		this.day = day;
	}

	public void addEntry(CalendarEntry entry)
	{
		entries.add(entry);
	}

	public List<CalendarEntry> getEntries()
	{
		return entries;
	}

	public LocalDate getDay()
	{
		return this.day;
	}
}
