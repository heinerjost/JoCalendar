package org.vaadin.addons.jostnet.jocalendar.data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.vaadin.flow.component.html.Div;

public class CalendarEntry extends Div
{
	private static final long serialVersionUID = 1L;

	private LocalDateTime date;

	private Time timeto;

	public CalendarEntry(LocalDateTime date, 
			String description,String foregroundColor, String backgroundColor)
	{
		this.date = date;
		getStyle().set("color", foregroundColor);
		getStyle().set("backgroundColor", backgroundColor);
		addClassName("jocalendar-month-day-body");
		setText(description);
	}

	public CalendarEntry(LocalDate date, String description,
			String foregroundColor, String backgroundColor)
	{
		this(LocalDateTime.of(date, LocalTime.MIN), description, foregroundColor,
				backgroundColor);
	}

	public LocalDateTime getDate()
	{
		return date;
	}

	@Override
	public String toString()
	{
		return "Date: " + date + ", timeto: " + timeto;
	}
}
