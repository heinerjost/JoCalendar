package org.vaadin.addons.jostnet.jocalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.vaadin.addons.jostnet.jocalendar.data.CalendarSupplier;
import org.vaadin.addons.jostnet.jocalendar.data.DataBlock;
import org.vaadin.addons.jostnet.jocalendar.views.MonthView;
import org.vaadin.addons.jostnet.jocalendar.views.WeekView;

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
		this.viewType = viewType;
		this.calendarSuppliers = calendarSuppliers;
		createView();
		this.addClassName("jocalendar");
	}

	private void createView()
	{
		this.removeAll();
		DataBlock dataBlock = new DataBlock(datum, viewType, calendarSuppliers);
		switch (viewType)
		{
			case MONTH:
				actualPeriod
						.setText(DateTimeFormatter.ofPattern("MMMM yyyy").format(datum));
				break;
			case WEEK:
				actualPeriod.setText(
						DateTimeFormatter.ofPattern("dd.MM.yyyy").format(getFrom()) + " - "
								+ DateTimeFormatter.ofPattern("dd.MM.yyyy").format(getTo()));
		}
		add(createToolbar());
		switch (viewType)
		{
			case MONTH:
				add(new MonthView(getFrom(), getTo(), dataBlock));
				break;
			case WEEK:
				add(new WeekView(getFrom(), getTo(), dataBlock));
				break;
		}
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
				case WEEK:
					datum = datum.minus(1, ChronoUnit.YEARS);
					break;
			}
			createView();
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
				case WEEK:
					datum = datum.minus(1, ChronoUnit.MONTHS);
					break;
			}
			createView();
		});
		left.add(b2);

		Button heute = new Button("Heute");
		heute.addClickListener(e ->
		{
			datum = LocalDate.now();
			createView();
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
				case WEEK:
					datum = datum.plus(1, ChronoUnit.MONTHS);
					break;
			}
			createView();
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
				case WEEK:
					datum = datum.plus(1, ChronoUnit.YEARS);
					break;
			}
			createView();
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
			createView();
		});
		right.add(monat);

		Button woche = new Button("Woche");
		woche.addClickListener(e ->
		{
			viewType = ViewType.WEEK;
			createView();
		});
		right.add(woche);

		Button tag = new Button("Tag");
		tag.addClickListener(e ->
		{
			viewType = ViewType.DAY;
			createView();
		});
		right.add(tag);

		Button terminuebersicht = new Button("Terminübersicht");
		terminuebersicht.addClickListener(e ->
		{
			viewType = ViewType.LIST;
			createView();
		});
		right.add(terminuebersicht);

		toolbar.add(right);

		return toolbar;
	}

	private LocalDate getFrom()
	{
		switch (viewType)
		{
			case MONTH:
				return datum.withDayOfMonth(1).with(DayOfWeek.MONDAY);
			case WEEK:
				return datum.with(DayOfWeek.MONDAY);
			case DAY:
				return datum;
			case LIST:
				return datum.withDayOfYear(1);
		}
		return null;
	}

	private LocalDate getTo()
	{
		switch (viewType)
		{
			case MONTH:
				return datum.plus(1, ChronoUnit.MONTHS).withDayOfMonth(1)
						.minus(1, ChronoUnit.DAYS).with(DayOfWeek.SUNDAY);
			case WEEK:
				return datum.with(DayOfWeek.SUNDAY);
			case DAY:
				return datum;
			case LIST:
				return datum.withDayOfYear(datum.isLeapYear() ? 366 : 365);
		}
		return null;
	}

}
