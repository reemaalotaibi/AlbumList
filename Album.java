
package gallery;

import java.util.ArrayList;


public class Album {
    //unique
    private String title; 
     ArrayList<Photo> photoalbum = new ArrayList<Photo>();

     //Setter and Getter for the private attributes
       public String getTitle() {
        return title;
    }
    public ArrayList<Photo> getPhotoalbum() {
        return photoalbum;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setList(ArrayList<Photo> list) {
        this.photoalbum = list;
    }

public String print(){
    String rr = "";
    for(Photo r : photoalbum) 
                  rr += r.getFileName() + " " + r.getYear() + " " + r.getTagged()+ "\n";
    return rr;
}   
    // toString method used to print invormation  of the class .
    @Override
    public String toString() {
    return  " Album " + title + "\n" + print() ;
    }
    
    
}

