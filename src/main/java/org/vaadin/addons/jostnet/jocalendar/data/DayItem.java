package org.vaadin.addons.jostnet.jocalendar.data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class DayItem extends VerticalLayout
{
	private static final long serialVersionUID = 4330132873154455934L;

	private List<CalendarEntry> entries = new ArrayList<>();

	public DayItem(LocalDate tag)
	{
		super();
		setMargin(false);
		setPadding(false);
		setSpacing(false);
		setMinHeight("100px");
		setWidthFull();
		setHeightFull();
		FlexLayout header = new FlexLayout();
		header.setFlexDirection(FlexDirection.ROW);
		header.setJustifyContentMode(JustifyContentMode.BETWEEN);
		header.setWidthFull();
		header.setHeightFull();

		if (tag.getDayOfWeek() == DayOfWeek.MONDAY)
		{
			int kw = tag.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
			header.add(new Label("KW" + kw));
		}
		else
		{
			header.add(new Label(""));
		}
		header.add(new Label(tag.getDayOfMonth() + ""));
		header.addClassName("jocalendar-dayitem-header");
		add(header);
		this.addClassName("jocalendar-border");

	}

	public void addEntry(CalendarEntry entry)
	{
		entries.add(entry);
		HorizontalLayout layout = new HorizontalLayout();
		layout.add(entry.getDescription());
		add(layout);
	}
}
