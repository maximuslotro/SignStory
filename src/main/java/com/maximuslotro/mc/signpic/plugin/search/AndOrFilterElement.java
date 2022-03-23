package com.maximuslotro.mc.signpic.plugin.search;

import com.maximuslotro.mc.signpic.attr.AttrReaders;
import com.maximuslotro.mc.signpic.entry.EntryId;
import com.maximuslotro.mc.signpic.entry.content.ContentId;
import com.maximuslotro.mc.signpic.plugin.SignData;

public interface AndOrFilterElement extends FilterElement {

	boolean filter(final SignData data, final EntryId entry, final ContentId content, final AttrReaders attr);
}
