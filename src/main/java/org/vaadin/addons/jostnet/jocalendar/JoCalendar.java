package org.vaadin.addons.jostnet.jocalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

import org.vaadin.addons.jostnet.jocalendar.data.CalendarEntry;
import org.vaadin.addons.jostnet.jocalendar.data.CalendarSupplier;
import org.vaadin.addons.jostnet.jocalendar.data.DayItem;
import org.vaadin.addons.jostnet.jocalendar.data.TagHeader;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;

@Tag("JoCalendar")
@CssImport("./css/style.css")
public class JoCalendar extends Div
{

	private static final long serialVersionUID = -295074448668050244L;

	private LocalDate datum;

	private CalendarSupplier[] calendarSuppliers;

	private ViewType viewType;

	private Div actualPeriod = new Div();

	public JoCalendar(LocalDate datum, ViewType viewType,
			CalendarSupplier... calendarSuppliers)
	{
		this.datum = datum;
		add(createToolbar());
		add(createMonth());
		this.viewType = viewType;
		this.calendarSuppliers = calendarSuppliers;
		setActualPeriod();
	}

	private Div createToolbar()
	{
		Div toolbar = new Div();
		toolbar.addClassName("jocalendar-topheader");

		Div left = new Div();

		Button b1 = new Button("<<");
		b1.addClickListener(e ->
		{
			switch (viewType)
			{
				case MONTH:
					datum = datum.minus(1, ChronoUnit.YEARS);
					break;
			}
			setActualPeriod();
		});
		left.add(b1);

		Button b2 = new Button("<");
		b2.addClickListener(e ->
		{
			switch (viewType)
			{
				case MONTH:
					datum = datum.minus(1, ChronoUnit.MONTHS);
					break;
			}
			setActualPeriod();
		});
		left.add(b2);

		Button heute = new Button("Heute");
		heute.addClickListener(e ->
		{
			datum = LocalDate.now();
			setActualPeriod();
		});
		left.add(heute);

		Button b3 = new Button(">");
		b3.addClickListener(e ->
		{
			switch (viewType)
			{
				case MONTH:
					datum = datum.plus(1, ChronoUnit.MONTHS);
					break;
			}
			setActualPeriod();
		});
		left.add(b3);

		Button b4 = new Button(">>");
		b4.addClickListener(e ->
		{
			switch (viewType)
			{
				case MONTH:
					datum = datum.plus(1, ChronoUnit.YEARS);
					break;
			}
			setActualPeriod();
		});
		left.add(b4);

		toolbar.add(left);

		actualPeriod.addClassName("jocalendar-header-main");
		toolbar.add(actualPeriod);

		Div right = new Div();

		Button monat = new Button("Monat");
		monat.addClickListener(e ->
		{
			viewType = ViewType.MONTH;
			setActualPeriod();
		});
		right.add(monat);

		Button woche = new Button("Woche");
		woche.addClickListener(e ->
		{
			viewType = ViewType.WEEK;
			setActualPeriod();
		});
		right.add(woche);

		Button tag = new Button("Tag");
		tag.addClickListener(e ->
		{
			viewType = ViewType.DAY;
			setActualPeriod();
		});
		right.add(tag);

		Button terminuebersicht = new Button("Terminübersicht");
		terminuebersicht.addClickListener(e ->
		{
			viewType = ViewType.LIST;
			setActualPeriod();
		});
		right.add(terminuebersicht);

		toolbar.add(right);

		return toolbar;
	}

	private Div createMonth()
	{
		Div month = new Div();
		month.addClassName("jocalendar-month");

		Div days = new Div();
		days.addClassName("jocalendar-month-header");

		days.add(new TagHeader("Mo"));
		days.add(new TagHeader("Di"));
		days.add(new TagHeader("Mi"));
		days.add(new TagHeader("Do"));
		days.add(new TagHeader("Fr"));
		days.add(new TagHeader("Sa"));
		days.add(new TagHeader("So"));
		month.add(days);

		LocalDate tmp = datum.withDayOfMonth(1).with(DayOfWeek.MONDAY);

		LocalDate end = datum.plus(1, ChronoUnit.MONTHS).withDayOfMonth(1);
		while (tmp.isBefore(end))
		{
			Div d1 = new Div();
			d1.addClassName("jocalendar-month-table");
			for (int i = 0; i < 7; i++)
			{
				DayItem dayItem = new DayItem(tmp);
				if (tmp.equals(LocalDate.now()))
				{
					CalendarEntry ce = new CalendarEntry();
					ce.setDate(LocalDateTime.now());
					ce.setDescription("Heute blablubb blubberup");
					dayItem.addEntry(ce);
					CalendarEntry ce2 = new CalendarEntry();
					ce2.setDate(LocalDateTime.now());
					ce2.setDescription("Heute 2");
					dayItem.addEntry(ce2);
					CalendarEntry ce3 = new CalendarEntry();
					ce3.setDate(LocalDateTime.now());
					ce3.setDescription("Heute 2");
					dayItem.addEntry(ce3);
				}
				d1.add(dayItem);
				tmp = tmp.plus(1, ChronoUnit.DAYS);
			}
//			d1.setFlexDirection(FlexDirection.ROW);
			month.add(d1);
		}
		return month;
	}

	private void setActualPeriod()
	{
		switch (viewType)
		{
			case MONTH:
				actualPeriod
						.setText(DateTimeFormatter.ofPattern("MMMM yyyy").format(datum));
				break;
			case WEEK:
				LocalDate start = datum.with(DayOfWeek.MONDAY);
				LocalDate end = datum.with(DayOfWeek.SUNDAY);
				actualPeriod.setText(
						DateTimeFormatter.ofPattern("dd.MM.yyyy").format(start) + " - "
								+ DateTimeFormatter.ofPattern("dd.MM.yyyy").format(end));
		}

	}
}
