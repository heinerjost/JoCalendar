package org.vaadin.addons.jostnet.jocalendar.data;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

public class TagHeader extends FlexLayout
{
	private static final long serialVersionUID = 1L;

	public TagHeader(String tag)
	{
		Div div = new Div();
		div.addClassName("jocalendar-month-header");
		div.setText(tag);
		add(div);
		setFlexDirection(FlexDirection.ROW);
		setJustifyContentMode(JustifyContentMode.CENTER);
		setWidthFull();
		addClassName("jocalendar-border");

	}

}
