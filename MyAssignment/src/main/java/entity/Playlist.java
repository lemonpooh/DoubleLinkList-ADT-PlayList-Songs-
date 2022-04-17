 
package entity;  
import adt.DoublyLinkedList; 
import adt.DoublyLinkedList_Interface;
import adt.DoublyNode; 
import java.util.Scanner; 

/** 
 * @author 
 */
public class Playlist {
        private String PlayListName; 
        int size=0;
        private int MAXLenght=6; 
        private DoublyLinkedList_Interface<Song> playlistsongs;
        private Song nowPlaying=null;
 
    public Playlist(String PlayListName) {
        this.PlayListName = PlayListName; 
        playlistsongs = new DoublyLinkedList();
    }  
    /***********/
    public void setPlayListName(String PlayListName) {
        this.PlayListName = PlayListName;
    } 
    public void setPlaylistsongs(DoublyLinkedList<Song> playlistsongs) {
        this.playlistsongs = playlistsongs;
    } 
    public void setNowPlaying(Song nowPlaying) {
        this.nowPlaying = nowPlaying;
    }
    
    /***********/
    public DoublyNode<Song> getPlaylistObject(int index) {
        return playlistsongs.getNode(index); 
    }  
    public DoublyLinkedList_Interface<Song> getPlaylistsongs() { 
        return (DoublyLinkedList<Song>) playlistsongs;
    }
    public String getPlayListName() {
        return PlayListName;
    } 
    public int getMAXLenght() {
        return MAXLenght;
    }  
    
    //   *********  displaySong   ******
    public void displaySong(){ 
        if(playlistsongs.size()>0){ 
           System.out.println("");
            System.out.println("*********  Playlist in Forward Direction.  **********"); 
            if(playlistsongs.isEmpty())
                System.out.println("The playlist is empty!");
            else{
                int i=1;
                System.out.println(playlistsongs);
            System.out.println("*****************************************************"); 
            }  
        }else
            System.out.println("Playlist is empty"); 
    }
    
//**************************  Add song  ****************************************    
 
    public boolean addLast(Song node){ //Add as last song
        if(playlistsongs.size()>=MAXLenght){
            System.out.println("The playlist is full!");return false;
        }else{
            if(playlistsongs.contain(node)){
            System.out.println("The song is in the playlist. Choose another song?");
            return false;
            } else{
                playlistsongs.addAtFirst(node);
                System.out.println("The song is added");
                return true;
            } 
        }   
    } 
    public boolean addFirst(Song node){ //Add as first song
        if(playlistsongs.size()>=MAXLenght){
            System.out.println("The playlist is full!");return false;
        }else{
            if(playlistsongs.contain(node)){ 
                System.out.println("The song is in the playlist. Choose another song?"); return false;
            } else{
                System.out.println("The song is added");
                playlistsongs.addAtFirst(node);return true;
            } 
        }  
    }
    
    public   boolean addAnyPlace( Song s, int index){ //Add at any position
        if(playlistsongs.contain(s)){
            System.out.println("The song is in the playlist. Choose another song?");
            return false;
        } else{
            try{
                playlistsongs.add(s, index-1);return true;
            }catch(IndexOutOfBoundsException e){
                System.out.println("Pleace enter correct index number.");
            }  
           return true; 
        }    
    }
     
//****************************** start song ************************************   
    
        public  void printMenu()
        {
            System.out.println("\n....🎵 🎵 🎵 🎵 🎵 🎵 🎵..🎵 🎵 .."
                            + "\n1. Play next"
                            + "\n2. Play previous"
                            + "\n3. Replay"
                            + "\n4. Display menu"
                            + "\n5. Display playlist in reverse order"
                            + "\n6. Display playlist in ascending order"
                            + "\n0. Quit"
                            + "\n....🎵 🎵 🎵 🎵 🎵 🎵 🎵..🎵 🎵 🎵 ..");
        }
        
        public void Play(Scanner sc) { //Start the palylist
                boolean quit = false;  
//                System.out.println("Song list: "+playlistsongs.size());
                if(playlistsongs == null){
                    System.out.println("No songs in the Playlist");
                } else{
                    if(playlistsongs.hasNext()){
                        nowPlaying=playlistsongs.next();
                        System.out.println("");
                        System.out.println( "\nNow playing - "+ nowPlaying.toString()
                                            + " .... .."
                                            +"\n(press 4 to display menu)"); 
                    } 
                }  
                while(!quit){   //user select menu to control the starting playlist
                switch (sc.nextInt()){
                    case 0 :
                        System.out.println("Playlist Complete.");
                        quit = true;
                        break;
                    case 1:   //play next song
                        playNext(); 
                        break;
                    case 2:   //play previous song
                        playPreviousSong();
                        break; 
                    case 3 :  //replay song
                        replaySong();
                        break;
                    case 4:  //print menu
                        printMenu(); 
                        break;
                    case 5:  //display playlist in reverse order
                        Display_in_Reverse_order();
                        break; 
                    case 6:  //display playlist in ascending order
                        displaySong();
                        break;
                    default: quit=true;
                }
            }
        }
        
        public boolean findSong(Song node){ // find Song from playlist
            
            if(playlistsongs.contain(node)){
                System.out.println(node+" is in the playlist!");
                return true;
            }else{
               System.out.println("The song is not in the playlist.");
                return false; 
            } 
        }   
        public void removeSong(int index){ // remove song by index
            if(playlistsongs.isEmpty())
            System.out.println("The playlist is empty!");
            else{ 
                try{
                    playlistsongs.deleteByIndex(index-1);
                    System.out.println("The song has removed!");
                    displaySong();
                }catch(IndexOutOfBoundsException e){
                    System.out.println(e);
                } 
            } 
        }  
        public void removeSong(Song node){ // remove song by Song Object
           if(playlistsongs.isEmpty())
            System.out.println("The playlist is empty!");
            else{  
                playlistsongs.delete(node);
                System.out.println(node+" has deleted.");
            } 
        } 
        public  void clearPlaylist( ){ // clear Playlist
            if(playlistsongs.isEmpty())
                System.out.println("The playlist is empty!");
            else{
                playlistsongs.clear();
                System.out.println("The playlist is clear");
                displaySong( );
            }    
        }
 
        public void playNext(){  // play next song
            if(playlistsongs.size()>0){
              if(playlistsongs.hasNext()){
                nowPlaying=playlistsongs.next();
                System.out.println("\n"
                        + "Now Playing "+nowPlaying.toString());
                }else{
                    System.out.println("");
                    System.out.println("We have reached the end of the playlist");
                }  
            }else
                System.out.println("Playlist is empty"); 
        }
    
    //
        public void skipNext( ){  // skip next song
            if(playlistsongs.size()>0){ 
               if(playlistsongs.hasNext()){
                int i=2;
                while(i>0){
                    nowPlaying=playlistsongs.next();i--;
                } 
                if(nowPlaying.equals(playlistsongs.getNode(0))){
                    nowPlaying=playlistsongs.next();
                }
                System.out.println("");
                System.out.println("Now Playing "+nowPlaying.toString());
                }else{
                    System.out.println("");
                    System.out.println("We have reached the end of the playlist");
                } 
            }else
                System.out.println("Playlist is empty"); 
        } 
    //
        public void replaySong(){   // replay the current song
            if(playlistsongs.size()>0){ 
                if(playlistsongs.isEmpty())
                System.out.println("The playlist is empty!");
                else{ 
                    System.out.println("Now Playing - "
                                        +this.nowPlaying
                                        + " .... .."
                                        +"\n(press 4 to display menu)");
                }
            }else
                System.out.println("Playlist is empty");  
        } 
    
    //
        public void playPreviousSong( ){  // play previous song
            if(playlistsongs.size()>0){ 
                if(playlistsongs.hasPrevious()){
                System.out.println("");
                System.out.println("Now Playing "+playlistsongs.previous().toString()
                                                 + " .... .."
                                                 +"\n(press 4 to display menu)");
                }else{
                    System.out.println("");
                    System.out.println("We have reached the start of the playlist"); 
                }
            }else
                System.out.println("Playlist is empty"); 
        } 

   
    //***********    Reverse    **********
    public void ReversePlaylist(){  //reverse the playlist song
        if(playlistsongs.size()>0){ 
            if(playlistsongs.isEmpty() )
                System.out.println("The playlist is empty!");
            else if(playlistsongs.size()==1)
            { 
                System.out.println("Add more song.");
            }
            else{
                System.out.println("Playlist has reversed");
                playlistsongs.reverse(true);  
                displaySong();
            }
        }else
            System.out.println("Playlist is empty"); 
    } 
    //
    public void Display_in_Reverse_order(){ //display the playlist in reverse order
        if(playlistsongs.size()>0){ 
            System.out.println("");
            System.out.println("*********  Display Playlist in Reverse Direction.  **********");
            if(playlistsongs.isEmpty())
                System.out.println("The playlist is empty!");
            else{       
                 playlistsongs.reverse(true); 
                 displaySong();
                 playlistsongs.reverse(true);
            } 
        }else
            System.out.println("Playlist is empty");  
    } 
     
    @Override
    public String toString() { 
            return "Player: " + PlayListName
                    + "\n"
                    + "Maximum Playlist-"+ MAXLenght 
                    + "\n"
                    + playlistsongs + "";  
    } 
}
