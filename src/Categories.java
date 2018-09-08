
public class Categories {
	int[]values; 
	String id;
	 int counter;
	
	public Categories(String id) {
		setIdName(id);
		setValues(id);
	}
	
	public void setIdName(String id) {
		this.id=id;
	}
	
	public void addCount(int catego) {
		counter+=catego;
	}
	
	public int finalCount(Categories category) {
		return counter;
	}
	public void setValues(String id) {
		String strValues[]=id.split(",");
		values = new int[strValues.length];
				for(int i =1; i<strValues.length;i++) {
					int value = Integer.parseInt(strValues[i]);
					values[i]=value;
				}
	}
	
	public String getIdName(Categories category) {
		String strValues[]=id.split(",");
		return strValues[0];
	}
	
	public int[] getValues(Categories category) {
		return values;
	}
	public int getIdValue(Categories category) {
		String categoryId[] = id.split(",");
		int value = Integer.parseInt(categoryId[0]);
		return value;
	}
}
