package com.maximuslotro.mc.signpic.plugin.search;

import com.maximuslotro.mc.signpic.attr.AttrReaders;

public interface AttrFilterElement extends FilterElement {

	boolean filter(AttrReaders attr);
}
