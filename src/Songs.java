
public class Songs {
	int[]values; 
	String id;
	
	public Songs(String id) {
		setIdName(id);
		setValues(id);
	}
	
	public void setIdName(String id) {
		this.id=id;
	}
	
	public void setValues(String id) {
		String strValues[]=id.split(",");
		values = new int[strValues.length];
		for(int i =1; i<strValues.length;i++) {
			int value = Integer.parseInt(strValues[i]);
			values[i]=value;
		}
	}
	
	public String getIdName(Songs song) {
		String strValues[] = id.split(",");
		return strValues[0];
	}
	
	public int[] getValues(Songs song) {
		return values;
	}
}
