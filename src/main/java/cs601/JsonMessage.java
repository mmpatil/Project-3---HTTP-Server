package cs601;

public class JsonMessage {
	
	private String token;
	private String channel;
	private String text;
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getToken() {
		return token;
	}
	
	public String getChannel() {
		return channel;
	}
	
	public String getText() {
		return text;
	}
}
