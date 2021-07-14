package sg.edu.np.mad.Studyfi;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Radio {

    public String radioName, radioLink;
    public boolean isOffline;


    public String getRadioName() {
        return radioName;
    }

    public void setRadioName(String radioName) {
        this.radioName = radioName;
    }

    /*
    public int getRadioPic() {
        return radioPic;
    }

    public void setRadioPic(int radioPic) {
        this.radioPic = radioPic;
    }

     */

    public String getRadioLink() {
        return radioLink;
    }

    public void setRadioLink(String radioLink) {
        this.radioLink = radioLink;
    }

    public boolean isOffline() {
        return isOffline;
    }

    public void setOffline(boolean offline) {
        isOffline = offline;
    }
}
