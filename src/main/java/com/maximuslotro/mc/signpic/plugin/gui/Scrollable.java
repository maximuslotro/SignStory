package com.maximuslotro.mc.signpic.plugin.gui;

import javax.annotation.Nullable;

import com.maximuslotro.mc.bnnwidget.position.Area;

public interface Scrollable {

	void scroll(final float scroll, final @Nullable GuiManager manager, final @Nullable Area position);

	void scrollTo(final float to, final @Nullable GuiManager manager, final @Nullable Area position);

	float getNowHeight();

	float getScrollableHeight();

	float getAllHeight();
}
