package com.maximuslotro.mc.signpic.plugin.search;

import com.maximuslotro.mc.signpic.entry.EntryId;
import com.maximuslotro.mc.signpic.entry.content.ContentId;
import com.maximuslotro.mc.signpic.plugin.SignData;

public interface DataFilterElement extends FilterElement {
	boolean filter(final SignData data, EntryId entry, ContentId content);
}
