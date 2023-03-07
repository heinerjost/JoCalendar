package org.vaadin.addons.jostnet.jocalendar.data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CalendarEntry
{
	private LocalDateTime date;

	private Time timeto;

	private String description;

	public void setDate(LocalDate date)
	{
		this.date = LocalDateTime.of(date, LocalTime.MIN);
	}

	public void setDate(LocalDateTime date)
	{
		this.date = date;
	}

	public LocalDateTime getDate()
	{
		return this.date;
	}

	public void setTimeto(Time timeto)
	{
		this.timeto = timeto;
	}

	public Time getTimeto()
	{
		return this.timeto;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return this.description;
	}
}
