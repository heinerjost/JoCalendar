package org.vaadin.addons.jostnet.jocalendar.data;

import com.vaadin.flow.component.html.Div;

public class TagHeader extends Div
{
	private static final long serialVersionUID = 1L;

	public TagHeader(String tag)
	{
		addClassName("jocalendar-border");

		Div div2 = new Div();
		div2.addClassName("jocalendar-center");
		div2.setText(tag);
		add(div2);
	}

}
