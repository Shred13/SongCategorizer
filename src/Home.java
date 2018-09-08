//20053370 
//Shreyansh Anand
//Robin Dawes
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Home {


	static ArrayList <String> errors = new ArrayList <String>();
	//holds all the errors
	static ArrayList<Songs> songArray = new ArrayList<Songs>();
	//holds all the song values
	static ArrayList<Categories> category = new ArrayList<Categories>();
	//this holds all the category values
	static ArrayList<String> everything = new ArrayList <String>();
	//this holds all the good data
	static ArrayList<String> statsCategory = new ArrayList<String>();
	//use of ArrayLists that will dynamically keep all the data
	static ArrayList<Categories>cateCount = new ArrayList<Categories>();
	public static void main(String [] args) {
		try {
			FileReader fr = new FileReader("songs.txt");
			//this creates a FileReader Object which opens up the file
			BufferedReader br = new BufferedReader(fr);
			//this creates a BufferedReader object that uses fr as a reference
			//and then reads whats in it.
			
			FileReader cfr = new FileReader("Categories.txt");
			BufferedReader cbr = new BufferedReader(cfr);
			String cstr;
			while ((cstr=cbr.readLine())!=null) {
				Categories categories = new Categories(cstr);
				category.add(categories);
			}
			
			String str;
			while((str=br.readLine())!=null) {
				String splitted[] = str.split(",");
				if (splitted.length<7) {
					errors.add(str+" not enough aspect values");
				}
				else if(splitted.length>7) {
					try {
						int checkForComma = Integer.parseInt(splitted[1]);
						errors.add(str+" There are too many category aspects");
					}catch(NumberFormatException e) {
						errors.add(str + " There is a comma in the title");
					}
					//checking for errors above ^^
				}
				else {
					try {
						int values[] =  new int[splitted.length]; 
						for(int i =1; i<splitted.length; i++) {
							if(Integer.parseInt(splitted[i])>10 || Integer.parseInt(splitted[i])<0) {
								errors.add(str+": Aspect value is out of bounds");
								//should print out all the values that are out of bounds in the errors list
								break;
							}
							else {
							 values[i] = Integer.parseInt(splitted[i]);
							}
						}
						Songs song = new Songs(str);
						songArray.add(song);
					}catch(NumberFormatException e) {
						errors.add(str + " There is non-integer aspect");
					}
				}
			}
			br.close();
			cbr.close();
		}catch (IOException e) {
			System.out.println("File does not exist");
		}
		for(int i=0; i<songArray.size(); i++) {
			int shortest=Integer.MAX_VALUE;
			String idName="";
			for(int j = 0; j<category.size(); j++) {
				int counter=0;
				Categories shortCategory = category.get(j);
				for(int k =0; k<6; k++) {
					int distance = (int)Math.pow((category.get(j).getValues(category.get(j)))[k]-(songArray.get(i).getValues(songArray.get(i))[k]), 2);
					counter+=distance;
					//gets the distance value and then adds it to the counter in order to find the shortest one
				}
				if(shortest>counter) {
					shortest = counter;
					idName = category.get(j).getIdName(category.get(j));
					shortCategory = category.get(j);
					//compares to see if shortest is bigger than counter and then makes it equal to that, it then finds 
					//which category that distance belongs too
				}
				else if(shortest == counter) {
					if(shortCategory.getIdValue(shortCategory)>category.get(j).getIdValue(category.get(j))) {
						idName = category.get(j).getIdName(category.get(j));
						cateCount.add(shortCategory);
					}
				else {
						idName = shortCategory.getIdName(shortCategory);
						//error is here, for some reason shortCategory first one is the only that is counting
						shortCategory.addCount(1);
						cateCount.add(shortCategory);
					}	
					//the above shortCategory will save the shortest distance and then will take it to idName which is then outputted
				}
			}
			everything.add(songArray.get(i).getIdName(songArray.get(i))+":"+idName);
			//adding all the good song values into the songArray where it is centralized
		} //end of the str while loop
		System.out.println(cateCount.size());
		fileWriter();
	}//end of main
	
	public static void fileWriter() {
		try {
			FileWriter fw = new FileWriter("song_category.txt");
			PrintWriter pw = new PrintWriter(fw);
			for(int i = 0; i<everything.size(); i++) {
				pw.println(everything.get(i));
				//print writer
			}
			FileWriter efw = new FileWriter ("errors.txt") ;
			PrintWriter epw = new PrintWriter(efw);
			for(int i = 0; i<errors.size(); i++) {
				epw.println(errors.get(i));
				//error print writer
			}
			//above creates the files and writes it out
			FileWriter CateStats = new FileWriter("category_stat.txt");
			PrintWriter CategoryStats =new PrintWriter(CateStats);
			for(int i = 0; i<category.size(); i++) {
				CategoryStats.println(category.get(i).getIdName(category.get(i))+":" + category.get(i).finalCount(category.get(i)));
			}
			pw.close();
			epw.close();
			CategoryStats.close();
		}catch (IOException e) {
			System.out.println("ERROR");
		}
	
		try {
			FileReader cfr = new FileReader("song_category.txt");
			BufferedReader cbr = new BufferedReader(cfr);		
			String cstr;
			while ((cstr=cbr.readLine())!=null) {
				String valuess [] = cstr.split(":");
				//for(int i=0;  i)
				//was going to make a for loop to see how much each time a category was used in the song_category file, but not enough time to implement it :/
			}
		}catch(IOException e) {
			
		}
	}
}//end of class
