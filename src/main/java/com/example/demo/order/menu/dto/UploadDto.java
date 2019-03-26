package com.example.demo.order.menu.dto;


public class UploadDto {
	private boolean resize = false;
	private int width;
	private int height;
	private String purpose;

	public boolean isResize() {
		return resize;
	}
	public void setResize(boolean resize) {
		this.resize = resize;
	}

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
}
