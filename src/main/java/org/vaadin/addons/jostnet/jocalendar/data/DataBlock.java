package org.vaadin.addons.jostnet.jocalendar.data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.vaadin.addons.jostnet.jocalendar.ViewType;

public class DataBlock
{

	private Map<LocalDate, DayItem> datemap = new HashMap<>();

	private LocalDate date;

	private ViewType viewType;

	public DataBlock(LocalDate date, ViewType viewType,
			CalendarSupplier[] calendarSuppliers)
	{
		this.date = date;
		this.viewType = viewType;

		LocalDate from = getFrom();
		LocalDate to = getTo();

		// Teil 1: Datenstruktur vorbereiten
		datemap = new HashMap<>();
		while (!from.isAfter(to))
		{
			datemap.put(from, new DayItem(from));
			from = from.plus(1, ChronoUnit.DAYS);
		}
		// Teil 2: Datenstruktur befüllen
		for (CalendarSupplier cs : calendarSuppliers)
		{
			for (CalendarEntry ce : cs.get(getFrom(), getTo()))
			{
				DayItem dayItem = datemap.get(ce.getDate().toLocalDate());
				dayItem.addEntry(ce);
			}
		}
	}

	public DayItem get(LocalDate date)
	{
		return datemap.get(date);
	}

	public LocalDate getFrom()
	{
		switch (viewType)
		{
			case MONTH:
				return date.withDayOfMonth(1).with(DayOfWeek.MONDAY);
			case WEEK:
				return date.with(DayOfWeek.MONDAY);
			case DAY:
				return date;
			case LIST:
				return date.withDayOfYear(1);
		}
		return null;
	}

	public LocalDate getTo()
	{
		switch (viewType)
		{
			case MONTH:
				return date.plus(1, ChronoUnit.MONTHS).withDayOfMonth(1)
						.minus(1, ChronoUnit.DAYS).with(DayOfWeek.SUNDAY);
			case WEEK:
				return date.with(DayOfWeek.SUNDAY);
			case DAY:
				return date;
			case LIST:
				return date.withDayOfYear(date.isLeapYear() ? 366 : 365);
		}
		return null;
	}

}
