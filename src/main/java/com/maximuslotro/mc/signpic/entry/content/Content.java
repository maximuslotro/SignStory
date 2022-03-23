package com.maximuslotro.mc.signpic.entry.content;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.maximuslotro.mc.signpic.ILoadCancelable;
import com.maximuslotro.mc.signpic.entry.ICollectable;
import com.maximuslotro.mc.signpic.entry.IInitable;
import com.maximuslotro.mc.signpic.entry.content.meta.ContentCache;
import com.maximuslotro.mc.signpic.entry.content.meta.ContentMeta;
import com.maximuslotro.mc.signpic.image.Image;
import com.maximuslotro.mc.signpic.image.RemoteImage;
import com.maximuslotro.mc.signpic.image.ResourceImage;
import com.maximuslotro.mc.signpic.state.State;
import com.maximuslotro.mc.signpic.state.StateType;

public class Content implements IInitable, ICollectable, ILoadCancelable {
	public final @Nonnull ContentId id;
	public final @Nonnull ContentMeta meta;
	public final @Nonnull State state;
	public @Nonnull Image image;
	public @Nullable String imagemeta;
	private boolean dirty;

	public Content(final @Nonnull ContentId id) {
		this.id = id;
		final String url = id.getURI();
		final String hash = ContentLocation.hash(url);
		this.meta = new ContentMeta(ContentLocation.metaLocation(hash));
		this.meta.setURL(url);
		this.meta.setMetaID(hash);
		this.state = new State();
		this.state.setName(id.getID());
		if (id.isResource())
			this.image = new ResourceImage(this);
		else
			this.image = new RemoteImage(this);
	}

	@Override
	public void onInit() {
		this.state.setType(StateType.INITALIZED);
		this.image.onInit();
	}

	@Override
	public void onCollect() {
		this.image.onCollect();
	}

	public boolean shouldCollect() {
		return this.dirty;
	}

	@Override
	public void cancel() {
		this.image.cancel();
	}

	public void markDirty() {
		this.dirty = true;
	}

	public void markDirtyWithCache() {
		this.meta.setTryCount(0);
		final String id = this.meta.getCacheID();
		if (id!=null) {
			final ContentCache cachemeta = new ContentCache(ContentLocation.cachemetaLocation(id));
			cachemeta.setDirty(true);
		}
		markDirty();
	}
}
