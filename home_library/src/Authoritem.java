
public class Authoritem {	  
	  int id; 
	  String name; 	
	  
	  public Authoritem( int id, String name ) 
	  { 
	  this.id = id; 
	  this.name = name; 
	  } 	
	  
	  @Override
	  public String toString() 
	  { 
	  return name; 
	  } 
	  public String getName(){
	  return name;
	  }
	  public int  getId() {
	  	return id;
	  }
	  }


