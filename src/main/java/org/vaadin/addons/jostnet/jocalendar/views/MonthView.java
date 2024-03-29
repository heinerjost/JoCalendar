package org.vaadin.addons.jostnet.jocalendar.views;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import org.vaadin.addons.jostnet.jocalendar.data.CalendarEntry;
import org.vaadin.addons.jostnet.jocalendar.data.DataBlock;
import org.vaadin.addons.jostnet.jocalendar.data.DayItem;
import org.vaadin.addons.jostnet.jocalendar.data.TagHeader;

import com.vaadin.flow.component.html.Div;

public class MonthView extends Div
{
	private static final long serialVersionUID = 1519402887914157543L;

	public MonthView(LocalDate from, LocalDate to, DataBlock dataBlock)
	{
		super();
		addClassName("jocalendar-month");

		Div days = new Div();
		days.addClassName("jocalendar-month-header");

		days.add(new TagHeader("Mo"));
		days.add(new TagHeader("Di"));
		days.add(new TagHeader("Mi"));
		days.add(new TagHeader("Do"));
		days.add(new TagHeader("Fr"));
		days.add(new TagHeader("Sa"));
		days.add(new TagHeader("So"));
		add(days);

		LocalDate tmp = from;
		LocalDate end = to;

		while (tmp.isBefore(end))
		{
			Div d1 = new Div();
			d1.addClassName("jocalendar-month-table");
			for (int i = 0; i < 7; i++)
			{
				DayItem dayItem = dataBlock.get(tmp);

				Div daycontainer = new Div();
				daycontainer.addClassName("jocalendar-month-day");

				Div header = new Div();
				header.addClassName("jocalendar-month-day-header");
				if (dayItem.getDay().getDayOfWeek() == DayOfWeek.MONDAY)
				{
					int kw = dayItem.getDay().get(ChronoField.ALIGNED_WEEK_OF_YEAR);
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
				head2.setText(dayItem.getDay().getDayOfMonth() + "");
				header.add(head2);
				daycontainer.add(header);

				Div monthBody = new Div();
				monthBody.addClassName("jocalendar-month-day-body");
				daycontainer.add(monthBody);
				for (CalendarEntry ce : dayItem.getEntries())
				{
					Div entry = ce.getDiv();
					entry.setText(ce.getDescription());
					entry.getStyle().set("color", ce.getColor());
					entry.getStyle().set("backgroundColor", ce.getBackgroundColor());
					monthBody.add(entry);
				}

				d1.add(daycontainer);

				tmp = tmp.plus(1, ChronoUnit.DAYS);
			}
			add(d1);
		}
	}
}
