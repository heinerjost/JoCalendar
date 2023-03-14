package org.vaadin.addons.jostnet.jocalendar.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CalendarEntry
{
	private LocalDateTime date;

	private LocalDateTime to;

	private String description;

	private String color = "black";

	private String backgroundColor = "white";

	public CalendarEntry()
	{
///		addClassName("jocalendar-month-day-body");
//		setText(description);
	}

	public LocalDateTime getDate()
	{
		return date;
	}

	public void setDate(LocalDateTime date)
	{
		this.date = date;
	}

	public void setDate(LocalDate date)
	{
		this.date = LocalDateTime.of(date, LocalTime.MIN);
	}

	public LocalDateTime getTo()
	{
		return to;
	}

	public void setTo(LocalDateTime to)
	{
		this.to = to;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getColor()
	{
		return this.color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public String getBackgroundColor()
	{
		return this.backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}

}
