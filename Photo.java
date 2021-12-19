
package gallery;


public class Photo {
    
    //Class Photo with 3 private attributes.
    private String filename;
    private int year;
    private String tag;
    // consractor method.
    public Photo(String fN, int Yea, String tagg){
        filename = fN;
        year = Yea;
        tag = tagg;
    }
    //setter and getter for the 3 attributes
    public String getFileName() {
        return filename;
    }
    public void setFileName(String Fn) {
        this.filename = Fn;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int Ye) {
        this.year = Ye;
    }

    public String getTagged() {
        return tag;
    }
    public void setTagged(String Tagge) {
        this.tag = Tagge;
    }
    
    // ToString method to print values of class .
    @Override
    public String toString() {
        return "Photo{" + "fileName=" + filename + ", year=" + year + ", tagged=" + tag + '}';
    }
    
    
}