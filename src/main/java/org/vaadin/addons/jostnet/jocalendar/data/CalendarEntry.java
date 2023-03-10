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

	private String foregroundColor = "black";

	private String backgroundColor = "white";

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

	public void setForegroundColor(String color)
	{
		this.foregroundColor = color;
	}

	public String getForegroundColor()
	{
		return foregroundColor;
	}

	public void setBackgroundColor(String color)
	{
		this.backgroundColor = color;
	}

	public String getBackgroundColor()
	{
		return backgroundColor;
	}

	@Override
	public String toString()
	{
		return "Date: " + date + ", timeto: " + timeto + ", description: "
				+ description + ", foregroundColor: " + foregroundColor
				+ ", backgroundColor: " + backgroundColor;
	}
}
