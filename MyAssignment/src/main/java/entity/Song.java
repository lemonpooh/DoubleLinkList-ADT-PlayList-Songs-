 
package entity;

/**
 * @author  
 */
public class Song{
     private String title;
     double duration;

    public Song(String title, double duration) {
        this.title = title;
        this.duration = duration;
    }

    public Song() {
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
    
    @Override
    public String toString() {
        return "Title:"+this.title+" Duration:"+this.duration;
    }
 
    @Override
    public boolean equals(Object obj) {
            //不一样就返回
            if (this == obj)
                    return true;
            if (obj == null)
                    return false;
            if (!(obj instanceof Song))
                    return false;
            Song other = (Song) obj;
            if (title == null) {
                    if (other.title != null)
                            return false;
            } else if (!title.equals(other.title))
                    return false; 
            return true;
    }
}
