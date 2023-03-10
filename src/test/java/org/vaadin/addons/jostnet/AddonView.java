package org.vaadin.addons.jostnet;

import java.time.LocalDate;

import org.vaadin.addons.jostnet.jocalendar.JoCalendar;
import org.vaadin.addons.jostnet.jocalendar.ViewType;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("")
public class AddonView extends Div
{

	private static final long serialVersionUID = 1L;

	public AddonView()
	{
		JoCalendar theAddon = new JoCalendar(LocalDate.now(), ViewType.MONTH);
		theAddon.setId("theAddon");
		add(theAddon);
	}
}
