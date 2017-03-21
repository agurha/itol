package com.wilutions.fx.acpl;

import java.util.List;

import com.wilutions.itol.db.Suggest;

import javafx.scene.Node;

public class AutoCompletionBinding<T> {
	
	private AutoCompletionControl<T> control;
	private String recentCaption;
	private String suggestionsCaption;
	private List<T> recentItems;
	private Suggest<T> suggest;
	private ExtractImage<T> extractImage;
	private boolean lockChangeEvent;
	
	public String getRecentCaption() {
		return recentCaption;
	}
	public void setRecentCaption(String recentCaption) {
		this.recentCaption = recentCaption;
	}
	public String getSuggestionsCaption() {
		return suggestionsCaption;
	}
	public void setSuggestionsCaption(String suggestionsCaption) {
		this.suggestionsCaption = suggestionsCaption;
	}
	public List<T> getRecentItems() {
		return recentItems;
	}

	/**
	 * Set list of recent items.
	 * @param recentItems
	 */
	public void setRecentItems(List<T> recentItems) {
		this.recentItems = recentItems;
	}
	
	/**
	 * Do not show recent items.
	 */
	public void disableRecentItems() {
		this.recentItems = null;
	}
	
	public Suggest<T> getSuggest() {
		return suggest;
	}
	public void setSuggest(Suggest<T> suggest) {
		this.suggest = suggest;
	}
	public ExtractImage<T> getExtractImage() {
		return extractImage;
	}
	public void setExtractImage(ExtractImage<T> extractImage) {
		this.extractImage = extractImage;
	}
	public boolean isLockChangeEvent() {
		return lockChangeEvent;
	}
	public void setLockChangeEvent(boolean lockChangeEvent) {
		this.lockChangeEvent = lockChangeEvent;
	}
	
	AutoCompletionControl<T> getControl() {
		return control;
	}
	void setControl(AutoCompletionControl<T> control) {
		this.control = control;
	}
	
	public void select(T item) {
		boolean lock = isLockChangeEvent();
		setLockChangeEvent(true);
		control.select(item);
		setLockChangeEvent(lock);
	}
	public T getSelectedItem() {
		return control.getSelectedItem();
	}
	public Node getNode() {
		return control.getNode();
	}
	public void setEditable(boolean en) {
		control.setEditable(en);
	}
	public boolean isEditable() {
		return control.isEditable();
	}
	public String getEditText() {
		return control.getEditText();
	}
}

