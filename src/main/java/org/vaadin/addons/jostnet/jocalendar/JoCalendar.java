package org.vaadin.addons.jostnet.jocalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.vaadin.addons.jostnet.jocalendar.data.CalendarEntry;
import org.vaadin.addons.jostnet.jocalendar.data.DayItem;
import org.vaadin.addons.jostnet.jocalendar.data.TagHeader;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@Tag("JoCalendar")
@CssImport("./css/style.css")
public class JoCalendar extends VerticalLayout
{

	private LocalDate datum;

	private static final long serialVersionUID = -295074448668050244L;

	public JoCalendar(LocalDate datum)
	{
		this.datum = datum;
		add(createToolbar());
		add(createMonth());
	}

	private FlexLayout createToolbar()
	{
		FlexLayout toolbar = new FlexLayout();

		HorizontalLayout left = new HorizontalLayout();
		left.setSpacing(false);
		left.setMargin(false);

		Button b1 = new Button("<<");
		left.add(b1);

		Button b2 = new Button("<");
		left.add(b2);

		Button heute = new Button("Heute");
		left.add(heute);

		Button b3 = new Button(">");
		left.add(b3);

		Button b4 = new Button(">>");
		left.add(b4);

		toolbar.add(left);

		Div center = new Div();
		center.addClassName("jocalendar-header-main");
		center.add("März 2023");

		toolbar.add(center);

		HorizontalLayout right = new HorizontalLayout();
		right.setSpacing(false);
		right.setMargin(false);

		Button monat = new Button("Monat");
		right.add(monat);

		Button woche = new Button("Woche");
		right.add(woche);

		Button tag = new Button("Tag");
		right.add(tag);

		Button terminuebersicht = new Button("Terminübersicht");
		right.add(terminuebersicht);

		toolbar.add(right);

		toolbar.setWidthFull();
		toolbar.setFlexDirection(FlexDirection.ROW);
		toolbar.setJustifyContentMode(JustifyContentMode.BETWEEN);

		return toolbar;
	}

	private FlexLayout createMonth()
	{
		FlexLayout month = new FlexLayout();
		month.setFlexDirection(FlexDirection.COLUMN);
		month.setHeightFull();

		FlexLayout days = new FlexLayout();
		days.setWidthFull();
		
		days.add(new TagHeader("Mo"));
		days.add(new TagHeader("Di"));
		days.add(new TagHeader("Mi"));
		days.add(new TagHeader("Do"));
		days.add(new TagHeader("Fr"));
		days.add(new TagHeader("Sa"));
		days.add(new TagHeader("So"));
		days.setFlexDirection(FlexDirection.ROW);
		days.setJustifyContentMode(JustifyContentMode.EVENLY);
		month.add(days);

		LocalDate tmp = datum.withDayOfMonth(1).with(DayOfWeek.MONDAY);

		LocalDate end = datum.plus(1, ChronoUnit.MONTHS).withDayOfMonth(1);
		while (tmp.isBefore(end))
		{
			FlexLayout d1 = new FlexLayout();
			d1.setWidthFull();
			d1.setHeightFull();
			for (int i = 0; i < 7; i++)
			{
				DayItem dayItem = new DayItem(tmp);
				if (tmp.equals(LocalDate.now()))
				{
					CalendarEntry ce = new CalendarEntry();
					ce.setDate(LocalDateTime.now());
					ce.setDescription("Heute");
					dayItem.addEntry(ce);
				}
				d1.add(dayItem);
				tmp = tmp.plus(1, ChronoUnit.DAYS);
			}
			d1.setFlexDirection(FlexDirection.ROW);
			d1.setJustifyContentMode(JustifyContentMode.EVENLY);
			month.add(d1);
		}
		return month;
	}
}
