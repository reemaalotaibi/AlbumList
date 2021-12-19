
package gallery;

import java.io.*;
import java.util.*;

public class AlbumList {

    static ArrayList<Album> ListofAlbums = new ArrayList<Album>();
    // add the new album in the list .
    public void createAlbum(String t) {
        Album nA = new Album();
        nA.setTitle(t);
        ListofAlbums.add(nA);

    }

    public void addPhoto(Photo pho, String T) {
        for (Album r : ListofAlbums) {
            if (r.getTitle().equals(T)) {
                r.getPhotoalbum().add(pho);
            }
        }

    }

    public ArrayList<String> displayAlbumsNames() {
        ArrayList<String> arlist = new ArrayList<String>();
        for (Album r : ListofAlbums) {
            arlist.add(r.getTitle());
        }
        return arlist;
    }
    
    public String displayAlbumLists() {
        String test = "";
        for (Album r : ListofAlbums) {
            test += r.toString() + "\n";
        }
        return test;
    }
    
     
    public void saveAlbum(String fileName1) {
        try {

            File myFile = new File(fileName1);
            PrintWriter pw = new PrintWriter(myFile);

               pw.print(displayAlbumLists());
            
            pw.close();
        } catch (IOException e) {
            System.out.println("File isn't found");
        }
    }

    public void ReadFromFile(String fileName1) {

        try {
            File myFile = new File(fileName1);
            Scanner in = new Scanner(myFile);
            String tit = "";
            while (in.hasNext()) {

                String test = in.next();
                if (test.equals("Album")) {
                    tit = in.next();
                    createAlbum(tit);

                } else {
                    String fileName = test;
                    int year = in.nextInt();
                    String tagged = in.next();
                    Photo ph = new Photo(fileName, year, tagged);
                    addPhoto(ph, tit);
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File isn't found");
        }
    }

}

