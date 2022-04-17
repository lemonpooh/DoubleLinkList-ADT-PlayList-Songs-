 
package client;
 
import adt.DoublyLinkedList; 
import adt.DoublyLinkedList_Interface;
import entity.Playlist; 
import entity.Song;
import java.util.InputMismatchException;
import java.util.Scanner;
 
/**
 *
 * @author 
 */
public class PlaylistDriver {
    public static void main(String[] args) {  
            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome to Karaoke!");    
            DoublyLinkedList_Interface<Playlist> playlist = new DoublyLinkedList(); 
            playlist.add(new Playlist("pooi")); //create playlist coolection with string name as param
            playlist.add(new Playlist("Amy"));
            playlist.add(new Playlist("Haiji"));
            playlist.add(new Playlist("Seol"));
            playlist.add(new Playlist("Miao"));
             
            boolean op1=true;   
            int index=1;
            do{
                
                System.out.println(playlist);
                System.out.println("Please select use playlist: ");
                try{
                    index=sc.nextInt();
                }catch(Exception e){
                    
                }
                if(index<0||index>playlist.size()){
                    break;
                }
                while(op1){  //SELECT KARAOKE MENU
                System.out.println("\n============================"
                        + "\n1. Add song"
                        + "\n2. Display playlist"
                        + "\n3. Start song"
                        + "\n4. Edit song"
                        + "\n5. Quit"
                        + "\nPlease select option: "); 
                int op2 = sc.nextInt();
                switch(op2){
                    case 1: 
                        addsong(playlist, index); //add song
                        break; 
                    case 2:
                        displayPlaylist(playlist, index); // display playlist
                        break;
                    case 3:
                        startsong(playlist, index); //start song
                        break;
                    case 4:   
                        editsong(playlist, index);  //modify the playlist
                        break; 
                    case 5:  
                        op1=false; //Quit
                        break;
                    default:
                        op1=false;
                        break;
                } 
            } 
                
                 
            }while(index>0||index<playlist.size());
             
} 
    //Start playing song
    private static void startsong(DoublyLinkedList_Interface<Playlist> playlist, int index){
        Scanner sc = new Scanner(System.in);  
        
        try  
        {  
        final String os = System.getProperty("os.name");  
        if (os.contains("Windows"))  
        {  
        Runtime.getRuntime().exec("clear");  
        }  
        }  
        catch (final Exception e)  
        {  
        e.printStackTrace();  
        } 
        
        playlist.get(index).printMenu();
        try{ 
            playlist.get(index).Play(sc);
        }catch(Exception e){
            System.out.println(e);
        }  
    }  
     
    /*************************************************************************/
    
    //Add song module
    private static void addsong(DoublyLinkedList_Interface<Playlist> playlist, int index) {
        Scanner sc = new Scanner(System.in);
        Song song = new Song(); 
        int songnum=0;
         
        do{ 
            System.out.println("<<<<<<<<<<<< Song Menu >>>>>>>>>>>>>>"
                        + "\n1. The Nights - Avicii, 4.2"
                        + "\n2. Waiting For Love - Avicii, 5.0"
                        + "\n3. Play - Alan Walker, 1.3"
                        + "\n4. All Falls Down - -Alan Walker, 1.2"
                        + "\n5. Alone - Alan Walker, 2.2"
                        + "\n6. Tim Christensen, 4.2"
                        + "\n7. Silver, 3.2"
                        + "\n8. Absurdity, 2.2");
            System.out.println("select song: ");
            songnum=sc.nextInt(); 
            if(songnum<0||songnum>9){
                System.out.println("Song number is not available.");break;
            }
            
            //select song to add into playlist
            switch(songnum){
                case 1:song = new Song("The Nights - Avicii",4.2);break;
                case 2:song = new Song("Waiting For Love - Avicii",5.0);break;
                case 3:song = new Song("Play - Alan Walker",2.2);break;
                case 4:song = new Song("All Falls Down - -Alan Walker",1.5);break;
                case 5:song = new Song("Alone - Alan Walker",2.2);break;
                case 6:song = new Song("Tim Christensen",4.2);break;
                case 7:song = new Song("Silver",3.2);break;
                case 8:song = new Song("Absurdity",2.2);break; 
                default:System.out.println("Song number is not available.");
            } 
            //add song

            boolean op2=true;
            do{
                System.out.println("\n============================"
                    + "\n1. Add Front"
                    + "\n2. Add Last"
                    + "\n3. Add any places" 
                    + "\n4. Quit"
                    + " "); 
                System.out.println("Select: "); 
                try{

                }catch(InputMismatchException e){
                    System.out.println(e);
                }
                int op1=sc.nextInt();
                switch(op1){
                    case 1:
                        playlist.get(index).addFirst(song); //add song as first position
                        playlist.get(index).displaySong();
                        break;
                    case 2:
                        playlist.get(index).addLast(song);//add song as last position
                        playlist.get(index).displaySong();
                        break; 
                    case 3:
                        boolean flag=false; //add song to any position index
                        try{
                            int num=sc.nextInt();
                            flag=playlist.get(index).addAnyPlace(song, num); 
                        }catch(Exception e){
                            System.out.println("Pleace enter correct index number.");
                        } 
                        if(flag){
                            playlist.get(index).displaySong();
                        }
                        break; 
                    default:
                        op2=false; //quit
                        break;
                }
            }while(op2); 
        }while(songnum<0||songnum>9);
        
        
    }
    /*************************************************************************/
    //display the playlist
    private static void displayPlaylist(DoublyLinkedList_Interface<Playlist> playlist, int index) {
        playlist.get(index).displaySong(); 
    }
    /*************************************************************************/
    // Edit Playlist
    private static void editsong(DoublyLinkedList_Interface<Playlist> playlist, int index) {
        Scanner sc = new Scanner(System.in);
         
        boolean op2=true;
        while(op2){
            System.out.print("\n============================"
                + "\n1. Delete song"
                + "\n2. Reverse playlist" 
                + "\n3. Clear playlist"
                + "\n4. Quit"
                + ""); 
            System.out.println("\nSelect:  ");
            int op1=sc.nextInt(),num=0;
            switch(op1){
                case 1:  //remove the song from playlist
                    playlist.get(index).displaySong();
                    System.out.println("Which song to delete? ");
                    try{
                        num=sc.nextInt();// user enter index number tobe remove
                        playlist.get(index).removeSong(num); 
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    
                    break;
                case 2: //reverse the playlist
                    playlist.get(index).ReversePlaylist();
                    playlist.get(index).displaySong();
                    break;
                case 3: //clear the playlist
                    playlist.get(index).clearPlaylist();
                    playlist.get(index).displaySong();
                    break;
                default:
                    op2=false; //quit
                    break;
            }
        }
    } 
}
