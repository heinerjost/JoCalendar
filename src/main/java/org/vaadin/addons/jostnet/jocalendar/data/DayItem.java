package org.vaadin.addons.jostnet.jocalendar.data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;

public class DayItem extends Div
{
	private static final long serialVersionUID = 4330132873154455934L;

	private List<CalendarEntry> entries = new ArrayList<>();

	public DayItem(LocalDate tag)
	{
		super();

		this.addClassName("jocalendar-month-day");

		Div header = new Div();
		header.addClassName("jocalendar-month-day-header");
		if (tag.getDayOfWeek() == DayOfWeek.MONDAY)
		{
			int kw = tag.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
			Div head1 = new Div();
			head1.addClassName("jocalendar-month-day-header-element");
			head1.setText("KW" + kw);
			header.add(head1);
		}
		else
		{
			Div head1 = new Div();
			head1.addClassName("jocalendar-month-day-header-element");
			head1.setText("");
		}
		Div head2 = new Div();
		head2.addClassName("jocalendar-month-day-header-element");
		head2.setText(tag.getDayOfMonth() + "");
		header.add(head2);
		add(header);
	}

	public void addEntry(CalendarEntry entry)
	{
		entries.add(entry);
		Div layout = new Div();
		layout.addClassName("jocalendar-month-day-body");
		layout.add(entry.getDescription());
		add(layout);
	}
}
