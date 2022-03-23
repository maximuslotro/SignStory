package com.maximuslotro.mc.signpic.plugin.search;

import com.maximuslotro.mc.signpic.entry.EntryId;
import com.maximuslotro.mc.signpic.entry.content.ContentId;
import com.maximuslotro.mc.signpic.plugin.SignData;

public class FalseFilterElement implements DataFilterElement {
	public static final FalseFilterElement INSTANCE = new FalseFilterElement();

	@Override
	public boolean filter(final SignData data, final EntryId entry, final ContentId content) {
		return false;
	}

}
