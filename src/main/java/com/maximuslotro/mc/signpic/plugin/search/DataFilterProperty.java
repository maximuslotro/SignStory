package com.maximuslotro.mc.signpic.plugin.search;

import javax.annotation.Nullable;

import com.maximuslotro.mc.signpic.entry.EntryId;
import com.maximuslotro.mc.signpic.entry.content.ContentId;
import com.maximuslotro.mc.signpic.plugin.SignData;

public interface DataFilterProperty<E> {

	@Nullable
	E get(SignData data, EntryId entry, ContentId content);
}
