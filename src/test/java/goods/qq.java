package goods;

public class qq {
	private String name;
	private String value;
	
	public qq(){
		this.name = null;
		this.value = null;
	}
	
	public qq(String name, String value){
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}	
}
