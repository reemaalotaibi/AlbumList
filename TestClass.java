package Gallery;
public class TestClass {
    public static void main(String[] args) throws DuplicateException{
       AlbumList alblist = new AlbumList();
        alblist.addAlbum("High School Graduation");
        alblist.addAlbum("World Tours");
        alblist.addAlbum("Family");
        //alblist.addAlbum("Family");
        
        
        
        
        
        alblist.getAlbum("World Tours").addPhoto(new Photo("Dubai", 2019, "City Walk"));
        alblist.getAlbum("World Tours").addPhoto(new Photo("Dubai", 2019, "Dubai Fountain"));
        alblist.getAlbum("Family").addPhoto(new Photo("Mom", 2012, "Eid"));
        alblist.getAlbum("High School Graduation").addPhoto(new Photo ("Ceremony", 2018, "RIS"));
      
    }//end of main method
    
    
}// end main

