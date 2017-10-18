package com.meession.market.exchange_record.entity;

public class ExchangeRecord {
	private long id;
	private String type;
	private long recordedObjectId;
	private long recorderId;
	private String recordContent;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getRecordedObjectId() {
		return recordedObjectId;
	}
	public void setRecordedObjectId(long recordedObjectId) {
		this.recordedObjectId = recordedObjectId;
	}
	public long getRecorderId() {
		return recorderId;
	}
	public void setRecorderId(long recorderId) {
		this.recorderId = recorderId;
	}
	public String getRecordContent() {
		return recordContent;
	}
	public void setRecordContent(String recordContent) {
		this.recordContent = recordContent;
	}
}
