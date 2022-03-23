package com.maximuslotro.mc.signpic.plugin.search;

import javax.annotation.Nullable;

import com.maximuslotro.mc.bnnwidget.util.NotifyCollections.IModCount;
import com.maximuslotro.mc.signpic.plugin.SignData;

public interface Searchable {

	void filter(@Nullable FilterExpression expression);

	IModCount<SignData> getNow();

	boolean isSearching();
}
